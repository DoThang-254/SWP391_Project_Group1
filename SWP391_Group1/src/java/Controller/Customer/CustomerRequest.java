/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import Model.Customer;
import Model.WarrantyRequirement;
import dao.WarrantyRequirementDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author thang
 */
public class CustomerRequest extends HttpServlet {

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
            out.println("<title>Servlet CustomerRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerRequest at " + request.getContextPath() + "</h1>");
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
    WarrantyRequirementDAO wrd = new WarrantyRequirementDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer c = (Customer) request.getSession().getAttribute("Customer");
        String input = request.getParameter("index");
        String amount = request.getParameter("amount");
        
        if (input == null || input.isEmpty()) {
            input = "1";
        }
        
        if (amount == null || amount.isEmpty()) {
            amount = "4";
        }
        int x = Integer.parseInt(amount);
        int index = Integer.parseInt(input);
        String faultType = request.getParameter("faulttype");
        String search = request.getParameter("table_search");
        int count = wrd.GetTotalWarrantyRequest(c.getCustomerId() , faultType , search);
        int endPage = count / x;
        if (endPage % x != 0) {
            endPage++;
        }
        List<WarrantyRequirement> list = wrd.GetAllRequestByCustomerId(index, c.getCustomerId(), x , faultType , search);
        request.setAttribute("list", list);
        request.setAttribute("tag", index);
        request.setAttribute("endpage", endPage);
        request.setAttribute("amount", x);
        request.setAttribute("faulttype", faultType);
        request.setAttribute("save", search);
        request.getRequestDispatcher("CustomerRequirement.jsp").forward(request, response);
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
        processRequest(request, response);
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
