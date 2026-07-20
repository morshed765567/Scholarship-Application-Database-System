package model;

public class Scholarship {

    private int scholarshipId;
    private String scholarshipName;
    private double minimumCgpa;
    private double amount;
    private String deadline;
    

    public Scholarship() {

    }

    public Scholarship(int scholarshipId,
            String scholarshipName,
            double minimumCgpa,
            double amount,
            String deadline) {

        this.scholarshipId = scholarshipId;
        this.scholarshipName = scholarshipName;
        this.minimumCgpa = minimumCgpa;
        this.amount = amount;
        this.deadline = deadline;
    }
    public int getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(int scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public double getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(double minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

}