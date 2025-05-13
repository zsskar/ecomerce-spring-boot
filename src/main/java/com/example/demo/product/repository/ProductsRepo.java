package com.example.demo.product.repository;

import com.example.demo.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Product, Long> {
}
