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

public class Blog {
    private int blogId;
    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private Date createdAt;
    private Staff staff;

    public Blog() {}

    public Blog(int blogId, String title, String description, String content, String imageUrl, Date createdAt, Staff staff) {
        this.blogId = blogId;
        this.title = title;
        this.description = description;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.staff = staff;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}


