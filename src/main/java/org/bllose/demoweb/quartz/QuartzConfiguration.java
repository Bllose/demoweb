package org.bllose.demoweb.quartz;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

    @Value("${quartz.job1.name}")
    private String myTestQuartzName;
    @Value("${quartz.job1.gourp}")
    private String myTestGroupName;
    @Value("${quartz.job1.function}")
    private String myTestFunction;
    @Value("${quartz.job1.trigger.name}")
    private String myTestTriggerName;
    @Value("${quartz.job1.trigger.time}")
    private String myTestTrigerTime;

    private static Logger logger = LoggerFactory.getLogger(QuartzConfiguration.class);

/*    @Bean(name = "myTestJob")
    public MethodInvokingJobDetailFactoryBean myTestJob(ScheduleTask task) {
        MethodInvokingJobDetailFactoryBean myTestJob = new MethodInvokingJobDetailFactoryBean();
        myTestJob.setConcurrent(false);
        myTestJob.setName(myTestQuartzName);// 设置任务的名字
        myTestJob.setGroup(myTestGroupName);// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用
        myTestJob.setTargetObject(task);
        myTestJob.setTargetMethod(myTestFunction);
        return myTestJob;
    }


    @Bean(name = "myTestJobTrigger")
    @Qualifier
    public CronTriggerFactoryBean myTestJobTrigger(MethodInvokingJobDetailFactoryBean myTestJob) {
        logger.trace("初始化 myTestJobTrigger");
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myTestJob.getObject());
        tigger.setCronExpression(myTestTrigerTime);// 初始时的cron表达式
        tigger.setName(myTestTriggerName);// trigger的name
        return tigger;
    }*/

/*    @Bean(name = "scheduler")
    public SchedulerFactoryBean myTestJobScheduler(Trigger cronJobTrigger) {

        logger.trace("初始化 scheduler");

        SchedulerFactoryBean bean = new SchedulerFactoryBean();
// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
// 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
// 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }*/


    /**
     * attention:
     * Details：配置定时任务
     */
    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task) {// ScheduleTask为需要执行的任务

        logger.trace("初始化job detail");
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
/*
 *  是否并发执行
 *  例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
 *  如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
 */
        jobDetail.setConcurrent(false);

        jobDetail.setName(myTestQuartzName);// 设置任务的名字
        jobDetail.setGroup(myTestGroupName);// 设置任务的分组，这些属性都可以存储在数据库中，在多任务的时候使用

/*
 * 为需要执行的实体类对应的对象
 */
        jobDetail.setTargetObject(task);

/*
 * sayHello为需要执行的方法
 * 通过这几个配置，告诉JobDetailFactoryBean我们需要执行定时执行ScheduleTask类中的sayHello方法
 */
        jobDetail.setTargetMethod(myTestFunction);
        return jobDetail;
    }

    /**
     * attention:
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务
     */
    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {

        logger.trace("初始化 trigger");
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail.getObject());
        tigger.setCronExpression(myTestTrigerTime);// 初始时的cron表达式
        tigger.setName(myTestTriggerName);// trigger的name
        return tigger;

    }

    /**
     * attention:
     * Details：定义quartz调度工厂
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger cronJobTrigger) {

        logger.trace("初始化 scheduler");

        SchedulerFactoryBean bean = new SchedulerFactoryBean();
// 用于quartz集群,QuartzScheduler 启动时更新己存在的Job
        bean.setOverwriteExistingJobs(true);
// 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
// 注册触发器
        bean.setTriggers(cronJobTrigger);
        return bean;
    }
}