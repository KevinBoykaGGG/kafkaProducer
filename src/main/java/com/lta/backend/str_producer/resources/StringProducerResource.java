package com.lta.backend.str_producer.resources;

import com.lta.backend.str_producer.services.StringProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //maneja solicitudes http y devuelve respuestas http para api resful (json,xml)
@RequestMapping("/producer") //unificar rutas
public class StringProducerResource {
    @Autowired
    private StringProducerService stringProducerService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message){ //clase tipo wrapped para controlar como se construyen y devuelven las respuestas http
        stringProducerService.sendMessage("str-topic",message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
