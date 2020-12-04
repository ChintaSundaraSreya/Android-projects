package com.example.fwm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.login);
        login.setOnClickListener(this);

        register=findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.register:
                startActivity(new Intent(MainActivity.this, register.class));
                break;

            case R.id.login:
                startActivity(new Intent(MainActivity.this, login.class));
                break;
        }
    }
}
