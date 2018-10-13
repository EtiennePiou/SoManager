package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Entity;

@Entity
public class Note {
    int idUser;
    String forename;
    String surname;
    float mynote;
    float avgnote;

    public Note(int idUser, String forename, String surname, float mynote, float avgnote) {
        this.setIdUser(idUser);
        this.setForename(forename);
        this.setSurname(surname);
        this.setMynote(mynote);
        this.setAvgnote(avgnote);
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

    public float getMynote() {
        return this.mynote;
    }

    public float getAvgnote() {
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

    public void setMynote(float mynote) {
        this.mynote = mynote;
    }

    public void setAvgnote(float avgnote) {
        this.avgnote = avgnote;
    }
}
