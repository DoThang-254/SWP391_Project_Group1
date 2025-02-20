/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
import java.util.Date;

public class Notification {

    private int notificationId;
    private Customer customer;
    private WarrantyRequirement requirement;
    private String title;
    private String message;
    private boolean isRead;
    private Date sendTime;

    public Notification() {
    }

    public Notification(int notificationId, Customer customer, WarrantyRequirement requirement, String title,
            String message, boolean isRead, Date sendTime) {
        this.notificationId = notificationId;
        this.customer = customer;
        this.requirement = requirement;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
        this.sendTime = sendTime;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WarrantyRequirement getRequirementId() {
        return requirement;
    }

    public void setRequirementId(WarrantyRequirement requirement) {
        this.requirement = requirement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
