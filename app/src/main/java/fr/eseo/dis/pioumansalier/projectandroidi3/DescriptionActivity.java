package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.PortUnreachableException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;


public class DescriptionActivity extends AppCompatActivity{

        private Project project;
        private TextView titre;
        private TextView description;
        private TextView supervisorforename;
        private TextView supervisorsurname;
        private TextView student;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_description);
            Intent intent = getIntent();
            Bundle data = intent.getExtras();
            project =  data.getParcelable(ProjetActivity.PROJET_EXTRA);
            titre = findViewById(R.id.project_title);
            description = findViewById(R.id.project_description);
            supervisorforename = findViewById(R.id.project_supervisorforename);
            supervisorsurname = findViewById(R.id.project_supervisorsurame);
            student = findViewById(R.id.project_student);


            titre.setText(project.getTitle());
            description.setText(project.getDescrib());
            supervisorforename.setText(project.getSupervisorforename());
            supervisorsurname.setText(project.getSupervisorsurname());
            String text="";
            for (int i =0; i<project.getStudents().size();i++){
                    text=project.getStudents().get(i).getSurname()+project.getStudents().get(i).getForename();
            }
            student.setText(text);


        }

}
