package com.o2.co.uk.infra;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class SpringMongoConfig {

    @Autowired
    private PropertiesManager propertiesManager;

    public @Bean MongoDbFactory mongoDbFactory() throws Exception {
//        MongoClient mongoClient = new MongoClient(propertiesManager.getProperty("mongodb.host"), Integer.parseInt(propertiesManager.getProperty("mongodb.port")));
//        return new SimpleMongoDbFactory(mongoClient, propertiesManager.getProperty("mongodb.name"));
        MongoClientURI mongoClientURI = new MongoClientURI(propertiesManager.getProperty("DB_URI"));
        return new SimpleMongoDbFactory(mongoClientURI);
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;

    }

}
