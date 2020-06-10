package com.lamarrulla.reproductosservicios.entity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
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
import com.lamarrulla.reproductosservicios.utils.Utils;
import com.lamarrulla.reproductosservicios.viewModel.UserViewModel;

import java.io.File;

@Entity(tableName = "user_table")
public class User extends BaseObservable {

    private static UserViewModel userViewModel;
    private static User NewUser;
    private static Context context;
    public static final int REQUEST_TAKE_PHOTO = 3;
    private static Utils utils;
    private static ImageView imageViewAvatar;

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
        imageViewAvatar = imageView;
        utils = new Utils(context);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //if(context.getClass().getName().contains("PrincipalActivity")){
                    if(takePictureIntent.resolveActivity(context.getPackageManager()) != null){
                        File photoFile = null;
                        photoFile = utils.createImageFile();
                        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".dataBinding.VenderDataBinding",photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                        //REQUEST_IMAGE_CAPTURE
                    }
                //}
            }
        });
    }

    public void activityResult(int requestCode, int resultCode, @Nullable Intent data){
        utils = new Utils(context);
        Uri uri = utils.galleryAddPic();
        ImageView imageView = utils.generateImageView();
        Bitmap bitmap = utils.setPic();
        //imageView.setImageURI(uri);
        //imageView.setImageBitmap(bitmap);
        //CharSequence cs = utils.getCurrentPhotoPath();
        //imageView.setTransitionName(utils.getCurrentPhotoPath());
        //linearLayoutFotos.addView(imageView);
        imageViewAvatar.setImageBitmap(bitmap);
    }
}
