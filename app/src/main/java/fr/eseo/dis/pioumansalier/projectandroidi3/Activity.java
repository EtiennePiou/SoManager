package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Activity extends AppCompatActivity {

    public User  user;
    public List<Note> listNotes = new ArrayList<>();
    public ArrayList<String> listIdProject = new ArrayList<>();

    public static final String USER = "user";
    public static final String PROJECTS = "projets";
    public static final String JURIES = "juries";
    public static final String NOTES = "notes";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GENERAL:
        final Button buttonProjet = findViewById(R.id.Projets);
        final Button buttonJury = findViewById(R.id.Juries);

        // PROFS:
        final Button buttonMyJuries = findViewById(R.id.MyJuries);
        final Button buttonMyProjets = findViewById(R.id.MyProjets);
        final Button buttonNote = findViewById(R.id.Notes);

        //COM:
        final Button buttonPseudoJury = findViewById(R.id.PseudoJury);
        final Button buttonRecupererNotes = findViewById(R.id.RecupererNotes);

        //VISITEUR:
        final Button buttonProjetsVisiteurs = findViewById(R.id.ProjetsVisiteurs);

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
                clickJury(FALSE);
            }
        });

        buttonMyJuries.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickMyJury(FALSE);
            }
        });
        buttonMyProjets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickMyProject();
            }
        });
        buttonNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickMyJury(TRUE);
            }
        });

        buttonPseudoJury.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickPseudoJury();
            }
        });
        buttonRecupererNotes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickRecupererNotes();
            }
        });

        buttonProjetsVisiteurs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickProjetsVisiteurs();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void clickProject() {
        if(isNetworkAvailable()){

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
                                        List<User> students = new ArrayList<>();
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

                                    //Ajout de la liste des projets dans la DummyData:
                                    DummyData.setListProject(projects);


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
        }else{
            Intent intent = new Intent(getApplicationContext(),ProjetActivity.class);
            intent.putParcelableArrayListExtra(PROJECTS, (ArrayList<? extends Parcelable>) DummyData.getListProject());
            intent.putExtra(USER, user);
            startActivity(intent);
        }


    }

    public void clickJury(final boolean isClickNote){
        if(isNetworkAvailable()) {

            final String url = "https://192.168.4.248/pfe/webservice.php?q=LIJUR&user=" + user.getUsername() + "&token=" + user.getToken();

            ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


            RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


            JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject s) {

                            Log.e("RESULT", String.valueOf(s));
                            try {
                                if (s.getString("result").equals("OK")) {
                                    JSONArray juriesJSON = s.getJSONArray("juries");
                                    List<Jury> juries = new ArrayList<>();
                                    for (int i = 0; i < juriesJSON.length(); i++) {

                                        JSONObject juryJSON = juriesJSON.getJSONObject(i);

                                        int juryId = juryJSON.getInt("idJury");
                                        String date = juryJSON.getString("date");

                                        JSONObject infoJSON = juryJSON.getJSONObject("info");
                                        JSONArray projectsJSON = infoJSON.getJSONArray("projects");

                                        List<Project> projects = new ArrayList<Project>();
                                        for (int j = 0; j < projectsJSON.length(); j++) {
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

                                    DummyData.setListJuries(juries);

                                    if (isClickNote == FALSE) {
                                        Intent intent = new Intent(getApplicationContext(), JuriesActivity.class);
                                        intent.putParcelableArrayListExtra(JURIES, (ArrayList<? extends Parcelable>) juries);

                                        startActivity(intent);
                                    } else {
                                        clickNote(juries);
                                    }

                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("RESULTfailder", volleyError.getMessage());
                        }
                    });
            rq.add(s);
        }else{
            if (isClickNote == FALSE) {
                Intent intent = new Intent(getApplicationContext(), JuriesActivity.class);
                intent.putParcelableArrayListExtra(JURIES, (ArrayList<? extends Parcelable>) DummyData.getListJuries());

                startActivity(intent);
            } else {
                clickNote(DummyData.getListJuries());
            }
        }
    }

    //PROF
    public void clickMyProject() {
        if(isNetworkAvailable()) {

            final String url = "https://192.168.4.248/pfe/webservice.php?q=MYPRJ&user=" + user.getUsername() + "&token=" + user.getToken();

            ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


            RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


            JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject s) {

                            Log.e("RESULT", String.valueOf(s));
                            try {
                                if (s.getString("result").equals("OK")) {
                                    JSONArray projectsJSON = s.getJSONArray("projects");
                                    List<Project> projects = new ArrayList<>();
                                    for (int i = 0; i < projectsJSON.length(); i++) {

                                        JSONObject projectJSON = projectsJSON.getJSONObject(i);

                                        int projectId = projectJSON.getInt("projectId");
                                        String title = projectJSON.getString("title");
                                        String descrip = projectJSON.getString("descrip");
                                        Boolean poster = projectJSON.getBoolean("poster");
                                        int confid = projectJSON.getInt("confid");
                                        JSONObject supervisorJSON = projectJSON.getJSONObject("supervisor");
                                        String forename = supervisorJSON.getString("forename");
                                        String surname = supervisorJSON.getString("surname");
                                        List<User> students = new ArrayList<>();
                                        JSONArray listStudents = projectJSON.getJSONArray("students");
                                        for (int j = 0; j < listStudents.length(); j++) {
                                            JSONObject student = listStudents.getJSONObject(j);
                                            students.add(new User(student.getInt("userId"),
                                                    student.getString("forename"),
                                                    student.getString("surname")
                                            ));
                                        }

                                        projects.add(new Project(projectId, title, descrip, poster,
                                                confid, forename, surname, students));
                                    }

                                    DummyData.setMyListProject(projects);

                                    Intent intent = new Intent(getApplicationContext(), ProjetActivity.class);
                                    intent.putParcelableArrayListExtra(PROJECTS, (ArrayList<? extends Parcelable>) projects);
                                    intent.putExtra(USER, user);


                                    startActivity(intent);
                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("RESULTfailder", volleyError.getMessage());
                        }
                    });
            rq.add(s);
        }else{
            Intent intent = new Intent(getApplicationContext(), ProjetActivity.class);
            intent.putParcelableArrayListExtra(PROJECTS, (ArrayList<? extends Parcelable>) DummyData.getMyListProject());
            intent.putExtra(USER, user);
            startActivity(intent);
        }
    }

    public void clickMyJury(final boolean isClickNote){
        if(isNetworkAvailable()) {

            final String url = "https://192.168.4.248/pfe/webservice.php?q=MYJUR&user=" + user.getUsername() + "&token=" + user.getToken();

            ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


            RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


            JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject s) {

                            Log.e("RESULT", String.valueOf(s));
                            try {
                                if (s.getString("result").equals("OK")) {
                                    JSONArray juriesJSON = s.getJSONArray("juries");
                                    List<Jury> juries = new ArrayList<>();
                                    for (int i = 0; i < juriesJSON.length(); i++) {

                                        JSONObject juryJSON = juriesJSON.getJSONObject(i);

                                        int juryId = juryJSON.getInt("idJury");
                                        String date = juryJSON.getString("date");

                                        JSONObject infoJSON = juryJSON.getJSONObject("info");
                                        JSONArray projectsJSON = infoJSON.getJSONArray("projects");

                                        List<Project> projects = new ArrayList<Project>();
                                        for (int j = 0; j < projectsJSON.length(); j++) {
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

                                    DummyData.setMyListJuries(juries);

                                    if (isClickNote == FALSE) {
                                        Intent intent = new Intent(getApplicationContext(), JuriesActivity.class);
                                        intent.putParcelableArrayListExtra(JURIES, (ArrayList<? extends Parcelable>) juries);

                                        startActivity(intent);
                                    } else {
                                        clickNote(juries);
                                    }

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("RESULTfailder", volleyError.getMessage());
                        }
                    });
            rq.add(s);
        }else{
            if (isClickNote == FALSE) {
                Intent intent = new Intent(getApplicationContext(), JuriesActivity.class);
                intent.putParcelableArrayListExtra(JURIES, (ArrayList<? extends Parcelable>) DummyData.getMyListJuries());

                startActivity(intent);
            } else {
                clickNote(DummyData.getMyListJuries());
            }
        }
    }

    public void webServiceNote(final String projectId, final Boolean lastProject, final List<Jury> juries){

        final String url = "https://192.168.4.248/pfe/webservice.php?q=NOTES&user="+user.getUsername()
                +"&proj=" + projectId
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
                                for(int i=0; i < notesJSONarray.length(); i++ ){

                                    JSONObject noteJSON = notesJSONarray.getJSONObject(i);

                                    int userId = noteJSON.getInt("userId");
                                    String forename = noteJSON.getString("forename");
                                    String surname = noteJSON.getString("surname");

                                    Double mynote = 0.0;
                                    if(!noteJSON.getString("mynote").equals("null")){
                                        mynote = noteJSON.getDouble("mynote");
                                    }
                                    Double avgnote = 0.0;
                                    if(!noteJSON.getString("avgNote").equals("null")){
                                        avgnote = noteJSON.getDouble("avgNote");
                                    }

                                    listNotes.add(new Note(userId, forename, surname, mynote, avgnote));
                                    listIdProject.add(projectId);
                                }

                                if(lastProject == TRUE){
                                    for(Note note: listNotes){
                                        Log.d("Note ", Double.toString(note.getMynote()));
                                    }
                                    Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                                    intent.putParcelableArrayListExtra(NOTES, (ArrayList<? extends Parcelable>) listNotes);
                                    intent.putStringArrayListExtra(PROJECTS, listIdProject);
                                    intent.putExtra(USER, user);
                                    startActivity(intent);
                                }

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

    public void clickNote(List<Jury> juries){
        Boolean lastProject = FALSE;
        listNotes.clear();
        for(int i=0; i<juries.size(); i++){
            List<Project> projects = juries.get(i).getProjets();
            for(int j=0; j<projects.size(); j++){
                if(i == juries.size()-1 && j==projects.size()-1){
                    lastProject = TRUE;
                }
                webServiceNote(Integer.toString(projects.get(j).getProjectId()), lastProject, juries);
            }
        }
    }

    //COM
    public void clickPseudoJury(){

        final String url = "https://192.168.4.248/pfe/webservice.php?q=MYPRJ&user="+user.getUsername()+"&token="+user.getToken();

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
                                    List<User> students = new ArrayList<>();
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

    public void clickRecupererNotes(){

    }

    //VISITEUR
    public void clickProjetsVisiteurs(){

    }

}
