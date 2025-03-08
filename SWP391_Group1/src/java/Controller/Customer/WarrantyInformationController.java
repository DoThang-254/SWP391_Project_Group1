/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import Dal.CustomerDao;
import Dal.WarrantyFormDao;
import Model.Customer;
import Model.Product;
import Model.WarrantyForm;
import Model.WarrantyProcessing;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thang
 */
public class WarrantyInformationController extends HttpServlet {

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
            out.println("<title>Servlet ReadWarrantyInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReadWarrantyInformation at " + request.getContextPath() + "</h1>");
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

        if (request.getServletPath().equals("/searchinformation")) {
            searchWarrantyInformation(request, response);
        }
        if (request.getServletPath().equals("/warrantyinformation")) {
            WarrantyInformation(request, response);
        }

    }
    CustomerDao cd = new CustomerDao();
    WarrantyFormDao wfd = new WarrantyFormDao();

    protected void searchWarrantyInformation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String amountRaw = request.getParameter("amount");
        if (amountRaw == null || amountRaw.trim().isEmpty()) {
            amountRaw = "10";
        }

        int amount = Integer.parseInt(amountRaw);
        Customer c = (Customer) request.getSession().getAttribute("Customer");
        String input = request.getParameter("index");
        if (input == null || input.isBlank()) {
            input = "1";
        }

        int index = Integer.parseInt(input);

        String searchBox = request.getParameter("table_search");
        String brand = request.getParameter("filterBrand");
        Product newProduct = new Product(null, null, 0, brand, c.getCustomerId());
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        int count = cd.GetTotalProductByProductId(c.getCustomerId(), searchBox, newProduct);
        int endPage = count / amount;
        if (count % amount != 0) {
            endPage++;
        }
        String priceRange = request.getParameter("filterPriceRange");
        List<Product> list = cd.SearchingProductByProductId(index, c.getCustomerId(), searchBox, newProduct, sort, order, priceRange, amount);

        for (Product p : list) {
            WarrantyForm activeWarranty = wfd.getActiveWarrantyFormByProduct(p.getProductId());
            if (activeWarranty != null) {
                p.setWarrantyStatus("Còn bảo hành");
            } else {
                p.setWarrantyStatus("Hết hạn bảo hành");
            }
        }

        request.setAttribute("endpage", endPage);
        request.setAttribute("listA", list);
        request.setAttribute("tag", index);
        request.setAttribute("brand", brand);
        request.setAttribute("sort", sort);
        request.setAttribute("order", order);
        request.setAttribute("priceRange", priceRange);
        request.setAttribute("save", searchBox);
        request.setAttribute("amount", amount);

        request.getRequestDispatcher("SearchInformation.jsp").forward(request, response);
    }

    protected void WarrantyInformation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String input = request.getParameter("index");
        if (input == null || input.isBlank()) {
            input = "1";
        }
        int index = Integer.parseInt(input);
        Customer c = (Customer) request.getSession().getAttribute("Customer");
        String searchBox = request.getParameter("table_search");
        String brand = request.getParameter("filterBrand");
        Product newProduct = new Product(null, null, 0, brand, c.getCustomerId());
        int count = cd.GetTotalProductWarrantyByCustomerId(c.getCustomerId(), searchBox, newProduct);

        int endPage = count / 10;
        if (count % 10 != 0) {
            endPage++;
        }

        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        String priceRange = request.getParameter("filterPriceRange");

        //List<WarrantyInformation> list = cd.WarrantyProductInformation(index, c.getCustomerId(), searchBox, newProduct, sort, order, priceRange);
        request.setAttribute("endpage", endPage);
        //  request.setAttribute("listA", list);
        request.setAttribute("tag", index);
        request.setAttribute("brand", brand);
        request.setAttribute("sort", sort);
        request.setAttribute("order", order);
        request.setAttribute("priceRange", priceRange);
        request.setAttribute("save", searchBox);
        request.getRequestDispatcher("WarrantyInformation.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
