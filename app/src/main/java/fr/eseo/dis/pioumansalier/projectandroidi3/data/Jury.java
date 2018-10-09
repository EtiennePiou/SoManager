package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import java.util.ArrayList;
import java.util.Date;

public class Jury {
    int idJury;
    Date date;
    ArrayList<User> evaluants;
    ArrayList<Project> projets;

    public Jury(int idJury, Date date, ArrayList<User> evaluants, ArrayList<Project> projets) {
        this.setIdJury(idJury);
        this.setDate(date);
        this.setEvaluants(evaluants);
        this.setProjets(projets);
    }

    public int getIdJury() {
        return this.idJury;
    }

    public Date getDate() {
        return this.date;
    }

    public ArrayList<User> getEvaluants() {
        return this.evaluants;
    }

    public ArrayList<Project> getProjets() {
        return this.projets;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEvaluants(ArrayList<User> evaluants) {
        this.evaluants = evaluants;
    }

    public void setProjets(ArrayList<Project> projets) {
        this.projets = projets;
    }
}
