package com.lamarrulla.reproductosservicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.lamarrulla.reproductosservicios.data.User;
import com.lamarrulla.reproductosservicios.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user);

        ActivityUserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        //setSupportActionBar(binding.toolbar);

        User user = new User("dave", "rincon", "", "", "");
        //binding.setUser(user);

    }
}