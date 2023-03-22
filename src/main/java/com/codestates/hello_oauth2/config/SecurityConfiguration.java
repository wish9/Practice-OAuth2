package com.codestates.hello_oauth2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration { // build.gradle dependences에 설정해뒀기 때문에 사실상 얜 없어도 자동구성 되는 클래스임
    @Value("${spring.security.oauth2.client.registration.google.clientId}")  // application.yml 파일에 설정되어 있는 구글의 Client ID 로드
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.clientSecret}") // clientSecret 로드
    private String clientSecret;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF 보호 기능을 비활성화
                .formLogin().disable() // 폼 기반 로그인을 비활성화
                .httpBasic().disable() // HTTP Basic 인증을 비활성화
                .authorizeHttpRequests(authorize -> authorize    // HttpSecurity를 인자로 받아서, 요청에 대한 인증 및 권한 부여
                        .anyRequest().authenticated() // 모든 요청에 대해서 인증된 사용자만 접근이 가능하도록 설정
                )
                .oauth2Login(withDefaults());    // OAuth2 로그인을 활성화하고 기본값으로 설정
        return http.build();
    }

    @Bean // ClientRegistration을 저장하기 위한 Responsitory 빈등록
    public ClientRegistrationRepository clientRegistrationRepository() {
        var clientRegistration = clientRegistration();    // ClientRegistration(클라이언트 등록) 인스턴스 가져오기

        return new InMemoryClientRegistrationRepository(clientRegistration);   // ClientRegistrationRepository 인터페이스의 구현 클래스인InMemoryClientRegistrationRepository의 인스턴스를 생성
        // InMemoryClientRegistrationRepository 는 ClientRegistration 을 메모리에 저장
    }

    private ClientRegistration clientRegistration() { // ClientRegistration 인스턴스를 생성하는 메서드
        return CommonOAuth2Provider // 구글 말고도 깃헙, 페이스북 등 연결할 때 사용
                .GOOGLE
                .getBuilder("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }
}
