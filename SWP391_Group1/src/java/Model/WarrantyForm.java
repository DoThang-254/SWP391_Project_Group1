/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
public class WarrantyForm {
    private int formId;
    private java.util.Date startDate;
    private java.util.Date endDate;
    private String status;
    private WarrantyRequirement requirement;
    private String verificationCode;
    private String verificationMethod;
    private char verified;
    private float price;
    private Invoice invoice;

    public WarrantyForm() {
    }

    public WarrantyForm(int formId, java.util.Date startDate, java.util.Date endDate, String status, 
                        WarrantyRequirement requirement, String verificationCode, String verificationMethod, 
                        char verified, float price, Invoice invoice) {
        this.formId = formId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.requirement = requirement;
        this.verificationCode = verificationCode;
        this.verificationMethod = verificationMethod;
        this.verified = verified;
        this.price = price;
        this.invoice = invoice;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WarrantyRequirement getRequirementId() {
        return requirement;
    }

    public void setRequirementId(WarrantyRequirement requirement) {
        this.requirement = requirement;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationMethod() {
        return verificationMethod;
    }

    public void setVerificationMethod(String verificationMethod) {
        this.verificationMethod = verificationMethod;
    }

    public char getVerified() {
        return verified;
    }

    public void setVerified(char verified) {
        this.verified = verified;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Invoice getInvoiceId() {
        return invoice;
    }

    public void setInvoiceId(Invoice invoice) {
        this.invoice = invoice;
    }
}
