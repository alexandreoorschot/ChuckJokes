package com.android.chuckjokes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;
import android.view.View;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        final EditText T1 = (EditText) findViewById(R.id.editText);
        final Button b1 = (Button) findViewById(R.id.joke);
        final Button s1 = (Button) findViewById(R.id.save);
        final Button d1 = (Button) findViewById(R.id.delete);
        final String PREFS_NAME = "preferencias";
        final EditText piadaSalva =(EditText) findViewById(R.id.camposalvo);
        s1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    SharedPreferences preferencia = getBaseContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edite = preferencia.edit();
                    edite.putString("Piada",T1.getText().toString());
                    if(edite.commit()){
                        Toast.makeText(getBaseContext(), "HUE", Toast.LENGTH_LONG).show();
                    }
                    String tempoString=null;
                    tempoString = preferencia.getString("Piada","defaultStringIfNothingFound");
                    piadaSalva.setText(tempoString);
                }catch (Exception e){
                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        d1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences preferencia = getBaseContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edite = preferencia.edit();
                    edite.putString("Piada","");
                    if(edite.commit()){
                        Toast.makeText(getBaseContext(), "HUE", Toast.LENGTH_LONG).show();
                    }
                    String tempoString=null;
                    tempoString = preferencia.getString("Piada","defaultStringIfNothingFound");
                    piadaSalva.setText(tempoString);
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String quoteText = new HttpConnect().BGhelper("https://api.chucknorris.io/jokes/random");
                    HttpConnect H1 = new HttpConnect();
                    T1.setText(H1.BGhelper("https://api.chucknorris.io/jokes/random"));
                    Json json = new Json(quoteText);
                    final EditText t1 = (EditText) findViewById(R.id.editText);
                    t1.setText(json.getValue());
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }

        });
        try {
            b1.performClick();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

}
