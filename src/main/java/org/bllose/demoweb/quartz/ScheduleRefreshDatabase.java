package org.bllose.demoweb.quartz;

import javax.annotation.Resource;

import org.bllose.demoweb.repository.RepositoryDemo;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class ScheduleRefreshDatabase {
    @Autowired
    private RepositoryDemo  repository;

    @Resource(name = "jobDetail")
    private JobDetail jobDetail;

    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @Value( "${env.currect}")
    private String env;

    /*
    * spring 环境加载的配置数据
    * */
    @Autowired
    private Environment environment;

    @Scheduled(fixedRate = 5000)
    public void test(){

    }

//    @Scheduled(fixedRate = 5000) // 每隔5s查库，并根据查询结果决定是否重新设置定时任务
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        System.out.println("当前运行环境："+ env);
        System.out.println(environment.getProperty("bllose.test.name"));
        System.out.println(environment.getProperty("bllose.test.property"));


        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = trigger.getCronExpression();// 当前Trigger使用的
//        String searchCron = repository.findOne(1L).getCron();// 从数据库查询出来的
        String searchCron = repository.findJobCron("srd-chhliu");
        System.out.println(currentCron);
        System.out.println(searchCron);
        if (currentCron.equals(searchCron)) {
            // 如果当前使用的cron表达式和从数据库中查询出来的cron表达式一致，则不刷新任务
        } else {
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
            // 按新的cronExpression表达式重新构建trigger
            trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
            trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                    .withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
            currentCron = searchCron;
        }
    }
}