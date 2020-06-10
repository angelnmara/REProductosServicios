package com.lamarrulla.reproductosservicios.entity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.lamarrulla.reproductosservicios.BR;
import com.lamarrulla.reproductosservicios.R;
import com.lamarrulla.reproductosservicios.viewModel.UserViewModel;

@Entity(tableName = "user_table")
public class User extends BaseObservable {

    private static UserViewModel userViewModel;
    private static User NewUser;
    private static Context context;

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String avatar;

    public User(Context contextg){
        context = contextg;
    }

    public User(String nombre, String email, String telefono, String direccion, String avatar) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.avatar = avatar;
    }

    @Bindable
    public int getId() {
        return id;
    }

    @Bindable
    public String getNombre() {
        return nombre;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getTelefono() {
        return telefono;
    }

    @Bindable
    public String getDireccion() {
        return direccion;
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        if(this.nombre != nombre){
            this.nombre = nombre;
            NewUser.nombre = nombre;
            notifyPropertyChanged(BR.nombre);
        }
    }

    public void setEmail(String email) {
        if(this.email!=email){
            this.email = email;
            NewUser.email = email;
            notifyPropertyChanged(BR.email);
        }
    }

    public void setTelefono(String telefono) {
        if(this.telefono!=telefono){
            this.telefono = telefono;
            NewUser.telefono = telefono;
            notifyPropertyChanged(BR.telefono);
        }
    }

    public void setDireccion(String direccion) {
        if(this.direccion != direccion){
            this.direccion = direccion;
            NewUser.direccion = direccion;
            notifyPropertyChanged(BR.direccion);
        }
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @BindingAdapter({ "avatar" })
    public static void loadImage(ImageView imageView, String imageURL) {

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }

    @BindingAdapter("android:GuardaUsuario")
    public static void GuardaUsuario(Button button, User userNuevo){
        NewUser = userNuevo;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel = ViewModelProviders.of((FragmentActivity) context).get(UserViewModel.class);
                userViewModel.update(NewUser);
                Snackbar.make(v, "Registro actualizado correctamente", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @BindingAdapter("android:SaveAvatar")
    public static void SaveAvatar(ImageView imageView, String avatar){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click en avatar", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
