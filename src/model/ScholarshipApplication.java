package model;

public class ScholarshipApplication {

    private int applicationId;
    private int studentId;
    private int scholarshipId;
    private String applicationDate;
    private String status;
    private int reviewedBy;
    private String remarks;

    public ScholarshipApplication() {

    }

    public ScholarshipApplication(int applicationId, int studentId,
            int scholarshipId, String applicationDate,
            String status, int reviewedBy, String remarks) {

        this.applicationId = applicationId;
        this.studentId = studentId;
        this.scholarshipId = scholarshipId;
        this.applicationDate = applicationDate;
        this.status = status;
        this.reviewedBy = reviewedBy;
        this.remarks = remarks;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(int scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(int reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}