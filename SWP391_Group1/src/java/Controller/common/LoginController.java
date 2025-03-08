package Controller.common;

import Dal.Dao;
import Dal.GoogleAccountDao;
import Model.Customer;
import Model.GoogleAccount;
import Model.Staff;
import Validation.Validation;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {

    private final GoogleAccountDao gg = new GoogleAccountDao();
    private final Dao d = new Dao();
    private final Validation v = new Validation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String rememberMe = request.getParameter("rememberMe");
        String code = request.getParameter("code");
        String error = request.getParameter("error");

        if (error != null) {
            response.sendRedirect("login");

            return;
        }

        if ((userName == null || passWord == null) && code == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Customer c = null;
        Staff s = null;

        if (code != null) {
            String accessToken = gg.getToken(code);
            GoogleAccount googleAccount = gg.getUserInfo(accessToken);
            String email = googleAccount.getEmail();
            c = d.LoginByEmail(email);
            s = d.StaffLoginByEmail(email);

            if (c != null || s != null) {
                if (s != null) {
                    request.getSession().setAttribute("Staff", s);
                    switch (s.getRole().getRoleId()) {
                        case 3 -> response.sendRedirect("Admin.jsp");
                        case 1 -> response.sendRedirect("Technician.jsp");
                        case 2 -> response.sendRedirect("ServiceManager.jsp");
                        case 4 -> response.sendRedirect("Cashier.jsp");
                        default -> response.sendRedirect("Login.jsp");
                    }
                } else {
                    request.getSession().setAttribute("Customer", c);
                    response.sendRedirect("CustomerHomePage.jsp");
                }
            } else {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } else {
            String hashPassword = v.encode(passWord);
            s = d.StaffLogin(userName, hashPassword);
            c = d.Login(userName, hashPassword);

            if (c != null || s != null) {
                if ("on".equals(rememberMe)) {
                    Cookie userNameCookie = new Cookie("username", userName);
                    userNameCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(userNameCookie);
                    
                    Cookie passWordCookie = new Cookie("password", passWord);
                    passWordCookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(passWordCookie);
                } else {
                    Cookie userNameCookie = new Cookie("username", "");
                    userNameCookie.setMaxAge(0);
                    response.addCookie(userNameCookie);
                    
                    Cookie passWordCookie = new Cookie("password", "");
                    passWordCookie.setMaxAge(0);
                    response.addCookie(passWordCookie);
                }

                if (s != null) {
                    request.getSession().setAttribute("Staff", s);
                    switch (s.getRole().getRoleId()) {
                        case 3 -> response.sendRedirect("Admin.jsp");
                        case 1 -> response.sendRedirect("Technician.jsp");
                        case 2 -> response.sendRedirect("ServiceManager.jsp");
                        case 4 -> response.sendRedirect("Cashier.jsp");
                        default -> response.sendRedirect("Login.jsp");
                    }
                } else {
                    request.getSession().setAttribute("Customer", c);
                    response.sendRedirect("CustomerHomePage.jsp");
                }
            } else {
                request.setAttribute("userName", userName);
                request.setAttribute("password", passWord);
                request.setAttribute("msg", "This account does not exist");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiện tại, doPost chưa được sử dụng
    }

    @Override
    public String getServletInfo() {
        return "Login Controller Servlet";
    }
}
