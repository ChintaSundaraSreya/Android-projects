package com.example.fwm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private TextView forget;
    private EditText email,password;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        login=findViewById(R.id.login);
        login.setOnClickListener(this);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        forget=findViewById(R.id.forget);
        forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.register:
                startActivity(new Intent(login.this, register.class));
                break;

            case R.id.login:
                loginuser();
                break;

            case R.id.forget:
                startActivity(new Intent(login.this, forget_password.class));
                break;
        }
    }

    private void loginuser() {
        String e=email.getText().toString().trim();
        String p=password.getText().toString().trim();

        if(e.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }

        if(p.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }

        if(p.length() < 6) {
            password.setError("Minimum length is 6 characters!");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(login.this, "check your email to verify your account!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                        Toast.makeText(login.this, "Failed to login please check your details", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}
