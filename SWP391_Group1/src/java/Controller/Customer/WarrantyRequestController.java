/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import Dal.ProductDao;
import Dal.WarrantyFormDao;
import Model.Customer;
import Model.Product;
import Model.WarrantyForm;
import Model.WarrantyRequirement;
import dao.WarrantyRequirementDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 *
 * @author thang
 */
public class WarrantyRequestController extends HttpServlet {

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
            out.println("<title>Servlet WarrantyRequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WarrantyRequestController at " + request.getContextPath() + "</h1>");
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
    private WarrantyRequirementDAO wrd = new WarrantyRequirementDAO();
    private ProductDao pd = new ProductDao();
    private WarrantyFormDao wfd = new WarrantyFormDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productid");
        String isPay = request.getParameter("ispay");
        Product product = pd.GetProductById(productId);
        request.setAttribute("product", product);
        request.setAttribute("ispay", isPay);
        request.setAttribute("productid", productId);
        request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
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
        Customer customer = (Customer) request.getSession().getAttribute("Customer");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String productId = request.getParameter("productId");
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        String isPay = request.getParameter("ispay");
        Product product = pd.GetProductById(productId);

        if (product == null) {
            request.setAttribute("errorMessage", "Sản phẩm không tồn tại.");
            request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
            return;
        }

        // Kiểm tra nếu đã có yêu cầu bảo hành đang chờ xử lý
        boolean hasPendingRequest = wrd.hasPendingRequest(productId);
//        WarrantyForm hasActive = wfd.hasActive(productId);
        boolean hasUnPay = wrd.hasUnPayRequest(productId);
        if (hasPendingRequest || hasUnPay || !wfd.hasActive(productId)) { //|| !hasActive.getStatus().equals("active")
            request.setAttribute("errorMessage", "Bạn đã gửi yêu cầu bảo hành cho sản phẩm này.");
            request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
            return;
        }

        // Tạo yêu cầu bảo hành mới
        WarrantyRequirement requestWarranty = new WarrantyRequirement();
        Product p = new Product();
        p.setProductId(productId);
        requestWarranty.setProduct(p);
        Customer c = new Customer();
        c.setCustomerId(customer.getCustomerId());
        requestWarranty.setCustomer(c);
        requestWarranty.setStatus(status);
        requestWarranty.setDescription(description);
        requestWarranty.setRegisterDate(new Date());
        requestWarranty.setIsPay(isPay);
        wrd.insertWarrantyRequirement(requestWarranty);

        request.setAttribute("successMessage", "Yêu cầu bảo hành đã được gửi thành công!");
        request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
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
