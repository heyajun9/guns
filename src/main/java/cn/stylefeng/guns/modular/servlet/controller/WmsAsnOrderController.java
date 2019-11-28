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

import cn.stylefeng.guns.core.log.LogManager;
import cn.stylefeng.guns.core.log.factory.LogTaskFactory;
import cn.stylefeng.guns.modular.servlet.entity.WmsAsnOrder;
import cn.stylefeng.guns.modular.servlet.entity.WmsAsnOrderDelete;
import cn.stylefeng.guns.modular.servlet.service.InsertMapService;
import cn.stylefeng.guns.modular.servlet.service.WmsAsnOrderDeleteService;
import cn.stylefeng.guns.modular.servlet.service.WmsAsnOrderService;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.InterfaceLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.dictmap.ServletDict;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.util.MD5Util;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.guns.modular.servlet.entity.InsertMap;
import cn.stylefeng.guns.modular.servlet.warpper.WmsAsnOrderWrapper;
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
 * 入库订单接口
 *
 * @author heyajun
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/asnOrder")
public class WmsAsnOrderController extends BaseController {

    private static String PREFIX = "/modular/servlet/asnOrder/";

    private static final int batchSize = 100;

//    private static final String partnerKey="WMS2019";

    @Autowired
    private WmsAsnOrderService wmsAsnOrderService;

    @Autowired
    private TableService tableService;

    @Autowired
    private InsertMapService insertMapService;

    @Autowired
    private InterService interService;

    @Autowired
    private WmsAsnOrderDeleteService wmsAsnOrderDeleteService;


    /**
     * 跳转到接口列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "asnOrder.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/asnOrder_add")
    public String asnOrderAdd() {
        return PREFIX + "asnOrder_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/asnOrder_edit")
    public String asnOrderEdit(@RequestParam Long asnOrderId) {
        if (ToolUtil.isEmpty(asnOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        WmsAsnOrder asnOrder = this.wmsAsnOrderService.getById(asnOrderId);
        LogObjectHolder.me().set(asnOrder);

        return PREFIX + "asnOrder_edit.html";
    }

    /**
     * 编辑
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @InterfaceLog(value = "编辑入库订单", key = "asnOrder", dict = ServletDict.class)
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        Collection<WmsAsnOrder> asnOrders = new ArrayList<>();
        //根据加密要求验证sign
        //partnerKey作为私秘钥
        List<Inter> inters = interService.selectByType("R");//W代表入库下传
        Inter inter = inters.get(0);//获取接口配置信息
        String partnerKey = inter.getPassword();//获取秘钥
        String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
        try {
            if (!MD5Util.stringToMd5(signString).equals(sign)) {
//                System.out.println("sign:"+MD5Util.stringToMd5(signString));
                List<TableOption> optionList = tableService.selectOptionByTable(inter.getDownTable());
                for (int i = 0; i < arry.size(); i++) {
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
//                this.wmsAsnOrderService.saveBatch(asnOrders, batchSize);
                return ResponseData.success(0, "WMS入库订单修改成功", arry.size() + "条");
            } else {
                return ResponseData.error(1, "签名错误", sign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error(1, "WMS入库订单修改异常", e.getMessage());
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
                       @RequestParam(required = false) String shangp_id) {
        Page<Map<String, Object>> asnOrders = this.wmsAsnOrderService.selectWmsAsnOrder(danj_no, shangp_id);
        Page<Map<String, Object>> wrap = new WmsAsnOrderWrapper(asnOrders).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增入库订单接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */

