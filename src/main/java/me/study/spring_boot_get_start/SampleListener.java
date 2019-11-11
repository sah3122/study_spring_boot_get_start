package me.study.spring_boot_get_start;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by dongchul on 2019-11-11.
 */
@Component
//public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
public class SampleListener implements ApplicationListener<ApplicationStartedEvent> {
//    @Override
//    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
//        System.out.println("Application is Starting");
//    }
    @Override
    public void onApplicationEvent(ApplicationStartedEvent ApplicationStartedEvent) {
        System.out.println("Application is Startied");
    }
}
