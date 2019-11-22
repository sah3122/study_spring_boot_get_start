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
    * 테스트
        * 시작은 spring-boot-starter-test를 추가하는것 부터
            * test scope로 추가
        * @SpringBootTest
            * @RunWith(SpringRunner.class)랑 같이 써야함
            * 빈 설정 파일은 설정을 안해주나 ? 알아서 찾는다. (@SpringBootApplication)
            * WebEnvironment
                * MOCK : mock servlet environment : 내장 톰캣 구동 안함.
                * RANDOM_PORT, DEFINED_PORT : 내장 톰캣 사용 함
                * NONE : 서블릿 환경 제공 안함
            * @MockBean
                * ApplicaionContext에 들어있는 빈을 Mock으로 만든 객체로 교체 함 
                * 모든 @Test 마다 자동으로 리셋
            * 슬라이스 테스트
                * 레이어 별로 잘라서 테스트하고 싶을떄
                * @JsonTest
                * @WebMvcTest
                * @WebFluxTest
                * @DataJpaTest
                * ...
            * 테스트 유틸
                * OutputCapture (log 및 stdout 관련 테스트)
                * TestPropertyValues
                * TestRestTemplate
                * ConfigFileApplicationContextInitailizer
    * Spring boot Devtools
        * 캐시 설정을 개발 환경에 맞게 변경.
        * 클래스패스에 있는 파일이 변경 될 때 마다 자동으로 재시작.
            * 직접 껐다 켜는것(cold starts) 보다 빠르다. 
            * 릴로딩 보다는 느리다. (JRebel같은게 아님)
            * 리스타트 하고 싶지 않은 리소스는 ? spring.devtools.restart.exclude
            * 리스타트 기능 끄려면 ? spring.devtools.restart.enabled = false
        * 라이브 릴로드 ? 리스타트 했을 때 브라우저 자동 리프레시 하는 기능
            * 브라우저 플러그인 설치해야 함
            * 라이브 릴로드 서버 끄려면 ? spring.devtools.liveload.enabled = false
        * 글로벌 설정
            * ~/.spring-boot-devtools.properties
        * 리모트 애플리케이션
    * 스프링 웹 MVC
        * 스프링 웹 MVC
            * https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/web.html#spring-web
        * 스프링 부트 MVC
            * 자동 설정으로 제공하는 여러 기본 기능(앞으로 살펴볼 예정)
        * 스프링 MVC 확장
            * @Configuration + WebMvcConfigurer
        * 스프링 MVC 재정의
            * @Configuration + @EnableWebMvc
        * HttpMessageConverters
            * HTTP 요청 본문을 객체로 변경하거나, 객체를 HTTP 응답 본문으로 변경 할 때 사용 <br>
            {“username”:”keesun”, “password”:”123”} <-> User
            * 스프링 부트
                * 뷰 리졸버 설정 제공
                * HttpMessageConvertersAutoConfiguration
        * 정적 리소스 매핑 "/**"
            * 기본 리소스 위치
                * classpath:/static
                * classpath:/pubilc
                * classpath:/resources/
                * classpath:/META-INF/resources
                * 예) "/hello.html" => static/hello.html
                * spring.mvc.static-path-pattern: 매핑 설정 변경 가능
                * spring.mvc.static-locations: 리소스 찾을 위치 변경 가능
            * Last-Modified 헤더를 보고 304 응답을 보냄
            * ResourceHttpRequestHandler가 처리
                * WebMvcConfigurer의 addResourceHandlers로 커스터 마이징 가능
        * 웹 JAR 맵핑 "/webjars/*"
            * 버전 생략 하고 사용하려면
                * webjars-locator-core 의존성 추가 
        * 웰컴 페이지
            * index.html 찾아 보고 있으면 제공.
            * index.템플릿 찾아보고 있으면 제공.
            * 둘다 없으면 에러 페이지 노출
        * 파비콘
            * favicon.ico 파일을 static 폴더 하위에 저장.
            * 파비콘 만들기 https://favicon.io/
            * 파비콘이 안 바뀔 때 ?
                * 파비콘 요청 -> 브라우저 껏다 켜기
        * 템플릿 엔진
            * 스프링 부트가 자동설정을 지원하는 템플릿 엔진
                * FreeMarker
                * Groovy
                * Thymeleaf
                * Mustache
            * JSP를 권장하지 않는 이유
                * JAR 패키징 할 때는 동작하지 않고 WAR 패키징 해야함.
                * Undertow는 JSP를 지원하지 않음.
            * Thymeleaf 사용하기
                * https://www.thymeleaf.org
                * https://www.thymeleaf.org/doc/articles/standarddialect5minutes.html
                * 의존성 추가 : spring-boot-starter-thymeleaf
                * 템플릿 파일 위치 : /src/main/resources/template
                * 예제 : https://github.com/thymeleaf/thymeleafexamples-stsm/blob/3.0-master/src/main/webapp/WEB-INF/templates/seedstartermng.html
        * HTML 템블릿 뷰 테스트를 보다 전문적으로 하자.
            * http://htmlunit.sourceforge.net/
            * http://htmlunit.sourceforge.net/gettingStarted.html
            * 의존성 추가
            * @Autowired WebClient
        * ExceptionHandler
            * 스프링 @MVC 예외 처리 방법
                * @ControllerAdvice
                * @ExceptionHanlder
            * 스프링 부트가 제공하는 기본 예외 처리기
                * BaseErrorController
                    * HTML 과 JSON 응답 지원
                * 커스터마이징 방법
                    * ErrorController 구현
            * 스프링 부트가 제공하는 기본 예외 처리기
                * BasicErrorController
                    * HTML 과 JSON 응답 지원
                * 커스터마이징 방법
                    * ErrorController 구현
            * 커스텀 에러 페이지
                * 상태 코드 값에 따라 에러 페이지 보여주기
                * src/main/resources/static/error/
                * 404.html
                * 5xx.html
                * ErrorViewResolver 구현
        * Spring HATEOAS <br>
        Hypermedia As The Engine Of Application State
            * 서버 : 현재 리소스와 연관된 링크 정보를 클라이언트에게 제공한다.
            * 클라이언트 : 연관된 링크 정보를 바탕으로 리소스에 접근한다.
            * 연관된 링크 정보
                * Relation
                * Hypertext Reference
            * spring-boot-starter-hateoas 의존성 추가
            * https://spring.io/understanding/HATEOAS
            * https://spring.io/guides/gs/rest-hateoas/
            * https://docs.spring.io/spring-hateoas/docs/current/reference/html/
            * ObjectMapper 제공
                * 커스터마이징
                    * property에 spring.jackson.* 값 변환하는 방식 추천
                * Jackson2ObjectMapperBuilder
            * LinkDiscovers 제공
                * 클라이언트 쪽에서 링크 정보를 Rel 이름으로 찾을 때 사용할 수 있는 XPath 확장 클래스
        * CORS
            * SOP와 CORS
                * Single-Origin Policy
                * Cross-Origin Resource Sharing
                * Origin ?
                    * URI 스키마 (http, https)
                    * hostname (localhost...)
                    * 포트 (8080, 18080)
            * 스프링 MVC @CrossOrigin
                * https://docs.spring.io/spring/docs/5.0.7.RELEASE/spring-framework-reference/web.html#mvc-cors
                * @Controller나 @RequestMapping에 추가 하거나
                * WebMvcConfigurer 사용해서 글로벌 설정
    * 스프링 데이터
        * 인메모리 데이터베이스
            * 지원하는 인-메모리 데이터베이스
                * H2 (추천, 콘솔때문에)
                * HSQL
                * Derby
            * Spring-JDBC가 클래스패스에 있으면 자동 설정이 필요한 빈을 설정 해준다.
                * DataSource
                * JdbcTemplate
            * 인-메모리 데이터베이스 기본 연결 정보 확인 하는 방법
                * URL : "testdb"
                * username : "sa"
                * password : ""
            * H2 콘솔 사용하는 방법
                * spring-boot-devtools를 추가 하거나
                * spring.h2.console.enabled=true 추가
                * /h2-console로 접속 (path변경 가능)
        * MySQL
            * 지원하는 DBCP
                1. HikariCP (기본)
                    * https://github.com/brettwooldridge/HikariCP#frequently-used
                2. Tomcat CP
                3. Commonc DBCP2
            * DBCP 설정
                * spring.datasource.hikari.*
                * spring.datasource.tomcat.*
                * spring.datasource.dbcp2.*
            * MySQL 라이센스 (GPL) 주의
                * MySQL 대신 MariaDB 사용 검토
                * 소스 코드 공개 의무 여부 확인
            * MySQL 접속시 에러
                * MySQL 5.* 최신 버전 사용할 때 문제
                    * Sat Jul 21 11:17:59 PDT 2018 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
                * 해결
                    * jdbc:mysql:/localhost:3306/springboot?useSSL=false
                * MySQL 8.* 최신 버전 사용할 때 문제
                    * com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: Public Key Retrieval is not allowed
                * 해결
                    * jdbc:mysql:/localhost:3306/springboot?useSSL=false&allowPublicKeyRetrieval=true
        * PostgreSQL
            * Mysql 과는 달리 라이센스 문제가 없다. 가장 추천하는 DB
             
         
* 스프링 부트 운영
