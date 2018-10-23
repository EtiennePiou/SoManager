package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Jury implements Parcelable {
    public static final Parcelable.Creator<Jury> CREATOR = new Parcelable.Creator<Jury>(){
        public Jury createFromParcel(Parcel source){
            return new Jury(source);
        }

        public Jury[] newArray(int size){
            return new Jury[size];
        }
    };

    @PrimaryKey
    int idJury;

    String date;
    List<User> evaluants;
    List<Project> projets;

    public Jury(int idJury, String date, ArrayList<User> evaluants, List<Project> projets) {
        this.setIdJury(idJury);
        this.setDate(date);
        this.setEvaluants(evaluants);
        this.setProjets(projets);
    }

    public Jury(int idJury,ArrayList<Project> projets) {
        this.setIdJury(idJury);
        this.projets=projets;
    }
    public Jury(int idJury) {
        this.setIdJury(idJury);

    }

    public Jury(Parcel in) {
        this.setIdJury(in.readInt());
        this.setDate(in.readString());

        List<User> listsEvaluants = new ArrayList<>();
        in.readTypedList(listsEvaluants, User.CREATOR);
        this.setEvaluants(listsEvaluants);

        List<Project> listsProjects = new ArrayList<>();
        in.readTypedList(listsProjects, Project.CREATOR);
        this.setProjets(listsProjects);
    }

    public int getIdJury() {
        return this.idJury;
    }

    public String getDate() {
        return this.date;
    }

    public List<User> getEvaluants() {
        return this.evaluants;
    }

    public List<Project> getProjets() {
        return this.projets;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEvaluants(List<User> evaluants) {
        this.evaluants = evaluants;
    }

    public void setProjets(List<Project> projets) {
        this.projets = projets;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idJury);
        dest.writeString(this.date);
        dest.writeTypedList(this.evaluants);
        dest.writeTypedList(this.projets);
    }
}
