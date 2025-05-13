package com.example.demo.product.service;

import com.example.demo.product.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product updatedProduct);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    BigDecimal getDiscountedPrice(Long id);
}
