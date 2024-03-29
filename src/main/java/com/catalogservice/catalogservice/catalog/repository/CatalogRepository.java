package com.catalogservice.catalogservice.catalog.repository;

import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<CatalogEntity, Long> {
    CatalogEntity findByproductId(String productId);
}
