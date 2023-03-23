package com.codestates.hello_oauth2.home;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloHomeController {
//    @GetMapping("/hello-oauth2-useSecurityContext")
//    public String home() {  // 인증정보 확인하는 방법1 - SecurityContext를 이용하는 방법
//        var oAuth2User = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //  SecurityContext(현재 스레드 인증정보)에서 인증된 Authentication 객체를 통해 Principal 객체 얻기
//        System.out.println(oAuth2User.getAttributes() // OAuth2User 객체에 저장되어 있는 사용자의 정보 중에서
//                .get("email"));   // 사용자의 이메일 정보 얻기
//        return "hello-oauth2"; // oauth2(구글) 인증 성공시에만 볼 수 있는 화면(hello-oauth2.html) 리턴
//    }
//
//    @GetMapping("/hello-oauth2-useAuthentication")  // 인증정보 확인하는 방법2 - Authentication 객체를 핸들러 메서드 파라미터로 전달받는 방법
//    public String home(Authentication authentication) {
//        var oAuth2User = (OAuth2User)authentication.getPrincipal();
//        System.out.println(oAuth2User);
//        System.out.println("User's email in Google: " + oAuth2User.getAttributes().get("email"));
//
//        return "hello-oauth2";
//    }
//
//    @GetMapping("/hello-oauth2-use@AuthenticationPrincipal") // 인증정보 확인하는 방법3
//    public String home(@AuthenticationPrincipal OAuth2User oAuth2User) {  // @AuthenticationPrincipal 애너테이션을 이용해 OAuth2User를 파라미터로 전달받는 방법
//        System.out.println("User's email in Google: " + oAuth2User.getAttributes().get("email"));
//        return "hello-oauth2";
//    }

//    private final OAuth2AuthorizedClientService authorizedClientService;
//
//    // OAuth2AuthorizedClientService를 DI 받는 방법
//    public HelloHomeController(OAuth2AuthorizedClientService authorizedClientService) {
//        this.authorizedClientService = authorizedClientService;
//    }
//
//    @GetMapping("/hello-oauth2")
//    public String home(Authentication authentication) {
//        var authorizedClient = authorizedClientService.loadAuthorizedClient("google", authentication.getName()); // loadAuthorizedClient()를 이용해 OAuth2AuthorizedClient 조회
//
//        OAuth2AccessToken accessToken = authorizedClient.getAccessToken(); // OAuth2AccessToken 객체 얻기
//        System.out.println("Access Token Value: " + accessToken.getTokenValue());  //  Access Token의 문자열을 출력
//        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());  //  Token의 타입을 출력
//        System.out.println("Access Token Scopes: " + accessToken.getScopes());       // 토큰으로 접근할 수 있는 리소스의 범위 목록을 출력
//        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());    // 토큰의 발행일시를 출력
//        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());  // 토큰의 만료일시를 출력
//
//        return "hello-oauth2";
//    }

    @GetMapping("/hello-oauth2") // OAuth2AuthorizedClient를 핸들러 메서드의 파라미터로 전달받는 방법
    public String home(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println("Access Token Value: " + accessToken.getTokenValue());
        System.out.println("Access Token Type: " + accessToken.getTokenType().getValue());
        System.out.println("Access Token Scopes: " + accessToken.getScopes());
        System.out.println("Access Token Issued At: " + accessToken.getIssuedAt());
        System.out.println("Access Token Expires At: " + accessToken.getExpiresAt());

        return "hello-oauth2";
    }
}
