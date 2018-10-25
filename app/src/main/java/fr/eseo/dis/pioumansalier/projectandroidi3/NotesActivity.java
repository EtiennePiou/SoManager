package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Note;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter;

import static java.lang.Boolean.TRUE;


public class NotesActivity extends AppCompatActivity{
    public static final String NOTES_EXTRA = "notes";

    private NotesAdapter notesAdapter;
    private User user;

    private ArrayList<Note> notes;
    private ArrayList<String> projets;

    private TextView validerounon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notation_student);

        RecyclerView recycler = findViewById(R.id.studentList);

        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        notesAdapter= new NotesAdapter(this);
        recycler.setAdapter(notesAdapter);
        loadNoteDetails();

        final Button buttonon = (Button)findViewById(R.id.button);
        buttonon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("tag",((EditText)(findViewById(R.id.myNote))).getText().toString());
                validate();
            }
        });

    }

    private void loadNoteDetails(){
        Intent intent = getIntent();
        this.notes = intent.getParcelableArrayListExtra(Activity.NOTES);
        this.projets = intent.getStringArrayListExtra(Activity.PROJECTS);
        notesAdapter.setNotes(notes);
        notesAdapter.setProjects(projets);

        Bundle data = intent.getExtras();
        user = (User) data.getParcelable(Activity.USER);
    }

    public void validate(){

        for(int i=0; i<notes.size(); i++){

            setNoteWebService(projets.get(i),
                    Integer.toString(notes.get(i).getIdUser()),
                    Double.toString(notes.get(i).getMynote()));
        }
    }

    public void setNoteWebService(String projectId, String studentId, String newNote){

        final String url = "https://192.168.4.248/pfe/webservice.php?q=NEWNT&user="+user.getUsername()
                +"&proj=" + projectId
                +"&student=" + studentId
                +"&note=" + newNote
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
                                Log.d("OK", "OK");
                            }else{
                                validerounon.setText("Error : Notes non mise à jour");
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



