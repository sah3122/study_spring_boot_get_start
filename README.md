# study_spring_boot_get_start
Inflearn 스프링 부트 개념과 활용 강의 정리

----------------

* 스프링 부트 시작하기
  * 스프링 부트 프로젝트 생성
* 스프링 부트 원리
    * 의존성 관리 이해
        * 스프링 부트에서는 자동으로 의존성을 관리 해준다. 
        * 일반적으로 spring-boot-starter-parent에 정의되어 있는 라이브러리는 버전 명시를 하지 않아도 자동으로 버전을 찾아서 적용해준다,
        parent에 등록되지 않은 라이브러리를 사용할때는 버전을 명시 해줘야 한다.
        * 특정 버전을 사용하고 싶을땐 버전을 명시한게 우선순위를 가지게 됨.
     * 의존성 관리 응용
        * 의존성 추가
            * 스프링 부트가 관리 해주지 않는 라이브러리는 버전을 명시 해주는것이 Best 
        * 의존성 변경
            * 스프링 부트가 관리 해주는 버전도 변경 할 수 있다.
    * 자동 설정 이해
        * @EnableAutoConfiguration (@SpringBootApplication 안에 숨어 있다.)
        * 빈은 사실 두 단계로 나눠서 읽힘
            * 1단계 : @ComponentScan
            * 2단계 : @EnableAutoConfiguration
        * @ComponentScan
            * @Component
            * @Configuration @Repository @Service @Controller @RestController
        * EnableAutoConfiguration
            * spring.factories
                * org.springframwork.boot.autoconfigure.EnableAutoConfiguration
            * @Configuration
            * @ConditionalOnXxxYyyZzz
    * 자동 설정 만들기 dongchul-spring-boot-starter 참고
        * Xxx-Spring-Boot-Autoconfigure 모듈: 자동 설정
        * Xxx-Spring-Boot-Starter 모듈: 필요한 의존성 정의
        * 구현 방법
            1. 의존성 추가 (new project 생성 - dongchul-spring-boot-starter)
            2. @Configuration 파일 작성
            3. src/main/resource/META-INF에 spring.factories 파일 만들기
            4. spring.factories 안에 자동 설정 파일 추가
               * org.springframework.boot.autoconfigure.EnableAutoConfiguration=class
            5. mvn install
        * 덮어쓰기 방지하기
            *  ConditionalOnMissingBean - 빈이 등록 되어 있지 않을때 빈 등록함.
        * 빈 재정의 수고 덜기
            * @ConfigurationProperties("holoman")
            * @EnableConfigurationProperties(HolomanProperties)
            * 프로퍼티 키 값 자동 완성
    * 내장 서블릿 컨테이너
        * 스프링 부트는 서버가 아니다.
        * 포트 설정
        * 톰캣에 컨텍스트 추가.
        * 서블릿 만들기
        * 톰캣에 서블릿 추가
        * 컨텍스트에 서블릿 매핑
        * 톰캣 실행 및 대기
        * 이 모든 과정을 보다 상세히 또 유연하고 설정하고 실행해주는게 바로 스프링 부트의 자동 설정.
            * Step 1 : ServletWebServerFactoryAutoConfiguration (서블릿 웹 서버 생성)
                * TomcatServletWebServerFactoryCustomizer (서버 커스터마이징)
            * Step 2 : DispatcherServletAutoConfiguration
                * 서블릿 만들고 등록.
    * 내장 웹 서버 응용 1부 : 컨테이너와 서버 포트
        * 다른 서블릿 컨테이너로 변경
            * boot-stater에 포함된 tomcat 의존성 제외
            * undertow, jetty ... 의존성 추가
        * 웹 서버 사용하지 않기
            * properties에 application-type 추가
        * 포트
    * 내장 웹 서버 응용 2부 : https 와 http2
        * HTTPS 설정하기
            * 키스토어 만들기
                * https://gist.github.com/keesun/f93f0b83d7232137283450e08a53c4fd
            * HTTP 는 사용하지 못함 ?
                * 기본적으로 http 커넥터는 1개라서 https를 설정하게 되면 http를 사용 못함.
                * connector를 따로 등록 해줘야 한다.
        * HTTP2
            * SSL 은 기본적으로 적용이 되어 있어야 한다.
            * undertow는 별다른 설정 없이 properties로 설정 가능
            * tomcat 은 8.5 버전 이하에선 설정할 것들이 많아 권장 하지 않음
            * tomcat 9.0 & java 9 이상부터 사용 권장. 
    * 톰캣 HTTP2
        * JDK9와 Tomcat 9+ 추천
        * 링크 참조
            * https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-embedded-web-servers

의존성 추가

* 스프링 부트 활용

* 스프링 부트 운영
