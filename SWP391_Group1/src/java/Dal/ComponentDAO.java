package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.*;

/**
 * DAO class for Component
 * @author thang
 */
public class ComponentDAO extends DBContext {

    public List<Component> getAllComponents() {
        List<Component> list = new ArrayList<>();
        String query = "SELECT * FROM Component";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Component component = new Component(
                    rs.getInt("componentId"),
                    rs.getString("componentName"),
                    rs.getString("brand"),
                    rs.getString("status"),
                    rs.getLong("price"),
                    rs.getInt("amount"),
                    new Staff(rs.getString("staffId")),
                    rs.getObject("invoiceId") != null ? new Invoice(rs.getInt("invoiceId")) : null
                );
                list.add(component);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Component getComponentById(int id) {
        String query = "SELECT * FROM Component WHERE componentId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Component(
                    rs.getInt("componentId"),
                    rs.getString("componentName"),
                    rs.getString("brand"),
                    rs.getString("status"),
                    rs.getLong("price"),
                    rs.getInt("amount"),
                    new Staff(rs.getString("staffId")),
                    rs.getObject("invoiceId") != null ? new Invoice(rs.getInt("invoiceId")) : null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addComponent(Component component) {
    String query = "INSERT INTO Component (componentName, brand, status, price, amount, staffId, invoiceId) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, component.getComponentName());
        ps.setString(2, component.getBrand());
        ps.setString(3, component.getStatus());
        ps.setLong(4, component.getPrice());
        ps.setInt(5, component.getAmount());
        ps.setString(6, component.getStaff().getStaffId());
        ps.setObject(7, component.getInvoice() != null ? component.getInvoice().getInvoiceId() : null);
        
        int rowsAffected = ps.executeUpdate();
        System.out.println("Rows Inserted: " + rowsAffected);
        
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    public boolean updateComponent(Component component) {
        String query = "UPDATE Component SET componentName = ?, brand = ?, status = ?, price = ?, amount = ?, staffId = ?, invoiceId = ? WHERE componentId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, component.getComponentName());
            ps.setString(2, component.getBrand());
            ps.setString(3, component.getStatus());
            ps.setLong(4, component.getPrice());
            ps.setInt(5, component.getAmount());
            ps.setString(6, component.getStaff().getStaffId());
            ps.setObject(7, component.getInvoice() != null ? component.getInvoice().getInvoiceId() : null);
            ps.setInt(8, component.getComponentId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComponent(int id) {
        String query = "DELETE FROM Component WHERE componentId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
   public static void main(String[] args) {
    ComponentDAO dao = new ComponentDAO();
    Staff staff = new Staff("S001"); // Thay bằng ID staff hợp lệ
    Invoice invoice = new Invoice(10); // Thay bằng ID invoice hợp lệ
    
    Component testComponent = new Component(0, "Test Component", "Brand X", "Available", 50000, 10, staff, invoice);
    boolean added = dao.addComponent(testComponent);
    System.out.println("Test Add Component: " + added);
}

}
