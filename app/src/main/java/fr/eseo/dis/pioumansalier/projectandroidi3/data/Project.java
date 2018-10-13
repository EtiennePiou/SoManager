package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Project {
    @PrimaryKey
    int idProject;

    String title;
    String describ;
    boolean poster;
    int confid;
    User supervisor;
    ArrayList<User> students;

    public Project(int idProject, String title, String describ, boolean poster, int confid, User supervisor, ArrayList<User> students) {
        this.idProject = idProject;
        this.title = title;
        this.describ = describ;
        this.poster = poster;
        this.confid = confid;
        this.supervisor = supervisor;
        this.students = students;
    }

    public int getidProject() {
        return this.idProject;
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

    public void setidProject(int idProject) {
        this.idProject = idProject;
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
