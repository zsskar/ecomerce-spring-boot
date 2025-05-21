package com.example.demo.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {
    @NotNull(message = "Product name is mandatory")
    @Size(min = 2, message = "Product name must be at least 2 characters")
    private String name;

    private String description;

    @NotNull(message = "Product price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than zero")
    private BigDecimal price;

    private BigDecimal discountPercentage;

    @NotNull(message = "Product quantity is mandatory")
    @Min(value = 0, message = "Stock quantity must be zero or greater")
    private Integer stockQuantity;
}
