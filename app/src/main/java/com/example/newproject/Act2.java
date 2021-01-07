package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Act2 extends AppCompatActivity {

    Button ok,cancel;
    EditText nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        ok = findViewById(R.id.OkResult);
        cancel = findViewById(R.id.CancelResult);
        nom = findViewById(R.id.inputNom);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nom.getText().toString();
                if (name!=""){
                    Intent intent = new Intent();
                    intent.putExtra("name",name);
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