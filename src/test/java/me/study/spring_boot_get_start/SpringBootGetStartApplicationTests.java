package me.study.spring_boot_get_start;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "dongchul.name=dong2")
//@TestPropertySource(properties = "dongchul.name=dong1")
@TestPropertySource(locations = "classpath:/test.properties")
@RunWith(SpringRunner.class)
class SpringBootGetStartApplicationTests {

    @Autowired
    Environment environment;

    @Test
    void contextLoads() {
        assertThat(environment.getProperty("dongchul.name")).isEqualTo("dongchul");
    }

}
