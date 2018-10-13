package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private int idUser;

    private String username;
    private String password;
    private String token;
    private int role;
    private String forename;
    private String surname;

    User(String username, String password, String token, int role, int idUser, String forename, String surnam){
        this.setUsername(username);
        this.setPassword(password);
        this.setToken(token);
        this.setRole(role);
        this.setidUser(idUser);
        this.setForename(forename);
        this.setSurname(surnam);
    }

    /* Accesseurs et Mutateurs */
    /**
     * Accessseur pour récupérer le nom de l'utilisateur
     * @return username
     */
    public String getUsername() {
        return this.username;
    }
    /**
     * Mutateur pour modifier le nom de l'utilisateur
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Accessseur pour récupérer le mot de passe de l'utilisateur
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    /**
     * Mutateur pour modifier le mot de passe de l'utilisateur
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Accessseur pour récupérer le token
     * @return token
     */
    public String getToken() {
        return this.token;
    }
    /**
     * Mutateur pour modifier le token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Accessseur pour récupérer le role
     * @return role
     */
    public int getRole() {
        return this.role;
    }
    /**
     * Mutateur pour modifier le role
     * @param role
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Accessseur pour récupérer l'id de l'utilisateur
     * @return idUser
     */
    public int getidUser() {
        return this.idUser;
    }
    /**
     * Mutateur pour modifier l'id de l'utilisateur
     * @param idUser
     */
    public void setidUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Accessseur pour récupérer prénom
     * @return forename
     */
    public String getForename() {
        return this.forename;
    }
    /**
     * Mutateur pour modifier le prénom
     * @param forename
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * Accessseur pour récupérer nom
     * @return surname
     */
    public String getSurname() {
        return this.surname;
    }
    /**
     * Mutateur pour modifier le nom
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
