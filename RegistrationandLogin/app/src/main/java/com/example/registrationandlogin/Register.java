package com.example.registrationandlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    EditText email,password,address,city,number,name;
    FirebaseAuth auth;
    DatabaseReference reference;
    ArrayList<MyModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        address=findViewById(R.id.address);
        city=findViewById(R.id.city);
        number=findViewById(R.id.number);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        list = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Data");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : Snapshot.getChildren()) {
                    MyModel model = dataSnapshot.getValue(MyModel.class);
                    list.add(model);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Register.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {
        String p=password.getText().toString();
        String a=address.getText().toString();
        String c=city.getText().toString();
        String e=email.getText().toString();
        String n=name.getText().toString();
        String no=number.getText().toString();
        if (e.isEmpty()|p.isEmpty()|a.isEmpty()|c.isEmpty()|no.isEmpty()|n.isEmpty()){
            Toast.makeText(this, "Fill all the details", Toast.LENGTH_SHORT).show();
        }
        else {
            auth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Register.this, "Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this,MainActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
        String saddress =address.getText().toString();
        String scity =city.getText().toString();
        String semail = email.getText().toString();
        String sname = name.getText().toString();
        String snum = number.getText().toString();
        MyModel model = new MyModel(saddress,scity,semail,sname,snum);
        //String id=reference.push().getKey();
        reference.child(sname).setValue(model);
        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
}
