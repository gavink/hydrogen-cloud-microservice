package com.hycloud.auth.sso;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "123";
        System.out.println(encoder.encode(pass));
    }
}
