/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
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
package cn.stylefeng.guns.modular.interfaces.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.constant.dictmap.InterDict;
import cn.stylefeng.guns.core.util.JsonFormatUtil;
import cn.stylefeng.guns.core.util.MD5Util;
import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.guns.modular.servlet.entity.WmsInterfaceLog;
import cn.stylefeng.guns.modular.system.warpper.InterWrapper;
import cn.stylefeng.guns.modular.system.warpper.LogWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口管理
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/inter")
public class InterController extends BaseController {

    private static String PREFIX = "/modular/interfaces/inter/";

    @Autowired
    private InterService interService;

    @Autowired
    private TableService tableService;

    /**
     * 跳转到接口列表列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "inter.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/inter_add")
    public String interAdd() {
        return PREFIX + "inter_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/inter_edit")
    public String interEdit(@RequestParam Long interId) {
        if (ToolUtil.isEmpty(interId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        Inter inter = this.interService.getById(interId);
        LogObjectHolder.me().set(inter);

        return PREFIX + "inter_edit.html";
    }

    /**
     * 跳转到接口对应关系详情列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/inter_option")
    public String interOption(@RequestParam String downTable) {
        if (ToolUtil.isEmpty(downTable)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

      //根据表名查找表属性
//        Inter inter = this.interService.getById(interId);
//        LogObjectHolder.me().set(inter);

        return PREFIX + "inter_option.html";
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @Permission
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(Inter inter) {
        this.interService.updateInter(inter);
        return SUCCESS_TIP;
    }

    /**
     * 接口对应关系
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @Permission
    @RequestMapping("/option")
    @ResponseBody
    public ResponseData option(Inter inter) {
        this.interService.updateInter(inter);
        return SUCCESS_TIP;
    }

    /**
     * 查询操作报文详情
     *
     * @author heyajun
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/detail/{interId}")
    @ResponseBody
    public Object detail(@PathVariable Long interId) {
        Inter inter = this.interService.getById(interId);
        //根据查找到的对象设置接口报文
        JSONObject json=new JSONObject();
        json.put("msgid",System.currentTimeMillis());
        json.put("partnerId",inter.getUserName());
        json.put("operatorType","add");
        JSONArray array=new JSONArray();
        List<TableOption> optionList = this.tableService.selectOptionByTable(inter.getDownTable());
        Map<String, Object> objectMap = new HashMap<>();
        if (optionList != null || optionList.size() > 0) {
            for (TableOption tableOption : optionList) {
                String key = tableOption.getDownColumnName();
                objectMap.put(key, tableOption.getTypeName()+"("+tableOption.getColumnSize()+")");
            }
            array.add(objectMap);
        }
        json.put("data",array);
        json.put("sign", System.currentTimeMillis());
        inter.setDetail(JsonFormatUtil.toFormat(json.toString(),true,false));
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(inter);
        return super.warpObject(new InterWrapper(stringObjectMap));
    }
    /**
     * 获取菜单列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String interfaceName,
                       @RequestParam(required =false) String interfaceType,
                       @RequestParam(required = false) String downTable){
        Page<Map<String, Object>> inters = this.interService.selectInter(interfaceName,interfaceType,downTable);
        Page<Map<String, Object>> wrap = new InterWrapper(inters).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增菜单
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @BussinessLog(value = "添加接口配置", key = "Inter", dict = InterDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(Inter inter) {
        this.interService.addInter(inter);
        return SUCCESS_TIP;
    }

    /**
     * 删除接口列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除接口列表", key = "interId", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long interId) {
        if (ToolUtil.isEmpty(interId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //缓存接口的名称
        LogObjectHolder.me().set(ConstantFactory.me().getInterfaceName(interId));

        this.interService.delInter(interId);

        return SUCCESS_TIP;
    }

    /**
     * 查看接口
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/view/{interId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long interId) {
        if (ToolUtil.isEmpty(interId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Inter inter = this.interService.getById(interId);
        return ResponseData.success(inter);
    }

    /**
     * 获取接口信息
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getInterInfo")
    @ResponseBody
    public ResponseData getInterInfo(@RequestParam Long interId) {
        if (ToolUtil.isEmpty(interId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        Inter inter = this.interService.getById(interId);

        return ResponseData.success(inter);
    }



}
