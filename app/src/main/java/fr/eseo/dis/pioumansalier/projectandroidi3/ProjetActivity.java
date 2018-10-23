package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.ProjectAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;

public class ProjetActivity  extends AppCompatActivity {

    public static final String PROJET_EXTRA = "project";

    private ProjectAdapter projectAdapter;
    TextView errorConnexion;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projects_list);


        RecyclerView recycler = findViewById(R.id.projectList);
        errorConnexion = findViewById(R.id.errorConnexion);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        final List<Project> project = (ArrayList<Project>) data.getParcelable(Activity.PROJECTS);
        System.out.print(project.get(0));
        projectAdapter= new ProjectAdapter(this);
        recycler.setAdapter(projectAdapter);
        loadProjectDetails();


    }
    private void loadProjectDetails(){
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        final List<Project> project =  data.getParcelable(Activity.PROJECTS);
        projectAdapter.setProjects(project);


    }

    public void clickProjectCard(Project project){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra(PROJET_EXTRA, project);
        startActivity(intent);
    }

}
