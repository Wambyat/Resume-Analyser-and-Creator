package main.model;
public class Skill {

    private String name;
    private int duration;
    private String description;

    public Skill() {
        this.setName("Java");
        this.setDuration(1);
        this.setDescription("I know Java");
    }

    public Skill(String name, int dur, String desc) {
        this.setName(name);
        this.setDuration(dur);
        this.setDescription(desc);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name + " for " + this.duration + " years.\n" + this.description;
    }
}
