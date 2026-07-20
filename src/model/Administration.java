package model;

public class Administration {

    private int adminId;
    private String name;
    private String email;
    private String password;
    private String designation;

    public Administration() {

    }

    public Administration(int adminId, String name,
            String email, String password,
            String designation) {

        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.designation = designation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

}