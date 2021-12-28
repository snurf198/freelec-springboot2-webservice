package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
// 여기서는 SpringRunner라는 스프링 실행자를 사용함
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
@WebMvcTest(controllers = HelloController.class) // 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API를 테스트할 때 사용함
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
                // 체이닝이 지원됨
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증함
                // 상태를 검증함
                // 여기선 ok 즉, 200인지 아닌지를 검증함
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증함
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name) // API 테스트할 때 사용될 요청 파라미터를 설정함
                        // 값은 String만 허용됨
                        // 숫자/ 날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능함
                        .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // JSON 응답값을 필드별로 검증할 수 있는 메소드
                // $를 기준으로 필드명을 명시함
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}