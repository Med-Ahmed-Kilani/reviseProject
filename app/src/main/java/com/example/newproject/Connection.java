package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Connection extends AppCompatActivity {

    Button submit, cancel;
    EditText login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
        login = findViewById(R.id.loginInput);
        password = findViewById(R.id.passwordInput);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EtudiantDatabaseHandler maBD = new EtudiantDatabaseHandler(getApplicationContext());
                String name = "";
                Etudiant et = maBD.getEtudiant(login.getText().toString());

                if (et!=null){
                    String pwd= et.getPassword();
                    if (pwd.compareTo(password.getText().toString().trim())==0 ){
                        name = et.getNom();
                        Intent intent = new Intent();
                        intent.putExtra("name2",name);
                        setResult(RESULT_OK,intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Login or Password is false", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "login incorrect!!!", Toast.LENGTH_SHORT).show();
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