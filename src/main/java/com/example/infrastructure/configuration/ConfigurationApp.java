package com.example.infrastructure.configuration;

import com.example.application.services.ServicePost;
import com.example.application.services.ServiceUndoPost;
import com.example.infrastructure.dbs.arraydb.repository.PostArrayDboRepository;
import com.example.infrastructure.dbs.mapper.MapperPostEntity;
import com.example.infrastructure.rest.mapper.MapperPost;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("com.example")
//@EnableMongoRepositories
@EnableMongoRepositories(basePackages = {"com.example"})
public class ConfigurationApp {

    @Value("${spring.data.mongodb.database}")
    private String mongoDBName;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

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

    @Bean
    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(getUrlMongoDB());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), this.mongoDBName);
    }

    private String getUrlMongoDB() {
        return "mongodb://"+this.username+":"+this.password+"@"+this.host+":"+this.port+"/" + this.mongoDBName + "?authSource=admin";
    }




}
