package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Note implements Parcelable {
    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>(){
        public Note createFromParcel(Parcel source){
            return new Note(source);
        }

        public Note[] newArray(int size){
            return new Note[size];
        }
    };

    int idUser;
    String forename;
    String surname;
    Double mynote;
    Double avgnote;

    public Note(int idUser, String forename, String surname, Double mynote, Double avgnote) {
        this.setIdUser(idUser);
        this.setForename(forename);
        this.setSurname(surname);
        this.setMynote(mynote);
        this.setAvgnote(avgnote);
    }

    public Note(Parcel in) {
        this.setIdUser(in.readInt());
        this.setForename(in.readString());
        this.setSurname(in.readString());
        this.setMynote(in.readDouble());
        this.setAvgnote(in.readDouble());
    }

    public int getIdUser() {
        return this.idUser;
    }

    public String getForename() {
        return this.forename;
    }

    public String getSurname() {
        return this.surname;
    }

    public Double getMynote() {
        return this.mynote;
    }

    public Double getAvgnote() {
        return this.avgnote;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMynote(Double mynote) {
        this.mynote = mynote;
    }

    public void setAvgnote(Double avgnote) {
        this.avgnote = avgnote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idUser);
        dest.writeString(this.forename);
        dest.writeString(this.surname);
        dest.writeDouble(this.mynote);
        dest.writeDouble(this.avgnote);
    }
}
