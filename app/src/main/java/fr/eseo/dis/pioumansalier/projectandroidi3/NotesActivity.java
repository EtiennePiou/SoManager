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

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Note;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter;


public class NotesActivity extends AppCompatActivity{





        public static final String NOTES_EXTRA = "notes";



        private NotesAdapter notesAdapter;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.notation_student);


            RecyclerView recycler = findViewById(R.id.studentList);

            recycler.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(llm);
            Intent intent = getIntent();
            ArrayList<Note> note = intent.getParcelableArrayListExtra(Activity.NOTES);
            ArrayList<String> idprojects = intent.getStringArrayListExtra(Activity.PROJECTS);
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

        }


    }



