package dao;

import database.DBConnection;
import model.Scholarship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScholarshipDAO {

    public boolean addScholarship(Scholarship scholarship) {

        String sql = "INSERT INTO scholarships(scholarship_name, minimum_cgpa, amount, deadline) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, scholarship.getScholarshipName());
            pst.setDouble(2, scholarship.getMinimumCgpa());
            pst.setDouble(3, scholarship.getAmount());
            pst.setString(4, scholarship.getDeadline());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Scholarship getScholarshipById(int scholarshipId) {

        Scholarship scholarship = null;

        String sql = "SELECT * FROM scholarships WHERE scholarship_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, scholarshipId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                scholarship = new Scholarship();

                scholarship.setScholarshipId(rs.getInt("scholarship_id"));
                scholarship.setScholarshipName(rs.getString("scholarship_name"));
                scholarship.setMinimumCgpa(rs.getDouble("minimum_cgpa"));
                scholarship.setAmount(rs.getDouble("amount"));
                scholarship.setDeadline(rs.getString("deadline"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scholarship;
    }
  
    public boolean updateScholarship(Scholarship scholarship) {

        String sql = "UPDATE scholarships SET scholarship_name=?, minimum_cgpa=?, amount=?, deadline=? WHERE scholarship_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, scholarship.getScholarshipName());
            pst.setDouble(2, scholarship.getMinimumCgpa());
            pst.setDouble(3, scholarship.getAmount());
            pst.setString(4, scholarship.getDeadline());
            pst.setInt(5, scholarship.getScholarshipId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public boolean deleteScholarship(int scholarshipId) {

        String sql = "DELETE FROM scholarships WHERE scholarship_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, scholarshipId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Scholarship> getAllScholarships() {

        ArrayList<Scholarship> scholarshipList = new ArrayList<>();

        String sql = "SELECT * FROM scholarships";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Scholarship scholarship = new Scholarship();

                scholarship.setScholarshipId(rs.getInt("scholarship_id"));
                scholarship.setScholarshipName(rs.getString("scholarship_name"));
                scholarship.setMinimumCgpa(rs.getDouble("minimum_cgpa"));
                scholarship.setAmount(rs.getDouble("amount"));
                scholarship.setDeadline(rs.getString("deadline"));

                scholarshipList.add(scholarship);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scholarshipList;
    }

}