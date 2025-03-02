package Controller.Admin;

import Dal.ComponentDAO;
import Model.Component;
import Model.Staff;
import Model.Invoice;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for managing Components
 * @author thang
 */
@WebServlet(name="ComponentController", urlPatterns={"/componentlist"})
public class ComponentController extends HttpServlet {
    
    private ComponentDAO componentDAO;
    
    public void init() {
        componentDAO = new ComponentDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("edit".equals(action)) {
            int componentId = Integer.parseInt(request.getParameter("componentId"));
            Component component = componentDAO.getComponentById(componentId);
            request.setAttribute("component", component);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editComponent.jsp");
            dispatcher.forward(request, response);
        } else if ("delete".equals(action)) {
            int componentId = Integer.parseInt(request.getParameter("componentId"));
            componentDAO.deleteComponent(componentId);
            response.sendRedirect("componentlist");
        } else {
            List<Component> components = componentDAO.getAllComponents();
            request.setAttribute("components", components);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Component.jsp");
            dispatcher.forward(request, response);
        }
    }
    
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String componentName = request.getParameter("componentName");
    String brand = request.getParameter("brand");
    String status = request.getParameter("status");
    long price = Long.parseLong(request.getParameter("price"));
    int amount = Integer.parseInt(request.getParameter("amount"));
    String staffId = request.getParameter("staffId");
    int invoiceId = request.getParameter("invoiceId") != null && !request.getParameter("invoiceId").isEmpty()
                    ? Integer.parseInt(request.getParameter("invoiceId")) : 0;

    Staff staff = new Staff(staffId);
    Invoice invoice = invoiceId > 0 ? new Invoice(invoiceId) : null;
    
    Component component = new Component(0, componentName, brand, status, price, amount, staff, invoice);
    
    boolean added = componentDAO.addComponent(component);
    System.out.println("Component Added: " + added);
    
    response.sendRedirect("componentlist");
}

}
