package com.example.mybooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {

    EditText Sname,Susername,Semail,Spass,SconfPass;

    TextView link;
    Button signUp;
    FirebaseDatabase DB;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        Sname=findViewById(R.id.et_signame);
        Susername=findViewById(R.id.et_sigUsername);
        Semail=findViewById(R.id.et_sigEmail);
        Spass=findViewById(R.id.et_sigPass);
        SconfPass=findViewById(R.id.et_sigConfPass);
        signUp=findViewById(R.id.btn_signup);

        link=findViewById(R.id.txt_loginLink);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB=FirebaseDatabase.getInstance();
                reference=DB.getReference("users");

                String name=Sname.getText().toString().trim();
                String username = Susername.getText().toString().trim();
                String email = Semail.getText().toString().trim();
                String password = Spass.getText().toString().trim();
                String confirmPassword = SconfPass.getText().toString().trim();

                // Input validation
                if (name.isEmpty()||username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpPage.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()>6){
                    Toast.makeText(SignUpPage.this, "Password must be more than 6 characters", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpPage.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    return;
                }

                helperClass helperClass =new helperClass(name,username,email,password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(SignUpPage.this, "SignUp successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SignUpPage.this,LoginPage.class);
                startActivity(intent);



            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpPage.this,LoginPage.class);
                startActivity(intent);
            }
        });

    }
}