/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

/**
 *
 * @author khang
 */
import Model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TechnicianDAO extends DBContext {
    public TechnicianDAO() {
        super();  
    }

    // Add a new technician
    public boolean addTechnician(Staff staff) {
        if (isStaffIdExists(staff.getStaffId())) {
            return false;
        }

        String query = "INSERT INTO Staff (StaffId, Username, Password, FirstName, LastName, Email, Phone, Gender, BirthDate, Status, RoleId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staff.getStaffId());
            ps.setString(2, staff.getUsername());
            ps.setString(3, "password123"); // Mật khẩu mặc định
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

    // Get technician by StaffId
    public Staff getTechnicianById(String staffId) {
        String query = "SELECT * FROM Staff WHERE StaffId = ? AND RoleId = 1";
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

    public List<Staff> getTechniciansByRole(int roleId) {
        List<Staff> Staff = new ArrayList<>();
        String query = "SELECT * FROM Staff WHERE RoleId = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaffId(rs.getString("StaffId"));
                staff.setUsername(rs.getString("Username"));
                staff.setFirstName(rs.getString("FirstName"));
                staff.setLastName(rs.getString("LastName"));
                staff.setEmail(rs.getString("Email"));
                staff.setPhone(rs.getString("Phone"));
                staff.setGender(rs.getString("Gender"));
//                staff.setBirthDate(rs.getDate("BirthDate"));
                staff.setStatus(rs.getString("Status"));
                staff.setRoleId(rs.getInt("RoleId"));
                Staff.add(staff);
            }
            return Staff;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

 

    // Update a technician
    public void updateTechnician(Staff Staff) {
        String query = "UPDATE Staff SET Username = ?, FirstName = ?, LastName = ?, Email = ?, Phone = ?, Gender = ?, BirthDate = ?, Status = ?, RoleId = ? WHERE StaffId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, Staff.getUsername());
            ps.setString(2, Staff.getFirstName());
            ps.setString(3, Staff.getLastName());
            ps.setString(4, Staff.getEmail());
            ps.setString(5, Staff.getPhone());
            ps.setString(6, Staff.getGender());
            if (Staff.getBirthDate() != null) {
                ps.setDate(7, new java.sql.Date(Staff.getBirthDate().getTime()));
            } else {
                ps.setNull(7, Types.DATE);
            }

            ps.setString(8, Staff.getStatus());
            ps.setInt(9, Staff.getRoleId());
            ps.setString(10, Staff.getStaffId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a technician
    public void deleteTechnician(String staffId) {
        String query = "DELETE FROM Staff WHERE StaffId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staffId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 public List<Staff> searchTechnicianByName(String name) {
    List<Staff> technicians = new ArrayList<>();

    if (name == null || name.trim().isEmpty()) {
        return technicians; 
    }

  
    String cleanedName = name.trim().replaceAll("\\s+", "%");

    String query = "SELECT * FROM Staff WHERE RoleId = 1 AND (FirstName LIKE ? OR LastName LIKE ? OR Username LIKE ?)";

    try (PreparedStatement ps = connection.prepareStatement(query)) {
        String searchPattern = "%" + cleanedName + "%";
        ps.setString(1, searchPattern);
        ps.setString(2, searchPattern);
        ps.setString(3, searchPattern);

        try (ResultSet rs = ps.executeQuery()) {
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
                technicians.add(staff);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
    return technicians;
}



// Lọc theo ID
    public List<Staff> searchTechnicianById(String staffId) {
        List<Staff> technicians = new ArrayList<>();
        String query = "SELECT * FROM Staff WHERE RoleId = 1 AND StaffId = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, staffId);
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
                technicians.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return technicians;
    }

    // Get total number of technicians
    public int getTotalTechnicians() {
        String query = "SELECT COUNT(*) FROM Staff WHERE RoleId = 1";
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

    // Get paginated list of technicians
    public List<Staff> getTechniciansByPage(int page, int recordsPerPage) {
        List<Staff> technicians = new ArrayList<>();
        int start = (page - 1) * recordsPerPage;

        String query = "SELECT * FROM Staff WHERE RoleId = 1 ORDER BY StaffId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

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
                technicians.add(staff);
            }
            System.out.println("Technicians fetched: " + technicians.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return technicians;
    }

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

}
