package com.example.infrastructure.dbs.mongodb.repository;

import com.example.infrastructure.dbs.mongodb.entity.PostEntityMongo;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MongoDbSpringIntegrationTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27018;

        ImmutableMongodConfig mongodConfig = MongodConfig.builder().version(Version.Main.PRODUCTION).net(new Net(ip, port, Network.localhostIsIPv6())).build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    }

    @Test
    void getById() {
        // Arrange
        PostDtoMongoRepository postDtoMongoRepository = new PostDtoMongoRepository(mongoTemplate);
        PostEntityMongo postEntityMongo = new PostEntityMongo(1, "titulo", "body");
        postDtoMongoRepository.insert(postEntityMongo);
        // Act - Assert
        Assertions.assertEquals(1, postDtoMongoRepository.getById(1).getId_post());
    }

    @Test
    void insert() {

        // Arrange
        PostDtoMongoRepository postDtoMongoRepository = new PostDtoMongoRepository(mongoTemplate);
        PostEntityMongo postEntityMongo = new PostEntityMongo(1, "titulo", "body");
        postDtoMongoRepository.insert(postEntityMongo);
        // Act - Assert
        Assertions.assertEquals(1, postDtoMongoRepository.getById(1).getId_post());

    }
}
