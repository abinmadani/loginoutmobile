package com.praktikan.tugasakhir009;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nim,pass;
    Button login,reg;
    DBHelper DB;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nim = (EditText) findViewById(R.id.nim);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        reg = (Button) findViewById(R.id.reg);
        DB = new DBHelper(this);

        sp = getSharedPreferences("Data",MODE_PRIVATE);
        editor = sp.edit();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimm = nim.getText().toString();
                String pw = pass.getText().toString();

                if(nimm.equals("")||pw.equals(""))
                    Toast.makeText(MainActivity.this, "Isi Semua Fiedls nya!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(nimm, pw);
                    if(checkuserpass==true){
                        editor.putString("nim",nimm);
                        editor.putString("password",pw);
                        editor.apply();
                        Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,utama.class));
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Akun Tidak Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }
}