package dao;

import database.DBConnection;
import model.Administration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministrationDAO {

    public Administration login(String email, String password) {

        Administration admin = null;

        String sql = "SELECT * FROM administration WHERE email=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                admin = new Administration();

                admin.setAdminId(rs.getInt("admin_id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setDesignation(rs.getString("designation"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }
    public boolean addAdministration(Administration admin) {

        String sql = "INSERT INTO administration(name,email,password,designation) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, admin.getName());
            pst.setString(2, admin.getEmail());
            pst.setString(3, admin.getPassword());
            pst.setString(4, admin.getDesignation());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Administration getAdministrationById(int adminId) {

        Administration admin = null;

        String sql = "SELECT * FROM administration WHERE admin_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, adminId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                admin = new Administration();

                admin.setAdminId(rs.getInt("admin_id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setDesignation(rs.getString("designation"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }
    public boolean updateAdministration(Administration admin) {

        String sql = "UPDATE administration SET name=?, email=?, password=?, designation=? WHERE admin_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, admin.getName());
            pst.setString(2, admin.getEmail());
            pst.setString(3, admin.getPassword());
            pst.setString(4, admin.getDesignation());
            pst.setInt(5, admin.getAdminId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAdministration(int adminId) {

        String sql = "DELETE FROM administration WHERE admin_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, adminId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Administration> getAllAdministrations() {

        ArrayList<Administration> adminList = new ArrayList<>();

        String sql = "SELECT * FROM administration";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Administration admin = new Administration();

                admin.setAdminId(rs.getInt("admin_id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setDesignation(rs.getString("designation"));

                adminList.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adminList;
    }

}
