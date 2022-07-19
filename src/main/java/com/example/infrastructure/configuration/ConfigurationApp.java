package com.example.infrastructure.configuration;

import com.example.application.services.ServiceUndoPost;
import com.example.application.services.ServicePost;
import com.example.infrastructure.db.mapper.MapperPostEntity;
import com.example.infrastructure.db.respository.PostDboRepository;
import com.example.infrastructure.rest.mapper.MapperPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example")
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
