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

import cn.stylefeng.guns.core.common.constant.dictmap.QuartzTaskDict;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.schedue.QuartzManager;
import cn.stylefeng.guns.modular.servlet.entity.QuartzTask;
import cn.stylefeng.guns.modular.servlet.service.QuartzTaskService;
import cn.stylefeng.guns.modular.servlet.warpper.QuartzTaskWrapper;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 定时任务管理
 *
 * @author heyajun
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/task")
public class QuartzTaskController extends BaseController {

    private static String PREFIX = "/modular/servlet/task/";

    @Autowired
    private QuartzTaskService quartzTaskService;
    @Autowired
    private QuartzManager quartzManager;

    /**
     * 跳转到定时任务列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "task.html";
    }

    /**
     * 跳转到菜单列表列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/task_add")
    public String taskAdd() {
        return PREFIX + "task_add.html";
    }

    /**
     * 跳转到菜单详情列表页面
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/task_edit")
    public String taskEdit(@RequestParam Long jobId) {
        if (ToolUtil.isEmpty(jobId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        //获取菜单当前信息，记录日志用
        QuartzTask task = this.quartzTaskService.getById(jobId);
        LogObjectHolder.me().set(task);

        return PREFIX + "task_edit.html";
    }

    /**
     * 编辑定时任务
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @Permission
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseData edit(QuartzTask task) {

        quartzManager.modifyJobTime(task.getJobName(),task.getJobGroupName(),task.getTriggerName(),task.getTriggerGroupName(),task.getCorn());
        this.quartzTaskService.updateQuartzTask(task);
        return SUCCESS_TIP;
    }

    /**
     * 获取菜单列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String jobName,
                       @RequestParam(required = false) String className){
        Page<Map<String, Object>> tasks = this.quartzTaskService.selectQuartzTask(jobName,className);
        Page<Map<String, Object>> wrap = new QuartzTaskWrapper(tasks).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增菜单
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @BussinessLog(value = "添加定时任务配置", key = "task", dict = QuartzTaskDict.class)
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(QuartzTask task) {
        this.quartzTaskService.addQuartzTask(task);
        return SUCCESS_TIP;
    }

    /**
     * 删除定时任务列表
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除定时任务列表", key = "jobId", dict = DeleteDict.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long jobId) {
        if (ToolUtil.isEmpty(jobId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //缓存定时任务的名称
        LogObjectHolder.me().set(ConstantFactory.me().getJobName(jobId));

        this.quartzTaskService.delQuartzTask(jobId);

        return SUCCESS_TIP;
    }

    /**
     * 查看定时任务
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/view/{jobId}")
    @ResponseBody
    public ResponseData view(@PathVariable Long jobId) {
        if (ToolUtil.isEmpty(jobId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        QuartzTask task = this.quartzTaskService.getById(jobId);
        return ResponseData.success(task);
    }

    /**
     * 获取定时任务信息
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @RequestMapping(value = "/getTaskInfo")
    @ResponseBody
    public ResponseData getTaskInfo(@RequestParam Long jobId) {
        if (ToolUtil.isEmpty(jobId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        QuartzTask task = this.quartzTaskService.getById(jobId);

        return ResponseData.success(task);
    }

    /**
     * 启动定时任务
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/startTask")
    @ResponseBody
    public ResponseData startTask(@RequestParam Long jobId) {
        if (ToolUtil.isEmpty(jobId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        QuartzTask task = this.quartzTaskService.getById(jobId);

        Scheduler scheduler = null;
        try {
            scheduler =quartzManager.getScheduler();

        Trigger.TriggerState state = scheduler
                    .getTriggerState(TriggerKey.triggerKey(task.getTriggerName(), task.getTriggerGroupName()));
       JobDetail jobDetail=scheduler.getJobDetail(JobKey.jobKey(task.getJobName(),task.getJobGroupName()));
            if (state.equals(Trigger.TriggerState.NONE)&&(jobDetail==null||"".equals(jobDetail))) {
                    quartzManager.addJob(task.getJobName(),
                            task.getJobGroupName(),
                            task.getTriggerName(),
                            task.getJobGroupName(),
                            Class.forName(task.getClassName()),
                            task.getCorn());
            } else {
                quartzManager.resumeJob(task.getJobName(),
                        task.getJobGroupName());
            }
            task.setStartStaus("0");
            this.quartzTaskService.updateQuartzTask(task);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS_TIP;
    }

    /**
     * 停止定时任务
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/endTask")
    @ResponseBody
    public ResponseData endTask(@RequestParam Long jobId) {
        if (ToolUtil.isEmpty(jobId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        QuartzTask task = this.quartzTaskService.getById(jobId);
        //关闭某个定时任务
        quartzManager.pauseJob(task.getJobName(),
                task.getJobGroupName());
        task.setStartStaus("1");
        this.quartzTaskService.updateQuartzTask(task);
        return SUCCESS_TIP;
    }

    /**
     * 立即执行定时任务
     *
     * @author heyajun
     * @Date 2018/12/23 5:53 PM
     */
    @Permission
    @RequestMapping(value = "/startOnce")
    @ResponseBody
    public ResponseData startOnce(@RequestParam Long jobId) {
        if (ToolUtil.isEmpty(jobId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        QuartzTask task = this.quartzTaskService.getById(jobId);

        quartzManager.runOnce(task.getJobName(), task.getJobGroupName());
        return SUCCESS_TIP;
    }


}
