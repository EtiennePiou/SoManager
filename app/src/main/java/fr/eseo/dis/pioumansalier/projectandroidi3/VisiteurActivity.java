package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Project;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.ProjectAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.VisiteurAdapter;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.src.DummyData;

public class VisiteurActivity extends AppCompatActivity{


        public static final String PROJET_EXTRA = "project";
        public static final String IMAGE = "image";
        Project project;
        public User user;
        EditText noteVisiteur;
        Button button;
        EditText commentaireVisiteur;
        private VisiteurAdapter visiteurAdapter;
        TextView errorConnexion;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.visiteur);
            Intent intent=getIntent();
            project=intent.getParcelableExtra(VisiteursProjetsActivity.PROJET_EXTRA);
            noteVisiteur=findViewById(R.id.note);
            commentaireVisiteur=findViewById(R.id.commentaire);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            button = findViewById(R.id.button);
            noteVisiteur.setText("Note");
            commentaireVisiteur.setText("Comm");
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    project.getNotesPseudoJury().add(noteVisiteur.getText().toString());
                    Log.d("TAG ========", Integer.toString(project.getNotesPseudoJury().size()));
                    
                    project.getCommentairesPseudoJury().add(commentaireVisiteur.getText().toString());


                }
            });





        }







       public void clickValidateNote(Project project){
            noteVisiteur= findViewById(R.id.note);
        commentaireVisiteur=findViewById(R.id.commentaire);
        project.getNotesPseudoJury().add(noteVisiteur.getText().toString());
        project.getCommentairesPseudoJury().add(commentaireVisiteur.getText().toString());
     }



}
