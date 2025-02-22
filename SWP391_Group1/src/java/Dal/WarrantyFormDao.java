/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;
import Model.WarrantyForm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thang
 */
public class WarrantyFormDao extends DBContext{
    
    private PreparedStatement p ;
//    public WarrantyForm getActiveWarrantyFormByProduct(String productId) {
//        WarrantyForm warrantyForm = null;
//        String sql = "SELECT * FROM WarrantyForm WHERE ProductId = ? AND EndDate >= GETDATE() ORDER BY EndDate DESC";
//        
//        try {
//            p = connection.prepareStatement(sql); 
//            
//            p.setString(1, productId);
//            ResultSet rs = p.executeQuery();
//            
//            if (rs.next()) {
//                warrantyForm = new WarrantyForm();
//                warrantyForm.setFormId(rs.getInt("FormId"));
//                warrantyForm.setProductId(rs.getString("ProductId"));
//                warrantyForm.setStartDate(rs.getDate("StartDate"));
//                warrantyForm.setEndDate(rs.getDate("EndDate"));
//                warrantyForm.setRegisterDate(rs.getDate("RegisterDate"));
//                warrantyForm.setStatus(rs.getString("Status"));
//            }
//        } catch (SQLException e) {
//        }
//        return warrantyForm;
//    }

   
}
