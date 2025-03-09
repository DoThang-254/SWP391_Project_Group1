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
public class WarrantyForm {

    private int formId;
    private Date startDate;
    private Date endDate;
    private String status;
    private String verified;
    private String faultType;
    private String imgUrl;
    private Product product;
    private String technicianVerify; 

    public WarrantyForm() {
    }

    public WarrantyForm(int formId, Date startDate, Date endDate, String status, String verified, Product product) {
        this.formId = formId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.verified = verified;
        this.product = product;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getFaultType() {
        return faultType;
    }

    public void setFaultType(String faultType) {
        this.faultType = faultType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTechnicianVerify() {
        return technicianVerify;
    }

    public void setTechnicianVerify(String technicianVerify) {
        this.technicianVerify = technicianVerify;
    }

  

    
}
