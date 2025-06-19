package com.example.message.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final MessageRepository repository;

    public SecurityUserDetailsService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Message user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));

        // return User.builder()
        //         .username(user.getEmail())
        //         .password(user.getPassword())
        //         .authorities("USER")
        //         .build();

        String authority;
        if ("admin".equals(user.getRole())) {
            authority = "ADMIN";  // 管理者権限
        } else {
            authority = "USER";   // 一般ユーザー権限
        }
        
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authority)  // roleに基づいた権限を設定
                .build();        
    }
}
