/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import Dal.WarrantyProcessDao;
import Model.Customer;
import Model.WarrantyProcessing;
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
public class CustomerProcess extends HttpServlet {

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
            out.println("<title>Servlet CustomerProcess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerProcess at " + request.getContextPath() + "</h1>");
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
        String amountRaw = request.getParameter("amount");
        if (amountRaw == null || amountRaw.trim().isEmpty()) {
            amountRaw = "10";
        }

        int amount = Integer.parseInt(amountRaw);
        Customer c = (Customer) request.getSession().getAttribute("Customer");
        String input = request.getParameter("index");
        if (input == null || input.isBlank()) {
            input = "1";
        }
        int index = Integer.parseInt(input);

        WarrantyProcessDao wpd = new WarrantyProcessDao();
        List<WarrantyProcessing> list = wpd.processListByCustomerId(index, c.getCustomerId(), amount);

        int count = wpd.GetTotalWarrantyProcess(c.getCustomerId());
        int endPage = count / amount;
        if (count % amount != 0) {
            endPage++;
        }
        request.setAttribute("list", list);
        request.setAttribute("tag", index);
        request.setAttribute("endpage", endPage);
        request.setAttribute("amount", amount);

        request.getRequestDispatcher("CustomerProcess.jsp").forward(request, response);
    }
    WarrantyProcessDao wpd = new WarrantyProcessDao();
    WarrantyRequirementDAO wrd = new WarrantyRequirementDAO();

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
