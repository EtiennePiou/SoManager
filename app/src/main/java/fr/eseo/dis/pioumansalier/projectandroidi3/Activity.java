package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;

public class Activity extends AppCompatActivity {

    public User  user;
    public static final String PROJECTS = "projets";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonProjet = findViewById(R.id.Projets);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        user = (User) data.getParcelable(MainActivity.USER);

        buttonProjet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickConnexion();
            }
        });
    }

    public void clickConnexion() {

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
                                JSONObject projectsJSON = s.getJSONObject("projects");
                                List<Project> projects = new ArrayList<Project>();
                                while( projectsJSON.keys().hasNext()){

                                    int projectId = projectsJSON.getInt("projectId");
                                    String title = projectsJSON.getString("title");
                                    String descrip = projectsJSON.getString("descrip");
                                    Boolean poster = projectsJSON.getBoolean("poster");
                                    int confid = projectsJSON.getInt("confid");
                                    String forename = projectsJSON.getString("forename");
                                    String surname = projectsJSON.getString("surname");
                                    List<User> students = new ArrayList<User>();
                                    JSONArray listStudents = projectsJSON.getJSONArray("students");
                                    for(int i=0; i<listStudents.length(); i++){
                                        JSONObject student = listStudents.getJSONObject(i);
                                        students.add(new User(student.getInt("userId"),
                                                student.getString("forename"),
                                                student.getString("surname")
                                        ));
                                    }

                                    projects.add(new Project(projectId, title, descrip, poster,
                                            confid, forename, surname, students));
                                }

                                Intent intent = new Intent (getApplicationContext(),Activity.class);
                                intent.putExtra(PROJECTS, projects);
                                startActivity(intent);
                            }else{

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
}
