package me.study.spring_boot_get_start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by dongchul on 2019-11-25.
 */
@Component
public class RestRunner implements ApplicationRunner {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    WebClient.Builder builder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        RestTemplate restTemplate = restTemplateBuilder.build();
        WebClient webClient = builder.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

//        String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
//        System.out.println(helloResult);
//
//        String worldResult = restTemplate.getForObject("http://localhost:8080/world", String.class);
//        System.out.println(worldResult);

        Mono<String> helloMono = webClient.get().uri("http://localhost:8080/hello")
                .retrieve()
                .bodyToMono(String.class);
        //subscribe 시 실제로 요청을 보내게 되며 non blocking 으로 동작 한다.
        helloMono.subscribe(s -> {
            System.out.println(s);
            if(stopWatch.isRunning())
                stopWatch.stop();

            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

        Mono<String> worldMono = webClient.get().uri("http://localhost:8080/world")
                .retrieve()
                .bodyToMono(String.class);
        worldMono.subscribe(s -> {
            System.out.println(s);
            if(stopWatch.isRunning())
                stopWatch.stop();

            System.out.println(stopWatch.prettyPrint());
            stopWatch.start();
        });

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }
}
