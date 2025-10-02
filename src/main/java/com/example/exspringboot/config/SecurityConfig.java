package com.example.exspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public/**").permitAll()     // 누구나 접근 가능
                        .anyRequest().authenticated()                           // 나머지는 로그인 필요
                )
                .formLogin(form -> form   // 기본 로그인 화면 사용
                        .defaultSuccessUrl("/", true) // 로그인 성공하면 /profile로 이동
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)    // 세션 무효화
                        .clearAuthentication(true)      // 인증정보삭제
                        .deleteCookies("JSESSIONID")      // 쿠키삭제
                        .logoutSuccessUrl("/login")      // 쿠키삭제
                );

        return http.build();
    }
}
