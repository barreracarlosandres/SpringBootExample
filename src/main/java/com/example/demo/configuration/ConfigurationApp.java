package com.example.demo.configuration;

import com.example.demo.services.PostRespository;
import com.example.demo.services.PostsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class ConfigurationApp {

    @Bean
    private static PostRespository initialPostsData() {
        return new PostRespository();
    }

    @Bean
    public PostsService postsService() {
        return new PostsService(initialPostsData());
    }
}
