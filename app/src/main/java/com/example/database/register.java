package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText StudentId;
    EditText Password;
    EditText RePass;
    Button SignUp,SignIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentId = (EditText)findViewById(R.id.StudentId);
        Password = (EditText)findViewById(R.id.Password);
        RePass = (EditText)findViewById(R.id.RePass);
        SignIn = (Button)findViewById(R.id.SignIn);
        SignUp = (Button)findViewById(R.id.SignUp);
        DB = new DBHelper(this);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = StudentId.getText().toString();
                String pass = Password.getText().toString();
                String rePass = RePass.getText().toString();

                if(user.equals("")|| pass.equals("")||rePass.equals(""))
                    Toast.makeText(register.this, "PLEASE FILL IN ALL SPACES", Toast.LENGTH_SHORT).show();
                else
                if(pass.equals(rePass)){
                    Boolean checkuser = DB.checkUserName(user);
                    if(checkuser == false){
                        Boolean insert = DB.insertdata(user,pass);
                        if(insert==true){

                            Toast.makeText(register.this, "successfully registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(register.this, "registration failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(register.this, "User exists", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    Toast.makeText(register.this, "passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}