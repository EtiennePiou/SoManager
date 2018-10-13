package fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import fr.eseo.dis.pioumansalier.projectandroidi3.R;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.ProjetActivity;
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<Project> projets;

    private ProjetActivity projectActivity;


    public ProjectAdapter(ProjetActivity projectActivity){
        this.projectActivity=projectActivity;
        setProjects(projets);

    }
    public void  setProjects(List<Project> project){
        this.projets=project;


    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final TextView projectId;
        private final TextView title;

        public ProjectViewHolder(View view) {
            super(view);
            this.view = view;
            projectId = view.findViewById(R.id.projectId);
            title = view.findViewById(R.id.title);


        }
    }


    @Override
    public int getItemCount() {
        return projets.size();
    }

    @NonNull
    @Override
    public  ProjectAdapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View projetView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_case, parent, false);
        return new ProjectViewHolder(projetView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ProjectViewHolder holder, int position) {
        final Project project = projets.get(position);
        holder.projectId.setText(String.valueOf(project.getProjectId()));
        holder.title.setText(project.getTitle());
    }

}
