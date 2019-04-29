package org.bllose.demoweb.quartz;



import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *     定时任务的各种定义方式
 *     1、Calendar-like
 *     2、固定间隔 fixed rate
 *     3、容器启动后延迟一段时间后开始执行 initialDelay
 * </pre>
 * @Auther Bllose
 * @Date 2019/4/29
 */
@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class ScheduleTask {

    @Scheduled(cron = "${quartz.job2.trigger.time}")
    public void sayHello(){
        System.out.println("Hello world, i'm the king of the world!!!");
    }

    @Scheduled(fixedRate = 5000) // 每隔五秒执行一次
    public void sayNice(){
        System.out.println("Nice to meet you!");
    }

    @Scheduled(initialDelay = 10000, fixedRate=5000)
    public void sayBye(){
        System.out.println("See you!");
    }
}
