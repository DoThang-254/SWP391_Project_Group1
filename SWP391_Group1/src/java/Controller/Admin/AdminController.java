/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Dal.AdminDAO;
import Model.Staff;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author khang
 */
@WebServlet(name = "AdminController", urlPatterns = {"/adminlist"})
public class AdminController extends HttpServlet {

    private AdminDAO adminDAO;

    public void init() {
        adminDAO = new AdminDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            String staffId = request.getParameter("staffId");
            Staff admin = adminDAO.getAdminById(staffId);
            request.setAttribute("admin", admin);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editAdmin.jsp");
            dispatcher.forward(request, response);
        } else if ("delete".equals(action)) {
            String staffId = request.getParameter("staffId");
            adminDAO.deleteAdmin(staffId);
            response.sendRedirect("adminlist");
        } else {
            String searchQuery = request.getParameter("search");
            int page = 1;
            int recordsPerPage = 10;

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int totalRecords = adminDAO.getTotalAdmins();
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            int startRecord = (page - 1) * recordsPerPage + 1;
            int endRecord = Math.min(page * recordsPerPage, totalRecords);

            List<Staff> admins;
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                admins = adminDAO.getListOfUser(searchQuery, 3);  // Search for admins based on the query
            } else {
                admins = adminDAO.getAdminsByPage(page, recordsPerPage);  // Fetch all admins with pagination
            }

            request.setAttribute("admins", admins);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("totalAdmins", totalRecords);
            request.setAttribute("startRecord", startRecord);
            request.setAttribute("endRecord", endRecord);

            RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
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
        int roleId = 3; // RoleId của Admin

        String staffIdRegex = "^S\\d{3}$";
        if (!staffId.matches(staffIdRegex)) {
            request.setAttribute("error", "Staff ID không hợp lệ! Phải có dạng SXXX (ví dụ: S001, S002).");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addAdmin.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            request.setAttribute("error", "Email không hợp lệ! Vui lòng nhập đúng định dạng.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addAdmin.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String phoneRegex = "^\\d{10}$";
        if (!phone.matches(phoneRegex)) {
            request.setAttribute("error", "Số điện thoại phải có đúng 10 chữ số!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("addAdmin.jsp");
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
                    RequestDispatcher dispatcher = request.getRequestDispatcher("addAdmin.jsp");
                    dispatcher.forward(request, response);
                    return;
                }

                birthDate = Date.valueOf(birthDateLocal);
            } catch (DateTimeParseException e) {
                request.setAttribute("error", "Ngày sinh không hợp lệ! Vui lòng nhập đúng định dạng yyyy-MM-dd.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("addAdmin.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        Staff admin = new Staff();
        admin.setStaffId(staffId);
        admin.setUsername(username);
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);
        admin.setPhone(phone);
        admin.setGender(gender);
        admin.setBirthDate(birthDate);
        admin.setStatus(status);
        admin.setRoleId(roleId);

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            boolean added = adminDAO.addAdmin(admin);
            if (!added) {
                request.setAttribute("error", "Staff ID đã tồn tại! Vui lòng nhập lại.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("addAdmin.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } else if ("update".equals(action)) {
            adminDAO.updateAdmin(admin);
        }

        response.sendRedirect("adminlist");
    }
}
