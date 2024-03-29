package com.catalogservice.catalogservice.catalog.controller;

import com.catalogservice.catalogservice.catalog.repository.CatalogEntity;
import com.catalogservice.catalogservice.catalog.dto.CatalogDto;
import com.catalogservice.catalogservice.catalog.service.CatalogService;
import com.catalogservice.catalogservice.catalog.vo.ResponseCatalog;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@Slf4j
public class CatalogController {
    Environment env;
    CatalogService catalogService;
    @Autowired
    public CatalogController(CatalogService catalogService, Environment env){
        this.env = env;
        this.catalogService = catalogService;
    }

    @GetMapping("/heath_check")
    public String status(){
        return String.format("It's Working in catalogService, port : %s", env.getProperty("local.server.port"));
    }
    /**
     * 전체 상품정보를 조회한다.
     * */

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getUsers() {
        Iterable<CatalogEntity> catalogList = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        catalogList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseCatalog.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/catalogs/{productId}")
    public ResponseEntity<ResponseCatalog> getCatalogs(@PathVariable("productId") String productId) {
        CatalogDto catalogDto = catalogService.getCatalogByProductId(productId);
        ModelMapper mapper = new ModelMapper();
        ResponseCatalog responseCatalog = mapper.map(catalogDto, ResponseCatalog.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseCatalog);
    }
}
