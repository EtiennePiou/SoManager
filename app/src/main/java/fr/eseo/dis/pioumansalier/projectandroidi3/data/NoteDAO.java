package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM note")
    public List<Note> findAllNote();
}
