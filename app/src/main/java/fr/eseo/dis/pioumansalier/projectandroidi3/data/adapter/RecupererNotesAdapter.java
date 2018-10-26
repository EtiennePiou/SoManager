package fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.ProjetActivity;
import fr.eseo.dis.pioumansalier.projectandroidi3.R;
import fr.eseo.dis.pioumansalier.projectandroidi3.RecupererNotesActivity;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;

public class RecupererNotesAdapter extends RecyclerView.Adapter<fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.RecupererNotesAdapter.RecupererNotesViewHolder> {

        private List<Project> projets= new ArrayList<Project>();

        private RecupererNotesActivity recupererNotesActivity;


        public RecupererNotesAdapter(RecupererNotesActivity recupererNotesActivity){
            this.recupererNotesActivity=recupererNotesActivity;
            setProjects(projets);

        }
        public void setProjects(List<Project> project){
            this.projets=project;


        }

        class RecupererNotesViewHolder extends RecyclerView.ViewHolder {

            private final View view;
            private final TextView project;
            private final TextView note;

            public RecupererNotesViewHolder(View view) {
                super(view);
                this.view = view;
                project = view.findViewById(R.id.projectId);
                note = view.findViewById(R.id.note);


            }
        }


        @Override
        public int getItemCount() {
            return projets.size();
        }

        @NonNull
        @Override
        public  fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.RecupererNotesAdapter.RecupererNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View projetView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recuperer_note, parent, false);
            return new fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.RecupererNotesAdapter.RecupererNotesViewHolder(projetView);

        }

        @Override
        public void onBindViewHolder(@NonNull fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.RecupererNotesAdapter.RecupererNotesViewHolder holder, int position) {
            final Project project = projets.get(position);
            holder.project.setText(String.valueOf(project.getTitle()));
            Double note=0.0;
            for(int i=0;i<project.getNotesPseudoJury().size();i++) {
                note = note + Double.valueOf(project.getNotesPseudoJury().get(i));

            }
            note=note/project.getNotesPseudoJury().size();
            holder.note.setText(String.valueOf(note));
        }
    }


