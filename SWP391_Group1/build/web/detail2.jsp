<%-- 
    Document   : detail2
    Created on : Feb 17, 2025, 11:53:02 PM
    Author     : tuan7
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container mt-4 mb-4 p-3 d-flex justify-content-center">
    <div class="card p-4">
        <div class="image d-flex flex-column justify-content-center align-items-left">
            <span class="name mt-3">Name: ${detail.firstName} ${detail.lastName} - ID${detail.staffId}</span>
            <span class="idd">Nickname: ${detail.username}</span>
            <span class="idd">Email: ${detail.email}</span>
            <span class="idd">Phone: ${detail.phone}</span>
            <span class="idd">Gender: ${detail.gender}</span>
            <span class="idd">Dob: ${detail.birthDate}</span>
            <span class="idd">Status: ${detail.status}</span>
        </div>
        <div class="d-flex mt-2">
            <button class="btn1 btn-dark">Edit Profile</button>
        </div>
        <div class="text mt-3">
            <span>This is information detail about ${detail.role.roleName}.</span>
        </div>
    </div>
</div>