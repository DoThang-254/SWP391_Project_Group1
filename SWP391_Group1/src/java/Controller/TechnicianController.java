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
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        int page = 1;
        int recordsPerPage = 10; 

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int totalRecords = technicianDAO.getTotalTechnicians();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        int startRecord = (page - 1) * recordsPerPage + 1;
        int endRecord = Math.min(page * recordsPerPage, totalRecords);

        List<Staff> technicians;
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            technicians = technicianDAO.searchTechnicianByName(searchQuery);
        } else {
            technicians = technicianDAO.getTechniciansByPage(page, recordsPerPage);
        }

        request.setAttribute("technicians", technicians);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalTechnicians", totalRecords);
        request.setAttribute("startRecord", startRecord); 
        request.setAttribute("endRecord", endRecord); 

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

    String staffIdRegex = "^S\\d{3}$";
    if (!staffId.matches(staffIdRegex)) {
        request.setAttribute("error", "Staff ID không hợp lệ! Phải có dạng SXXX (ví dụ: S001, S002).");
        RequestDispatcher dispatcher = request.getRequestDispatcher("addTechnician.jsp");
        dispatcher.forward(request, response);
        return;
    }

    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    if (!email.matches(emailRegex)) {
        request.setAttribute("error", "Email không hợp lệ! Vui lòng nhập đúng định dạng.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("addTechnician.jsp");
        dispatcher.forward(request, response);
        return;
    }

    String phoneRegex = "^\\d{10}$";
    if (!phone.matches(phoneRegex)) {
        request.setAttribute("error", "Số điện thoại phải có đúng 10 chữ số!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("addTechnician.jsp");
        dispatcher.forward(request, response);
        return;
    }

    Date birthDate = null;
    if (birthDateStr != null && !birthDateStr.isEmpty()) {
    try {
        LocalDate birthDateLocal = LocalDate.parse(birthDateStr);
        LocalDate today = LocalDate.now();
        
        if (!birthDateLocal.isBefore(today)) {
            request.setAttribute("error", "Ngày sinh không hợp lệ! Phải trước ngày hôm nay.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addTechnician.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        birthDate = Date.valueOf(birthDateLocal);
    } catch (DateTimeParseException e) {
        request.setAttribute("error", "Ngày sinh không hợp lệ! Vui lòng nhập đúng định dạng yyyy-MM-dd.");
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
        boolean added = technicianDAO.addTechnician(s);
        if (!added) {
            request.setAttribute("error", "Staff ID đã tồn tại! Vui lòng nhập lại.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addTechnician.jsp");
            dispatcher.forward(request, response);
            return;
        }
    } else if ("update".equals(action)) {
        technicianDAO.updateTechnician(s);
    }

    response.sendRedirect("technicianlist");
}
 
}
