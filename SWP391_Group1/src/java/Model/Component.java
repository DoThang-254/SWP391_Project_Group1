/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
public class Component {
    private int componentId;
    private String componentName;
    private String brand;
    private String status;
    private long price;
    private int amount;
    private Staff staff;
    private Invoice invoice;

    public Component() {
    }

    public Component(int componentId, String componentName, String brand, String status, 
                     long price, int amount, Staff staff, Invoice invoice) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.brand = brand;
        this.status = status;
        this.price = price;
        this.amount = amount;
        this.staff = staff;
        this.invoice = invoice;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaffId(Staff staff) {
        this.staff = staff;
    }

    public Invoice getInvoiceId() {
        return invoice;
    }

    public void setInvoiceId(Invoice invoice) {
        this.invoice = invoice;
    }
}
