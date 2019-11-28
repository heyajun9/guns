package cn.stylefeng.guns.core.schedue.fhQuartzTask;


import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.core.util.HttpClient;
import cn.stylefeng.guns.core.util.MD5Util;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.guns.modular.servlet.entity.WmsAsnBackOrder;
import cn.stylefeng.guns.modular.servlet.entity.WmsPickTickeBackOrder;
import cn.stylefeng.guns.modular.servlet.service.WmsPickTicketBackOrderService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 抓取出库单回传
 * @author heyajun
 * @date   2019年8月13日
 */
@Repository
@DisallowConcurrentExecution
public class WmsPickTicketBackJob implements Job {

	@Autowired
	private WmsPickTicketBackOrderService wmsPickTicketBackOrderService;
	@Autowired
	private TableService  tableService;
	@Autowired
	private InterService interService;



	@Override
	public void execute(JobExecutionContext arg0) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		//查找接口配置表中配置项（查找类型为入库的单据）
		String url="";
		try {
			String msg="";
		List<Inter> inters=interService.selectByType("O");//O代表出库
		if(inters!=null&&inters.size()>0){
			for(Inter inter:inters){
				String type=inter.getMessageContext();
				Long interId=inter.getInterId();
				String[] strs=type.split(",");
				List<String> list=wmsPickTicketBackOrderService.selectOrderCode(strs);//获取回传数据
				//获取回传对照关系
				List<TableOption> tableOptions=tableService.selectTableOption(interId);
				if(list!=null&&list.size()>0) {
					for (String orderCode : list) {
						JSONObject json=new JSONObject();
						JSONArray arr=new JSONArray();
						List<WmsPickTickeBackOrder> listAsnBack = wmsPickTicketBackOrderService.selectWmsPickTickeBackOrderByCode(orderCode);
						if(listAsnBack!=null&&listAsnBack.size()>0){
							JSONArray array=new JSONArray();
							JSONObject object=new JSONObject();
							WmsPickTickeBackOrder wmsPickTickeBackOrder=listAsnBack.get(0);
							if(wmsPickTickeBackOrder.getTiaom_num()==listAsnBack.size()){
							for(TableOption table: tableOptions) {
								String key=table.getUpColumnName();
								String value=table.getDownColumnName();
								if("bodys".equals(key)){
									for (WmsPickTickeBackOrder wmsPickTickeBackOrder1 : listAsnBack) {
										JSONObject jsonObject = new JSONObject();
										String[] values = value.split(";");
										for (String str : values) {
											String[] sts = str.split(" ");
											String up = sts[0];
											String down = sts[1];
											Object obj = wmsPickTicketBackOrderService.selectObject(down, orderCode, String.valueOf(wmsPickTickeBackOrder1.getHanghao()));
											jsonObject.put(up,obj);
										}
										array.add(jsonObject);
									}
									object.put("bodys",array);
								}else {
									if("riqi_date".equals(value)) {
										SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
										object.put(key,sf.format(new Date()));
									}else {
										Object obj = wmsPickTicketBackOrderService.selectObject(value, orderCode, String.valueOf(wmsPickTickeBackOrder.getHanghao()));
										object.put(key, obj);
									}
								}
							}
//						     arr.add(object);
							json.put("data",object);
							json.put("orgcode",("1001".equals(wmsPickTickeBackOrder.getHuoz_id()) ? "217":"020"));//217代表测试股份，020代表正式电商，017代表正式电商
							json.put("operation","0");
							long date =System.currentTimeMillis();
							json.put("primarykey",date);//取当前时间戳
							System.out.println("json:"+json);
							url=inter.getUrl().trim();
							HttpClient newClient=new HttpClient();
							msg+="request:"+json;
							//需要设置用户名和密码
							String ret=newClient.httpClientPostForm(inter.getUrl().trim(), json, inter.getUserName(), inter.getPassword());

							JSONObject reJson=JSONObject.parseObject(ret.trim());
//							JSONArray array=reJson.getJSONArray("RETURN");
							msg+="\n";
							msg+="response:"+reJson;
							LogManager.me().executeLog(LogTaskFactory.upInterfaceLog(1L, "出库上传", inter.getUrl(), "add", msg));
							//拆分插入到日志表中
//							for(int j=0;j<array.size();j++){
								//根据返回值的单据号查找推送对应单据
								String state=reJson.getString("successful");
								if("0".equals(state)){
									if(listAsnBack!=null&&listAsnBack.size()>0){
										for(WmsPickTickeBackOrder pickTickeBackOrder:listAsnBack){
											pickTickeBackOrder.setZt("Y");
											wmsPickTicketBackOrderService.updateWmsPickTickeBackOrder(pickTickeBackOrder);
										}
									}
								}else{
									if(listAsnBack!=null&&listAsnBack.size()>0){
										for(WmsPickTickeBackOrder pickTickeBackOrder:listAsnBack){
											pickTickeBackOrder.setZt("R");
											wmsPickTicketBackOrderService.updateWmsPickTickeBackOrder(pickTickeBackOrder);
										}
									}
								}
							}
						}
					}
				}

			}
		}
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.me().executeLog(LogTaskFactory.upInterfaceLogError(1L, "出库上传", url, "add", e.getMessage()));
		}
	}






}
