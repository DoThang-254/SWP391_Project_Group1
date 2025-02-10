/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Customer;
import Model.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thang
 */
public class AdminDAO extends DBContext {

    public List<Staff> getListOfUser(String searchKeyword, Integer roleId) {
        List<Staff> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT \n"
                + "    s.StaffId, \n"
                + "    s.Username, \n"
                + "    s.FirstName, \n"
                + "    s.LastName, \n"
                + "    s.Email, \n"
                + "    s.Phone, \n"
                + "    s.Gender, \n"
                + "    s.BirthDate, \n"
                + "    s.Status, \n"
                + "    s.RoleId, \n"
                + "    r.RoleName  \n"
                + "FROM [demo4].[dbo].[Staff] s  \n"
                + "JOIN [demo4].[dbo].[Role] r ON s.RoleId = r.RoleId \n"
                + "WHERE 1 = 1");
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append(" AND (s.Username LIKE ? OR s.Email LIKE ? OR s.Phone LIKE ?)");
        }

        if (roleId != null) {
            sql.append(" AND s.RoleId = ?");
        }

        try (PreparedStatement p = connection.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                String searchPattern = "%" + searchKeyword + "%";
                p.setString(paramIndex++, searchPattern);
                p.setString(paramIndex++, searchPattern);
                p.setString(paramIndex++, searchPattern);
            }

            if (roleId != null) {
                p.setInt(paramIndex++, roleId);
            }

            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String StaffId = rs.getString(1);
                String Username = rs.getString(2);
                String FirstName = rs.getString(3);
                String LastName = rs.getString(4);
                String Email = rs.getString(5);
                String Phone = rs.getString(6);
                String Gender = rs.getString(7);
                Date BirthDate = rs.getDate(8);
                String Status = rs.getString(9);
                int RoleId = rs.getInt(10);
                String RoleName = rs.getString(11);
                Role role = new Role(RoleId, RoleName);
                list.add(new Staff(StaffId, Username, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, RoleId, role));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ (in ra lỗi hoặc ghi log)
        }
        return list;
    }

    public List<Staff> getListByPage(List<Staff> list, int start, int end) {
        List<Staff> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    // Test
    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        List<Staff> list = dao.getListOfUser("te", 2);

        for (Staff staff : list) {
            System.out.println("UserName: " + staff.getUsername()
                    + ", Role: " + staff.getRole().getRoleName());
        }
    }
}
