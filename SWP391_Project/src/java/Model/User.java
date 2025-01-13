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
    private String FullName;
    private String Email;
    private String Phone;
    private String Position;

    public User(int UserID, String FullName, String Email, String Phone, String Position) {
        this.Email = Email;
        this.FullName=FullName;
        this.Phone=Phone;
        this.Position=Position;
        
    }
    

    public User(int userId, String userName, String PasswordHash, int role) {
        this.userId = userId;
        this.userName = userName;
        this.PasswordHash = PasswordHash;
        this.role = role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
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
