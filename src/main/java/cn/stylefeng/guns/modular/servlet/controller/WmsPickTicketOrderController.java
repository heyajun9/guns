/**
 * Copyright 2018-2020 stylefeng & heyajun (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.servlet.controller;

import cn.stylefeng.guns.core.common.annotion.InterfaceLog;
import cn.stylefeng.guns.core.common.constant.dictmap.ServletDict;
import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.core.util.MD5Util;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.servlet.entity.InsertMap;
import cn.stylefeng.guns.modular.servlet.entity.WmsPickTicketOrder;
import cn.stylefeng.guns.modular.servlet.entity.WmsPickTicketOrderDelete;
import cn.stylefeng.guns.modular.servlet.entity.WmsSaleAsnOrder;
import cn.stylefeng.guns.modular.servlet.service.InsertMapService;
import cn.stylefeng.guns.modular.servlet.service.WmsAsnSaleOrderService;
import cn.stylefeng.guns.modular.servlet.service.WmsPickTicketOrderDeleteService;
import cn.stylefeng.guns.modular.servlet.service.WmsPickTicketOrderService;
import cn.stylefeng.guns.modular.servlet.warpper.WmsPickTicketOrderWrapper;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 出库订单接口
 *
 * @author heyajun
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/pickTicketOrder")
public class WmsPickTicketOrderController extends BaseController {

    private static String PREFIX = "/modular/servlet/pickTicketOrder/";

    private static final int batchSize=100;

    private static final String partnerKey="WMS2019";

    @Autowired
    private WmsPickTicketOrderService wmsPickTicketOrderService;

    @Autowired
    private TableService tableService;

    @Autowired
    private InsertMapService insertMapService;

    @Autowired
    private InterService interService;

    @Autowired
    private WmsAsnSaleOrderService wmsAsnSaleOrderService;

    @Autowired
    private WmsPickTicketOrderDeleteService wmsPickTicketOrderDeleteService;

    /**
     * 跳转到接口列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pickTicketOrder.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/pickTicketOrder_add")
    public String pickTicketOrderAdd() {
        return PREFIX + "pickTicketOrder_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/pickTicketOrder_edit")
    public String pickTicketOrderEdit(@RequestParam Long pickTicketOrderId) {
        if (ToolUtil.isEmpty(pickTicketOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        WmsPickTicketOrder pickTicketOrder = this.wmsPickTicketOrderService.getById(pickTicketOrderId);
        LogObjectHolder.me().set(pickTicketOrder);

        return PREFIX + "pickTicketOrder_edit.html";
    }

    /**
     * 编辑
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @InterfaceLog(value = "编辑出库订单", key = "pickTicketOrder", dict = ServletDict.class)
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody String params) {
        JSONObject json=JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        Collection<WmsPickTicketOrder> pickTicketOrders=new ArrayList<>();
        //根据加密要求验证sign
        //partnerKey作为私秘钥
        List<Inter> inters=interService.selectByType("X");//X代表出库下传
        Inter inter=inters.get(0);//获取接口配置信息
        String partnerKey=inter.getPassword();//获取秘钥
        String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
        try {
            if (!MD5Util.stringToMd5(signString).equals(sign)) {
//                System.out.println("sign:"+MD5Util.stringToMd5(signString));
                List<TableOption> optionList= tableService.selectOptionByTable(inter.getDownTable());
                for (int i = 0; i < arry.size(); i++) {
                    JSONObject object = arry.getJSONObject(i);
                    Map<String,Object> objectMap=new HashMap<>();
                    if(optionList!=null||optionList.size()>0){
                        for(TableOption tableOption:optionList){
                            String key=tableOption.getDownColumnName();
                            objectMap.put(key,object.get(key));
                        }
                    }
                    InsertMap insertMap=new InsertMap();
                    insertMap.setTableName(inter.getDownTable());
                    insertMap.setParams(objectMap);
                    this.insertMapService.addMap(insertMap);
                }
//                this.wmsPickTicketOrderService.saveBatch(pickTicketOrders, batchSize);
                return ResponseData.success(0, "WMS出库订单修改成功", arry.size() + "条");
            }else{
                return ResponseData.error(1,"签名错误",sign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error(1,"WMS出库订单修改异常",e.getMessage());
        }
    }

    /**
     * 获取列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String danj_no,
                       @RequestParam(required = false) String shangp_id){
        Page<Map<String, Object>> pickTicketOrders = this.wmsPickTicketOrderService.selectWmsPickTicketOrder(danj_no,shangp_id);
        Page<Map<String, Object>> wrap = new WmsPickTicketOrderWrapper(pickTicketOrders).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增出库订单接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @InterfaceLog(value = "添加出库订单", key = "pickTicketOrder", dict = ServletDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(@RequestBody String params ) {
        JSONObject json=JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        if("delete".equalsIgnoreCase(operatorType)){
            return delete(params);
        }else {
            ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
            Collection<WmsPickTicketOrder> pickTicketOrders = new ArrayList<>();
            String msg="";
            msg+="request:"+json.toJSONString();
            msg+="===================================";
            //根据加密要求验证sign
            //partnerKey作为私秘钥
            List<Inter> inters = interService.selectByType("X");//X代表出库下传
            Inter inter = inters.get(0);//获取接口配置信息
            String partnerKey = inter.getPassword();//获取秘钥
            String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
            try {
                if (!MD5Util.stringToMd5(signString).equals(sign)) {
//                System.out.println("sign:"+MD5Util.stringToMd5(signString));
                    List<TableOption> optionList = tableService.selectOptionByTable(inter.getDownTable());
                    for (int i = 0; i < arry.size(); i++) {
                        if (0 > Integer.parseInt(arry.getJSONObject(i).getString("num"))) {
                            WmsSaleAsnOrder wmsSaleAsnOrder = new WmsSaleAsnOrder();
                            wmsSaleAsnOrder.setDanj_no(arry.getJSONObject(i).getString("danj_no"));
                            wmsSaleAsnOrder.setHanghao(Long.parseLong(arry.getJSONObject(i).getString("hanghao")));
                            wmsSaleAsnOrder.setHuoz_id("217".equalsIgnoreCase(arry.getJSONObject(i).getString("huoz_id")) ? "1001" : "1002");
                            wmsSaleAsnOrder.setDanw_id(arry.getJSONObject(i).getString("danw_id"));
                            wmsSaleAsnOrder.setWlzx_code(arry.getJSONObject(i).getString("wlzx_code"));
                            wmsSaleAsnOrder.setRuk_type("3");
                            wmsSaleAsnOrder.setNum(-Double.parseDouble(arry.getJSONObject(i).getString("num")));
                            wmsSaleAsnOrder.setShangp_id(arry.getJSONObject(i).getString("shangp_id"));
                            wmsSaleAsnOrder.setRiqi_date(arry.getJSONObject(i).getString("riqi_date"));
                            wmsSaleAsnOrder.setLot(arry.getJSONObject(i).getString("lot_request") == "" ? "9999" : arry.getJSONObject(i).getString("lot_request"));
                            wmsSaleAsnOrder.setTiaom_num(Long.parseLong(arry.getJSONObject(i).getString("tiaom_num")));
                            wmsSaleAsnOrder.setYew_type("4");
                            wmsSaleAsnOrder.setNote(arry.getJSONObject(i).getString("note"));
                            wmsSaleAsnOrder.setTuih_reason("TH");
                            wmsSaleAsnOrder.setZt("N");
                            wmsAsnSaleOrderService.addWmsSaleAsnOrder(wmsSaleAsnOrder);
                        } else {
                            JSONObject object = arry.getJSONObject(i);
                            Map<String, Object> objectMap = new HashMap<>();
                            if (optionList != null || optionList.size() > 0) {
                                for (TableOption tableOption : optionList) {
                                    String key = tableOption.getDownColumnName();
                                    objectMap.put(key, object.get(key));
                                }
                            }
                            InsertMap insertMap = new InsertMap();
                            insertMap.setTableName(inter.getDownTable());
                            insertMap.setParams(objectMap);
                            this.insertMapService.addMap(insertMap);
                        }
                    }
//                this.wmsPickTicketOrderService.saveBatch(pickTicketOrders, batchSize);
                    msg+="response:"+ResponseData.success(0, "WMS出库订单新增成功", arry.size() + "条").toString();
                    LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "出库单新增成功", "/add", "add", msg));
                    return ResponseData.success(0, "WMS出库订单新增成功", arry.size() + "条");
                } else {
                    msg+="response:"+ResponseData.error(1, "签名错误", sign);
                    LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/add", "add", msg));
                    return ResponseData.error(1, "签名错误", sign);
                }
            } catch (Exception e) {
                e.printStackTrace();
                msg+="response:"+e.getMessage();
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "出库单新增异常", "/add", "add", msg));
                return ResponseData.error(1, "WMS出库订单新增异常", e.getMessage());
            }
        }
    }

    /**
     * 删除接口列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除接口列表", key = "pickTicketOrder", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam String params) {
        JSONObject json=JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        Collection<WmsPickTicketOrder> pickTicketOrders=new ArrayList<>();
        String msg="";
        msg+="request:"+json.toJSONString();
        msg+="===================================";
        //根据加密要求验证sign
        //partnerKey作为私秘钥
        List<Inter> inters=interService.selectByType("X");//X代表出库下传
        Inter inter=inters.get(0);//获取接口配置信息
        String partnerKey=inter.getPassword();//获取秘钥
        String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
        try {
            if (!MD5Util.stringToMd5(signString).equals(sign)) {
                for (int i = 0; i < arry.size(); i++) {
                    JSONObject jsonObject=arry.getJSONObject(i);
                    WmsPickTicketOrderDelete wmsPickTicketOrderDelete=new WmsPickTicketOrderDelete();
                    wmsPickTicketOrderDelete.setDanj_no(jsonObject.getString("danj_no"));
                    wmsPickTicketOrderDelete.setHanghao(Long.parseLong(jsonObject.getString("hanghao")));
                    wmsPickTicketOrderDelete.setHuoz_id(jsonObject.getString("huoz_id"));
                    wmsPickTicketOrderDelete.setDanj_no_y(jsonObject.getString("danj_no"));
                    wmsPickTicketOrderDelete.setHanghao_y(jsonObject.getString("hanghao"));
                    wmsPickTicketOrderDelete.setRiqi_date(jsonObject.getString("riqi_date"));
                    wmsPickTicketOrderDelete.setWlzx_code(jsonObject.getString("wlzx_code"));
                    wmsPickTicketOrderDelete.setYew_staff("".equalsIgnoreCase(jsonObject.getString("shouh_staff"))? "管理员":jsonObject.getString("shouh_staff"));
                    wmsPickTicketOrderDelete.setNum(Double.parseDouble(jsonObject.getString("num")));
                    wmsPickTicketOrderDelete.setYuan_yin(jsonObject.getString("yuan_yin"));
                    wmsPickTicketOrderDelete.setTiaom_num(Double.parseDouble(jsonObject.getString("tiaom_num")));

                    wmsPickTicketOrderDeleteService.addWmsPickTicketOrderDelete(wmsPickTicketOrderDelete);
                }
                msg+="response:"+ResponseData.success(0, "WMS出库订单删除成功", arry.size() + "条").toString();
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "出库单删除成功","/delete" , "/delete", msg));
                return ResponseData.success(0, "WMS出库订单删除成功", arry.size() + "条");
            }else{
                msg+="response:"+ResponseData.error(1, "签名错误", sign);
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/delete", "/delete", msg));
                return ResponseData.error(1,"签名错误",sign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg+="response:"+e.getMessage();
            LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "出库单删除异常", "/delete", "/delete", msg));
            return ResponseData.error(1,"WMS出库订单删除异常",e.getMessage());
        }
    }

    /**
     * 查看接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/view/{pickTicketOrderId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long pickTicketOrderId) {
        if (ToolUtil.isEmpty(pickTicketOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        WmsPickTicketOrder pickTicketOrder = this.wmsPickTicketOrderService.getById(pickTicketOrderId);
        return ResponseData.success(pickTicketOrder);
    }

    /**
     * 获取接口信息
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getpickTicketOrderInfo")
    @ResponseBody
    public ResponseData getpickTicketOrderInfo(@RequestParam Long pickTicketOrderId) {
        if (ToolUtil.isEmpty(pickTicketOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        WmsPickTicketOrder pickTicketOrder = this.wmsPickTicketOrderService.getById(pickTicketOrderId);

        return ResponseData.success(pickTicketOrder);
    }



}
