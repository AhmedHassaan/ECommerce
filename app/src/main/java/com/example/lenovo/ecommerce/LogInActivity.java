package com.example.lenovo.ecommerce;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.ecommerce.Database.Users;
import com.example.lenovo.ecommerce.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {
    ActivityLogInBinding binding;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_log_in);
        users = new Users(getApplicationContext());
    }

    public void login(View view){
        if(binding.username.getText().toString().isEmpty())
            binding.username.setError("Can't be empty");
        else if(binding.password.getText().toString().isEmpty())
            binding.password.setError("Can't be empty");
        else {
            String username = binding.username.getText().toString();
            String password = binding.password.getText().toString();
            if (users.checkUser(username, password)) {
                users.setLogin(username);
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Wrong Username or Password");
                builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        }
    }

    public void signup(View view){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
