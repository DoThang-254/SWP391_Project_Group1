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
public class WarrantyInformation {
    private int informationId;
    private String status;
    private String note;
    private Date returnDate;
    private String productId;
    private String staffId;

    public WarrantyInformation() {
    }

    public WarrantyInformation(int informationId, String status, String note, java.util.Date returnDate, 
                        String productId,String staffId) {
        this.informationId = informationId;
        this.status = status;
        this.note = note;
        this.returnDate = returnDate;
        this.productId = productId;
        this.staffId = staffId;
    }

    public int getInformationId() {
        return informationId;
    }

    public void setInformationId(int informationId) {
        this.informationId = informationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public java.util.Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(java.util.Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getProduct() {
        return productId;
    }

    public void setProduct(String product) {
        this.productId = product;
    }

    public String getStaff() {
        return staffId;
    }

    public void setStaff(String staff) {
        this.staffId = staff;
    }
}

