package cn.stylefeng.guns.core.schedue.quartzTask;


import cn.stylefeng.guns.core.util.HttpClient;
import cn.stylefeng.guns.core.util.MD5Util;
import cn.stylefeng.guns.modular.servlet.entity.WmsCountInventory;
import cn.stylefeng.guns.modular.servlet.service.WmsCountInventoryService;
import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.List;


/**
 * 抓取盘点单回传
 * @author heyajun
 * @date   2019年8月13日
 */
@Repository
@DisallowConcurrentExecution
public class WmsCountInventoryJob implements Job {

	@Autowired
	private WmsCountInventoryService wmsCountInventoryService;
	@Autowired
	private TableService  tableService;
	@Autowired
	private InterService interService;



	@Override
	public void execute(JobExecutionContext arg0) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		String url="";
		try {
			String msg="";
		List<Inter> inters=interService.selectByType("P");//P代表盘点
		if(inters!=null&&inters.size()>0){
			for(Inter inter:inters){
				String type=inter.getMessageContext();
				Long interId=inter.getInterId();
				String[] strs=type.split(",");
				//获取回传对照关系
				List<TableOption> tableOptions=tableService.selectTableOption(interId);
						JSONObject json=new JSONObject();
						JSONArray arr=new JSONArray();
						List<WmsCountInventory> countInventories = wmsCountInventoryService.selectWmsCountInventoryByCode(null);
						if(countInventories!=null&&countInventories.size()>0){
							for(WmsCountInventory countInventory:countInventories){
								JSONObject jsonput=new JSONObject();
								for(TableOption table: tableOptions) {
									String key=table.getUpColumnName();
									String value=table.getDownColumnName();
									Object obj=wmsCountInventoryService.selectObject(value, countInventory.getDanj_no(),String.valueOf(countInventory.getHanghao()));
									jsonput.put(key, obj);
								}
								arr.add(jsonput);
							}
							json.put("data",arr);
							json.put("operatorType","add");
							json.put("partnerId",inter.getUserName());
							long date =System.currentTimeMillis();
							json.put("msgid",date);//取当前时间戳
							//生成签名
							StringBuffer buffer=new StringBuffer();
							buffer.append(date);
							buffer.append("add");
							buffer.append(arr.toString());
							buffer.append(inter.getUserName());
							buffer.append(inter.getPassword());
							buffer.append(date);
							String sign= null;
							System.out.println("需要签名的数据："+buffer.toString());

							sign = MD5Util.stringToMd5(buffer.toString());

							json.put("sign",sign);
							System.out.println("json:"+json);
							url=inter.getUrl().trim();
							HttpClient newClient=new HttpClient();
							msg+="request:"+json;
							//需要设置用户名和密码
							String ret=newClient.httpClientPostJson(inter.getUrl().trim(), json, inter.getUserName().trim(), inter.getPassword().trim());

							JSONObject reJson=JSONObject.parseObject(ret.trim());
							JSONArray array=reJson.getJSONArray("RETURN");
							msg+="\n";
							msg+="response:"+reJson;
							LogManager.me().executeLog(LogTaskFactory.upInterfaceLog(1L, "盘点上传", inter.getUrl(), "add", msg));
							//拆分插入到日志表中
							for(int j=0;j<array.size();j++){
								//根据返回值的单据号查找推送对应单据
								String state=array.getJSONObject(j).getString("RESULT");
								if("S".equals(state)||"W".equals(state)){
									if(countInventories!=null&&countInventories.size()>0){
										for(WmsCountInventory wmsCountInventory:countInventories){
											wmsCountInventory.setZt("Y");
											wmsCountInventoryService.updateWmsCountInventory(wmsCountInventory);
										}
									}
								}else{
									if(countInventories!=null&&countInventories.size()>0){
										for(WmsCountInventory wmsCountInventory:countInventories){
											wmsCountInventory.setZt("R");
											wmsCountInventoryService.updateWmsCountInventory(wmsCountInventory);
										}
									}
								}
							}
						}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("异常信息："+e.getMessage());
			LogManager.me().executeLog(LogTaskFactory.upInterfaceLogError(1L, "盘点上传", url, "add", e.getMessage()));
		}
	}






}
