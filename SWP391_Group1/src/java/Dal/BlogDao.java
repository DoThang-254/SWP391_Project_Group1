/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Blog;
import Model.Staff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thang
 */
public class BlogDao extends DBContext {

    private PreparedStatement p;
    private ResultSet rs;

    public static void main(String[] args) {
        BlogDao bd = new BlogDao();
        for (Blog b : bd.GetAllBlog(1)) {
            System.out.println(b.getBlogId());
        }
    }

    public int CountBlog() {
        String sql = "select count(*) from Blog";

        try {
            p = connection.prepareStatement(sql);

            rs = p.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Blog> GetAllBlog(int index) {
        List<Blog> list = new ArrayList<>();
        String sql = "select * from Blog order by BlogId\n"
                + "offset ? rows fetch next 5 rows only";

        try {
            p = connection.prepareStatement(sql);
            p.setInt(1, (index - 1)* 5);
            rs = p.executeQuery();

            while (rs.next()) {
                Staff s = new Staff();
                s.setStaffId(rs.getString(7));
                list.add(new Blog(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getDate(6), s));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
