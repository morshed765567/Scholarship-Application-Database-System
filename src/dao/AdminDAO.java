package dao;

import database.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminDAO {

    public Admin login(String username, String password) {

        Admin admin = null;

        String sql = "SELECT * FROM admins WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                admin = new Admin();

                admin.setAdminId(rs.getInt("admin_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return admin;
    }

    public ArrayList<Object[]> getAllStudentStatus() {

        ArrayList<Object[]> list = new ArrayList<>();

        String sql =
                "SELECT st.student_id, st.name, " +
                "IFNULL(sc.scholarship_name,'No Application') AS scholarship, " +
                "IFNULL(sa.status,'Not Applied') AS status " +
                "FROM students st " +
                "LEFT JOIN scholarship_applications sa " +
                "ON st.student_id = sa.student_id " +
                "LEFT JOIN scholarships sc " +
                "ON sa.scholarship_id = sc.scholarship_id " +
                "ORDER BY st.student_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                list.add(new Object[]{

                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("scholarship"),
                        rs.getString("status")

                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<Object[]> searchStudentStatus(String keyword) {

        ArrayList<Object[]> list = new ArrayList<>();

        String sql =
                "SELECT st.student_id, st.name, " +
                "IFNULL(sc.scholarship_name,'No Application') AS scholarship, " +
                "IFNULL(sa.status,'Not Applied') AS status " +
                "FROM students st " +
                "LEFT JOIN scholarship_applications sa " +
                "ON st.student_id = sa.student_id " +
                "LEFT JOIN scholarships sc " +
                "ON sa.scholarship_id = sc.scholarship_id " +
                "WHERE st.name LIKE ? " +
                "ORDER BY st.student_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                list.add(new Object[]{

                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("scholarship"),
                        rs.getString("status")

                });

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}