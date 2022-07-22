package com.example.infrastructure.configuration;

import com.example.application.services.ServicePost;
import com.example.application.services.ServiceUndoPost;
import com.example.infrastructure.dbs.arraydb.repository.PostArrayDboRepository;
import com.example.infrastructure.dbs.mapper.MapperPostEntity;
import com.example.infrastructure.rest.mapper.MapperPost;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("com.example")
//@EnableMongoRepositories
@EnableMongoRepositories(basePackages = {"com.example"})
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

    public String getDatabaseName() {
        return "myMongoDB";
    }
    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString("mongodb://post:post@localhost:27017/posts?authSource=admin");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "posts");
    }

}
