/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
public class TokenForgetPassword {

    private int id, customerId;
    private boolean isUsed;
    private String token, staffId;
    private LocalDateTime expiryTime;

    public TokenForgetPassword() {
    }

    public TokenForgetPassword(int id, int customerId , String staffId, boolean isUsed, String token, LocalDateTime expiryTime) {
        this.id = id;
        this.customerId = customerId;
        this.staffId = staffId;
        this.isUsed = isUsed;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public TokenForgetPassword(int customerId, boolean isUsed, String token, LocalDateTime expiryTime) {
        this.customerId = customerId;
        this.isUsed = isUsed;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public TokenForgetPassword(String staffId, boolean isUsed, String token, LocalDateTime expiryTime) {
        this.staffId = staffId;
        this.isUsed = isUsed;
        this.token = token;
        this.expiryTime = expiryTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
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
