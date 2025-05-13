package com.example.demo.product.service;

import com.example.demo.errors.EcomErrorDetails;
import com.example.demo.errors.ErrorCodes;
import com.example.demo.errors.ErrorMessages;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepo productsRepo;
    @Override
    public Product createProduct(Product product) {
        return productsRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        return productsRepo.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setDiscountPercentage(
                            updatedProduct.getDiscountPercentage() != null
                                    ? updatedProduct.getDiscountPercentage()
                                    : BigDecimal.ZERO
                    );
                    existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
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
