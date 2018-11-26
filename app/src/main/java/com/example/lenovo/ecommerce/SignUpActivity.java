package com.example.lenovo.ecommerce;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.ecommerce.Database.Users;
import com.example.lenovo.ecommerce.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    Users users;
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        users = new Users(getApplicationContext());
    }

    public void signup(View view){
        if(binding.regUsername.getText().toString().isEmpty())
            binding.regUsername.setError("Can't be empty");
        else if(binding.regPassword.getText().toString().isEmpty())
            binding.regPassword.setError("Can't be empty");
        else{
            users.registerUser(binding.regUsername.getText().toString(),
                    binding.regPassword.getText().toString());
            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
            users.setLogin(binding.regUsername.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}
