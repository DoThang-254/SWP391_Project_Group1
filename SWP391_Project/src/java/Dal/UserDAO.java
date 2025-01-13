/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class UserDAO {

    public UserDAO() {
        connectDB();
    }

    Connection cnn; // ket noi db
    Statement stm; // thuc thi cac cau lenh sql
    ResultSet rs; // luu tru va xu ly du lieu

    private void connectDB() {
        try {
            cnn = (new DBContext().getConnection());
            System.out.println("Connect successfully!");
        } catch (Exception e) {
            System.out.println("Connect error:" + e.getMessage());
        }
    }

    public User getUser(String key, String pass) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "Select * from [Users] where (Username='" + key + "' OR email='" + key + "') AND PasswordHash='" + pass + "'";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int UserID = rs.getInt(1);
                String FullName = rs.getString(2);
                String Email = rs.getString(5);
                String Phone = rs.getString(6);
                String Position = rs.getString(7);
                User u = new User(UserID, FullName, Email, Phone, Position);
                return u;
            }
        } catch (Exception e) {
            System.out.println("getUser Error:" + e.getMessage());
        }
        return null;
    }

    public String generatePass() {
        int data[] = IntStream.concat(IntStream.rangeClosed('0', '9'),
                IntStream.concat(IntStream.rangeClosed('A', 'Z'),
                        IntStream.rangeClosed('a', 'z'))).toArray();
        char index[] = new char[10];

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            index[i] = (char) data[r.nextInt(data.length)];
        }
        return new String(index);
    }

    public boolean checkDupEmail(String email) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select * from [User] where email='" + email + "'";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return true;
    }

    public void editProfile(int UserID, String FullName, String Email, String Phone) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "update [User] set "
                    + "  [FullName] = N'" + FullName + "' AND"
                    + " [Email] = N'" + Email + "' AND"
                    + " [Phone] = N'" + Phone + "' AND"
                    + "where [UserID] = " + UserID;
            stm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("editProfile Error:" + e.getMessage());
        }
    }

    public void changePassword(int userid, String newPassword) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "update [User] set "
                    + "  [PasswordHash] = N'" + newPassword + "'"
                    + "where [id] = " + userid;
            stm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("changePass Error:" + e.getMessage());
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "Select * from [User] where  = 0";
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                int UserID = rs.getInt(1);
                String Username = rs.getString(2);
                String FullName = rs.getString(3);
                String Email = rs.getString(4);
                String Phone = rs.getString(5);
                String Position = rs.getString(6);
                list.add(new User(UserID, Username, FullName, Phone, Position));
            }
        } catch (Exception e) {
            System.out.println("getUser Error:" + e.getMessage());
        }
        return list;
    }

    public String getUsername(int userid) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT username FROM [demo4].[dbo].[Users] where UserID = " + userid;
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("getNumberUser Error");
        }
        return null;
    }

    public void updateUser(int id, String name, String phone, String Email) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "update [User] set "
                    + "  [FullName] = N'" + name + "'"
                    + ", [Phone] = N'" + phone + "'"
                    + ", [Email] = N'" + Email + "'"
                    + "where [id] = " + id;
            stm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("updateUser Error:" + e.getMessage());
        }
    }

    public boolean checkOldPass(int id, String oldpass) {
        try {
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String sql = "select * from [User] where [id] = " + id + " AND [PasswordHash]=N'" + oldpass + "'";
            rs = stm.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("updateUser Error:" + e.getMessage());
        }
        return false;
    }
    
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        String getname = dao.getUsername(1);
        
        System.out.println(getname);
    }
}


