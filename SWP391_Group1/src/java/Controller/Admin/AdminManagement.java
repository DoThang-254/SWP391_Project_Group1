/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Dal.*;
import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author tuand
 */
public class AdminManagement extends HttpServlet {

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
            out.println("<title>Servlet AdminManagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManagement at " + request.getContextPath() + "</h1>");
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
        String detailId = request.getParameter("getdetailId");
        if (detailId != null) {
            AdminDAO adminDAO = new AdminDAO();
            Staff staffDetail = adminDAO.userDetail(detailId);
            request.setAttribute("detail", staffDetail);
            request.getRequestDispatcher("/detail2.jsp").forward(request, response);
            return;
        }

        String searchKeyword = request.getParameter("searchKeyword");
        String roleIdParam = request.getParameter("roleId");
        Integer roleId = null;
        if (roleIdParam != null && !roleIdParam.isEmpty()) {
            roleId = Integer.parseInt(roleIdParam);
        }
        AdminDAO adminDAO = new AdminDAO();
        List<Staff> staffList = adminDAO.getListOfUser(searchKeyword, roleId);
        int page, numberpage = 100;
        int size = staffList.size();
        int num = (size % 5 == 0 ? (size / 5) : (size / 5) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numberpage;
        end = Math.min(page * numberpage, size);
        List<Staff> list = adminDAO.getListByPage(staffList, start, end);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("staffList", list);
        request.getRequestDispatcher("/Admin_decentralization.jsp").forward(request, response);
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
        String staffId = request.getParameter("staffId");
        String status = request.getParameter("status");
        if (staffId != null && status != null) {
            AdminDAO adminDAO = new AdminDAO();
            String newStatus = status.equals("Active") ? "Inactive" : "Active";
            adminDAO.deactiveStaves(staffId, newStatus);
        }

        String searchKeyword = request.getParameter("searchKeyword");
        String roleIdParam = request.getParameter("roleId");
        String xpage = request.getParameter("page");

        if (xpage == null) {
            xpage = "1";
        }
        if (roleIdParam == null) {
            roleIdParam = "";
        }
        if (searchKeyword == null) {
            searchKeyword = "";
        }
        response.sendRedirect("/SWP391_Group1/admin-management?roleId=" + roleIdParam + "&searchKeyword=" + searchKeyword + "&page=" + xpage);
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
