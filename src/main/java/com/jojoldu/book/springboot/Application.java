package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 이 애너테이션이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 함
@SpringBootApplication  // 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정됨
public class Application {
    public static void main(String args[]) {
        SpringApplication.run(Application.class, args); // 내장 WAS를 실행함
        // 내장 WAS : 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부 WAS를 실행하는 것
        // 항상 서버에 톰갯을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar 파일로 실행하면 됨
        // 스프링은 '언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있다는 이유로 내장 WAS를 사용하는 것을 권장하고 있음
    }
}

