package com.lamarrulla.reproductosservicios.data;

import android.widget.ImageView;

//import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lamarrulla.reproductosservicios.R;

public class User {
    private final String nombre;
    private final String email;
    private final String telefono;
    private final String direccion;
    private final String avatar;

    public User(String nombre, String email, String telefono, String direccion, String avatar) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.avatar = avatar;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getEmail() {
        return this.email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDireccion() {
        return direccion;
    }

    //@BindingAdapter({ "avatar" })
    public static void loadImage(ImageView imageView, String imageURL) {

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }

}
