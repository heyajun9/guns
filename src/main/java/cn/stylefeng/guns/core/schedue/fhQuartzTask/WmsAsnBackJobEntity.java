package cn.stylefeng.guns.core.schedue.fhQuartzTask;


import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.core.util.HttpClient;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.guns.modular.servlet.entity.InsertMap;
import cn.stylefeng.guns.modular.servlet.service.InsertMapService;
import cn.stylefeng.guns.modular.servlet.service.WmsAsnBackOrderService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.List;
import java.util.Map;


/**
 * 抓取入库单回传（采用可配置的方式进行定时任务回传）
 * 对json进行拆解，分成多个子项目
 * @author heyajun
 * @date   2019年8月13日
 */
@Repository
@DisallowConcurrentExecution
public class WmsAsnBackJobEntity implements Job {

	@Autowired
	private WmsAsnBackOrderService wmsAsnBackOrderService;
	@Autowired
	private TableService  tableService;
	@Autowired
	private InterService interService;
	@Autowired
	private InsertMapService insertMapService;



	@Override
	public void execute(JobExecutionContext arg0) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		//查找接口配置表中配置项（查找类型为入库的单据）
		String url="";
		try {
			String msg="";
		List<Inter> inters=interService.selectByType("I");//I代表入库
		if(inters!=null&&inters.size()>0){
			for(Inter inter:inters){
				String type=inter.getMessageContext();
					Long interId = inter.getInterId();
					String[] strs = type.split(",");
					List<String> list = wmsAsnBackOrderService.selectOrderCode(strs);//获取回传数据
					//获取回传对照关系
					List<TableOption> tableOptions = tableService.selectTableOption(interId);
					if (list != null && list.size() > 0) {
						for (String orderCode : list) {
							JSONObject json = new JSONObject();
//							JSONArray arr = new JSONArray();
//							List<WmsAsnBackOrder> listAsnBack = wmsAsnBackOrderService.selectWmsAsnBackOrderByCode(orderCode);
							List<Map<String,Object>> listAsnBack = wmsAsnBackOrderService.selectWmsAsnBackOrderList(orderCode);
							System.out.println("订单号："+orderCode);
							if (listAsnBack != null && listAsnBack.size() > 0) {
								JSONArray array=new JSONArray();
//								WmsAsnBackOrder wmsAsnInfoBack=listAsnBack.get(0);
								Map<String,Object> wmsAsnInfoBack=listAsnBack.get(0);
//								for (WmsAsnBackOrder wmsAsnInfoBack : listAsnBack) {
									JSONObject jsonput = new JSONObject();
									for (TableOption table : tableOptions) {
										System.out.println("明细表："+table.toString());
										String key = table.getUpColumnName();
										String value = table.getDownColumnName();
//										System.out.println("父字段："+table.getParentMenu().toString());
										if("".equalsIgnoreCase(table.getParentMenu()) || table.getParentMenu()==null){
											if(value!=null) {
												Object obj = wmsAsnBackOrderService.selectObject(value, orderCode, String.valueOf(wmsAsnInfoBack.get("hanghao")));
												json.put(key, table.getDefaultValue() == null ? obj : table.getDefaultValue());
											}else{
												json.put(key,table.getDefaultValue());
											}
										}else{
										String[] vals=value.split(";");//先采用分号分隔符
										if(value.contains(";")&&vals.length>0){
											for (Map<String,Object> wmsAsnInfoBack1 : listAsnBack) {
												JSONObject object = new JSONObject();
												String[] values = value.split(";");
												for (String str : values) {
													String[] sts = str.split(" ");//再采用空格分隔符
													String up = sts[0];
													String down = sts[1];
													Object obj = wmsAsnBackOrderService.selectObject(down, orderCode, String.valueOf(wmsAsnInfoBack1.get("hanghao")));
											        object.put(up,obj);
											   }
												array.add(object);
											}
											jsonput.put(key,array);
										}else {
											Object obj = wmsAsnBackOrderService.selectObject(value, orderCode, String.valueOf(wmsAsnInfoBack.get("hanghao")));
											jsonput.put(key, obj);
										}
										json.put(table.getParentMenu(),jsonput);

										}
									}
//									arr.add(jsonput);
//								}
//								json.put("data", jsonput);
//								json.put("orgcode", "217");//根据上传单据中货主进行判断
//								json.put("operation", "0");
//								long date = System.currentTimeMillis();
//								json.put("primarykey", date);//取当前时间戳
								url = inter.getUrl().trim();
								HttpClient newClient = new HttpClient();
								msg += "request:" + json;
								//需要设置用户名和密码
								String ret = newClient.httpClientPostForm(inter.getUrl().trim(), json, inter.getUserName(), inter.getPassword());

								JSONObject reJson = JSONObject.parseObject(ret.trim());
//							JSONArray array=reJson.getJSONArray("RETURN");
								msg += "\n";
								msg += "response:" + reJson;
								LogManager.me().executeLog(LogTaskFactory.upInterfaceLog(1L, "入库上传", inter.getUrl(), "add", msg));
								//拆分插入到日志表中
//							for(int j=0;j<array.size();j++){
								//根据返回值的单据号查找推送对应单据
								String state = reJson.getString("successful");
								if ("0".equals(state)) {
									if (listAsnBack != null && listAsnBack.size() > 0) {
										for (Map<String,Object> asnInfoBack : listAsnBack) {
											InsertMap insertMap=new InsertMap();
											insertMap.setTableName(inter.getDownTable());
											asnInfoBack.replace("zt","Y");
											insertMap.setParams(asnInfoBack);
											insertMapService.updateMap(insertMap);
										}
									}
								} else {
									if (listAsnBack != null && listAsnBack.size() > 0) {
										for (Map<String,Object> asnInfoBack : listAsnBack) {
											InsertMap insertMap=new InsertMap();
											insertMap.setTableName(inter.getDownTable());
											asnInfoBack.replace("zt","R");
											insertMap.setParams(asnInfoBack);
											insertMapService.updateMap(insertMap);
										}
									}
//								}
								}
							}
						}
					}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.me().executeLog(LogTaskFactory.upInterfaceLogError(1L, "入库上传", url, "add", e.getMessage()));
		}
	}






}
