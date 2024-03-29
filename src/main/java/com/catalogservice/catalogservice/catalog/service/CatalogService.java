package com.catalogservice.catalogservice.catalog.service;

import com.catalogservice.catalogservice.catalog.dto.CatalogDto;
import com.catalogservice.catalogservice.catalog.repository.CatalogEntity;

public interface CatalogService {
    Iterable<CatalogEntity> getAllCatalogs();
    CatalogDto getCatalogByProductId(String productId);
}
