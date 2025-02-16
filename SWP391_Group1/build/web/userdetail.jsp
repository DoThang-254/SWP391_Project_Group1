<%-- 
    Document   : userdetail.jsp
    Created on : Feb 13, 2025, 1:31:29 AM
    Author     : tuan7
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>titlfffe</h1>
        <c:set var="ud" value="${requestScope.detail}"></c:set>
            <div class="form-popup" id="myForm">
                <div class="container mt-4 mb-4 p-3 d-flex justify-content-center"> 
                    <div class="card p-4"> 
                        <div class=" image d-flex flex-column justify-content-center align-items-center"> 

                            <span class="name mt-3">Tên ${ud.email}</span> 
                        <span class="idd">${ud.email}</span> 
                        <div class="d-flex flex-row justify-content-center align-items-center gap-2"> 
                            <span class="idd1"></span>

                        </div>
                    </div> 
                    <div class=" d-flex mt-2"> 
                        <button class="btn1 btn-dark">Edit Profile</button> 
                    </div> 
                    <div class="text mt-3"> 
                        <span>Eleanor Pena is a creator of minimalistic x bold graphics and digital artwork.<br><br> Artist/ Creative Director by Day #NFT minting@ with FND night. 
                        </span>
                    </div>

                    <button type="button" class=" cancel" onclick="closeForm()">Close</button>
                </div> 
            </div>
        </div>
    </body>
</html>
