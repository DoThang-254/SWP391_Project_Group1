/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;


import Dal.TechnicianDAO;
import Model.Staff;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date; 


public class TechnicianController extends HttpServlet {
    private TechnicianDAO technicianDAO;

    public void init() {
        technicianDAO = new TechnicianDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    
    if ("edit".equals(action)) {
        String staffId = request.getParameter("staffId");
        Staff technician = technicianDAO.getTechnicianById(staffId);
        request.setAttribute("technician", technician);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editTechnician.jsp");
        dispatcher.forward(request, response);
    } else if ("delete".equals(action)) {
        String staffId = request.getParameter("staffId");
        technicianDAO.deleteTechnician(staffId);
        response.sendRedirect("technicianlist");
    } else {
        String searchQuery = request.getParameter("search");
        List<Staff> technicians;
        
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            technicians = technicianDAO.searchTechnicianByName(searchQuery);
        } else {
            technicians = technicianDAO.getTechniciansByRole(2);
        }

        request.setAttribute("technicians", technicians);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ServiceManager.jsp");
        dispatcher.forward(request, response);
    }
}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String staffId = request.getParameter("staffId");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");

        String birthDateStr = request.getParameter("birthDate");
        String status = request.getParameter("status");
        int roleId = 2; 

        Date birthDate = null; 
        if (birthDateStr != null && !birthDateStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(birthDateStr);

                birthDate = new java.sql.Date(utilDate.getTime()); 
            } catch (ParseException e) {
                request.setAttribute("error", "Invalid birth date format. Please use yyyy-MM-dd.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("addTechnician.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        Staff s = new Staff();
        s.setStaffId(staffId);
        s.setUsername(username);
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setEmail(email);
        s.setPhone(phone);
        s.setGender(gender);
        s.setBirthDate(birthDate); 
        s.setStatus(status);
        s.setRoleId(roleId);

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            technicianDAO.addTechnician(s);
        } else if ("update".equals(action)) {
            technicianDAO.updateTechnician(s);
        }

        response.sendRedirect("technicianlist");
    }
}