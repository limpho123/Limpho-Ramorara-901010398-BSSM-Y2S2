package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText StudentId,Password;
    Button login, signUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentId = (EditText)findViewById(R.id.StudentId1);
        Password = (EditText)findViewById(R.id.Password1);
        DB = new DBHelper(this);
        login = (Button)findViewById(R.id.SignIn1);
        signUp = (Button)findViewById(R.id.SignUp1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = StudentId.getText().toString();
                String pass = Password.getText().toString();

                if(user.equals("")||pass.equals("")) {
                    Toast.makeText(MainActivity.this, "PLEASE FILL IN ALL SPACES", Toast.LENGTH_SHORT).show();
                }

                else{
                    Boolean checkuserpass = DB.checkUsernamePassword(user, pass);
                    if (checkuserpass==true){

                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "signIn successful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new  Intent(MainActivity.this, register.class);
                startActivity(intent);


            }
        });

    }


}