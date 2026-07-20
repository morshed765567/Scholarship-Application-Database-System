package model;

public class Student {

    private int studentId;
    private String name;
    private String email;
    private String password;
    private String department;
    private int semester;
    private double cgpa;
    private String dateOfBirth;
    private String phone;

    public Student() {

    }

    public Student(int studentId, String name, String email,
            String password, String department,
            int semester, double cgpa,
            String dateOfBirth, String phone) {

 this.studentId = studentId;
 this.name = name;
 this.email = email;
 this.password = password;
 this.department = department;
 this.semester = semester;
 this.cgpa = cgpa;
 this.dateOfBirth = dateOfBirth;
 this.phone = phone;
}
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}