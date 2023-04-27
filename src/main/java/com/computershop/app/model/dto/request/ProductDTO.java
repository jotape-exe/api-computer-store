package com.computershop.app.model.dto.request;

import com.computershop.app.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {
    @NotBlank(message = "Invalid Input!")
    private String name;

    @NotBlank(message = "Invalid Input!")
    private String description;

    @NotBlank(message = "Invalid Input!")
    private String manufacturer;

    @NotNull(message = "Invalid Input!")
    @Min(value = 1)
    private Double value;

    @NotNull(message = "Invalid Input!")
    @Min(value = 1)
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Product toEntity(){
        return new Product(this.name, this.description, this.manufacturer, this.value, this.amount );
    }

    public Product toEntity(Product product){
        product.setName(this.name);
        product.setDescription(this.description);
        product.setManufacturer(this.manufacturer);
        product.setAmount(this.amount);
        product.setValue(this.value);
        return product;
    }
}
