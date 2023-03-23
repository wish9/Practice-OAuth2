package com.codestates.hello_oauth2.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {
//    @GetMapping("/hello-oauth2")
//    public String home() {  // SecurityContext를 이용하는 방법
//        var oAuth2User = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //  SecurityContext(현재 스레드 인증정보)에서 인증된 Authentication 객체를 통해 Principal 객체 얻기
//        System.out.println(oAuth2User.getAttributes() // OAuth2User 객체에 저장되어 있는 사용자의 정보 중에서
//                .get("email"));   // 사용자의 이메일 정보 얻기
//        return "hello-oauth2"; // oauth2(구글) 인증 성공시에만 볼 수 있는 화면(hello-oauth2.html) 리턴
//    }

//    @GetMapping("/hello-oauth2")  // Authentication 객체를 핸들러 메서드 파라미터로 전달받는 방법
//    public String home(Authentication authentication) {
//        var oAuth2User = (OAuth2User)authentication.getPrincipal();
//        System.out.println(oAuth2User);
//        System.out.println("User's email in Google: " + oAuth2User.getAttributes().get("email"));
//
//        return "hello-oauth2";
//    }

    @GetMapping("/hello-oauth2")
    public String home(@AuthenticationPrincipal OAuth2User oAuth2User) {  // @AuthenticationPrincipal 애너테이션을 이용해 OAuth2User를 파라미터로 전달받는 방법
        System.out.println("User's email in Google: " + oAuth2User.getAttributes().get("email"));
        return "hello-oauth2";
    }
}
