package com.example.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    EditText log_username,log_pass;
    Button login;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        log_username=findViewById(R.id.et_logUsername);
        log_pass=findViewById(R.id.et_logPass);
        login=findViewById(R.id.btn_login);
        link=findViewById(R.id.signUpUser);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ValidatUsername()||!ValidatPassword()){

                }else{
                    checkUser();
                }

            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this,SignUpPage.class);
                startActivity(intent);
            }
        });

    }



    public Boolean ValidatUsername(){
        String val=log_username.getText().toString();
        if(val.isEmpty()){
            log_username.setError("username cannot be empty");
            return false;

        }else{
            log_username.setError(null);
            return true;
        }
    }

    public Boolean ValidatPassword(){
        String val=log_pass.getText().toString();
        if(val.isEmpty()){
            log_pass.setError("password cannot be empty");
            return false;

        }else{
            log_username.setError(null);
            return true;
        }
    }

    public void checkUser(){

        String username = log_username.getText().toString().trim();
        String password = log_pass.getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase=reference.orderByChild("username").equalTo(username);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){
                   log_username.setError(null);
                   String passwordDromDB=snapshot.child(username).child("password").getValue(String.class);

                   if (passwordDromDB.equals(password)){
                       log_username.setError(null);
                       Intent intent=new Intent(LoginPage.this,MainActivity.class);
                       startActivity(intent);
                   }else{
                       log_username.setError("Invalid credentials");
                       log_pass.requestFocus();
                   }
               }else{
                   log_username.setError("User does not exists");
                   log_username.requestFocus();
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}