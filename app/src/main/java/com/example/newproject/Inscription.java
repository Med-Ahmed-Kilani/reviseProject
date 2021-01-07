package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    Button ok,cancel;
    EditText prenom, nom, login, password;
    Spinner spinnerClasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ok = findViewById(R.id.OkResult);
        cancel = findViewById(R.id.CancelResult);
        prenom = findViewById(R.id.inputPrenom);
        spinnerClasse = findViewById(R.id.spinnerClasse);
        nom = findViewById(R.id.inputNom);
        login = findViewById(R.id.inputlogin);
        password = findViewById(R.id.inputPassword);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClasse.setAdapter(adapter);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastName = prenom.getText().toString().trim();
                if (lastName.length()!=0){
                    EtudiantDatabaseHandler insertDB = new EtudiantDatabaseHandler(getApplicationContext());
                    Etudiant et = new Etudiant(prenom.getText().toString().trim(), nom.getText().toString().trim(), login.getText().toString().trim(), password.getText().toString().trim());
                    insertDB.insertEtudiant(et);
                    Intent intent = new Intent();
                    intent.putExtra("name",lastName);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Fill the name Box Please!",Toast.LENGTH_LONG).show();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}