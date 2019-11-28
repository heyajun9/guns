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

import cn.stylefeng.guns.modular.servlet.entity.WmsInterfaceLog;
import cn.stylefeng.guns.modular.servlet.service.WmsInterfaceLogService;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.state.BizLogType;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.system.warpper.LogWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author heyajun
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/interfaceLog")
public class WmsInterfaceLogController extends BaseController {

    private static String PREFIX = "/modular/servlet/interfaceLog/";

    @Autowired
    private WmsInterfaceLogService interfaceLogService;

    /**
     * 跳转到日志管理的首页
     *
     * @author heyajun
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "interfaceLog.html";
    }

    /**
     * 查询操作日志列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) String logName,
                       @RequestParam(required = false) String succeed,
                       @RequestParam(required = false) Integer logType) {

        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();

        //根据条件查询操作日志
        List<Map<String, Object>> result = interfaceLogService.getInterfaceLogs(page, beginTime, endTime, logName, BizLogType.valueOf(logType),succeed);

        page.setRecords(new LogWrapper(result).wrap());

        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 查询操作日志详情
     *
     * @author heyajun
     * @Date 2018/12/23 5:34 PM
     */
    @RequestMapping("/detail/{id}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable Long id) {
        WmsInterfaceLog interfaceLog = interfaceLogService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(interfaceLog);
        return super.warpObject(new LogWrapper(stringObjectMap));
    }

    /**
     * 清空日志
     *
     * @author heyajun
     * @Date 2018/12/23 5:34 PM
     */
    @BussinessLog(value = "清空接口日志")
    @RequestMapping("/delLog")
    @Permission
    @ResponseBody
    public Object delLog() {
        SqlRunner.db().delete("delete from wms_interface_log");
        return SUCCESS_TIP;
    }
}
