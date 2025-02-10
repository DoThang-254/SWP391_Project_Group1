/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.Dao;
import Model.Customer;
import Validation.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class RegisterController extends HttpServlet {

    Dao d = new Dao();
    Validation v = new Validation();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName").trim().replaceAll("\\s+", "");
        String password = request.getParameter("passWord").trim().replaceAll("\\s+", "");
        String cPassword = request.getParameter("confirmPassword").trim();
        String firstName = request.getParameter("firstName").trim().replaceAll("\\s+", "");
        String lastName = request.getParameter("lastName").trim().replaceAll("\\s+", "");
        String email = request.getParameter("email").trim().replaceAll("\\s+", "");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("birthdate");
        String phone = request.getParameter("phone").trim().replaceAll("\\s+", "");
        String address = request.getParameter("address").trim().replaceAll("\\s+", "");
        String status = request.getParameter("status");

        List<String> msg = new ArrayList<>();
        if (!v.checkMatching(password, cPassword)) {
            msg.add("password and confirm password are not matching");
        }
        if (d.checkAccountExisted(userName)) {
            msg.add(" , username is existed");

        } else {
            if (v.checkHashOfPassword(password)) {
                if (!v.isValidVietnamesePhoneNumber(phone)) {
                    msg.add(" , phone number is invalid");
                }
                if (d.checkPhoneExisted(phone) && d.checkPhoneExistedInStaff(phone)) {
                    msg.add(" , phone number is existed");
                }
                if (d.checkEmailExisted(email) && d.checkEmailExistedInStaff(email)) {
                    msg.add(" , email is existed");
                }
                if (!v.isValidEmail(email)) {
                    msg.add(" , email is invalid");
                }
                if (!d.checkEmailExisted(email) && !d.checkPhoneExisted(phone) && !d.checkPhoneExistedInStaff(phone)
                        && !d.checkEmailExistedInStaff(email) && v.isValidEmail(email) && v.isValidVietnamesePhoneNumber(phone)) {
                    String hashPassword = v.encode(password);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(dob);
                    Customer newCustomer = new Customer(userName, hashPassword, firstName, lastName, phone, email, gender, sqlDate, status, address);

                    d.RegisterCustomer(newCustomer);
                    request.setAttribute("msg2", "Sign up successfully");
                }

            } else {
                msg.add(" , the password is weak");

            }

        }

        request.setAttribute("msg", msg);
        request.setAttribute("userName", userName);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("gender", gender);
        request.setAttribute("dob", dob);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.getRequestDispatcher("Register.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
