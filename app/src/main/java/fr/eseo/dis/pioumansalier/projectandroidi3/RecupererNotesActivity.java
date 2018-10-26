package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Note;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.RecupererNotesAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;

public class RecupererNotesActivity extends AppCompatActivity {


        private RecupererNotesAdapter recupererNotesAdapter;
        private User user;


        public static final String NOTE = "notes";
        public static final String USER = "user";
        public static final String PROJECT = "projets";


        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.recuperer_list);
            RecyclerView recycler = findViewById(R.id.recupererNote);
            recycler.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(llm);
            Intent intent = getIntent();

            recupererNotesAdapter= new RecupererNotesAdapter(this);
            recycler.setAdapter(recupererNotesAdapter);
            loadNoteDetails();
        }

        private void loadNoteDetails(){

            recupererNotesAdapter.setProjects(DummyData.getPseudoJuryPrjects());

        }





    }




