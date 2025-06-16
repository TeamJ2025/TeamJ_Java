package com.example.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll() // indexページだけ許可
                .anyRequest().permitAll()         // 他も全部許可（必要に応じて制限可）
            )
            .csrf(csrf -> csrf.disable())         // POST送信用にCSRF無効（開発時向け）
            .formLogin(login -> login.disable()); // Spring Securityのデフォルトログイン画面を無効

        return http.build();
    }
}
