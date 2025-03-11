/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Technician;

import Dal.WarrantyFormDao;
import Model.WarrantyForm;
import dao.WarrantyRequirementDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thang
 */
public class CreateForm extends HttpServlet {

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
            out.println("<title>Servlet CreateForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateForm at " + request.getContextPath() + "</h1>");
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
    WarrantyFormDao wfd = new WarrantyFormDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productid");
        int requireId = Integer.parseInt(request.getParameter("requireId"));
        if (productId == null || productId.trim().isEmpty()) {

        }

        if (!wfd.hasFormId(requireId)) {
            wfd.createWarrantyForm(productId);
            WarrantyForm newWf = wfd.getWarrantyFormbyProductId(productId);
            wrd.UpdateFormId(newWf.getFormId(), requireId);
            request.setAttribute("form", newWf);
            request.getRequestDispatcher("CreateForm.jsp").forward(request, response);
            return;
        }
        request.setAttribute("msg", "You created Form for this request.");
        request.getRequestDispatcher("CreateForm.jsp").forward(request, response);

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

//        String faultType = request.getParameter("faultType");
//        int formId = Integer.parseInt(request.getParameter("formId"));
//        WarrantyFormDao wfd = new WarrantyFormDao();
//        wfd.updateForm(faultType, null, formId);
//        response.sendRedirect("technicianrequest");
        String formId = request.getParameter("formId");
        String endDate = request.getParameter("endDate");
        String faultType = request.getParameter("faultType");
        String img = request.getParameter("img");
        WarrantyForm wf = new WarrantyForm();
        wf.setFormId(Integer.parseInt(formId));
        if (endDate != null && !endDate.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date newEndDate = sdf.parse(endDate);
                wf.setEndDate(newEndDate); // Gán ngày vào đối tượng WarrantyForm
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        wf.setFaultType(faultType);
        wf.setImgUrl(img);
        wfd.UpdateFullFormId(wf);
        response.sendRedirect("updateform?formId=" + formId);
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
