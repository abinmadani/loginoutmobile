package com.praktikan.tugasakhir009;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText nama,nim1,pass1,repas;
    Button reg1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nama = (EditText) findViewById(R.id.nama);
        nim1 = (EditText) findViewById(R.id.nim1);
        pass1 = (EditText) findViewById(R.id.pass1);
        repas = (EditText) findViewById(R.id.repas);
        reg1 = (Button) findViewById(R.id.reg1);
        DB = new DBHelper(this);

        reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaa = nama.getText().toString();
                String nimm1 = nim1.getText().toString();
                String pw1 = pass1.getText().toString();
                String repass = repas.getText().toString();

                if(namaa.equals("")||nimm1.equals("")||pw1.equals(""))
                    Toast.makeText(Register.this, "Tolong diisi Semua Fieldnya", Toast.LENGTH_SHORT).show();
                else{
                    if(pw1.equals(repass)){
                        Boolean checkuser = DB.checkusername(nimm1);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(nimm1, namaa, pw1);
                            if(insert==true){
                                Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),utama.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(Register.this, "Registrati tidak berhasil", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Register.this, "Nim sudah terdaftar", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}