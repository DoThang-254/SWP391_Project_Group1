/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Technician;

import Dal.WarrantyFormDao;
import Dal.WarrantyProcessDao;
import Model.WarrantyForm;
import dao.WarrantyRequirementDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
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
        String staffId = request.getParameter("staffId");
        if (productId == null || productId.trim().isEmpty()) {

        }

        if (!wfd.hasFormId(requireId)) {
            wfd.createWarrantyForm(productId);
            WarrantyForm newWf = wfd.getWarrantyFormbyProductId(productId);
            wrd.UpdateFormId(newWf.getFormId(), requireId);
            wrd.UpdateTechVerifyForm(newWf.getFormId());
            request.setAttribute("form", newWf);
            request.setAttribute("requireId", requireId);
            request.setAttribute("staffId", staffId);
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
        int requireId = Integer.parseInt(request.getParameter("requireId"));
        String productId = request.getParameter("productId");
        String staffId = request.getParameter("staffId");
        int formId = Integer.parseInt(request.getParameter("formId"));
        String endDate = request.getParameter("endDate");
        String faultType = request.getParameter("faultType");

        WarrantyForm wf = new WarrantyForm();
        wf.setFormId(formId);
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

        Part filePart = request.getPart("image"); // Lấy file từ input name="image"
        String existingImage = request.getParameter("existingImage");
        String imagePath = existingImage; // Mặc định giữ ảnh cũ
        String msg = null;
        // Danh sách định dạng ảnh hợp lệ
        List<String> allowedExtensions = Arrays.asList("png", "jpg", "jpeg");

        if (filePart != null && filePart.getSize() > 0) { // Nếu có ảnh mới, cập nhật ảnh
            String fileName = filePart.getSubmittedFileName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            // Kiểm tra kích thước ảnh (<= 3MB)
            if (filePart.getSize() > 3 * 1024 * 1024) {
                msg = "img <= 3 mb";
                response.sendRedirect("updateform?formId=" + formId + "&requireId=" + requireId + "&staffId=" + staffId + "&msg=" + msg);
                return;
            }

            // Kiểm tra đuôi ảnh có hợp lệ không
            if (!allowedExtensions.contains(fileExtension)) {
                msg = "img is .png, jpg, jpeg!";
                response.sendRedirect("updateform?formId=" + formId + "&requireId=" + requireId + "&staffId=" + staffId + "&msg=" + msg);
                return;
            }

            // Đường dẫn lưu ảnh
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

            // Lưu file vào server
            String filePath = uploadPath + File.separator + uniqueFileName;

            filePart.write(filePath);

            imagePath = "uploads/" + uniqueFileName;
        } else if (existingImage == null || existingImage.isEmpty()) {
            // Trường hợp không chọn ảnh và ảnh cũ không tồn tại
            msg = "img is not empty";
            response.sendRedirect("updateform?formId=" + formId + "&requireId=" + requireId + "&staffId=" + staffId + "&msg=" + msg);
            return;
        }

        wf.setImgUrl(imagePath);
        wfd.UpdateFullFormId(wf);

        boolean isUserFault = wfd.isUserFault(formId);
        boolean isPay = wfd.isPay(formId);
        WarrantyForm isWarrantyValid = wfd.getActiveWarrantyFormByProduct(productId); // Kiểm tra còn hạn bảo hành không
        WarrantyProcessDao wpd = new WarrantyProcessDao();

        if (isUserFault) {
            if (isWarrantyValid != null) {
                wfd.updateIsPay(formId, "yes"); // Còn hạn + lỗi user 
                wfd.updateTechVerify(formId);

                if (!wpd.isWarrantyProcessExists(requireId)) {
                    wpd.insertWarrantyProcess(requireId, staffId);
                    wrd.UpdateStatusRequest("Checked", requireId);
                } else {
                    System.out.println("Yêu cầu này đã được xử lý!");
                }
            } else {
                if (!wpd.isWarrantyProcessExists(requireId)) {
                    wpd.insertWarrantyProcess(requireId, staffId);
                    wrd.UpdateStatusRequest("Checked", requireId);
                } else {
                    System.out.println("Yêu cầu này đã được xử lý!");
                }
            }

            // Nếu hết hạn mà `isPay` đã là "yes", giữ nguyên (không cần update lại)
        } else {
            if (isWarrantyValid != null) {
                wfd.updateIsPay(formId, "no"); // Còn hạn + lỗi NSX → Miễn phí
                if (!wpd.isWarrantyProcessExists(requireId)) { // Nếu chưa tồn tại, mới insert
                    wpd.insertWarrantyProcess(requireId, staffId);
                    wrd.UpdateStatusRequest("Checked", requireId);
                } else {
                    System.out.println("Yêu cầu này đã được xử lý!");
                }
            } else {
                if (!wpd.isWarrantyProcessExists(requireId)) {
                    wpd.insertWarrantyProcess(requireId, staffId);
                    wrd.UpdateStatusRequest("Checked", requireId);
                } else {
                    System.out.println("Yêu cầu này đã được xử lý!");
                }
            }
        }

        response.sendRedirect("updateform?formId=" + formId + "&requireId=" + requireId + "&staffId=" + staffId);
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
