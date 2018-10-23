package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Jury;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.JuryDAO;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.JuryAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.ProjectAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;


public class JuriesActivity extends AppCompatActivity {
    private JuryAdapter juryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_juries);


        RecyclerView recycler = findViewById(R.id.JuriesList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);

        juryAdapter= new JuryAdapter(this);
        recycler.setAdapter(juryAdapter);
        loadProjectDetails();



    }
    private void loadProjectDetails(){
        Intent intent = getIntent();
        ArrayList<Jury> juries = intent.getParcelableArrayListExtra(Activity.JURIES);
        juryAdapter.setJurys(juries);
    }
}



