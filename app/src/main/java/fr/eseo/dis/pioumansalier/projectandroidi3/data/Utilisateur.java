package fr.eseo.dis.pioumansalier.projectandroidi3.data;

public class Utilisateur {
    private String username;
    private String hash;
    private String token;

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
    public void setIdUtilisateur(String username) {
        this.username = username;
    }

    /**
     * Accessseur pour récupérer le hash du mot de passe de l'utilisateur
     * @return hash
     */
    public String getHash() {
        return this.hash;
    }
    /**
     * Mutateur pour modifier le hash du mot de passe de l'utilisateur
     * @param hash
     */
    public void setHash(String hash) {
        this.hash = hash;
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
}
