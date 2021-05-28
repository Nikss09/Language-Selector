package com.example.languageselector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView textView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.language,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.english:
                Toast.makeText(MainActivity.this,"Language changed English",Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putString("language","English").apply();
                textView.setText("Hello Friends! How are you?");

                return true;
            case R.id.spanish:
                Toast.makeText(MainActivity.this,"Language changed to  Spanish",Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putString("language","Spanish").apply();
                textView.setText("Hola amigos! Cómo estás?");
                return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=this.getSharedPreferences("com.example.alertdemo", Context.MODE_PRIVATE);
        //sharedPreferences.edit().clear().apply();
        textView=findViewById(R.id.textView);

        String language=sharedPreferences.getString("language","default");

        if(language.equals("default")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_btn_speak_now);
            builder.setTitle("Language?");
            builder.setMessage("Select the language of the app.");

            builder.setPositiveButton("English", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "You pressed English", Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putString("language", "English").apply();
                    textView.setText("Hello Friends! How are You?");
                }
            });

            builder.setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "You pressed Spanish", Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putString("language", "Spanish").apply();
                    textView.setText("Hola amigos! Cómo estás?");
                }
            });
            builder.show();
        }
        else{
            if(language.equals("English"))
                textView.setText("Hello Friends! How are You?");
            else if(language.equals("Spanish"))
                textView.setText("Hola amigos! Cómo estás?");
        }
    }
}