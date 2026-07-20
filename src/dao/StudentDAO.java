package dao;

import database.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class StudentDAO {
	
    public Student login(String email, String password) {

        Student student = null;

        String sql = "SELECT * FROM students WHERE email=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setDepartment(rs.getString("department"));
                student.setSemester(rs.getInt("semester"));
                student.setCgpa(rs.getDouble("cgpa"));
                student.setDateOfBirth(rs.getString("date_of_birth"));
                student.setPhone(rs.getString("phone"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students(name,email,password,department,semester,cgpa,date_of_birth,phone) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getName());
            pst.setString(2, student.getEmail());
            pst.setString(3, student.getPassword());
            pst.setString(4, student.getDepartment());
            pst.setInt(5, student.getSemester());
            pst.setDouble(6, student.getCgpa());
            pst.setString(7, student.getDateOfBirth());
            pst.setString(8, student.getPhone());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    public boolean updateStudent(Student student) {

        String sql = "UPDATE students SET name=?, email=?, password=?, department=?, semester=?, cgpa=?, date_of_birth=?, phone=? WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getName());
            pst.setString(2, student.getEmail());
            pst.setString(3, student.getPassword());
            pst.setString(4, student.getDepartment());
            pst.setInt(5, student.getSemester());
            pst.setDouble(6, student.getCgpa());
            pst.setString(7, student.getDateOfBirth());
            pst.setString(8, student.getPhone());
            pst.setInt(9, student.getStudentId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, studentId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Student getStudentById(int studentId) {

        Student student = null;

        String sql = "SELECT * FROM students WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, studentId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setDepartment(rs.getString("department"));
                student.setSemester(rs.getInt("semester"));
                student.setCgpa(rs.getDouble("cgpa"));
                student.setDateOfBirth(rs.getString("date_of_birth"));
                student.setPhone(rs.getString("phone"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
 public boolean updateProfile(Student student) {

     String sql = "UPDATE students SET password=?, date_of_birth=?, phone=? WHERE student_id=?";

     try (Connection con = DBConnection.getConnection();
          PreparedStatement pst = con.prepareStatement(sql)) {

         pst.setString(1, student.getPassword());
         pst.setString(2, student.getDateOfBirth());
         pst.setString(3, student.getPhone());
         pst.setInt(4, student.getStudentId());

         return pst.executeUpdate() > 0;

     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }

 }

    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> studentList = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setPassword(rs.getString("password"));
                student.setDepartment(rs.getString("department"));
                student.setSemester(rs.getInt("semester"));
                student.setCgpa(rs.getDouble("cgpa"));
                student.setDateOfBirth(rs.getString("date_of_birth"));
                student.setPhone(rs.getString("phone"));

                studentList.add(student);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

}
