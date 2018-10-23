package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface JuryDAO {

    @Query("SELECT * FROM jury")
    public List<Jury> findAllJury();

    @Query("SELECT * FROM jury WHERE idjury = :idJury")
    public Jury findJuryFromId(int idJury);

    @Query("SELECT idJury,date FROM jury WHERE idjury = :idJury")
    public Jury findJuryAndDateFromId(int idJury);
}
