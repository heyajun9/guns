package cn.stylefeng.guns.core.schedue;

import org.quartz.*;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;


import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class QuartzManager  {

    private   Scheduler scheduler;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public QuartzManager(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * @Description: 添加一个定时任务
     *
     * @param jobName 任务名
     * @param jobGroupName  任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param jobClass  任务
     * @param cron   时间设置，参考quartz说明文档
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public  void addJob(String jobName, String jobGroupName,
                              String triggerName, String triggerGroupName, Class jobClass, String cron) {
        try {
//            Scheduler scheduler=schedulerFactory.getScheduler();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= newJob(jobClass).withIdentity(jobName, jobGroupName).build();

            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 修改一个任务的触发时间
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param cron   时间设置，参考quartz说明文档
     */
    public   void modifyJobTime(String jobName,
                              String jobGroupName, String triggerName, String triggerGroupName, String cron) {
        try {
//            Scheduler scheduler=schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }

            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                // 创建Trigger对象
                trigger = (CronTrigger) triggerBuilder.build();
                // 方式一 ：修改一个任务的触发时间
                scheduler.rescheduleJob(triggerKey, trigger);
                /** 方式一 ：调用 rescheduleJob 结束 */

                /** 方式二：先删除，然后在创建一个新的Job  */
                //JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                //Class<? extends Job> jobClass = jobDetail.getJobClass();
                //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
                /** 方式二 ：先删除，然后在创建一个新的Job */
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     */
    public  void removeJob(String jobName, String jobGroupName,
                          String triggerName, String triggerGroupName) {
        try {
//            Scheduler scheduler=schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);

            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description:启动所有定时任务
     */
    public   void startJobs() {
        try {
//            Scheduler scheduler=schedulerFactory.getScheduler();
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 启动某个定时任务
     */
    public   void resumeJob(String jobName, String jobGroupName) {
        try {
//            Scheduler scheduler=schedulerFactory.getScheduler();
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Description:关闭所有定时任务
     */
    public  void shutdownJobs() {
        try {
//            Scheduler scheduler=schedulerFactory.getScheduler();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 删除某个任务
     */
    public   void deleteJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
        try {
//            Scheduler scheduler=schedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
             		scheduler.unscheduleJob(triggerKey);// 移除触发器
             		scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (SchedulerException e) {
             		e.printStackTrace();
        }
    }


            /**
             * 关闭某个定时任务
             * @return
             */
            public  void pauseJob(String jobName, String jobGroupName) {
                try {
//                    Scheduler scheduler=schedulerFactory.getScheduler();
                    scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }

            /**
             * 立即运行一次定时任务
             * @return
             */
            public   void runOnce(String jobName, String jobGroupName){
                try {
//                    Scheduler scheduler=schedulerFactory.getScheduler();
                    scheduler.triggerJob(JobKey.jobKey(jobName, jobGroupName));
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }

            /**
             * 查看某个任务状态
             * @return
             */
            public   Trigger.TriggerState getJobStatus(String triggerName, String triggerGroupName) throws SchedulerException {
//                Scheduler scheduler=schedulerFactory.getScheduler();
                Trigger.TriggerState state = scheduler.getTriggerState(TriggerKey.triggerKey(triggerName, triggerGroupName));
                return state;
            }




        }
