package com.test.http.controller;


import com.test.http.controller.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/product")
@RestController
public class ProductController {

    @GetMapping
    public ProductDTO getProduct() {
        return ProductDTO.getProductDTO();
    }


    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productDTO;
    }

    @PutMapping
    public ProductDTO updateProduct(@RequestBody int price) {
        ProductDTO productDTO = ProductDTO.getProductDTO();
        productDTO.setPrice(price);
        return productDTO;
    }
}
