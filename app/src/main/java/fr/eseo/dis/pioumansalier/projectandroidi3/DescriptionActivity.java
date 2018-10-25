package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.PortUnreachableException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.ProjectAdapter;


public class DescriptionActivity extends AppCompatActivity{

        private Project project;
        private TextView titre;
        private TextView description;
        private TextView supervisorforename;
        private TextView supervisorsurname;
        private TextView student;
        private ImageView project_image;
        private TextView confidentiel;
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
            project_image = findViewById(R.id.project_image);
            confidentiel=findViewById(R.id.project_confidentiality);
            String image = intent.getStringExtra(ProjetActivity.IMAGE);
            byte [] encodeByte = Base64.decode(image,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            project_image.setImageBitmap(bitmap);

            titre.setText(project.getTitle());
            confidentiel.setText("Confidentiel : "+project.getConfid());
            description.setText(project.getDescrib());
            supervisorforename.setText("Superviseur : " +project.getSupervisorforename()+project.getSupervisorsurname());
            String text="Etudiants : "+"\n";
            for (int i =0; i<project.getStudents().size();i++){
                    text=text+"Prénom "+project.getStudents().get(i).getSurname()+" Nom "+project.getStudents().get(i).getForename()+"\n";
            }
            student.setText(text);
        }
}
