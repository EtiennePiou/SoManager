package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import java.util.List;
import android.arch.persistence.room.Dao;


@DAO
public interface UtilisateurDAO {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * from user_table ORDER BY id ASC")
    List<User> getAllUser();
}