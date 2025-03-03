/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
public class Invoice {

    private int invoiceId;
    private long price;
    private String status;
    private String note;
    private WarrantyRequirement requirement;
    private boolean confirmed ;
    public Invoice() {
    }

    public Invoice(int invoiceId, long price, String status, String note, WarrantyRequirement requirement) {
        this.invoiceId = invoiceId;
        this.price = price;
        this.status = status;
        this.note = note;
        this.requirement = requirement;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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

    public WarrantyRequirement getRequirement() {
        return requirement;
    }

    public void setRequirement(WarrantyRequirement requirement) {
        this.requirement = requirement;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
    
   public Invoice(int invoiceId) {
    this.invoiceId = invoiceId;
}

}
