package com.o2.co.uk.infra;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class SpringMongoConfig {

	@Autowired
	private PropertiesManager propertiesManager;

	public @Bean MongoDbFactory mongoDbFactoryidentity() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI(propertiesManager.getProperty("DB_URI"));
		setKeystoreInfo();
		return new SimpleMongoDbFactory(mongoClientURI);
	}

	@Primary
	public @Bean(name = "identityMongoTemplate") MongoTemplate mongoTemplateIdentity() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactoryidentity());
		return mongoTemplate;
	}

	public @Bean MongoDbFactory mongoDbFactoryAsset() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI(propertiesManager.getProperty("DB_URI_Asset"));
		setKeystoreInfo();
		return new SimpleMongoDbFactory(mongoClientURI);
	}

	public @Bean(name = "assetMongoTemplate") MongoTemplate mongoTemplateAsset() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactoryAsset());
		return mongoTemplate;
	}

	public void setKeystoreInfo() {
		System.setProperty("javax.net.ssl.keyStore", propertiesManager.getProperty("jks.path"));
		System.setProperty("javax.net.ssl.keyStorePassword", propertiesManager.getProperty("jks.password"));
		System.setProperty("javax.net.ssl.trustStore", propertiesManager.getProperty("jks.path"));
		System.setProperty("javax.net.ssl.trustStorePassword", propertiesManager.getProperty("jks.password"));
	}
}
