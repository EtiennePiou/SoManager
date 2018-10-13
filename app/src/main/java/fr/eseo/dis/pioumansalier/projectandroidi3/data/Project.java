package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import java.util.ArrayList;

public class Project {
    int projectId;
    String title;
    String describ;
    boolean poster;
    int confid;
    User supervisor;
    ArrayList<User> students;

    public Project(int projectId, String title, String describ, boolean poster, int confid, User supervisor, ArrayList<User> students) {
        this.projectId = projectId;
        this.title = title;
        this.describ = describ;
        this.poster = poster;
        this.confid = confid;
        this.supervisor = supervisor;
        this.students = students;
    }


    public Project(int projectId, String title) {
        this.projectId = projectId;
        this.title = title;
    }


    public int getProjectId() {
        return this.projectId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescrib() {
        return this.describ;
    }

    public boolean isPoster() {
        return this.poster;
    }

    public int getConfid() {
        return this.confid;
    }

    public User getSupervisor() {
        return this.supervisor;
    }

    public ArrayList<User> getStudents() {
        return this.students;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public void setStudents(ArrayList<User> students) {
        this.students = students;
    }
}
