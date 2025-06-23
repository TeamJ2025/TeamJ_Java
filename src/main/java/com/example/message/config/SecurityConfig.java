package com.example.message.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            // 誰でもアクセス可能（ログイン前）
            .requestMatchers("/", "/login", "/register", "/css/**", "/img/**", "/js/**","/.well-known/**").permitAll()
            
            // 管理者のみアクセス可能
            .requestMatchers("/staff/**").hasAuthority("ADMIN")          // スタッフ管理
            .requestMatchers("/brands/**").hasAuthority("ADMIN")         // 銘柄追加
            .requestMatchers("/Performance/PerformanceView").hasAuthority("ADMIN")  // 管理者用販売実績閲覧
            .requestMatchers("/sales/**").hasAnyAuthority("ADMIN")              // 販売実績一覧

            // 一般ユーザーもアクセス可能
            .requestMatchers("/Performance/Input").hasAnyAuthority("ADMIN", "USER")     // 販売実績入力
            .requestMatchers("/Performance/Confirm").hasAnyAuthority("ADMIN", "USER")   // 販売実績確認zz
            .requestMatchers("/Performance/PerformanceViewForUsers").hasAnyAuthority("ADMIN", "USER")  // 一般ユーザー用販売実績閲覧
            .requestMatchers("/forecast/**").hasAnyAuthority("ADMIN", "USER")           // 需要予測
            .requestMatchers("/salesforusers/**").hasAnyAuthority("ADMIN", "USER")      // 販売実績一覧（一般ユーザー用）
            .requestMatchers("/main").hasAnyAuthority("ADMIN", "USER")                  // メイン画面
            .requestMatchers("/main_user").hasAnyAuthority("ADMIN", "USER")             // ユーザー用メイン画面
            
            // その他はログインが必要
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/main", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );

    return http.build();
}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
