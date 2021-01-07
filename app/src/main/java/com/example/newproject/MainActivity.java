package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    Button nextBtn, login, contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.Tv1);
        nextBtn = findViewById(R.id.nextBtn);
        login = findViewById(R.id.login);
        contact = findViewById(R.id.contact);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Act2.class);
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
    }

    @Override
    public void onActivityResult(int requestCode, int
            resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                switch (resultCode) {
                    case RESULT_OK:
                        if (data != null) {
                            String nom = data.getStringExtra("name");
                            Toast.makeText(this, nom, Toast.LENGTH_SHORT).show();
                        }
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                        return;
                }
            case 2 :
                switch (resultCode) {
                    case RESULT_OK:
                        Toast.makeText(getApplicationContext(),"Connection reussite",Toast.LENGTH_SHORT).show();
                        return;
                    case RESULT_CANCELED:
                        Toast.makeText(getApplicationContext(), "Connection annulee", Toast.LENGTH_SHORT).show();
                }
        }

    }
}