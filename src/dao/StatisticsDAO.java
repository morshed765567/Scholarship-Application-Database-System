package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatisticsDAO {

    public int getStudentCount() {

        return getCount("SELECT COUNT(*) FROM students");

    }

    public int getScholarshipCount() {

        return getCount("SELECT COUNT(*) FROM scholarships");

    }

    public int getPendingCount() {

        return getStatusCount("Pending");

    }

    public int getApprovedCount() {

        return getStatusCount("Approved");

    }

    public int getRejectedCount() {

        return getStatusCount("Rejected");

    }

    private int getCount(String sql) {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    private int getStatusCount(String status) {

        String sql =
                "SELECT COUNT(*) FROM scholarship_applications WHERE status=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, status);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

}