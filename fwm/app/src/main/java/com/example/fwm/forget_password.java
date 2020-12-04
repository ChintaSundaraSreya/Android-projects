package com.example.fwm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_password extends AppCompatActivity {
    private EditText email;
    private Button reset;

    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email=findViewById(R.id.email);
        reset=findViewById(R.id.reset);

        Auth= FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });
    }

    private void resetpassword(){
        String e =email.getText().toString().trim();

        if(e.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }

        Auth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(forget_password.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(forget_password.this, "Try again something wrong happened!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
