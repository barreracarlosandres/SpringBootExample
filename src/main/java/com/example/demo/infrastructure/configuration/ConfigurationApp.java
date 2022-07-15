package com.example.demo.infrastructure.configuration;

import com.example.demo.application.services.ServicePost;
import com.example.demo.application.services.ServiceUndoPost;
import com.example.demo.infrastructure.db.mapper.MapperPostEntity;
import com.example.demo.infrastructure.db.respository.PostDboRepository;
import com.example.demo.infrastructure.rest.mapper.MapperPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class ConfigurationApp {

    @Bean
    private static PostDboRepository postDboRepository() {
        return new PostDboRepository();
    }

    @Bean
    public MapperPostEntity mapperPostEntity() {
        return new MapperPostEntity();
    }

    @Bean
    public ServicePost postsService() {
        return new ServicePost(postDboRepository());
    }

    @Bean
    public ServiceUndoPost serviceUndoPost() {
        return new ServiceUndoPost(postDboRepository());
    }

    @Bean
    public MapperPost mapperPost() {
        return new MapperPost();
    }

}
