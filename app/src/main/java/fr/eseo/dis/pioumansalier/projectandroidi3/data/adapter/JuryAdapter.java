package fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.JuriesActivity;
import fr.eseo.dis.pioumansalier.projectandroidi3.R;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Jury;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.ProjetActivity;
public class JuryAdapter extends RecyclerView.Adapter<JuryAdapter.JuryViewHolder> {

    private List<Jury> jurys;

    private JuriesActivity juriesActivity;


    public JuryAdapter(JuriesActivity juriesActivity){
        this.juriesActivity=juriesActivity;
        setJurys(jurys);

    }
    public void setJurys(List<Jury> jurys){
        this.jurys=jurys;


    }

    class JuryViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final TextView juryId;
        private final TextView date;
        private final TextView projets;

        public JuryViewHolder(View view) {
            super(view);
            this.view = view;
            juryId = view.findViewById(R.id.juryId);
            date = view.findViewById(R.id.date);
            projets=view.findViewById(R.id.Projets);
        }
    }


    @Override
    public int getItemCount() {
        return jurys.size();
    }

    @NonNull
    @Override
    public  JuryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View juryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jury_case, parent, false);
        return new JuryAdapter.JuryViewHolder(juryView);

    }

    @Override
    public void onBindViewHolder(@NonNull JuryAdapter.JuryViewHolder holder, int position) {
        final Jury jury = jurys.get(position);
        holder.juryId.setText("Id "+String.valueOf(jury.getIdJury()));
        holder.date.setText(String.valueOf(jury.getDate()));
        String projets="";
        for (int i=0;i<jury.getProjets().size();i++){
            projets=projets+"Projet n°"+i+"\n"+jury.getProjets().get(i).getDescrib();
        }
        holder.projets.setText(projets);
    }

}
