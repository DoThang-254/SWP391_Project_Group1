/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.common;

import Dal.Dao;
import Model.Customer;
import Model.Staff;
import Validation.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author thang
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Login.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Dao d = new Dao();
    Validation v = new Validation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String rememberMe = request.getParameter("rememberMe");
        String hashPassword = v.encode(passWord);
        Staff s = d.StaffLogin(userName, hashPassword);
        Customer c = d.Login(userName, hashPassword);
        if (c != null || s != null) {
            if (rememberMe != null) {
                Cookie userNameCookie = new Cookie("username", userName);
                userNameCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(userNameCookie);
                Cookie passWordCookie = new Cookie("password", passWord);
                passWordCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(passWordCookie);
            } else {
                Cookie userNameCookie = new Cookie("username", "");
                userNameCookie.setMaxAge(0);
                response.addCookie(userNameCookie);
                Cookie passWordCookie = new Cookie("password", "");
                passWordCookie.setMaxAge(0);
                response.addCookie(passWordCookie);
            }

            if (s != null) {
                request.getSession().setAttribute("Staff", s);
                if (s.getRole().getRoleId() == 1) {
                    response.sendRedirect("Admin.jsp");

                }
                if (s.getRole().getRoleId() == 2) {
                    response.sendRedirect("Technician.jsp");

                }
                if (s.getRole().getRoleId() == 3) {
                    response.sendRedirect("ServiceManager.jsp");

                }
                if (s.getRole().getRoleId() == 4) {
                    response.sendRedirect("Cashier.jsp");
                }
            } else {
                request.getSession().setAttribute("Customer", c);
                response.sendRedirect("CustomerHomePage.jsp");

            }
        } else {
            request.setAttribute("userName", userName);
            request.setAttribute("password", passWord);
            request.setAttribute("msg", "this account is not existed");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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
