package com.lta.backend.str_producer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminConfig {

    @Autowired
    private KafkaProperties kafkaProperties;
    //KafkaProperties kar = new KafkaProperties();
    @Bean
    public KafkaAdmin kafkaAdmin() {
        var configs = new HashMap<String, Object> ();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics((
                TopicBuilder.name("str-topic").partitions(2).replicas(1).build()
                //TopicBuilder.name("test-topic").partitions(1).replicas(1).build()
                ));
    }

}
