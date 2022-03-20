package com.fyp.supplierHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {



    @Bean
    public PasswordEncoder passwordEncoderBean(){
        return new BCryptPasswordEncoder(10);
    }
}
