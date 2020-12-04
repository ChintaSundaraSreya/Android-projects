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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private EditText username,email,password,address,mobile;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();


        register=findViewById(R.id.register);
        register.setOnClickListener(this);
        username = findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        address=findViewById(R.id.address);
        mobile=findViewById(R.id.mobile);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                registeruser();
                break;
        }
    }

    private void registeruser() {
        final String un= username.getText().toString().trim();
        final String e= email.getText().toString().trim();
        final String p= password.getText().toString().trim();
        final String a= address.getText().toString().trim();
        final String m= mobile.getText().toString().trim();

        if(un.isEmpty()){
            username.setError("Username is required");
            username.requestFocus();
            return;
        }
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

        if(p.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if (p.length() < 6){
            password.setError("Minimum length should be 6 characters!");
            password.requestFocus();
            return;
        }

        if(a.isEmpty()){
            address.setError("Address is required");
            address.requestFocus();
            return;
        }

        if(m.isEmpty()){
            mobile.setError("Mobile number is required");
            mobile.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            user user = new user(un,e,a,m);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(register.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(register.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
