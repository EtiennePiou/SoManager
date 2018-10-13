package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.ProjectAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;

public class ProjetActivity  extends AppCompatActivity {

    private ProjectAdapter projectAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projects_list);


        RecyclerView recycler = findViewById(R.id.projectList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);

        projectAdapter= new ProjectAdapter(this);
        recycler.setAdapter(projectAdapter);
        loadProjectDetails();


    }
    private void loadProjectDetails(){

        projectAdapter.setProjects(DummyData.getProjects());
    }
}
