/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import Dal.CustomerDao;
import Dal.WarrantyFormDao;
import Model.Customer;
import Model.WarrantyForm;
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
public class WarrantyFormDetail extends HttpServlet {

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
            out.println("<title>Servlet WarrantyFormDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WarrantyFormDetail at " + request.getContextPath() + "</h1>");
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
    private CustomerDao cd = new CustomerDao();
    private WarrantyFormDao wfd = new WarrantyFormDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");
        if (customerIdParam == null || customerIdParam.isEmpty()) {
            Customer c = (Customer) request.getSession().getAttribute("Customer");
            if (c == null) {
                response.sendRedirect("404.jsp"); // Yêu cầu đăng nhập nếu không có session
                return;
            }
            customerIdParam = String.valueOf(c.getCustomerId());
        }

        int customerId = Integer.parseInt(customerIdParam);
        String input = request.getParameter("index");
        String amount_raw = request.getParameter("amount");
        String productId = request.getParameter("productid");

        if (input == null || input.trim().isEmpty()) {
            input = "1";
        }

        if (amount_raw == null || amount_raw.trim().isEmpty()) {
            amount_raw = "5";
        }
        int index = Integer.parseInt(input);
        int amount = Integer.parseInt(amount_raw);
        int count = cd.GetTotalProductDetail(customerId, productId);

        int endPage = count / amount;
        if (count % amount != 0) {
            endPage++;
        }

        List<WarrantyForm> wf = cd.ProductDetail(index, customerId, productId, amount);

        Customer customer = cd.GetCustomer(customerId); 
        if (customer != null) {
            request.setAttribute("customerUsername", customer.getUsername());

        }

        request.setAttribute("form", wf);
        request.setAttribute("tag", index);
        request.setAttribute("endpage", endPage);
        request.setAttribute("productid", productId);
        request.getRequestDispatcher("WarrantyForm.jsp").forward(request, response);

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
