package fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.NotesActivity;
import fr.eseo.dis.pioumansalier.projectandroidi3.R;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Note;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;

public class NotesAdapter extends RecyclerView.Adapter<fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter.NoteViewHolder>{


        private List<Note> notes= new ArrayList<Note>();
        private List<String> projets= new ArrayList<String>();

        private NotesActivity notesActivity;


        public NotesAdapter(NotesActivity notesActivity){
            this.notesActivity=notesActivity;
            setNotes(notes);
            setProjects(projets);
        }
        public void setNotes(List<Note> notes){
            this.notes=notes;


        }
        public void setProjects(List<String> projets){
            this.projets=projets;
            }

        class NoteViewHolder extends RecyclerView.ViewHolder {

            private final View view;
            private final TextView surname;
            private final TextView forename;
            private final EditText mynote;
            private final TextView avgnote;
            private final TextView project;

            public NoteViewHolder(View view) {
                super(view);
                this.view = view;
                surname = view.findViewById(R.id.surname);
                forename = view.findViewById(R.id.forename);
                mynote = view.findViewById(R.id.myNote);
                avgnote = view.findViewById(R.id.avgNote);
                project = view.findViewById(R.id.idproject);



            }
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

        @NonNull
        @Override
        public   NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View notesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card, parent, false);
            return new fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter.NoteViewHolder(notesView);

        }


        public void onBindViewHolder(@NonNull fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter.NoteViewHolder holder, int position) {
            final Note note = notes.get(position);
            final String projects = projets.get(position);
            holder.surname.setText(String.valueOf(note.getSurname()));
            holder.forename.setText(note.getForename());
            holder.mynote.setText(String.valueOf(note.getMynote()));
            holder.project.setText(projects);
            holder.avgnote.setText("Note moyenne "+String.valueOf(note.getAvgnote()));
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


