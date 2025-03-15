/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Technician;

import Model.Staff;
import Model.WarrantyRequirement;
import dao.WarrantyRequirementDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public class TechnicianRequestManagementController extends HttpServlet {

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
            out.println("<title>Servlet TechnicianRequestManagementController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TechnicianRequestManagementController at " + request.getContextPath() + "</h1>");
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

//        WarrantyRequirementDAO wrd = new WarrantyRequirementDAO();
//        List<WarrantyRequirement> list = wrd.GetAllRequestByStaffId(s.getStaffId());
//        request.setAttribute("list", list);
        WarrantyRequirementDAO wrd = new WarrantyRequirementDAO();
        List<WarrantyRequirement> listA = wrd.GetAllRequestByStaffId(s.getStaffId());
        List<WarrantyRequirement> listB = wrd.GetAllRequestByStaffIdWithout(s.getStaffId());

        Map<Integer, WarrantyRequirement> map = new HashMap<>();

        for (WarrantyRequirement r : listB) {
            r.setCategory("B");
            map.put(r.getRequirementId(), r);
        }

        for (WarrantyRequirement r : listA) {
            if (map.containsKey(r.getRequirementId())) {
                WarrantyRequirement existing = map.get(r.getRequirementId());
                existing.setForm(r.getForm()); // Gán thông tin form
            } else {
                r.setCategory("A");
                map.put(r.getRequirementId(), r);
            }
        }

        List<WarrantyRequirement> mergedList = new ArrayList<>(map.values());

        request.setAttribute("list", mergedList);
        request.getRequestDispatcher("TechnicianRequestManagement.jsp").forward(request, response);

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
