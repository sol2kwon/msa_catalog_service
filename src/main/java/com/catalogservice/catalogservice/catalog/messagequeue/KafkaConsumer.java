package com.catalogservice.catalogservice.catalog.messagequeue;

import com.catalogservice.catalogservice.CatalogServiceApplication;
import com.catalogservice.catalogservice.catalog.repository.CatalogEntity;
import com.catalogservice.catalogservice.catalog.repository.CatalogRepository;
import com.catalogservice.catalogservice.catalog.service.CatalogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    CatalogRepository repository;
    @Autowired
    public KafkaConsumer(   CatalogRepository repository){
        this.repository = repository;
    }

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage){
        log.info("kafkaMessage :: {}",kafkaMessage);
        HashMap<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<HashMap<Object, Object>>() {});

        }catch (JsonProcessingException e){
            e.printStackTrace();

        }

       CatalogEntity entity = repository.findByproductId((String) map.get("productId"));

        if (entity != null){
            entity.setStock(entity.getStock() - (Integer)map.get("qty"));
            log.info("  entity.setStock :: {}",entity);
            repository.save(entity);
        }


    }
}
