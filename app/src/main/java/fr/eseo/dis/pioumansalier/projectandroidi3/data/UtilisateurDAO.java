package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import java.util.List;

@Dao
public interface UtilisateurDAO {

    @Query("SELECT * FROM INTERNAUTES")
    public List<Internaute> findAllInernautes();
}
