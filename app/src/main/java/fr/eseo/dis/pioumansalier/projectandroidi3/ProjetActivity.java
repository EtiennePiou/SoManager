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
import com.android.volley.toolbox.StringRequest;
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
    public static final String IMAGE = "image";

    public User  user;

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
        ArrayList<Project> project = intent.getParcelableArrayListExtra(Activity.PROJECTS);
        projectAdapter= new ProjectAdapter(this);
        recycler.setAdapter(projectAdapter);
        loadProjectDetails();
    }

    private void loadProjectDetails(){
        Intent intent = getIntent();
        ArrayList<Project> project = intent.getParcelableArrayListExtra(Activity.PROJECTS);
        projectAdapter.setProjects(project);

        Bundle data = intent.getExtras();
        user = (User) data.getParcelable(Activity.USER);
    }

    public void clickProjectCard(Project project, String response){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra(PROJET_EXTRA, project);
        intent.putExtra(IMAGE, response);
        startActivity(intent);
    }

    public void clickPoster(final Project project) {

        final String url = "https://192.168.4.248/pfe/webservice.php?q=POSTR&user="+user.getUsername()
                + "&proj=" + project.getProjectId()
                + "&style=" + "THB64"
                + "&token="+user.getToken();
        //FULL, THUMB, FLB64, THB64

        ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


        RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


        StringRequest s = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.d("Image ",response);
                clickProjectCard(project, response);
            }
        },new  Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorResponse ", "erreur image");
            }
        });

        rq.add(s);
    }
}
