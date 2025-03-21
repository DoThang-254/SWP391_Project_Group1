/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Technician;

import Dal.InvoiceDao;
import Dal.WarrantyFormDao;
import Dal.WarrantyProcessDao;
import Model.Customer;
import Model.Staff;
import Model.WarrantyForm;
import Model.WarrantyProcessing;
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
public class TaskController extends HttpServlet {

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
            out.println("<title>Servlet TaskController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TaskController at " + request.getContextPath() + "</h1>");
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
        Staff s = (Staff) request.getSession().getAttribute("Staff");
        WarrantyProcessDao wpd = new WarrantyProcessDao();
        List<WarrantyProcessing> list = wpd.getAllWarrantyProcess(s.getStaffId());
        request.setAttribute("list", list);
        request.getRequestDispatcher("Task.jsp").forward(request, response);
    }
    WarrantyFormDao wfd = new WarrantyFormDao();

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
        String status = request.getParameter("status");
        int processId = Integer.parseInt(request.getParameter("processingId"));
        int requirementId = Integer.parseInt(request.getParameter("requirementId"));
        String productId = request.getParameter("productId");
        WarrantyProcessDao wpd = new WarrantyProcessDao();
        wpd.updateStatusWarrantyProcess(processId, status);
        //check xem ispay của requirement 1 trong process 1 có phải là yes ko nếu yes thì tạo hóa đơn
        boolean checkIsPay = wpd.checkIsPayinRequirement(requirementId, processId);
        boolean checkFaultType = wpd.checkFaultTypeInRequirement(productId, requirementId, processId);
        if ((status.equals("Completed") && checkIsPay)
                || (status.equals("Completed") && !checkIsPay && checkFaultType)) {
            WarrantyForm updateForm = wfd.getWarrantyFormByRequirementId(productId, requirementId);
//            wfd.updateStatus(updateForm);
//            wfd.markAsCompleted(requirementId);
        } else if (status.equals("Completed") && !checkIsPay && !checkFaultType) {
            WarrantyForm updateForm = wfd.getWarrantyFormByRequirementId(productId, requirementId);
            wfd.updateStatus(updateForm);
            wfd.markAsCompleted(requirementId);
        }

        response.sendRedirect("task");
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
