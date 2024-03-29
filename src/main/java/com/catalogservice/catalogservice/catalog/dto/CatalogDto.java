package com.catalogservice.catalogservice.catalog.dto;

import com.catalogservice.catalogservice.catalog.vo.ResponseCatalog;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CatalogDto implements Serializable {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private String orderId;
    private String userId;

    private List<ResponseCatalog> catalogs;



}
