package com.example.infrastructure.dbs.mongodb.repository;

import com.example.common.infrastructure.exception.RuntimeExceptionNullPost;
import com.example.domain.Post;
import com.example.infrastructure.dbs.arraydb.db.ArrayPosts;
import com.example.infrastructure.dbs.arraydb.entity.PostEntityArray;
import com.example.infrastructure.dbs.mongodb.entity.PostEntityMongo;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PostDtoMongoRepositoryTest {

    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    private PostDtoMongoRepository postDtoMongoRepository;

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

        postDtoMongoRepository = new PostDtoMongoRepository(mongoTemplate);

//        ArrayPosts arrayPosts = ArrayPosts.getInstance();
//        for (PostEntityArray post: arrayPosts.getAllPosts()) {
//            mongoTemplate.insert(post);
//        }
    }

    @Test
    void getEntityByIdThatExist() {
        // Arrange
        PostEntityMongo postEntityMongo = new PostEntityMongo(1, "titulo", "body");
        // Act
        mongoTemplate.insert(postEntityMongo);
        // Act
        Assertions.assertEquals(postEntityMongo.toString(), postDtoMongoRepository.getById(1).toString());
    }

    @Test
    void tryToGetEntityByIdThatNotExist() {
        // Arrange
        PostEntityMongo postEntityMongo = new PostEntityMongo(3, "titulo", "body");
        try {
            // Act
            Assertions.assertEquals(postEntityMongo.toString(), postDtoMongoRepository.getById(postEntityMongo.getId_post()).toString());
        } catch (RuntimeExceptionNullPost exception) {
            // Assert
            Assertions.assertEquals("Post no existe", exception.getMessage());
        }
    }

    @Test
    void insert() {

        // Arrange
//        PostDtoMongoRepository postDtoMongoRepository = new PostDtoMongoRepository(mongoTemplate);
        PostEntityMongo postEntityMongo = new PostEntityMongo(2, "titulo", "body");
        // Act
        postDtoMongoRepository.insert(postEntityMongo);
        // Act - Assert
        Assertions.assertEquals(postEntityMongo.toString(), mongoTemplate.findOne(Query.query(Criteria.where("_id").is(postEntityMongo.getId_post())), PostEntityMongo.class).toString());

    }
}
