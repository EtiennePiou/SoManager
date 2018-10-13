package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;
public class Visiteur extends AppCompatActivity {


    ArrayList<String> projets  = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String messsage = intent.getStringExtra(MainActivity.MESSAGE);
        TextView textView = new TextView(this);
        textView.setText(messsage);
        setContentView(R.layout.activity_connexion_permanent);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          startActivity(new Intent (getApplicationContext(),ProjetActivity.class));
                                      }
                                  }
        );

    }

}
