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

import cn.stylefeng.guns.modular.interfaces.entity.Inter;
import cn.stylefeng.guns.modular.interfaces.entity.TableOption;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.interfaces.service.InterService;
import cn.stylefeng.guns.modular.interfaces.service.TableService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 接口表配置管理
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/table")
public class TableController extends BaseController {

    private static String PREFIX = "/modular/interfaces/table/";

    @Autowired
    private TableService tableService;

    @Autowired
    private InterService interService;

    /**
     * 跳转到接口列表列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index(@RequestParam("interId") Long interId, Model model) {
        model.addAttribute("interId", interId);

        //获取type的名称
        Inter inter=this.interService.getById(interId);
        if (inter == null) {
            throw new RequestEmptyException();
        }
        model.addAttribute("tableName", inter.getDownTable());
        return PREFIX + "table.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/table_add")
    public String tableAdd(@RequestParam("interId") Long interId, Model model) {
        model.addAttribute("interId", interId);

        //获取type的名称
        Inter inter=this.interService.getById(interId);
        if (inter == null) {
            throw new RequestEmptyException();
        }
        model.addAttribute("tableName", inter.getDownTable());
        return PREFIX + "table_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/table_edit")
    public String tableEdit(@RequestParam("optionId") Long optionId, Model model) {
        //获取type的id
        TableOption tableOption = this.tableService.getById(optionId);
        if (tableOption == null) {
            throw new RequestEmptyException();
        }

        //获取type的名称
        Inter inter = interService.getById(tableOption.getInterId());
        if (inter == null) {
            throw new RequestEmptyException();
        }

        model.addAttribute("interId", inter.getInterId());
        model.addAttribute("tableName", tableOption.getTableName());

        return PREFIX + "table_edit.html";
    }

    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(TableOption table) {
        this.tableService.updateTable(table);
        return SUCCESS_TIP;
    }

    /**
     * 查询列表
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(TableOption table) {
        return this.tableService.findPageBySpec(table);
    }

    /**
     * 新增菜单
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
//    @Permission
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(TableOption table) {
        this.tableService.addTable(table);
        return SUCCESS_TIP;
    }

    /**
     * 删除接口列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
//    @Permission
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除接口列表", key = "optionId", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long optionId) {
        if (ToolUtil.isEmpty(optionId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.tableService.delTable(optionId);

        return SUCCESS_TIP;
    }

    /**
     * 获取接口信息
     *
     * @author fengshuonan
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getTableInfo")
    @ResponseBody
    public ResponseData getTableInfo(@RequestParam Long optionId) {
        if (ToolUtil.isEmpty(optionId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        TableOption tableOption = this.tableService.getById(optionId);

        return ResponseData.success(tableOption);
    }





}
