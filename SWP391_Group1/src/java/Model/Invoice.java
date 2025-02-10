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
    private float price;
    private String status;
    private String note;
    private WarrantyForm form;

    public Invoice() {
    }

    public Invoice(int invoiceId, float price, String status, String note, WarrantyForm form) {
        this.invoiceId = invoiceId;
        this.price = price;
        this.status = status;
        this.note = note;
        this.form = form;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public WarrantyForm getForm() {
        return form;
    }

    public void setForm(WarrantyForm form) {
        this.form = form;
    }
}
