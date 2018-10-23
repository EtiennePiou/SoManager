package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Project implements Parcelable {
        public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>(){
            public Project createFromParcel(Parcel source){
                return new Project(source);
            }

            public Project[] newArray(int size){
                return new Project[size];
            }
        };

    int projectId;
    String title;
    String describ;
    boolean poster;
    int confid;
    String supervisorforename;
    String supervisorsurname;
    List<User> students;

    public Project(int projectId, String title, String describ, boolean poster, int confid, String supervisorforename, String supervisorsurname, List<User> students) {
        this.projectId = projectId;
        this.title = title;
        this.describ = describ;
        this.poster = poster;
        this.confid = confid;
        this.supervisorforename = supervisorforename;
        this.supervisorsurname = supervisorsurname;
        this.students = students;
    }


    public Project(int projectId, String title) {
        this.projectId = projectId;
        this.title = title;
    }


    public Project(Parcel in) {
        this.setProjectId(in.readInt());
        this.setTitle(in.readString());
        this.setDescrib(in.readString());
        this.setPoster(Boolean.valueOf(in.readString()));
        this.setConfid(in.readInt());
        this.setSupervisorforename(in.readString());
        this.setSupervisorsurname(in.readString());

        List<User> liststudent = new ArrayList<User>();
        in.readTypedList(liststudent, User.CREATOR);
        this.setStudents(liststudent);
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

    public String getSupervisorforename() {
        return supervisorforename;
    }

    public String getSupervisorsurname() {
        return supervisorsurname;
    }

    public List<User> getStudents() {
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

    public void setSupervisorforename(String supervisorforename) {
        this.supervisorforename = supervisorforename;
    }

    public void setSupervisorsurname(String supervisorsurname) {
        this.supervisorsurname = supervisorsurname;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.projectId);
        dest.writeString(this.title);
        dest.writeString(this.describ);
        dest.writeString(Boolean.toString(this.poster));
        dest.writeInt(this.confid);
        dest.writeString(this.supervisorforename);
        dest.writeString(this.supervisorsurname);
        dest.writeTypedList(this.students);
    }
}
