package me.study.spring_boot_get_start.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by dongchul on 2019-11-18.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**")
                .addResourceLocations("classpath:/m/") // 반드시 / 로 끝나야함
                .setCachePeriod(20);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("htt://localhost:18080");
    }
// view요청만 매핑하려면 controller를 등록 하면 된다.
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/hello").setViewName("hello");
//    }
}
