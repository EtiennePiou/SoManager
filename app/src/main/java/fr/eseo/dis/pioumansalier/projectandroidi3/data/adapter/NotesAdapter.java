package fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        public View notesView;

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
            private final TextView mynote;
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

            notesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_card, parent, false);
            return new fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter.NoteViewHolder(notesView);

        }


        public void onBindViewHolder(@NonNull fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter.NoteViewHolder holder, int position) {
            final Note note = notes.get(position);
            final String projects = projets.get(position);
            holder.surname.setText(String.valueOf(note.getSurname()));
            holder.forename.setText(note.getForename());
            holder.mynote.setText(String.valueOf(note.getMynote()));
            holder.project.setText("Id du projet : "+projects);
            holder.avgnote.setText("Note moyenne : "+String.valueOf(note.getAvgnote()));

        }


    }


