/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author thang
 */
public class User {
    private int userId ;
    private String userName ;
    private String PasswordHash ;
    private int role ;

    public User() {
    }

    public User(int userId, String userName, String PasswordHash, int role) {
        this.userId = userId;
        this.userName = userName;
        this.PasswordHash = PasswordHash;
        this.role = role;
    }

     public User(String userName, String PasswordHash, int role) { // login
        this.userName = userName;
        this.PasswordHash = PasswordHash;
        this.role = role;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String PasswordHash) {
        this.PasswordHash = PasswordHash;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
