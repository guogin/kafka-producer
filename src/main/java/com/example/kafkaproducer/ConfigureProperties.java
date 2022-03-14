package com.example.kafkaproducer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.example.kafka.producer")
@Data
public class ConfigureProperties {
    private String bootstrapServer;
}
