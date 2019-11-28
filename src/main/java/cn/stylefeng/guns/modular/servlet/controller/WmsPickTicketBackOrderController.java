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

import cn.stylefeng.guns.modular.servlet.entity.WmsPickTickeBackOrder;
import cn.stylefeng.guns.modular.servlet.service.WmsPickTicketBackOrderService;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.servlet.warpper.WmsPickTicketOrderWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 入库订单接口
 *
 * @author heyajun
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/pickTicketBackOrder")
public class WmsPickTicketBackOrderController extends BaseController {

    private static String PREFIX = "/modular/servlet/pickTicketBackOrder/";

    private static final int batchSize=100;

    private static final String partnerKey="WMS2019";

    @Autowired
    private WmsPickTicketBackOrderService wmsPickTicketBackOrderService;

    /**
     * 跳转到接口列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pickTicketBackOrder.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/pickTicketBackOrder_add")
    public String pickTicketBackOrderAdd() {
        return PREFIX + "pickTicketBackOrder_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/pickTicketBackOrder_edit")
    public String pickTicketBackOrderEdit(@RequestParam Long pickTicketBackOrderId) {
        if (ToolUtil.isEmpty(pickTicketBackOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        WmsPickTickeBackOrder wmsPickTickeBackOrder = this.wmsPickTicketBackOrderService.getById(pickTicketBackOrderId);
        LogObjectHolder.me().set(wmsPickTickeBackOrder);

        return PREFIX + "pickTicketBackOrder_edit.html";
    }

    /**
     * 编辑
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    /**
     * 编辑接口
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @Permission
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(WmsPickTickeBackOrder wmspickTicketBackOrder) {
        this.wmsPickTicketBackOrderService.updateWmsPickTickeBackOrder(wmspickTicketBackOrder);
        return SUCCESS_TIP;
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
                       @RequestParam(required = false) String shangp_id,
                       @RequestParam(required = false) String zt){
        Page<Map<String, Object>> pickTicketBackOrders = this.wmsPickTicketBackOrderService.selectWmsPickTickeBackOrder(danj_no,shangp_id,zt);
        Page<Map<String, Object>> wrap = new WmsPickTicketOrderWrapper(pickTicketBackOrders).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }


    /**
     * 删除接口列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除接口列表", key = "pickTicketBackOrderId", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long pickTicketBackOrderId) {
        if (ToolUtil.isEmpty(pickTicketBackOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //缓存接口的名称
        LogObjectHolder.me().set(ConstantFactory.me().getItemCode(pickTicketBackOrderId));

        this.wmsPickTicketBackOrderService.delWmsPickTickeBackOrder(pickTicketBackOrderId);

        return SUCCESS_TIP;
    }

    /**
     * 查看接口
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/view/{pickTicketBackOrderId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long pickTicketBackOrderId) {
        if (ToolUtil.isEmpty(pickTicketBackOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        WmsPickTickeBackOrder pickTicketBackOrder = this.wmsPickTicketBackOrderService.getById(pickTicketBackOrderId);
        return ResponseData.success(pickTicketBackOrder);
    }

    /**
     * 获取接口信息
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getpickTicketBackOrderInfo")
    @ResponseBody
    public ResponseData getpickTicketBackOrderInfo(@RequestParam Long pickTicketBackOrderId) {
        if (ToolUtil.isEmpty(pickTicketBackOrderId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        WmsPickTickeBackOrder pickTicketBackOrder = this.wmsPickTicketBackOrderService.getById(pickTicketBackOrderId);

        return ResponseData.success(pickTicketBackOrder);
    }



}
