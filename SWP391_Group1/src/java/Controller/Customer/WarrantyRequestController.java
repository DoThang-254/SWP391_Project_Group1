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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
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
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        Customer customer = (Customer) request.getSession().getAttribute("Customer");
//        if (customer == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        String productId = request.getParameter("productId");
//        String status = request.getParameter("status");
//        String description = request.getParameter("description");
//        String isPay = request.getParameter("ispay");
//        Product product = pd.GetProductById(productId);
//
//        if (product == null) {
//            request.setAttribute("errorMessage", "Sản phẩm không tồn tại.");
//            request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
//            return;
//        }
//        boolean hasPendingRequest = wrd.hasPendingRequest(productId);
//        boolean hasUnPay = wrd.hasUnPayRequest(productId);
//        if (!hasPendingRequest && !hasUnPay && wfd.hasActive(productId)) {
//
//            // Tạo yêu cầu bảo hành mới
//            WarrantyRequirement requestWarranty = new WarrantyRequirement();
//            Product p = new Product();
//            p.setProductId(productId);
//            requestWarranty.setProduct(p);
//            Customer c = new Customer();
//            c.setCustomerId(customer.getCustomerId());
//            requestWarranty.setCustomer(c);
//            requestWarranty.setStatus(status);
//            requestWarranty.setDescription(description);
//            requestWarranty.setRegisterDate(new Date());
//            requestWarranty.setIsPay(isPay);
//            wrd.insertWarrantyRequirement(requestWarranty);
//
//            request.setAttribute("successMessage", "Yêu cầu bảo hành đã được gửi thành công!");
//            request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
//            return;
//        }
//        // Kiểm tra nếu đã có yêu cầu bảo hành đang chờ xử lý
//
//        if (hasPendingRequest || hasUnPay || !wfd.hasActive(productId)) { //|| !hasActive.getStatus().equals("active")
//            request.setAttribute("errorMessage", "Bạn đã gửi yêu cầu bảo hành cho sản phẩm này.");
//            request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
//            return;
//        }
//
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("Customer");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Đọc các tham số từ request
        String productId = request.getParameter("productId");
        String status = request.getParameter("status");
        String description = request.getParameter("description");
        String isPay = request.getParameter("ispay");

        // Kiểm tra file upload
        Part filePart = request.getPart("image"); // Lấy file từ input name="image"

        // Đường dẫn ảnh để hiển thị lại trên JSP
        // Tiếp tục xử lý lưu dữ liệu bảo hành
        Product product = pd.GetProductById(productId);
        if (product == null) {
            request.setAttribute("errorMessage", "Sản phẩm không tồn tại.");
            request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
            return;
        }

        // Kiểm tra trạng thái yêu cầu bảo hành
        boolean hasPendingRequest = wrd.hasPendingRequest(productId);
        boolean hasUnPay = wrd.hasUnPayRequest(productId);
        if (!hasPendingRequest && !hasUnPay && wfd.hasActive(productId)) {
            WarrantyRequirement requestWarranty = new WarrantyRequirement();
            requestWarranty.setProduct(product);
            requestWarranty.setCustomer(customer);
            requestWarranty.setStatus(status);
            requestWarranty.setDescription(description);
            requestWarranty.setRegisterDate(new Date());
            requestWarranty.setIsPay(isPay);
            String fileName = filePart.getSubmittedFileName(); // Lấy tên file gốc

            // Đường dẫn thư mục lưu ảnh (thay đổi tùy theo server của bạn)
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Tạo thư mục nếu chưa có
            }

            // Đường dẫn lưu file
            String filePath = uploadPath + File.separator + fileName;
            try (InputStream input = filePart.getInputStream(); FileOutputStream output = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
            String imagePath = "uploads/" + fileName;
            request.setAttribute("imagePath", imagePath);
            requestWarranty.setImg(imagePath); // Lưu đường dẫn ảnh vào DB (nếu có cột này)

            wrd.insertWarrantyRequirement(requestWarranty);

            request.setAttribute("successMessage", "Yêu cầu bảo hành đã được gửi thành công!");
            request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
        }
        
        request.setAttribute("errorMessage", "Bạn đã gửi yêu cầu bảo hành cho sản phẩm này.");
        request.getRequestDispatcher("WarrantyRequirementForm.jsp").forward(request, response);
    }
}
