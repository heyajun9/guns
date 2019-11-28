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
import cn.stylefeng.guns.modular.servlet.entity.WmsOrganization;
import cn.stylefeng.guns.modular.servlet.service.InsertMapService;
import cn.stylefeng.guns.modular.servlet.warpper.WmsOrganizationWrapper;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.guns.modular.servlet.service.WmsOrganizationService;
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
 * 单位接口
 *
 * @author heyajun
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/organization")
public class WmsOrganizationController extends BaseController {

    private static String PREFIX = "/modular/servlet/organization/";

    private static final int batchSize=100;

//    private static final String partnerKey="WMS2019";

    @Autowired
    private WmsOrganizationService wmsOrganizationService;

    @Autowired
    private InterService interService;

    @Autowired
    private TableService tableService;

    @Autowired
    private InsertMapService insertMapService;

    /**
     * 跳转到接口列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "organization.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/organization_add")
    public String organizationAdd() {
        return PREFIX + "organization_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/organization_edit")
    public String organizationEdit(@RequestParam Long organizationId) {
        if (ToolUtil.isEmpty(organizationId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        WmsOrganization organization = this.wmsOrganizationService.getById(organizationId);
        LogObjectHolder.me().set(organization);

        return PREFIX + "organization_edit.html";
    }

    /**
     * 跳转到接口对应关系详情列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/organization_option")
    public String organizationOption(@RequestParam String downTable) {
        if (ToolUtil.isEmpty(downTable)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

      //根据表名查找表属性
//        organization organization = this.organizationService.getById(organizationId);
//        LogObjectHolder.me().set(organization);

        return PREFIX + "organization_edit.html";
    }

    /**
     * 编辑
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @InterfaceLog(value = "编辑单位", key = "organization", dict = ServletDict.class)
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
        Collection<WmsOrganization> organizations=new ArrayList<>();
        //根据加密要求验证sign
        //partnerKey作为私秘钥
        List<Inter> inters=interService.selectByType("C");//W代表物料下传
        Inter inter=inters.get(0);//获取接口配置信息
        String partnerKey=inter.getPassword();//获取秘钥
        String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
        try {
            if (!MD5Util.stringToMd5(signString).equals(sign)) {
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
//                this.wmsOrganizationService.saveBatch(organizations, batchSize);
                return ResponseData.success(0, "WMS单位修改成功", arry.size() + "条");
            }else{
                return ResponseData.error(1,"签名错误",sign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.error(1,"WMS单位修改异常",e.getMessage());
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
    public Object list(@RequestParam(required = false) String danw_id,
                       @RequestParam(required = false) String danw_name){
        Page<Map<String, Object>> organizations = this.wmsOrganizationService.selectWmsOrganization(danw_id,danw_name);
        Page<Map<String, Object>> wrap = new WmsOrganizationWrapper(organizations).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增单位接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @InterfaceLog(value = "添加单位", key = "organization", dict = ServletDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(@RequestBody String params ) {
        JSONObject json=JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        Collection<WmsOrganization> organizations=new ArrayList<>();
        String msg="";
        msg+="request:"+json.toJSONString();
        msg+="===================================";
        //根据加密要求验证sign
        //partnerKey作为私秘钥
        List<Inter> inters=interService.selectByType("C");//W代表物料下传
        Inter inter=inters.get(0);//获取接口配置信息
        String partnerKey=inter.getPassword();//获取秘钥
        String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
        try {
            if (!MD5Util.stringToMd5(signString).equals(sign)) {
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
//                this.wmsOrganizationService.saveBatch(organizations, batchSize);
                msg+="response:"+ResponseData.success(0, "WMS单位新增成功", arry.size() + "条").toString();
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "单位新增", "/add", "add", msg));
                return ResponseData.success(0, "WMS单位新增成功", arry.size() + "条");
            }else{
                msg+="response:"+ResponseData.error(1,"签名错误",sign);
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/add", "add", msg));
                return ResponseData.error(1,"签名错误",sign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg+="response:"+e.getMessage();
            LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "WMS单位新增异常", "/add", "add", msg));
            return ResponseData.error(1,"WMS单位新增异常",e.getMessage());
        }
    }

    /**
     * 删除接口列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除接口列表", key = "organization", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam String params) {
        JSONObject json=JSONObject.parseObject(params);
        String msgId = json.getString("msgid");//当前时间戳
        String partnerId = json.getString("partnerId");
        String sign = json.getString("sign");
        String operatorType = json.getString("operatorType");
        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        JSONArray arry = json.getJSONArray("data");//获取到data数据数组
        Collection<WmsOrganization> organizations=new ArrayList<>();
        String msg="";
        msg+="request:"+json.toJSONString();
        msg+="===================================";
        //根据加密要求验证sign
        //partnerKey作为私秘钥
        List<Inter> inters=interService.selectByType("C");//W代表物料下传
        Inter inter=inters.get(0);//获取接口配置信息
        String partnerKey=inter.getPassword();//获取秘钥
        String signString = msgId + operatorType + arry.toString() + partnerId + partnerKey + msgId;
        try {
            if (!MD5Util.stringToMd5(signString).equals(sign)) {
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
//                this.wmsOrganizationService.saveBatch(organizations, batchSize);
                msg+="response:"+ResponseData.success(0, "WMS单位删除成功", arry.size() + "条").toString();
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLog(1L, "单位新增", "/add", "add", msg));
                return ResponseData.success(0, "WMS单位删除成功", arry.size() + "条");
            }else{
                msg+="response:"+ResponseData.error(1,"签名错误",sign);
                LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "签名错误", "/add", "add", msg));
                return ResponseData.error(1,"签名错误",sign);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg+="response:"+e.getMessage();
            LogManager.me().executeLog(LogTaskFactory.downInterfaceLogError(1L, "WMS单位新增异常", "/add", "add", msg));
            return ResponseData.error(1,"WMS单位删除异常",e.getMessage());
        }
    }

    /**
     * 查看接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/view/{organizationId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long organizationId) {
        if (ToolUtil.isEmpty(organizationId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        WmsOrganization organization = this.wmsOrganizationService.getById(organizationId);
        return ResponseData.success(organization);
    }

    /**
     * 获取接口信息
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getorganizationInfo")
    @ResponseBody
    public ResponseData getorganizationInfo(@RequestParam Long organizationId) {
        if (ToolUtil.isEmpty(organizationId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        WmsOrganization organization = this.wmsOrganizationService.getById(organizationId);

        return ResponseData.success(organization);
    }



}
