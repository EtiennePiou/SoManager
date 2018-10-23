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

import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.ProjectAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;

public class ProjetActivity  extends AppCompatActivity {

    public static final String PROJET_EXTRA = "test";

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

        projectAdapter= new ProjectAdapter(this);
        recycler.setAdapter(projectAdapter);
        //loadProjectDetails();


    }
    private void loadProjectDetails(){
        getProjects();
        projectAdapter.setProjects(DummyData.getProjects());
    }

    private void getProjects(){

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
       final User user = (User) data.getParcelable(MainActivity.USER);
        final String url = "https://192.168.4.248/pfe/webservice.php?q=LIPRJ&user="+user.getUsername()+"&token="+user.getToken();

        ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


        RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {

                        Log.e("RESULT", String.valueOf(s));
                        try {
                            if(s.getString("result").equals("OK")) {
                                String token = s.getString("token");
                                errorConnexion.setText("");
                                startActivity(new Intent(getApplicationContext(),Activity.class).putExtra("userId",user.getidUser()));
                            }else{
                                errorConnexion.setText("Erreur de connexion");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("RESULTfailder",volleyError.getMessage()); }
                } );
        rq.add(s);
    }




    public void clickProjectCard(Project project){

    }

}
