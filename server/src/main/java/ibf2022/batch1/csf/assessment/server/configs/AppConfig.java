package ibf2022.batch1.csf.assessment.server.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class AppConfig {

    public static final String DATABASE = "tfip_day40";

    // Inject the mongo connection String
    @Value("${mongo.url}")
    private String mongoUrl;

    // Create and initialize MongoTemplate
    @Bean
    public MongoTemplate createMongoTemplate() {
        // Create Mongo Client
        MongoClient client = MongoClients.create(mongoUrl);
        return new MongoTemplate(client, DATABASE);
    }
    
}
