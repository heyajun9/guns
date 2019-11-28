package cn.stylefeng.guns.modular.servlet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("tb_task_t")
public class QuartzTask implements Serializable {

    @TableId(value = "jobid", type = IdType.ID_WORKER)
    private Long jobId;
   @TableField("jobname")
    private String jobName;
   @TableField("jobgroupname")
    private String jobGroupName;
   @TableField("triggername")
    private String triggerName;
   @TableField("triggergroupname")
    private String triggerGroupName;
   @TableField("corn")
    private String corn;
   @TableField("classname")
    private String className;
   @TableField("methodname")
    private String methodName;
   @TableField("startstaus")
    private String startStaus;//是否启动
   @TableField("runstatus")
    private String runStatus;//是否运行
   @TableField("note")
    private String note;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroupName() {
        return triggerGroupName;
    }

    public void setTriggerGroupName(String triggerGroupName) {
        this.triggerGroupName = triggerGroupName;
    }

    public String getCorn() {
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStartStaus() {
        return startStaus;
    }

    public void setStartStaus(String startStaus) {
        this.startStaus = startStaus;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
