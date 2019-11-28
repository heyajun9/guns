package cn.stylefeng.guns.core.schedue.fhQuartzTask;


import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.core.util.HttpClient;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.guns.modular.servlet.entity.WmsCountInventory;
import cn.stylefeng.guns.modular.servlet.entity.WmsInventory;
import cn.stylefeng.guns.modular.servlet.service.WmsCountInventoryService;
import cn.stylefeng.guns.modular.servlet.service.WmsInventoryService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 抓取库存对账数据(20191019)
 * @author heyajun
 * @date   2019年8月13日
 */
@Repository
@DisallowConcurrentExecution
public class WmsInventoryCheckJob implements Job {

	@Autowired
	private WmsCountInventoryService wmsCountInventoryService;
	@Autowired
	private TableService  tableService;
	@Autowired
	private InterService interService;
	@Autowired
	private WmsInventoryService wmsInventoryService;



	@Override
	public void execute(JobExecutionContext arg0) {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		String url="";
		try {
			String msg="";
		List<Inter> inters=interService.selectByType("U");//U代表LMIS从U8中抓取库存
		if(inters!=null&&inters.size()>0){
			for(Inter inter:inters){
				Long interId=inter.getInterId();
				//获取回传对照关系
				List<TableOption> tableOptions=tableService.selectTableOption(interId);
						JSONObject json=new JSONObject();
						JSONObject jsonput=new JSONObject();
						jsonput.put("strWhere","");//库存数据主动抓取上游系统
							json.put("data",jsonput);
							json.put("orgcode","217");//需要根据实际情况设置
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
							LogManager.me().executeLog(LogTaskFactory.upInterfaceLog(1L, "库存抓取", inter.getUrl(), "add", msg));

								String state=reJson.getString("successful");
								if("0".equals(state)){
									JSONArray dataArray= (JSONArray) reJson.get("data");
									List<WmsInventory> list=new ArrayList<>();
									for(Object object:dataArray){
										JSONObject jsonObject= (JSONObject) object;
										WmsInventory inventory=new WmsInventory();
										inventory.setHuoz_id(jsonObject.getString("accid"));//货主名称（需要修改）
										inventory.setWlzx_code(jsonObject.getString("cwhcode"));
										inventory.setShangp_no(jsonObject.getString("cwhcode"));
										inventory.setNum(Double.parseDouble(jsonObject.getString("iquantity")));//主数量
										inventory.setLot(jsonObject.getString("cbatch"));
										inventory.setBaoz_danw(jsonObject.getString("cunitname"));
										inventory.setCreate_time(new Date());
										list.add(inventory);
									}
									wmsInventoryService.saveBatch(list,500);
								}
						}
		}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("异常信息："+e.getMessage());
			LogManager.me().executeLog(LogTaskFactory.upInterfaceLogError(1L, "抓取库存信息", url, "add", e.getMessage()));
		}
	}






}
