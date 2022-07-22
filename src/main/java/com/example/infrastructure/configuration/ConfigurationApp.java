package com.example.infrastructure.configuration;

import com.example.application.services.ServicePost;
import com.example.application.services.ServiceUndoPost;
import com.example.infrastructure.dbs.arraydb.repository.PostArrayDboRepository;
import com.example.infrastructure.dbs.mapper.MapperPostEntity;
import com.example.infrastructure.rest.mapper.MapperPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("com.example")
//@EnableMongoRepositories(basePackages = {"com.example"})
public class ConfigurationApp {

    @Bean
    private static PostArrayDboRepository postDboRepository() {
        return new PostArrayDboRepository();
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

    @Bean
    public MapperPostEntity mapperPostEntity() {
        return new MapperPostEntity();
    }

}
