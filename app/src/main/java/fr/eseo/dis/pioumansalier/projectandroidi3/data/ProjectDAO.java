package fr.eseo.dis.pioumansalier.projectandroidi3.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProjectDAO {
    @Query("SELECT * FROM project")
    public List<Project> findAllProject();

    @Query("SELECT * FROM project WHERE idproject = :idProject")
    public Project findProjectFromId(int idProject);
}
