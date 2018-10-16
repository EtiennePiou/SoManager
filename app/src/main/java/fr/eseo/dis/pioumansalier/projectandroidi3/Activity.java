package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.*;
public class Activity extends AppCompatActivity {





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.Projets);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          startActivity(new Intent (getApplicationContext(),ProjetActivity.class));
                                      }
                                  }
        );
    }

}
