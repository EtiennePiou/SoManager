package fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.ProjetActivity;
import fr.eseo.dis.pioumansalier.projectandroidi3.R;
import fr.eseo.dis.pioumansalier.projectandroidi3.VisiteurActivity;
import fr.eseo.dis.pioumansalier.projectandroidi3.VisiteursProjetsActivity;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;

public class VisiteurAdapter extends RecyclerView.Adapter<fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.VisiteurAdapter.VisiteurViewHolder> {

        private List<Project> projets= new ArrayList<Project>();

        private VisiteursProjetsActivity visiteurProjetsActivity;

        private List<String> posters = new ArrayList<>();
        public View projetView;
        public VisiteurAdapter(VisiteursProjetsActivity visiteurProjetsActivity){
            this.visiteurProjetsActivity=visiteurProjetsActivity;
            setProjects(projets);
            setPoster(posters);

        }
        public void  setProjects(List<Project> project){
            this.projets=project;


        }
    public void setPoster(List<String> posters){
        this.posters=posters;


    }

        class VisiteurViewHolder extends RecyclerView.ViewHolder {

            private final View view;
            private final TextView projecttitle;
            private final TextView description;
            private final ImageView poster;
            private final Button noter;


            public VisiteurViewHolder(View view) {
                super(view);
                this.view = view;
                projecttitle = view.findViewById(R.id.project);
                description = view.findViewById(R.id.description);
                poster = view.findViewById(R.id.poster);
                noter= view.findViewById(R.id.noter);
            }
        }


        @Override
        public int getItemCount() {
            return projets.size();
        }

        @NonNull
        @Override
        public VisiteurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            projetView = LayoutInflater.from(parent.getContext()).inflate(R.layout.visiteurs_projects, parent, false);
            return new fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.VisiteurAdapter.VisiteurViewHolder(projetView);

        }

        @Override
        public void onBindViewHolder(@NonNull fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.VisiteurAdapter.VisiteurViewHolder holder, int position) {
            final Project project = projets.get(position);
            Log.d("projects",project.getTitle());
            holder.projecttitle.setText(project.getTitle());
            holder.description.setText(project.getDescrib());
            byte [] encodeByte = Base64.decode(posters.get(position),Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            holder.poster.setImageBitmap(bitmap);
            holder.noter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    visiteurProjetsActivity.clickValidateNote(project);
                }
            });
        }
    }


