package main.model;
import java.util.Date;

public class Education {
    private String Degree;
    private String Subject;
    private String School;
    private Date startDate;
    private Date endDate;
    private String description;

    public Education() {
        this.setDegree("btech");
        this.setSubject("cse");
        this.setSchool("carmel");
        this.setStartDate(new Date());
        this.setEndDate(new Date());
        this.setDescription("Did a btech");
    }

    public Education(String Degree, String Subject, String School, Date startDate, Date endDate, String description) {
        this.setDegree(Degree);
        this.setSubject(Subject);
        this.setSchool(School);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setDescription(description);
    }

    public String getDegree() {
        return this.Degree;
    }

    public void setDegree(String degree) {
        this.Degree = degree;
    }

    public String getSubject() {
        return this.Subject;
    }

    public void setSubject(String subject) {
        this.Subject = subject;
    }

    public String getSchool() {
        return this.School;
    }

    public void setSchool(String School) {
        this.School = School;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String toString() {
        return "Studied at " + this.School + " and was awarded a " + this.Degree + " in " + this.Subject + " (" + this.startDate + " - "
                + this.endDate + "). " + this.description;
    }
    public static void main(String[] args)
    {
        Education e = new Education();
        System.out.println(e.toString());
    }
}