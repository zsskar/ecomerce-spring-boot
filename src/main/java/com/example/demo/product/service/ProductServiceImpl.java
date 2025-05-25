package com.example.demo.product.service;

import com.example.demo.errors.EcomErrorDetails;
import com.example.demo.errors.ErrorCodes;
import com.example.demo.errors.ErrorMessages;
import com.example.demo.product.dto.ProductDTO;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepo productsRepo;
    @Override
    public Product createProduct(Product product) {
        return productsRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        return productsRepo.findById(id)
                .map(existingProduct -> {
                    // Since validation is done at DTO level, we can safely update all fields
                    existingProduct.setName(productDTO.getName());
                    existingProduct.setDescription(productDTO.getDescription());
                    existingProduct.setPrice(productDTO.getPrice());
                    existingProduct.setDiscountPercentage(
                            Optional.ofNullable(productDTO.getDiscountPercentage())
                                    .orElse(BigDecimal.ZERO)
                    );
                    existingProduct.setStockQuantity(productDTO.getStockQuantity());

                    return productsRepo.save(existingProduct);
                })
                .orElseThrow(() -> new EcomErrorDetails(
                        ErrorCodes.PRODUCT_NOT_FOUND,
                        ErrorMessages.PRODUCT_NOT_FOUND,
                        "Product with ID " + id + " not found"
                ));
    }

    @Override
    public void deleteProduct(Long id) {
         productsRepo.delete(productsRepo.findById(id).orElseThrow(() -> new EcomErrorDetails(
                ErrorCodes.PRODUCT_DELETION_FAILED,
                ErrorMessages.PRODUCT_NOT_FOUND,
                "Product with ID " + id + " not found"
        )));
    }

    @Override
    public Product getProductById(Long id) {
        return productsRepo.findById(id).orElseThrow(() -> new EcomErrorDetails(
                ErrorCodes.PRODUCT_NOT_FOUND,
                ErrorMessages.PRODUCT_NOT_FOUND,
                "Product with ID " + id + " not found"
        ));
    }

    @Override
    public List<Product> getAllProducts() {
        return productsRepo.findAll();
    }

    @Override
    public BigDecimal getDiscountedPrice(Long id) {
        Product product = productsRepo.findById(id)
                .orElseThrow(() -> new EcomErrorDetails(
                        ErrorCodes.PRODUCT_NOT_FOUND,
                        ErrorMessages.PRODUCT_NOT_FOUND,
                        "Product with ID " + id + " not found"
                ));

        return product.getDiscountedPrice();
    }
}
