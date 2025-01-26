/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
public class WarrantyRequirement {
    private int requirementId;
    private String status;
    private String description;
    private java.util.Date registerDate;
    private int customerId;
    private String staffId;

    public WarrantyRequirement() {
    }

    public WarrantyRequirement(int requirementId, String status, String description, java.util.Date registerDate,
                       int customerId, String staffId) {
        this.requirementId = requirementId;
        this.status = status;
        this.description = description;
        this.registerDate = registerDate;
        this.customerId = customerId;
        this.staffId = staffId;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.util.Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(java.util.Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}

