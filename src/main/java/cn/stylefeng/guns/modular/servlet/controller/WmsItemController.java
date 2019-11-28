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
import cn.stylefeng.guns.modular.servlet.entity.WmsItem;
import cn.stylefeng.guns.modular.servlet.service.InsertMapService;
import cn.stylefeng.guns.modular.servlet.service.WmsItemService;
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
import cn.stylefeng.guns.modular.servlet.warpper.WmsItemWrapper;
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
import java.util.concurrent.*;

/**
 * 物料接口
 *
 * @author heyajun
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/wmsItem")
public class WmsItemController extends BaseController {

    private static String PREFIX = "/modular/servlet/wmsItem/";

    private static final int batchSize=100;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(200));

//    private static final String partnerKey="WMS2019";

    @Autowired
    private WmsItemService wmsItemService;

    @Autowired
    private TableService tableService;

    @Autowired
    private InsertMapService insertMapService;

    @Autowired
    private InterService interService;

    /**
     * 跳转到接口列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wmsItem.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/wmsItem_add")
    public String wmsItemAdd() {
        return PREFIX + "wmsItem_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/wmsItem_edit")
    public String wmsItemEdit(@RequestParam Long wmsItemId) {
        if (ToolUtil.isEmpty(wmsItemId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        WmsItem wmsItem = this.wmsItemService.getById(wmsItemId);
        LogObjectHolder.me().set(wmsItem);

        return PREFIX + "wmsItem_edit.html";
    }

    /**
     * 编辑
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @InterfaceLog(value = "编辑物料", key = "wmsItem", dict = ServletDict.class)
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(@RequestBody String params) {
        System.out.println(Thread.currentThread().getName()+":start add "+System.currentTimeMillis());
        ResponseData responseData=new ResponseData();
        Callable<ResponseData> callable = new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                System.out.println(Thread.currentThread().getName()+":start callable add "+System.currentTimeMillis());
                JSONObject json = JSONObject.parseObject(params);
                String msgId = json.getString("msgid");//当前时间戳
                String partnerId = json.getString("partnerId");
                String sign = json.getString("sign");
                System.out.println("签名："+sign);
                String operatorType = json.getString("operatorType");
                ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
                JSONArray arry = json.getJSONArray("data");//获取到data数据数组
                Collection<WmsItem> wmsItems = new ArrayList<>();
                //根据加密要求验证sign
                //partnerKey作为私秘钥
                List<Inter> inters = interService.selectByType("W");//W代表物料下传
                Inter inter = inters.get(0);//获取接口配置信息
                String partnerKey = inter.getPassword();//获取秘钥
                String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
                System.out.println("json 字符串："+signString);
                try {
                    if (!MD5Util.stringToMd5(signString).equals(sign)) {
                        System.out.println("本地签名:"+MD5Util.stringToMd5(signString));
                        List<TableOption> optionList = tableService.selectOptionByTable(inter.getDownTable());
                        for (int i = 0; i < arry.size(); i++) {
                            JSONObject object = arry.getJSONObject(i);//获取到实体json
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
                            insertMapService.addMap(insertMap);

                        }
//                this.wmsItemService.saveBatch(wmsItems, batchSize);
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        return ResponseData.success(0, "WMS物料修改成功", arry.size() + "条");
                    } else {
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        return ResponseData.error(1, "签名错误", sign);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                    return ResponseData.error(1, "WMS物料修改异常", e.getMessage());
                }
            }
        };
        FutureTask<ResponseData> futuretask= (FutureTask<ResponseData>) executor.submit(callable);
        try {
            responseData=futuretask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":end all add "+System.currentTimeMillis());
        return responseData;
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
    public Object list(@RequestParam(required = false) String shangp_id,
                       @RequestParam(required = false) String chinese_name){
        Page<Map<String, Object>> wmsItems = this.wmsItemService.selectWmsItem(shangp_id,chinese_name);
        Page<Map<String, Object>> wrap = new WmsItemWrapper(wmsItems).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增物料接口(异步多线程)
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */

    @RequestMapping(value = "/add")
    @ResponseBody
    @InterfaceLog(value = "添加物料", key = "wmsItem", dict = ServletDict.class)
    public  ResponseData  add(@RequestBody String params ) {
        System.out.println(Thread.currentThread().getName()+":start add "+System.currentTimeMillis());
        ResponseData responseData=new ResponseData();
        Callable<ResponseData> callable = new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                System.out.println(Thread.currentThread().getName()+":start callable add "+System.currentTimeMillis());
                JSONObject json = JSONObject.parseObject(params);
                String msgId = json.getString("msgid");//当前时间戳
                String partnerId = json.getString("partnerId");
                String sign = json.getString("sign");
                System.out.println("签名："+sign);
                String operatorType = json.getString("operatorType");
                ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
                JSONArray arry = json.getJSONArray("data");//获取到data数据数组
                Collection<WmsItem> wmsItems = new ArrayList<>();
                //根据加密要求验证sign
                //partnerKey作为私秘钥
                List<Inter> inters = interService.selectByType("W");//W代表物料下传
                Inter inter = inters.get(0);//获取接口配置信息
                String partnerKey = inter.getPassword();//获取秘钥
                String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
                System.out.println("json 字符串："+signString);
                try {
                    if (!MD5Util.stringToMd5(signString).equals(sign)) {
                System.out.println("本地签名:"+MD5Util.stringToMd5(signString));
                        List<TableOption> optionList = tableService.selectOptionByTable(inter.getDownTable());
                        for (int i = 0; i < arry.size(); i++) {
                            JSONObject object = arry.getJSONObject(i);//获取到实体json
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
                            insertMapService.addMap(insertMap);
//                    WmsItem wmsItem = new WmsItem();
////                    wmsItem.setLius_no(System.currentTimeMillis());
//                    wmsItem.setShangp_id(object.getString("shangp_id"));
//                    wmsItem.setShangp_no(object.getString("shangp_no"));
//                    wmsItem.setWlzx_code(object.getString("wlzx_code"));
//                    wmsItem.setHuoz_id(object.getString("huoz_id"));
//                    wmsItem.setChinese_name(object.getString("chinese_name"));
//                    wmsItem.setZhuj_code(object.getString("zhuj_code"));
//                    wmsItem.setYaop_guig(object.getString("yaop_guig"));
//                    wmsItem.setMaker(object.getString("maker"));
//                    wmsItem.setChandi(object.getString("chandi"));
//                    wmsItem.setBaoz_num(Long.valueOf(object.getString("baoz_num")));
//                    wmsItem.setBaoz_danw(object.getString("baoz_danw"));
//                    wmsItem.setChaif_lid(object.getString("chaif_lid"));
//                    wmsItem.setKaipdw_min(Long.valueOf(object.getString("kaipdw_min")));
//                    wmsItem.setYaop_category(object.getString("yaop_category"));
//                    wmsItem.setCunc_condition(object.getString("cunc_condition"));
//                    wmsItem.setBeactive(object.getString("beactive"));
//                    wmsItem.setZhongbz(Long.parseLong(object.getString("zhongbz")));
//                    wmsItem.setJixing(object.getString("jixing"));
//                    wmsItem.setTongy_name(object.getString("tongy_name"));
//                    wmsItem.setLot_flg(object.getString("lot_flg"));
//                    wmsItem.setZengp_flg(object.getString("zengp_flg"));
//                    wmsItem.setJiang_flg(object.getString("jiang_flg"));
//                    wmsItem.setFangc_flg(object.getString("fangc_flg"));
//                    wmsItem.setTgyp_flg(object.getString("tgyp_flg"));
//                    wmsItem.setJink_flg(object.getString("jink_flg"));
//                    wmsItem.setYis_flg(object.getString("yis_flg"));
//                    wmsItem.setGzyp_flg(object.getString("gzyp_flg"));
//                    wmsItem.setYxp_flg(object.getString("yxp_flg"));
//                    wmsItem.setCaigou_staff(object.getString("caigou_staff"));
//                    wmsItem.setYpyh_type(object.getString("ypyh_type"));
//                    wmsItem.setGeiy_way(object.getString("geiy_way"));
//                    wmsItem.setYaop_xingz(object.getString("yaop_xingz"));
//                    wmsItem.setShiyz(object.getString("shiyz"));
//                    wmsItem.setGengx_time(object.getString("gengx_time"));
//                    wmsItem.setYp_flg(object.getString("yp_flg"));
//                    wmsItem.setZt(object.getString("zt"));
//                    wmsItem.setPiz_no(object.getString("piz_no"));
//                    wmsItem.setMerge_flg(object.getString("merge_flg"));
//                    wmsItem.setEnglish_name(object.getString("english_name"));
//                    wmsItem.setSf_zdkz(object.getString("sf_zdkz"));
//                    wmsItem.setSf_tdyp(object.getString("sf_tdyp"));
//                    wmsItem.setShouy_flg(object.getString("shouy_flg"));
//                    wmsItem.setSf_zy(object.getString("sf_zy"));
//                    wmsItem.setMedicinespecicalcontrol(object.getString("medicinespecicalcontrol"));
//                    wmsItem.setBarcode(object.getString("barcode"));
//                    wmsItem.setWeight(object.getString("weight"));
//                    wmsItem.setHeight(object.getString("height"));
//                    wmsItem.setLength(object.getString("length"));
//                    wmsItem.setWidth(object.getString("width"));
//                    wmsItem.setYouxq_flg(object.getString("youxq_flg"));
//                    wmsItem.setForbid_days(object.getString("forbid_days"));
//                    wmsItem.setCommodity(object.getString("commodity"));
//                    wmsItem.setDemurrage_day(object.getString("demurrage_day"));
//                    wmsItem.setForeboed_days(object.getString("foreboed_days"));
//                    wmsItem.setBan_days(object.getString("ban_days"));
//                    wmsItems.add(wmsItem);
                        }
//                this.wmsItemService.saveBatch(wmsItems, batchSize);
                        String msg="";
                        msg+="request:"+json.toJSONString();
                        msg+="===================================";
                        msg+="response:"+ResponseData.success(0, "WMS物料新增成功", arry.size() + "条").toString();
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "物料新增", "/add", "add", msg));
                        return ResponseData.success(0, "WMS物料新增成功", arry.size() + "条");
                    } else {
                        String msg="";
                        msg+="request:"+json.toJSONString();
                        msg+="===================================";
                        msg+="response:"+ResponseData.error(1, "签名错误", sign);
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/add", "add", msg));
                        return ResponseData.error(1, "签名错误", sign);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                    String msg="";
                    msg+="request:"+json.toJSONString();
                    msg+="===================================";
                    msg+="response:"+e.getMessage();
                    LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "物料新增异常", "/add", "add", msg));
                    return ResponseData.error(1, "WMS物料新增异常", e.getMessage());
                }
            }
        };
        FutureTask<ResponseData> futuretask= (FutureTask<ResponseData>) executor.submit(callable);
        try {
            responseData=futuretask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":end all add "+System.currentTimeMillis());
        return responseData;
    }

    /**
     * 删除接口列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除接口列表", key = "wmsItem", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam String params) {
        System.out.println(Thread.currentThread().getName()+":start add "+System.currentTimeMillis());
        ResponseData responseData=new ResponseData();
        Callable<ResponseData> callable = new Callable<ResponseData>() {
            @Override
            public ResponseData call() throws Exception {
                System.out.println(Thread.currentThread().getName()+":start callable add "+System.currentTimeMillis());
                JSONObject json = JSONObject.parseObject(params);
                String msgId = json.getString("msgid");//当前时间戳
                String partnerId = json.getString("partnerId");
                String sign = json.getString("sign");
                System.out.println("签名："+sign);
                String operatorType = json.getString("operatorType");
                ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
                JSONArray arry = json.getJSONArray("data");//获取到data数据数组
                Collection<WmsItem> wmsItems = new ArrayList<>();
                //根据加密要求验证sign
                //partnerKey作为私秘钥
                List<Inter> inters = interService.selectByType("W");//W代表物料下传
                Inter inter = inters.get(0);//获取接口配置信息
                String partnerKey = inter.getPassword();//获取秘钥
                String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
                System.out.println("json 字符串："+signString);
                try {
                    if (!MD5Util.stringToMd5(signString).equals(sign)) {
                        System.out.println("本地签名:"+MD5Util.stringToMd5(signString));
                        List<TableOption> optionList = tableService.selectOptionByTable(inter.getDownTable());
                        for (int i = 0; i < arry.size(); i++) {
                            JSONObject object = arry.getJSONObject(i);//获取到实体json
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
                            insertMapService.addMap(insertMap);

                        }
                        String msg="";
                        msg+="request:"+json.toJSONString();
                        msg+="===================================";
                        msg+="response:"+ResponseData.success(0, "WMS物料删除成功", arry.size() + "条").toString();
//                this.wmsItemService.saveBatch(wmsItems, batchSize);
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "物料删除", "/delete", "add", msg));
                        return ResponseData.success(0, "WMS物料删除成功", arry.size() + "条");
                    } else {
                        System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                        String msg="";
                        msg+="request:"+json.toJSONString();
                        msg+="===================================";
                        msg+="response:"+ResponseData.error(1, "签名错误", sign);
                        LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/delete", "add", msg));
                        return ResponseData.error(1, "签名错误", sign);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+":end callable add "+System.currentTimeMillis());
                    String msg="";
                    msg+="request:"+json.toJSONString();
                    msg+="===================================";
                    msg+="response:"+e.getMessage();
                    LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "物料新增异常", "/delete", "add", msg));
                    return ResponseData.error(1, "WMS物料删除异常", e.getMessage());
                }
            }
        };
        FutureTask<ResponseData> futuretask= (FutureTask<ResponseData>) executor.submit(callable);
        try {
            responseData=futuretask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":end all add "+System.currentTimeMillis());
        return responseData;
    }

    /**
     * 查看接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/view/{wmsItemId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long wmsItemId) {
        if (ToolUtil.isEmpty(wmsItemId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        WmsItem wmsItem = this.wmsItemService.getById(wmsItemId);
        return ResponseData.success(wmsItem);
    }

    /**
     * 获取接口信息
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getwmsItemInfo")
    @ResponseBody
    public ResponseData getwmsItemInfo(@RequestParam Long wmsItemId) {
        if (ToolUtil.isEmpty(wmsItemId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        WmsItem wmsItem = this.wmsItemService.getById(wmsItemId);

        return ResponseData.success(wmsItem);
    }



}
