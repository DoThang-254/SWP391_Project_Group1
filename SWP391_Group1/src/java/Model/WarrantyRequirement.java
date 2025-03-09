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
public class WarrantyRequirement {

    private int requirementId;
    private String status;
    private String description;
    private java.util.Date registerDate;
    private Customer customer;
    private Staff staff;
    private Product product;
    private String isPay;
    private WarrantyForm form;
    private String category; 

    public WarrantyRequirement() {
    }

    public WarrantyRequirement(int requirementId, String status, String description, Date registerDate, Customer customer, Staff staff, Product product, String isPay) {
        this.requirementId = requirementId;
        this.status = status;
        this.description = description;
        this.registerDate = registerDate;
        this.customer = customer;
        this.staff = staff;
        this.product = product;
        this.isPay = isPay;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(java.util.Date registerDate) {
        this.registerDate = registerDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public WarrantyForm getForm() {
        return form;
    }

    public void setForm(WarrantyForm form) {
        this.form = form;
    }
    
     public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
