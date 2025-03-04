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
        Customer c = (Customer) request.getSession().getAttribute("Customer");

        WarrantyProcessDao wpd = new WarrantyProcessDao();
        List<WarrantyProcessing> list = wpd.processListByCustomerId(c.getCustomerId());
        request.setAttribute("list", list);
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
        String action = request.getParameter("customerAction");
        System.out.println("action = " + action);
        int processId = Integer.parseInt(request.getParameter("processingId"));
        int requirementId = Integer.parseInt(request.getParameter("requirementId"));

        if (action.equalsIgnoreCase("accept")) {
            wpd.updateIsAcceptWarrantyProcess(processId, 1);
        }
        if (action.equals("reject")) {
            wpd.updateIsAcceptWarrantyProcess(processId, 0);
            wrd.UpdateStatusRequest("Rejected", requirementId);
        }
        response.sendRedirect("customerprocess");
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
