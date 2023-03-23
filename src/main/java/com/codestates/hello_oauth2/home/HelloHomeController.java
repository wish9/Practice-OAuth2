package com.codestates.hello_oauth2.home;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {
    @GetMapping("/hello-oauth2")
    public String home() {
        var oAuth2User = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //  SecurityContext(현재 스레드 인증정보)에서 인증된 Authentication 객체를 통해 Principal 객체 얻기
        System.out.println(oAuth2User.getAttributes() // OAuth2User 객체에 저장되어 있는 사용자의 정보 중에서
                .get("email"));   // 사용자의 이메일 정보 얻기
        return "hello-oauth2"; // oauth2(구글) 인증 성공시에만 볼 수 있는 화면(hello-oauth2.html) 리턴
    }
}
