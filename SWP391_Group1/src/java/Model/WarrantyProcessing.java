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
public class WarrantyProcessing {
    private int processingId;
    private WarrantyRequirement requirement;
    private String status;
    private String note;
    private Date returnDate;
    private Staff staff;
    private boolean isAccept;

    public WarrantyProcessing() {
    }

    public WarrantyProcessing(int processingId, WarrantyRequirement requirement, String status, String note, Date returnDate, Staff staff) {
        this.processingId = processingId;
        this.requirement = requirement;
        this.status = status;
        this.note = note;
        this.returnDate = returnDate;
        this.staff = staff;
    }

    public int getProcessingId() {
        return processingId;
    }

    public void setProcessingId(int processingId) {
        this.processingId = processingId;
    }

    public WarrantyRequirement getRequirement() {
        return requirement;
    }

    public void setRequirement(WarrantyRequirement requirement) {
        this.requirement = requirement;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public boolean isIsAccept() {
        return isAccept;
    }

    public void setIsAccept(boolean isAccept) {
        this.isAccept = isAccept;
    }

    
    
}

