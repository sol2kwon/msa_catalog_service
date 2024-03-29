package com.catalogservice.catalogservice.catalog.service;

import com.catalogservice.catalogservice.catalog.dto.CatalogDto;
import com.catalogservice.catalogservice.catalog.repository.CatalogEntity;
import com.catalogservice.catalogservice.catalog.repository.CatalogRepository;
import com.catalogservice.catalogservice.catalog.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
    CatalogRepository catalogRepository;

    @Autowired
    public void CatalogRepository (CatalogRepository catalogRepository){

        this.catalogRepository = catalogRepository;
    }
    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
    @Override
    public CatalogDto getCatalogByProductId(String productId) {
        CatalogEntity catalogEntity = catalogRepository.findByproductId(productId);
/*
        if (catalogEntity == null)
            throw new UsernameNotFoundException("User not found");*/


        ModelMapper mapper = new ModelMapper();
        CatalogDto catalogDto =  mapper.map(catalogEntity,CatalogDto.class);

        List<ResponseCatalog> catalogs = new ArrayList<>();
        catalogDto.setCatalogs(catalogs);

        return catalogDto;
    }

}
