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
    private Date warrantyDateTime;
    private float price;
    private String brand;
    private Date buyTime;
    private int customerId;

    public Product() {
    }

    public Product(String productId, String productName, java.util.Date warrantyDateTime,
                   float price, String brand, Date buyTime, int customerId) {
        this.productId = productId;
        this.productName = productName;
        this.warrantyDateTime = warrantyDateTime;
        this.price = price;
        this.brand = brand;
        this.buyTime = buyTime;
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

    public java.util.Date getWarrantyDateTime() {
        return warrantyDateTime;
    }

    public void setWarrantyDateTime(java.util.Date warrantyDateTime) {
        this.warrantyDateTime = warrantyDateTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public java.util.Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(java.util.Date buyTime) {
        this.buyTime = buyTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

