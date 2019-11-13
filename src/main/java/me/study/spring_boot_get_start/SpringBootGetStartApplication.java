package me.study.spring_boot_get_start;

import me.dongchul.Holoman;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;

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

    /**
     * WebApplicationType
     * NONE 서블릿과 Webflux가 없을때
     * SERVLET : Servlet
     * REACTIVE : WebFlux
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootGetStartApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
//        new SpringApplicationBuilder()
//                .sources(SpringBootGetStartApplication.class)
//                .run(args);
        // applicationStartingEvent 는 application context 생성 전에 발생하기 때문에 이렇게 등록 해줘야 한다,
//        SpringApplication app = new SpringApplication(SpringBootGetStartApplication.class);
//        app.addListeners(new SampleListener());
//        app.run(args);
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

    /**
     * https와 http를 사용하기 위해 connector 등록.
     *
     */
    //@Bean
    public ServletWebServerFactory serverFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache/.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return createStandardConnector();
    }


}
