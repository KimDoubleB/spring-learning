package com.test.http.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private int price;

    public ProductDTO(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static ProductDTO getProductDTO() {
        return new ProductDTO(1L, "GALAXY S21", 999_999);
    }


}
