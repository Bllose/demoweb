package org.bllose.demoweb.quartz;



import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@Component // 此注解必加
@EnableScheduling // 此注解必加
public class ScheduleTask {

    public void sayHello(){
        System.out.println("Hello world, i'm the king of the world!!!");
    }

}
