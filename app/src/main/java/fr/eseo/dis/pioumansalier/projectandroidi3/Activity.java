package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Jury;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Note;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;

public class Activity extends AppCompatActivity {

    public User  user;

    public static final String USER = "user";
    public static final String PROJECTS = "projets";
    public static final String JURIES = "juries";
    public static final String NOTES = "notes";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonProjet = findViewById(R.id.Projets);
        final Button buttonJury = findViewById(R.id.Juries);
        final Button buttonNote = findViewById(R.id.Notes);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        user = (User) data.getParcelable(MainActivity.USER);

        buttonProjet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickProject();
            }
        });

        buttonJury.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickJury();
            }
        });

        buttonNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickNote();
            }
        });
    }

    public void clickProject() {

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
                                JSONArray projectsJSON = s.getJSONArray("projects");
                                List<Project> projects = new ArrayList<>();
                                for(int i=0; i < projectsJSON.length(); i++ ){

                                    JSONObject projectJSON = projectsJSON.getJSONObject(i);

                                    int projectId = projectJSON.getInt("projectId");
                                    String title = projectJSON.getString("title");
                                    String descrip = projectJSON.getString("descrip");
                                    Boolean poster = projectJSON.getBoolean("poster");
                                    int confid = projectJSON.getInt("confid");
                                    JSONObject supervisorJSON = projectJSON.getJSONObject("supervisor");
                                    String forename = supervisorJSON.getString("forename");
                                    String surname = supervisorJSON.getString("surname");
                                    List<User> students = new ArrayList<User>();
                                    JSONArray listStudents = projectJSON.getJSONArray("students");
                                    for(int j=0; j<listStudents.length(); j++){
                                        JSONObject student = listStudents.getJSONObject(j);
                                        students.add(new User(student.getInt("userId"),
                                                student.getString("forename"),
                                                student.getString("surname")
                                        ));
                                    }

                                    projects.add(new Project(projectId, title, descrip, poster,
                                            confid, forename, surname, students));
                                }

                                Intent intent = new Intent(getApplicationContext(),ProjetActivity.class);
                                intent.putParcelableArrayListExtra(PROJECTS, (ArrayList<? extends Parcelable>) projects);
                                intent.putExtra(USER, user);


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

    public void clickJury(){

        final String url = "https://192.168.4.248/pfe/webservice.php?q=MYJUR&user="+user.getUsername()+"&token="+user.getToken();

        ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


        RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {

                        Log.e("RESULT", String.valueOf(s));
                        try {
                            if(s.getString("result").equals("OK")) {
                                JSONArray juriesJSON = s.getJSONArray("juries");
                                List<Jury> juries = new ArrayList<>();
                                for(int i=0; i < juriesJSON.length(); i++ ){

                                    JSONObject juryJSON = juriesJSON.getJSONObject(i);

                                    int juryId = juryJSON.getInt("idJury");
                                    String date = juryJSON.getString("date");

                                    JSONObject infoJSON = juryJSON.getJSONObject("info");
                                    JSONArray projectsJSON = infoJSON.getJSONArray("projects");

                                    List<Project> projects = new ArrayList<Project>();
                                    for(int j=0; j<projectsJSON.length(); j++){
                                        JSONObject projectJSON = projectsJSON.getJSONObject(j);
                                        int projectId = projectJSON.getInt("projectId");
                                        String title = projectJSON.getString("title");
                                        int confid = projectJSON.getInt("confid");
                                        Boolean poster = projectJSON.getBoolean("poster");
                                        JSONObject supervisorJSON = projectJSON.getJSONObject("supervisor");
                                        String forename = supervisorJSON.getString("forename");
                                        String surname = supervisorJSON.getString("surname");
                                        projects.add(new Project(projectId, title, "", poster,
                                                confid, forename, surname, null));
                                    }

                                    juries.add(new Jury(juryId, date, null, projects));
                                }

                                Log.d("TEST ", juries.get(0).getProjets().get(0).getTitle());

                                Intent intent = new Intent(getApplicationContext(),JuriesActivity.class);
                                intent.putParcelableArrayListExtra(JURIES, (ArrayList<? extends Parcelable>) juries);

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

    public void clickNote(){

        final String url = "https://192.168.4.248/pfe/webservice.php?q=NOTES&user="+user.getUsername()
                +"&proj=" + "4"
                +"&token=" + user.getToken();

        ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


        RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {

                        Log.e("RESULT", String.valueOf(s));
                        try {
                            if(s.getString("result").equals("OK")) {
                                JSONArray notesJSONarray = s.getJSONArray("notes");
                                List<Note> notes = new ArrayList<>();
                                for(int i=0; i < notesJSONarray.length(); i++ ){

                                    JSONObject noteJSON = notesJSONarray.getJSONObject(i);

                                    int userId = noteJSON.getInt("userId");
                                    String forename = noteJSON.getString("forename");
                                    String surname = noteJSON.getString("surname");

                                    Double mynote = noteJSON.getDouble("mynote");
                                    Double avgnote = noteJSON.getDouble("avgnote");

                                    notes.add(new Note(userId, forename, surname, mynote, avgnote));
                                }

                                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                                intent.putParcelableArrayListExtra(NOTES, (ArrayList<? extends Parcelable>) notes);

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
