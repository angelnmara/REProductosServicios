package com.lamarrulla.reproductosservicios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lamarrulla.reproductosservicios.dataBinding.VenderDataBinding;
import com.lamarrulla.reproductosservicios.entity.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class PrincipalActivity extends AppCompatActivity {

    VenderDataBinding venderDataBinding;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_vender, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    //cacha activiti resylt del fragment y lo envia de vuelta al venderdatabinding
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                venderDataBinding = new VenderDataBinding();
                venderDataBinding.activityResult(requestCode, resultCode, data);
                break;
            case 3:
                user = new User(this);
                user.activityResult(requestCode, resultCode, data);
                break;
            default:
                Toast.makeText(this, "Opcion no reconocida", Toast.LENGTH_SHORT).show();
        }

    }
}