package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

public static final String MESSAGE="android";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_permanent);
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          startActivity(new Intent (getApplicationContext(),Activity.class));
                                      }
                                  }
        );
    }
    public void visitorclient(View view){
        Intent intent = new Intent (this,Visiteur.class);
        intent.putExtra(MESSAGE, "bonjour");
        startActivity(intent);

    }
}
