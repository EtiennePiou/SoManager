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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.ProjectAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.VisiteurAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;

public class VisiteursProjetsActivity  extends AppCompatActivity {

        public static final String PROJET_EXTRA = "project";
        public static final String IMAGE = "image";

        public User user;

        private VisiteurAdapter visiteurAdapter;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.visiteur_project);


            RecyclerView recycler = findViewById(R.id.projectListpseudojury);

            recycler.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(llm);


            visiteurAdapter= new VisiteurAdapter(this);
            recycler.setAdapter(visiteurAdapter);
            loadProjectDetails();
        }

        private void loadProjectDetails(){
            List<Project> projects = DummyData.getPseudoJuryPrjects();
            List<String> posters= DummyData.getPseudoJuryPosters();
            visiteurAdapter.setProjects(projects);
            visiteurAdapter.setPoster(posters);

        }

    public void clickValidateNote(Project projet){
       Intent intent= new Intent(this, VisiteurActivity.class);
       intent.putExtra(PROJET_EXTRA, projet);
       startActivity(intent);
    }

}
