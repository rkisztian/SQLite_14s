package com.example.sqlite_14s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RogzitesActivity extends AppCompatActivity {

    private EditText editTextJegy;
    private EditText editTextVezNev;
    private EditText editTextKerNev;
    private Button buttonMentes;
    private Button buttonVisszaRog;
    private DBHelper adatbazis ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rogzites);
        init();
        buttonMentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vezeteknev = editTextVezNev.getText().toString().trim();
                String keresztnev = editTextKerNev.getText().toString().trim();
                String jegyString = editTextJegy.getText().toString().trim();
                if (vezeteknev.isEmpty()||keresztnev.isEmpty()||jegyString.isEmpty()){
                    Toast.makeText(RogzitesActivity.this,
                            "Minden mező kitöltése kötelező",
                            Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        int jegy = Integer.parseInt(jegyString);
                        if (jegy < 1 || jegy > 5){
                            Toast.makeText(RogzitesActivity.this,
                                    "A jegynek 1 és 5 között kell lennie",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            if (adatbazis.rogzites(vezeteknev,keresztnev,jegy)){
                                Toast.makeText(RogzitesActivity.this,
                                        "Sikeres rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RogzitesActivity.this,
                                        "Sikertelen rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }catch (NumberFormatException ex){
                        Toast.makeText(RogzitesActivity.this,
                                "A jegynek számnak kell lennie",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        
        buttonVisszaRog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RogzitesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        editTextJegy = findViewById(R.id.editTextJegy);
        editTextVezNev = findViewById(R.id.editTextVezNev);
        editTextKerNev = findViewById(R.id.editTextKerNev);
        buttonMentes = findViewById(R.id.buttonMentes);
        buttonVisszaRog = findViewById(R.id.buttonVisszaRog);
        adatbazis = new DBHelper(RogzitesActivity.this);
    }
}