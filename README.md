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
    * 독립적으로 실행 가능한 JAR 
        * mvn package 를 하면 실행 가능한 JAR 파일 하나가 생성 됨.
        * spring-maven-plugin이 해주는 일(패키징)
        * 과거 "uber" jar 사용          
            * 모든 클래스 (의존성 및 애플리케이션)을 하나로 압축 하는 방법
            * 뭐가 어디에서 온건지 알 수가 없음
                * 무슨 라이브러리를 사용한건지
            * 내용은 다르지만 이름이 같은 파일이 또 어떻게?
        * 스프링 부트의 전략
            * 내장 JAR : 기본적으로 자바에는 내장 JAR 를 로딩하는 표준적인 방법이 없음.
            * 애플리케이션 클래스와 라이브러리 위치 구분
            * org.springframework.boot.loader.jar.JarFile을 사용해서 내장 JAR 를 읽는다.
            * org.springframework.boot.loader.Launcher를 사용해서 실행한다.

* 스프링 부트 활용
    * 스프링 부트 활용 소개
        * 스프링 부트 핵심 기능
            * SpringApplication
            * 외부 설정
            * 프로파일
            * 로깅
            * 테스트
            * Spring-Dev-Tools
        * 각종 기술 연동
            * 스프링 웹 MVC
            * 스프링 데이터
            * 스프링 시큐리티
            * REST API 클라이언트
            * 다루지 않은 내용들.
    * SpringApplication
        * 기본 로그 레벨 INFO
            * 뒤에 로깅 수업때 자세히 살펴볼 예정
        * FailureAnalyzer
        * 배너
            * banner.txt | gif | jpg
            * classpath 또는 spring.banner.location
            * ${spring-boot.version} 등의 변수를 사용할 수 있음.
            * Banner 클래스 구현하고 SpringApplication.setBanner()로 설정 가능.
        * SpringApplicationBuilder로 빑더 패턴 사용 가능.
        * ApplicationEvent 등록
            * ApplicationContext를 만들기 전에 사용하는 리스너는 Bean등록 할 수 없다.
                * SpringApplication.addListeners()
        * WebApplication Type 설정
        * 애플리케이션 아규먼트 사용하기
            * -D 는 JVM 옵션
            * -- Application Arguements
            * ApplicationArguments를 빈으로 등록해주니깐 가져다 쓰면 된다.
        * 애플리케이션 실행한 뒤 뭔가 실행 하고 싶을때
            * ApplicationRunner(추천) 또는 CommandLineRunner
            * 순서 지정 기능 @Order 
    * 외부 설정
        * properties 
        * yaml
        * 환경 변수
        * 커맨드 라인 아규먼트
        
        * 프로퍼티 우선순위
            1. 유저 홈 디렉토리에 있는 spring-boot-dev-tools.properties
            2. 테스트에 있는 @TestPropertySource
            3. @SpringBootTest 애노테이션의 properties 애트리뷰트
            4. 커맨드 라인 아규먼트
            5. SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로티) 에 들어있는 프로퍼티
            6. ServletConfig 파라미터
            7. ServletContext 파라미터
            8. java:comp/env JNDI 애트리뷰트
            9. System.getProperties() 자바 시스템 프로퍼티
            10. OS 환경 변수
            11. RandomValuePropertySource
            12. JAR 밖에 있는 특정 프로파일용 application properties
            13. JAR 안에 있는 특정 프로파일용 application properties
            14. JAR 밖에 있는 application properties
            15. JAR 안에 있는 application properties
            16. @PropertySource
            17. 기본 프로퍼티 (SpringApplication.setDefaultProperties)
        * application.properties 우선순위
            1. file:./config/
            2. file:./
            3. classpath:/config/
            4. classpath:/ 
        * 랜덤 값 설정하기
            * ${random.*}    
        * 플레이스 홀더
            * name = dongchul
            * fullName = ${name} lee
        * 타입-세이프 프로퍼티 @ConfigurationProperties
            * 여러 프로퍼티를 묶어서 읽어올 수 있음.
            * 빈으로 등록해서 다른 빈에 주입 할 수 있음
                * @EnableConfigurationProperties
                * @Component
                * @Bean
            * 융통성있는 바인딩
                * context-path (kebab)
                * context)path (under score)
                * contextPath (camel)
                * CONTEXTPATH
            * 프로퍼티 타입 컨버전
                * @DurationUnit
            * 프로퍼티 값 검증
                * @Validated
                * JSR-303(@NotNull, ...)
            * 메타 정보 생성
            * @Value
                * SpEL 을 사용할 수 있지만
                * 위에 있는 기능들을 전부 사용 못함.
    * 프로파일
        * @Profile 애노테이션은 어디에서 사용하는가 ?
            * @Configuration
            * @Component
        * 어떤 프로파일을 활성화 할 것인가 ?
            * spring.profiles.active
        * 어떤 프로파일을 추가 할 것인가 ?
            * spring.profiles.include
        * 프로파일용 프로퍼티
            * application-{profile}.properties
    * 로깅
        * 로깅 퍼사드 vs 로거
            * Commons Logging, SLF4j
            * JUL, Log4J2, LogBack
        * 스프링 5에 로거 관련 변경 사항
            * https://docs.spring.io/spring/docs/5.0.0.RC3/spring-framework-reference/overview.html#overview-logging
            * Spring-JCL
                * Commons Logging -> SLF4j or Log4j2
                * pom.xml에 exclusion 안해도 됨 
        * 스프링 부트 로깅 
            * 기본 포맷
            * --debug (일부 핵심 라이브러리만 디버깅 모드로)
            * --trace (전부 다 디버깅 모드로)
            * 컬러 출력 : spring.output.n\ansi.enabled
            * 파일 출력 : logging.file 또는 logging.path
            * 로그 레벨 조정 : logging.level.패키지 = 로그레벨
        * 커스텀 로그 설정 파일 사용하기 https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html 
            * Logback : logback-spring.xml  (추천 logback.xml 보다 더 많은 기능을 사용할 수 있다.)
            * Log4J2 : log4j2-spring.xml
            * JUL (비추) : logging.properties
            * Logback extention
                * 프로파일 <springProfile name="프로파일">
                * Environment 프로퍼티 <springProperty>
        * 로거를 Log4j2로 변경하기
            * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html#howto-configure-log4j-for-logging          
* 스프링 부트 운영
