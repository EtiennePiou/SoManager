package fr.eseo.dis.pioumansalier.projectandroidi3.data.src;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Jury;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Note;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;

public class DummyData {

    private static List<Project> LIST_PROJECT = new ArrayList<>();
    private static List<Jury> LIST_JURIES = new ArrayList<>();

    private static List<Project> MY_LIST_PROJECT = new ArrayList<>();
    private static List<Jury> MY_LIST_JURIES = new ArrayList<>();
    private static List<Note> LIST_NOTE = new ArrayList<>();

    private static Jury PSEUDO_JURY;
    private static List<Project> PSEUDO_JURY_PROJECTS = new ArrayList<>();
    private static List<String> PSEUDO_JURY_POSTERS = new ArrayList<>();

    private DummyData() {
    }

    public static List<Project> getListProject() {
        return LIST_PROJECT;
    }

    public static List<Jury> getListJuries() {
        return LIST_JURIES;
    }

    public static List<Project> getMyListProject() {
        return MY_LIST_PROJECT;
    }

    public static List<Jury> getMyListJuries() {
        return MY_LIST_JURIES;
    }

    public static List<Note> getListNote() {
        return LIST_NOTE;
    }

    public static Jury getPseudoJury() {
        return PSEUDO_JURY;
    }

    public static List<Project> getPseudoJuryPrjects() {
        return PSEUDO_JURY_PROJECTS;
    }

    public static void setListProject(List<Project> listProject) {
        LIST_PROJECT = listProject;
    }

    public static void setListJuries(List<Jury> listJuries) {
        LIST_JURIES = listJuries;
    }

    public static void setMyListProject(List<Project> myListProject) {
        MY_LIST_PROJECT = myListProject;
    }

    public static void setMyListJuries(List<Jury> myListJuries) {
        MY_LIST_JURIES = myListJuries;
    }

    public static void setListNote(List<Note> listNote) {
        LIST_NOTE = listNote;
    }

    public static void setPseudoJury(Jury pseudoJury) {
        PSEUDO_JURY = pseudoJury;
    }

    public static void setPseudoJuryProjects(List<Project> pseudoJuryProjects) {
        PSEUDO_JURY_PROJECTS = pseudoJuryProjects;
    }
}
