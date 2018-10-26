package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;

public class MainActivity extends AppCompatActivity {

    Button connexionButton;
    EditText loginEdit;
    EditText passwdEdit;
    TextView errorConnexion;

    User user;
    public static final String USER = "user";

    public static final String MESSAGE="android";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_permanent);

        Log.d("ERROR CEATION","creation fail !");

        loginEdit = (EditText)findViewById(R.id.editText);
        passwdEdit = (EditText)findViewById(R.id.editText2);
        errorConnexion = (TextView)findViewById(R.id.errorConnexion);

        connexionButton = (Button) findViewById(R.id.button);
        connexionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickConnexion();
            }
        });
        //VISITEUR:
        Button buttonProjetsVisiteurs =  findViewById(R.id.visiteur);
        buttonProjetsVisiteurs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("dedans","de");
                visiteurs();
            }
        });
    }

    public void clickConnexion() {
        final String username = loginEdit.getText().toString();
        final String password = passwdEdit.getText().toString();

        final String url = "https://192.168.4.248/pfe/webservice.php?q=LOGON&user="+username+
                "&pass="+password;

        ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


        RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {

                        Log.e("RESULT", String.valueOf(s));
                        try {
                            if(s.getString("result").equals("OK")) {
                                String token = s.getString("token");
                                user = new User(username, password, token);
                                errorConnexion.setText("");
                                Intent intent = new Intent (getApplicationContext(),Activity.class);
                                intent.putExtra(USER, user);
                                startActivity(intent);
                            }else{
                                errorConnexion.setText("Erreur de connexion");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("RESULTfailder",volleyError.getMessage()); }
                } );

        rq.add(s);
    }

    public void visiteurs(){
        Log.d("dedans","de");
        Intent intent = new Intent(this, VisiteurActivity.class);
        startActivity(intent);
    }
}