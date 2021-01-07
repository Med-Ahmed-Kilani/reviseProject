package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button nextBtn, login, contact, affichage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextBtn = findViewById(R.id.nextBtn);
        login = findViewById(R.id.login);
        contact = findViewById(R.id.contact);
        affichage = findViewById(R.id.affichage);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inscription.class);
                startActivityForResult(intent, 1);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Connection.class);
                startActivityForResult(intent, 2);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Contact.class);
                startActivity(intent);
            }
        });

        affichage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tousEtudiants = "";
                final EtudiantDatabaseHandler maBD = new EtudiantDatabaseHandler(getApplicationContext());
                for (Etudiant etudiant : maBD.getAllEtudiantes()){
                    tousEtudiants += "\nId : " + etudiant.getId() + "\nNom : " + etudiant.getNom() + "\nPrenom : "+etudiant.getPrenom()+"\nLogin : "+etudiant.getLogin()+"\nPassword : "+etudiant.getPassword();
                }
                Toast.makeText(getApplicationContext(),tousEtudiants,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data.getStringExtra("name") != "") {
                            String nom = data.getStringExtra("name");
                            Toast.makeText(this, "L'inscription de "+nom+" est valide", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Inscription annulée", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 2 :
                switch (resultCode) {
                    case RESULT_OK:
                        if (data.getExtras()!=null){
                            String na = data.getStringExtra("name2");
                            Toast.makeText(getApplicationContext(),"Connection du "+na+" reussite",Toast.LENGTH_SHORT).show();
                        }

                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(getApplicationContext(), "Connection annulee", Toast.LENGTH_SHORT).show();
                }
        }

    }
}