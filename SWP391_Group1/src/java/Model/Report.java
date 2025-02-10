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
    private WarrantyInformation information;

    public Report() {
    }

    public Report(int reportId, String comment, Customer customer, WarrantyInformation information) {
        this.reportId = reportId;
        this.comment = comment;
        this.customer = customer;
        this.information = information;
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

    public WarrantyInformation getInformation() {
        return information;
    }

    public void setInformation(WarrantyInformation information) {
        this.information = information;
    }
}
