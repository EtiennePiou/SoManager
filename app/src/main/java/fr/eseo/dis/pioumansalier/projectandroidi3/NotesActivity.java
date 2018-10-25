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


    public static final String NOTE = "notes";
    public static final String USER = "user";
    public static final String PROJECT = "projets";
    public static final String ETUDIANT = "etudiant";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notation_student);
        RecyclerView recycler = findViewById(R.id.studentList);
        recycler.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        Intent intent = getIntent();

        notesAdapter= new NotesAdapter(this);
        recycler.setAdapter(notesAdapter);
        loadNoteDetails();
    }

    private void loadNoteDetails(){
        Intent intent = getIntent();
        ArrayList<Note> note = intent.getParcelableArrayListExtra(Activity.NOTES);
        ArrayList<String> projets = intent.getStringArrayListExtra(Activity.PROJECTS);
        notesAdapter.setNotes(note);
        notesAdapter.setProjects(projets);
        Bundle data = intent.getExtras();
        user = (User) data.getParcelable(Activity.USER);

    }
    public void validate(Note note,String project){
        Intent intent = new Intent(this,NotesStudent.class);
        intent.putExtra(NOTE,""+note.getMynote());
        intent.putExtra(USER,user);
        intent.putExtra(PROJECT,project);
        intent.putExtra(ETUDIANT,""+note.getIdUser());
        startActivity(intent);
    }




}



