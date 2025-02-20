/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
public class Report {
    private int reportId;
    private String comment;
    private Customer customer;
    private WarrantyProcessing processing;

    public Report() {
    }

    public Report(int reportId, String comment, Customer customer, WarrantyProcessing processing) {
        this.reportId = reportId;
        this.comment = comment;
        this.customer = customer;
        this.processing = processing;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WarrantyProcessing getProcessing() {
        return processing;
    }

    public void setProcessing(WarrantyProcessing processing) {
        this.processing = processing;
    }

    
}
