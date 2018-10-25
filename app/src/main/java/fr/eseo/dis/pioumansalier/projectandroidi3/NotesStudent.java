package fr.eseo.dis.pioumansalier.projectandroidi3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.eseo.dis.pioumansalier.projectandroidi3.Activity;
import fr.eseo.dis.pioumansalier.projectandroidi3.R;
import fr.eseo.dis.pioumansalier.projectandroidi3.Util.ServiceWebUtil;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.Note;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.User;
import fr.eseo.dis.pioumansalier.projectandroidi3.data.adapter.NotesAdapter;

public class NotesStudent extends AppCompatActivity {
        private EditText myNote;
        User user;
        Button valider;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.notation);

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            Intent intent = getIntent();
            final String note = intent.getStringExtra(NotesActivity.NOTE);
            final String user = intent.getStringExtra(NotesActivity.USER);
            final String projets = intent.getStringExtra(NotesActivity.PROJECT);
            myNote=findViewById(R.id.myNote);
            myNote.setText(note);

            valider = findViewById(R.id.button);
            valider.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setNoteWebService(projets,user,note);
                }
            });
        }



    }

    public void setNoteWebService(String projectId, String studentId, String newNote){

        final String url = "https://192.168.4.248/pfe/webservice.php?q=NEWNT&user="+user.getUsername()
                +"&proj=" + projectId
                +"&student=" + studentId
                +"&note=" + newNote
                +"&token=" + user.getToken();

        ServiceWebUtil serviceWeb = new ServiceWebUtil(this);


        RequestQueue rq = Volley.newRequestQueue(this, new HurlStack(null, serviceWeb.getSocketFactory()));


        JsonObjectRequest s = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject s) {

                        Log.e("RESULT", String.valueOf(s));
                        try {
                            if(s.getString("result").equals("OK")) {
                                Log.d("OK", "OK");
                            }else{
                                valider.setText("Error : Notes non mise à jour");
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


}
