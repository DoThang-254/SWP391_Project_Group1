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
    private int requirementId;
    private String verificationCode;
    private String verificationMethod;
    private char verified;
    private float price;
    private int invoiceId;

    public WarrantyForm() {
    }

    public WarrantyForm(int formId, java.util.Date startDate, java.util.Date endDate, String status, 
                        int requirementId, String verificationCode, String verificationMethod, 
                        char verified, float price, int invoiceId) {
        this.formId = formId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.requirementId = requirementId;
        this.verificationCode = verificationCode;
        this.verificationMethod = verificationMethod;
        this.verified = verified;
        this.price = price;
        this.invoiceId = invoiceId;
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

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
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

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
