package com.lta.backend.str_producer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//libreria loombok sirve para simplificar codigo haciendo uso de anotaciones @
@Service //logica de negocio de la app
@Slf4j //es un log para registrar cualquier evento relevante del sistema (errores, excepciones, warnings, debug,trace)
public class StringProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message).whenComplete((result, exception) -> {
            if (exception != null) {
                log.error("Error while sending kafka message: {}", exception.getMessage());
            }
            log.info("Message was sent successfully: {}",result.getProducerRecord().value());
            log.info("Partition: {}, Offset: {}",result.getRecordMetadata().partition(),result.getRecordMetadata().offset());
        });
    }

}
