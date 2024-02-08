package maincode.main.model;
import java.util.*;
public class Resume {
    private int id;
    private String Name;
    private String Phone;
    private String Summary;
    private List<Skill> Skills;
    private List<Experience> Experience;
    private List<Education> Education;

    public Resume() {
        // Get ID from SQL.
        this.setId(1);
        this.setName("Default Name");
        this.setPhone("1234567890");
        this.setSummary("Default resume summery.");
        this.setSkill(new ArrayList<Skill>());
        this.setEducation(new ArrayList<Education>());
        this.setExperience(new ArrayList<Experience>());
    }

    public Resume(int id, String name, String Phone, String Summary, ArrayList<Experience> experience,
            ArrayList<Education> education, ArrayList<Skill> skill) {
        this.setId(id);
        this.setName(name);
        this.setPhone(Phone);
        this.setSummary(Summary);
        this.setSkill(skill);
        this.setEducation(education);
        this.setExperience(experience);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getSummary() {
        return this.Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public List<Education> getEducation() {
        return this.Education;
    }

    public void setEducation(List<Education> education) {
        this.Education = education;
    }

    public void addEducation(Education education) {
        this.Education.add(education);
    }

    public List<Experience> getExperience() {
        return Experience;
    }

    public void setExperience(List<Experience> experience) {
        this.Experience = experience;
    }

    public void addExperience(Experience experience) {
        Experience.add(experience);
    }

    public void setSkill(ArrayList<Skill> skill) {
        this.Skills = skill;
    }

    public List<Skill> getSkills() {
        return Skills;
    }

    public void addSkill(Skill skill) {
        Skills.add(skill);
    }

    public String toString() {
        return "Name: " + Name + "\nPh No: " + Phone + "\n" + Education + "\n" + Experience + "\n" + Skills + "\n"
                + Summary;
    }
}
