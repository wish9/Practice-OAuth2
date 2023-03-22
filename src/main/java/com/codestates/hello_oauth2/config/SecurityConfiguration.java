package com.codestates.hello_oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
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
}
