package cn.stylefeng.guns.core.schedue;

import cn.stylefeng.guns.modular.servlet.entity.QuartzTask;
import cn.stylefeng.guns.modular.servlet.service.QuartzTaskService;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

/**
 * quartz的启动示例
 *
 * @author fengshuonan
 * @Date 2019/2/24 16:55
 */
public  class StartQuartz implements CommandLineRunner {

    @Autowired
    private QuartzTaskService quartzTaskService;
    @Autowired
    private QuartzManager quartzManager;

    public StartQuartz() {
    }

    @Override
    public  void run(String... args) throws Exception {

        List<QuartzTask> jobList = (List<QuartzTask>) quartzTaskService.selectQuartzTaskAll();
        if (jobList.size() > 0) {
            for (QuartzTask job : jobList) {
                if (job.getStartStaus().equals("0")) {
                    if (CronExpression.isValidExpression(job.getCorn())) {
//                            QuartzManager.deleteJob(job.getJobName(), job.getJobGroupName(), job.getTriggerName(),
//                                    job.getTriggerGroupName());
                        try {
                            quartzManager.addJob(job.getJobName(), job.getJobGroupName(), job.getTriggerName(),
                                    job.getTriggerGroupName(), Class.forName(job.getClassName()), job.getCorn());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

}