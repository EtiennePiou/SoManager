package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface UserDAO {

    @Query("SELECT * FROM user")
    public List<User>  findAllUser();

    @Query("SELECT * FROM user WHERE iduser = :idUser")
    public User findUserFromId(int idUser);
}