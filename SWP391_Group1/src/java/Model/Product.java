/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author thang
 */
public class Product {

    private String productId;
    private String productName;
    private long price;
    private String brand;
    private int customerId;
    private String warrantyStatus; 

    public Product() {
    }

    public Product(String productId, String productName,
            long price, String brand, int customerId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.customerId = customerId;
    }

    public Product(String productId, String productName,
            String brand) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(String warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }
}
