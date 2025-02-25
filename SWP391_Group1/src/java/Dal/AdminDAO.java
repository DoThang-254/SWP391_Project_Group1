/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Types;
import java.sql.SQLException;

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
                + "FROM [Staff] s  \n"
                + "JOIN [Role] r ON s.RoleId = r.RoleId \n"
                + "WHERE 1 = 1");
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append(" AND (\n" +
"    REPLACE(s.Username, ' ', ' ') LIKE REPLACE(?, ' ', '') \n" +
"    OR REPLACE(s.Email, ' ', ' ') LIKE REPLACE(?, ' ', '') \n" +
"    OR REPLACE(s.LastName, ' ', '') = ?\n" +
");");
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
                list.add(new Staff(StaffId, Username, Phone, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, 0, role));
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

    public void deactiveStaves(String StaffId, String Status) {
        String sql = "UPDATE [demo4].[dbo].[Staff] SET [Status] = ? WHERE [StaffId] = ?";
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setString(1, Status);
            p.setString(2, StaffId);
            int rowsUpdated = p.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Cập nhật thành công " + rowsUpdated + " bản ghi.");
            } else {
                System.out.println("Không có nhân viên nào được cập nhật.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Staff userDetail(String StaffId) {
        String sql = "SELECT \n"
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
                + "FROM [Staff] s  \n"
                + "JOIN [Role] r ON s.RoleId = r.RoleId \n"
                + "WHERE s.StaffId = ?";
        try (PreparedStatement p = connection.prepareStatement(sql)) {
            p.setString(1, StaffId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String StaffId2 = rs.getString(1);
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
                return new Staff(StaffId2, Username, Phone, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, 0, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  
      // Thêm một admin mới
    public boolean addAdmin(Staff staff) {
        if (isStaffIdExists(staff.getStaffId())) {
            return false;
        }
        String query = "INSERT INTO Staff (StaffId, Username, Password, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, RoleId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staff.getStaffId());
            ps.setString(2, staff.getUsername());
            ps.setString(3, "admin123"); // Mật khẩu mặc định cho Admin
            ps.setString(4, staff.getFirstName());
            ps.setString(5, staff.getLastName());
            ps.setString(6, staff.getEmail());
            ps.setString(7, staff.getPhone());
            ps.setString(8, staff.getGender());

            if (staff.getBirthDate() != null) {
                ps.setDate(9, new java.sql.Date(staff.getBirthDate().getTime()));
            } else {
                ps.setNull(9, Types.DATE);
            }

            ps.setString(10, staff.getStatus());
            ps.setInt(11, staff.getRoleId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin admin
    public void updateAdmin(Staff staff) {
        String query = "UPDATE Staff SET Username = ?, FirstName = ?, LastName = ?, Email = ?, Phone = ?, Gender = ?, BirthDate = ?, Status = ?, RoleId = ? WHERE StaffId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staff.getUsername());
            ps.setString(2, staff.getFirstName());
            ps.setString(3, staff.getLastName());
            ps.setString(4, staff.getEmail());
            ps.setString(5, staff.getPhone());
            ps.setString(6, staff.getGender());
            if (staff.getBirthDate() != null) {
                ps.setDate(7, new java.sql.Date(staff.getBirthDate().getTime()));
            } else {
                ps.setNull(7, Types.DATE);
            }

            ps.setString(8, staff.getStatus());
            ps.setInt(9, staff.getRoleId());
            ps.setString(10, staff.getStaffId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa admin
    public void deleteAdmin(String staffId) {
        String query = "DELETE FROM Staff WHERE StaffId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staffId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy danh sách admin theo vai trò
    public List<Staff> getAdminsByRole(int roleId) {
        List<Staff> admins = new ArrayList<>();
        String query = "SELECT * FROM Staff WHERE RoleId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getString("StaffId"));
                staff.setUsername(rs.getString("Username"));
                staff.setFirstName(rs.getString("FirstName"));
                staff.setLastName(rs.getString("LastName"));
                staff.setEmail(rs.getString("Email"));
                staff.setPhone(rs.getString("Phone"));
                staff.setGender(rs.getString("Gender"));
                staff.setBirthDate(rs.getDate("BirthDate"));
                staff.setStatus(rs.getString("Status"));
                staff.setRoleId(rs.getInt("RoleId"));
                admins.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Tìm admin theo ID
    public Staff getAdminById(String staffId) {
        String query = "SELECT * FROM Staff WHERE StaffId = ? AND RoleId = 3";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staffId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getString("StaffId"));
                staff.setUsername(rs.getString("Username"));
                staff.setFirstName(rs.getString("FirstName"));
                staff.setLastName(rs.getString("LastName"));
                staff.setEmail(rs.getString("Email"));
                staff.setPhone(rs.getString("Phone"));
                staff.setGender(rs.getString("Gender"));
                staff.setBirthDate(rs.getDate("BirthDate"));
                staff.setStatus(rs.getString("Status"));
                staff.setRoleId(rs.getInt("RoleId"));
                return staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy tổng số admin
    public int getTotalAdmins() {
        String query = "SELECT COUNT(*) FROM Staff WHERE RoleId = 3";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy danh sách admin theo trang (phân trang)
    public List<Staff> getAdminsByPage(int page, int recordsPerPage) {
        List<Staff> admins = new ArrayList<>();
        int start = (page - 1) * recordsPerPage;

        String query = "SELECT * FROM Staff WHERE RoleId = 3 ORDER BY StaffId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, start);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getString("StaffId"));
                staff.setUsername(rs.getString("Username"));
                staff.setFirstName(rs.getString("FirstName"));
                staff.setLastName(rs.getString("LastName"));
                staff.setEmail(rs.getString("Email"));
                staff.setPhone(rs.getString("Phone"));
                staff.setGender(rs.getString("Gender"));
                staff.setBirthDate(rs.getDate("BirthDate"));
                staff.setStatus(rs.getString("Status"));
                staff.setRoleId(rs.getInt("RoleId"));
                admins.add(staff);
            }
            System.out.println("Admins fetched: " + admins.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    // Kiểm tra xem StaffId có tồn tại không
    public boolean isStaffIdExists(String staffId) {
        String query = "SELECT COUNT(*) FROM Staff WHERE StaffId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staffId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    // Test
    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
//        List<Staff> list = dao.getListOfUser("jd", null);
//
//        for (Staff staff : list) {
//            System.out.println("UserName: " + staff.getUsername()
//                    + ", Role: " + staff.getRole().getRoleName());
//        }
        dao.deactiveStaves("6", "InActive");
    }
}
