package dao;

import database.DBConnection;
import model.ScholarshipApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicationDAO {

    public boolean addApplication(ScholarshipApplication application) {

        String sql = "INSERT INTO scholarship_applications(student_id, scholarship_id, application_date, status, reviewed_by, remarks) VALUES(?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, application.getStudentId());
            pst.setInt(2, application.getScholarshipId());
            pst.setString(3, application.getApplicationDate());
            pst.setString(4, application.getStatus());
            pst.setInt(5, application.getReviewedBy());
            pst.setString(6, application.getRemarks());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ScholarshipApplication getApplicationById(int applicationId) {

        ScholarshipApplication application = null;

        String sql = "SELECT * FROM scholarship_applications WHERE application_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, applicationId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                application = new ScholarshipApplication();

                application.setApplicationId(rs.getInt("application_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setScholarshipId(rs.getInt("scholarship_id"));
                application.setApplicationDate(rs.getString("application_date"));
                application.setStatus(rs.getString("status"));
                application.setReviewedBy(rs.getInt("reviewed_by"));
                application.setRemarks(rs.getString("remarks"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return application;
    }
  
    public boolean updateApplicationStatus(ScholarshipApplication application) {

        String sql = "UPDATE scholarship_applications SET status=?, reviewed_by=?, remarks=? WHERE application_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, application.getStatus());
            pst.setInt(2, application.getReviewedBy());
            pst.setString(3, application.getRemarks());
            pst.setInt(4, application.getApplicationId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteApplication(int applicationId) {

        String sql = "DELETE FROM scholarship_applications WHERE application_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, applicationId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
 public boolean hasAppliedForScholarship(int studentId, int scholarshipId) {

     String sql = "SELECT COUNT(*) FROM scholarship_applications WHERE student_id = ? AND scholarship_id = ?";

     try (Connection con = DBConnection.getConnection();
          PreparedStatement pst = con.prepareStatement(sql)) {

         pst.setInt(1, studentId);
         pst.setInt(2, scholarshipId);

         ResultSet rs = pst.executeQuery();

         if (rs.next()) {
             return rs.getInt(1) > 0;
         }

     } catch (SQLException e) {
         e.printStackTrace();
     }

     return false;
 }

    public ArrayList<ScholarshipApplication> getApplicationsByStudent(int studentId) {

        ArrayList<ScholarshipApplication> applicationList = new ArrayList<>();

        String sql = "SELECT * FROM scholarship_applications WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, studentId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                ScholarshipApplication application = new ScholarshipApplication();

                application.setApplicationId(rs.getInt("application_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setScholarshipId(rs.getInt("scholarship_id"));
                application.setApplicationDate(rs.getString("application_date"));
                application.setStatus(rs.getString("status"));
                application.setReviewedBy(rs.getInt("reviewed_by"));
                application.setRemarks(rs.getString("remarks"));

                applicationList.add(application);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applicationList;
    }
 public ArrayList<Object[]> getApplicationsWithScholarshipName(int studentId) {

     ArrayList<Object[]> list = new ArrayList<>();

     String sql =
             "SELECT sa.application_id, " +
             "s.scholarship_name, " +
             "sa.application_date, " +
             "sa.status, " +
             "sa.remarks " +
             "FROM scholarship_applications sa " +
             "JOIN scholarships s " +
             "ON sa.scholarship_id = s.scholarship_id " +
             "WHERE sa.student_id = ?";

     try (Connection con = DBConnection.getConnection();
          PreparedStatement pst = con.prepareStatement(sql)) {

         pst.setInt(1, studentId);

         ResultSet rs = pst.executeQuery();

         while (rs.next()) {

             Object[] row = {

                     rs.getInt("application_id"),
                     rs.getString("scholarship_name"),
                     rs.getString("application_date"),
                     rs.getString("status"),
                     rs.getString("remarks")

             };

             list.add(row);
         }

     } catch (SQLException e) {
         e.printStackTrace();
     }

     return list;
 }
 
public ArrayList<Object[]> getAllApplicationDetails() {

  ArrayList<Object[]> list = new ArrayList<>();

  String sql =
          "SELECT sa.application_id, " +
          "st.name AS student_name, " +
          "sc.scholarship_name, " +
          "sa.application_date, " +
          "sa.status, " +
          "sa.remarks " +
          "FROM scholarship_applications sa " +
          "JOIN students st ON sa.student_id = st.student_id " +
          "JOIN scholarships sc ON sa.scholarship_id = sc.scholarship_id " +
          "ORDER BY sa.application_id";

  try (Connection con = DBConnection.getConnection();
       PreparedStatement pst = con.prepareStatement(sql);
       ResultSet rs = pst.executeQuery()) {

      while (rs.next()) {

          Object[] row = {
                  rs.getInt("application_id"),
                  rs.getString("student_name"),
                  rs.getString("scholarship_name"),
                  rs.getString("application_date"),
                  rs.getString("status"),
                  rs.getString("remarks")
          };

          list.add(row);
      }

  } catch (SQLException e) {
      e.printStackTrace();
  }

  return list;
}
    public ArrayList<ScholarshipApplication> getAllApplications() {

        ArrayList<ScholarshipApplication> applicationList = new ArrayList<>();

        String sql = "SELECT * FROM scholarship_applications";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                ScholarshipApplication application = new ScholarshipApplication();

                application.setApplicationId(rs.getInt("application_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setScholarshipId(rs.getInt("scholarship_id"));
                application.setApplicationDate(rs.getString("application_date"));
                application.setStatus(rs.getString("status"));
                application.setReviewedBy(rs.getInt("reviewed_by"));
                application.setRemarks(rs.getString("remarks"));

                applicationList.add(application);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applicationList;
    }

}
