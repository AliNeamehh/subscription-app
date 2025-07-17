package com.example.subscription_service.BeanForTest;


import com.example.subscription_service.model.User;
import com.example.subscription_service.model.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    public User testUser() {
        return new User("u123", "tenant_abc", "test@example.com", "Test User", UserRole.ADMIN);

    }
}
