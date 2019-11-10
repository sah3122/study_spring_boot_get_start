package me.study.spring_boot_get_start;

import me.dongchul.Holoman;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication

/**
 * @SpringBootApplication 은 아래 3가지 애노테이션을 포함 하고 있다.
 *
 * @SpringBootConfiguration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
public class SpringBootGetStartApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SpringBootGetStartApplication.class, args);
        SpringApplication application = new SpringApplication(SpringBootGetStartApplication.class);
        application.run(args);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("/", "/");

        HttpServlet httpServlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                PrintWriter writer = resp.getWriter();
                writer.println("");
            }
        };

        String servletName = "helloServlet";
        tomcat.addServlet("/", servletName, httpServlet);
        context.addServletMappingDecoded("/hello", servletName);

    }

    /**
     * autoconfiguration 으로 등록된 bean 은 component scan으로 등록하는 bean을 덮어 쓴다.
     * ConditionalOnMissingBean - 빈이 등록 되어 있지 않을때 빈 등록함.
     * component scan -> autoConfiguration 순으로
     */
    @Bean
    public Holoman holoman() {
        Holoman holoman = new Holoman();
        holoman.setName("dongchul");
        holoman.setHowLong(60);
        return holoman;
    }

}
