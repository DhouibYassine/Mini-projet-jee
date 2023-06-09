package tn.iit.models;


public class Authorization {
    private Teacher teacher;
    private int authorizedHours;

    public Authorization(Teacher teacher, int authorizedHours) {
        this.teacher = teacher;
        this.authorizedHours = authorizedHours;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getAuthorizedHours() {
        return authorizedHours;
    }
}
