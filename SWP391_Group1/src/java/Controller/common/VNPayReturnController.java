/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.common;

import Validation.VNPayUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thang
 */
public class VNPayReturnController extends HttpServlet {

    private static final String VNP_HASH_SECRET = "FOFVFJKSXHFE5VYCT4MVE0415T8PLJFK";  // Thay bằng Secret Key thực tế

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            params.put(entry.getKey(), entry.getValue()[0]);
        }

        String vnp_SecureHash = params.remove("vnp_SecureHash"); // Lấy mã checksum từ request

        // Kiểm tra checksum để đảm bảo dữ liệu không bị thay đổi
        String generatedHash = VNPayUtils.hmacSHA512(VNP_HASH_SECRET, VNPayUtils.createQueryString(params));

        if (generatedHash.equals(vnp_SecureHash)) {
            String vnp_ResponseCode = params.get("vnp_ResponseCode");
            if ("00".equals(vnp_ResponseCode)) {
                // Thanh toán thành công
                request.setAttribute("message", "Giao dịch thành công!");
                request.setAttribute("orderId", params.get("vnp_TxnRef"));
                request.setAttribute("amount", Long.parseLong(params.get("vnp_Amount")) / 100);
                request.setAttribute("bankCode", params.get("vnp_BankCode"));
            } else {
                // Giao dịch thất bại
                request.setAttribute("message", "Giao dịch thất bại! Mã lỗi: " + vnp_ResponseCode);
            }
        } else {
            // Sai checksum, có thể dữ liệu đã bị thay đổi
            request.setAttribute("message", "Dữ liệu không hợp lệ!");
        }

        request.getRequestDispatcher("PaymentReturn.jsp").forward(request, response);
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
