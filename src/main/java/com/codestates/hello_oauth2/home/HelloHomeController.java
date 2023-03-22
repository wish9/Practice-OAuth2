package com.codestates.hello_oauth2.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {
    @GetMapping("/hello-oauth2")
    public String home() {
        return "hello-oauth2"; // oauth2(구글) 인증 성공시에만 볼 수 있는 화면(hello-oauth2.html) 리턴
    }
}
