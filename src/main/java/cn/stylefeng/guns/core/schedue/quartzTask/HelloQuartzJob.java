package cn.stylefeng.guns.core.schedue.quartzTask;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@DisallowConcurrentExecution
public class HelloQuartzJob implements Job {

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("Hello, Quartz! - executing its JOB at "+
                new Date() + " by " + context.getTrigger());
    }
}
