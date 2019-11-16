package me.study.spring_boot_get_start.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @SpringBootTest
 * 통합 테스트용
 *
 * @WebMvcTest(SampleContorller.class)
 * 슬라이싱 테스트용
 * 선언한 Controller 관련된 Bean 만 생성 하기 때문에
 * Service ,Repository 같은 Bean 들은 모킹 해야 한다.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 기본값
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 실제로 서버가 구동됨. rest template 로 확인 해야함.
@AutoConfigureMockMvc // mock mvc를 사용 하기 위함
public class SampleControllerTest {
    // async client test for webflux
    // webflux의존성을 가지고 있어야 사용 가능.
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean //컨트롤러 테스트를 위해 모킹.
    SampleService mockSampleService;

    @Test
    public void hello() throws Exception {
        // sample service 를 mocking
        when(mockSampleService.getName()).thenReturn("dongchul");

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello dongchul"))
                .andDo(print());

        String result = testRestTemplate.getForObject("/hello", String.class);
        assertEquals(result, "hello dongchul");

        webTestClient.get().uri("/hello")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("hello dongchul");
    }
}