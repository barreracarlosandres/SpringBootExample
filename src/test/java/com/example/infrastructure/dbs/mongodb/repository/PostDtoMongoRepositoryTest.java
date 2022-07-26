package com.example.infrastructure.dbs.mongodb.repository;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ConfigurationApp.class)         // Bean configuration
//@SpringBootTest(classes=PostDtoMongoRepository.class)
class PostDtoMongoRepositoryTest {

    public MongoClient mongo() {
        ConnectionString connectionString = new ConnectionString(getUrlMongoDB());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();

        return MongoClients.create(mongoClientSettings);
    }

    private String getUrlMongoDB() {
        return "mongodb://post:post@localhost:27017/posts?authSource=admin";
    }

//    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), "posts");
    }

    @Test
    void getById( ) {
        // Arrange

        PostDtoMongoRepository postDtoMongoRepository = new PostDtoMongoRepository(mongoTemplate());
        // Act
        // Assert
        Assertions.assertEquals(1, postDtoMongoRepository.getById(1).getId_post());
    }

    @Test
    void insert() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
    }
}