    @RequestMapping(value = "/add")
    @ResponseBody
    @InterfaceLog(value = "添加入库订单", key = "asnOrder", dict = ServletDict.class)
    public ResponseData add(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        if ("delete".equalsIgnoreCase(operatorType)) {
            return delete(params);
        } else {
            ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
            Collection<WmsAsnOrder> asnOrders = new ArrayList<>();
            //根据加密要求验证sign
            //partnerKey作为私秘钥
            List<Inter> inters = interService.selectByType("R");//W代表入库下传
            Inter inter = inters.get(0);//获取接口配置信息
            String partnerKey = inter.getPassword();//获取秘钥
            String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
            try {
                if (!MD5Util.stringToMd5(signString).equals(sign)) {
//                System.out.println("sign:"+MD5Util.stringToMd5(signString));
                    List<TableOption> optionList = tableService.selectOptionByTable(inter.getDownTable());
                    for (int i = 0; i < arry.size(); i++) {
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
//                this.wmsAsnOrderService.saveBatch(asnOrders, batchSize);
                    String msg = "";
                    msg += "request:" + json.toJSONString();
                    msg += "===================================";
                    msg += "response:" + ResponseData.success(0, "WMS入库订单新增成功", arry.size() + "条").toString();
                    LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "入库单新增", "/add", "add", msg));
                    return ResponseData.success(0, "WMS入库订单新增成功", arry.size() + "条");
                } else {
                    String msg = "";
                    msg += "request:" + json.toJSONString();
                    msg += "===================================";
                    msg += "response:" + ResponseData.error(1, "签名错误", sign).toString();
                    LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/add", "add", msg));
                    return ResponseData.error(1, "签名错误", sign);
                }
            } catch (Exception e) {
                e.printStackTrace();
                String msg = "";
                msg += "request:" + json.toJSONString();
                msg += "===================================";
                msg += "response:" + e.getMessage();
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "入库单新增异常", "/add", "add", msg));
                return ResponseData.error(1, "WMS入库订单新增异常", e.getMessage());
            }
        }
    }

    /**
     * 删除接口列表(删除入库单接口)
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除接口列表", key = "asnOrder", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam String params) {
        JSONObject json = JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        Collection<WmsAsnOrder> asnOrders = new ArrayList<>();
        //根据加密要求验证sign
        //partnerKey作为私秘钥
        List<Inter> inters = interService.selectByType("R");//W代表入库下传
        Inter inter = inters.get(0);//获取接口配置信息
        String partnerKey = inter.getPassword();//获取秘钥
        String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
        try {
            if (!MD5Util.stringToMd5(signString).equals(sign)) {
//                System.out.println("sign:"+MD5Util.stringToMd5(signString));
                for (int i = 0; i < arry.size(); i++) {
                    JSONObject jsonObject = arry.getJSONObject(i);
                    WmsAsnOrderDelete wmsAsnOrderDelete = new WmsAsnOrderDelete();
                    wmsAsnOrderDelete.setDanj_no(jsonObject.getString("danj_no"));
                    wmsAsnOrderDelete.setHanghao(Long.parseLong(jsonObject.getString("hanghao")));
                    wmsAsnOrderDelete.setHuoz_id(jsonObject.getString("huoz_id"));
                    wmsAsnOrderDelete.setDanj_no_y(jsonObject.getString("danj_no"));
                    wmsAsnOrderDelete.setHanghao_y(jsonObject.getString("hanghao"));
                    wmsAsnOrderDelete.setRiqi_date(jsonObject.getString("riqi_date"));
                    wmsAsnOrderDelete.setWlzx_code(jsonObject.getString("wlzx_code"));
                    wmsAsnOrderDelete.setYew_staff("".equalsIgnoreCase(jsonObject.getString("lianx_staff")) ? "管理员" : jsonObject.getString("lianx_staff"));
                    wmsAsnOrderDelete.setNum(Double.parseDouble(jsonObject.getString("num")));
                    wmsAsnOrderDelete.setTiaom_num(Double.parseDouble(jsonObject.getString("tiaom_num")));

                    wmsAsnOrderDeleteService.addWmsAsnOrderDelete(wmsAsnOrderDelete);
                }

                String msg = "";
                msg += "request:" + json.toJSONString();
                msg += "===================================";
                msg += "response:" + ResponseData.success(0, "WMS入库订单删除成功", arry.size() + "条").toString();
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "WMS入库订单删除成功", "/delete", "add", msg));
                return ResponseData.success(0, "WMS入库订单删除成功", arry.size() + "条");
            } else {
                String msg = "";
                msg += "request:" + json.toJSONString();
                msg += "===================================";
                msg += "response:" + ResponseData.error(1, "签名错误", sign);
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/delete", "add", msg));
                return ResponseData.error(1, "签名错误", sign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "";
            msg += "request:" + json.toJSONString();
            msg += "===================================";
            msg += "response:" + e.getMessage();
            LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "入库单新增异常", "/delete", "add", msg));
            return ResponseData.error(1, "WMS入库订单删除异常", e.getMessage());
        }
    }

    /**
     * 查看接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/view/{asnOrderId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long asnOrderId) {
        if (ToolUtil.isEmpty(asnOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        WmsAsnOrder asnOrder = this.wmsAsnOrderService.getById(asnOrderId);
        return ResponseData.success(asnOrder);
    }

    /**
     * 获取接口信息
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getasnOrderInfo")
    @ResponseBody
    public ResponseData getasnOrderInfo(@RequestParam Long asnOrderId) {
        if (ToolUtil.isEmpty(asnOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        WmsAsnOrder asnOrder = this.wmsAsnOrderService.getById(asnOrderId);

        return ResponseData.success(asnOrder);
    }


}
