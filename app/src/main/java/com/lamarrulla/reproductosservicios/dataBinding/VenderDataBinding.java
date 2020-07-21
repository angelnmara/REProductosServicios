package com.lamarrulla.reproductosservicios.dataBinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.lamarrulla.reproductosservicios.GalleryActivity;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.entity.TipoNegocio;
import com.lamarrulla.reproductosservicios.entity.TipoServicio;
import com.lamarrulla.reproductosservicios.entity.TipoVenta;
import com.lamarrulla.reproductosservicios.utils.Utils;
import com.lamarrulla.reproductosservicios.viewModel.ActividadViewModel;
import com.lamarrulla.reproductosservicios.viewModel.TipoNegocioViewModel;
import com.lamarrulla.reproductosservicios.viewModel.TipoServicioViewModel;
import com.lamarrulla.reproductosservicios.viewModel.TipoVentaViewModel;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class VenderDataBinding {

    //public final String TAG = this.getClass().getSimpleName();

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 2;
    public static final String EXTRA_FOTO = "extra_foto";
    public static final String EXTRA_IMAGEVIEW = "extra_imageView";


    private static Utils utils;

    static Context context;
    static ActividadViewModel actividadViewModel;
    static TipoNegocioViewModel tipoNegocioViewModel;
    static TipoVentaViewModel tipoVentaViewModel;
    static TipoServicioViewModel tipoServicioViewModel;


    //static AutoCompleteTextView autoCompleteTextViewG;
    //static AutoCompleteTextView autoCompleteTextViewTVG;

    static TextInputLayout textInputLayoutTipoVentaDLL;
    static TextInputLayout textInputLayoutTipoNegocioDDL;
    static TextInputLayout textInputLayoutTipoServicioDDL;
    static TextInputLayout textInputLayoutTipoNegocioIT;
    //static ImageView imageViewFoto;
    static LinearLayout linearLayoutFotos;
    static Button btnGuardar;
    private ImageView imageViewTemp;

    boolean isImageFitToScreen;

    public VenderDataBinding(){}

    public VenderDataBinding(Context context) {
        this.context = context;
        utils = new Utils(context);
        //cargadatos();
    }

    private static Boolean ft;
//    static TextView textViewG;

    private String text;
    //public String value;
    //public Boolean mostrarTV;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static void setFt(Boolean ft) {
        VenderDataBinding.ft = ft;
    }

    /*  Bindea text inputs y edit text para ocultar campos  */

    @BindingAdapter("android:textInputTipoVenta")
    public static void textInputTipoVenta(TextInputLayout textInputLayout, Boolean mostrarTV){
        textInputLayoutTipoVentaDLL = textInputLayout;
        textInputLayout.setVisibility(View.GONE);
    }

    @BindingAdapter("android:textInputTipoNegocio")
    public static void textInputTipoNegocio(TextInputLayout textInputLayout, Boolean mostrar){
        textInputLayoutTipoNegocioDDL = textInputLayout;
        textInputLayout.setVisibility(View.GONE);
    }

    @BindingAdapter("android:textInputTipoServicio")
    public static void textInputTipoServicio(TextInputLayout textInputLayout, Boolean mostrar){
        textInputLayoutTipoServicioDDL = textInputLayout;
        textInputLayout.setVisibility(View.GONE);
    }

    @BindingAdapter("android:textInputEditTipoNegocio")
    public static void bindingTextInputEditTipoNegocio(TextInputLayout textInputLayout, Boolean mostrarTV){
        textInputLayoutTipoNegocioIT = textInputLayout;
        textInputLayout.setVisibility(View.GONE);
    }

    /*@BindingAdapter("android:imageViewCamara")
    public static void imageViewCamara(ImageView imageView, String text){
        imageViewFoto = imageView;
    }*/

    @BindingAdapter("android:llImages")
    public static void llImages(LinearLayout linearLayout, String text){
        linearLayoutFotos = linearLayout;
    }

    /*  Termina Bindea text inputs y edit text para ocultar campos  */

    /*  Carga datos */
    //carga tipos negocio
    private static void cargaDatosTipoNegocio(final AutoCompleteTextView autoCompleteTextView){
        tipoNegocioViewModel = ViewModelProviders.of((FragmentActivity) context).get(TipoNegocioViewModel.class);
        tipoNegocioViewModel.getAll().observe((LifecycleOwner) context, new Observer<List<TipoNegocio>>() {
            @Override
            public void onChanged(List<TipoNegocio> tipoNegocios) {
                autoCompleteTextView.setAdapter(utils.regresaAdaptador(new ArrayList<Object>(tipoNegocios), "getTipoNegocio"));
            }
        });
    }

    public static void cargaDatosTipoServicio(final AutoCompleteTextView autoCompleteTextView){
        tipoServicioViewModel = ViewModelProviders.of((FragmentActivity) context).get(TipoServicioViewModel.class);
        tipoServicioViewModel.getAll().observe((LifecycleOwner) context, new Observer<List<TipoServicio>>() {
            @Override
            public void onChanged(List<TipoServicio> tipoServicios) {
                autoCompleteTextView.setAdapter(utils.regresaAdaptador(new ArrayList<Object>(tipoServicios), "getTipoServicio"));
            }
        });
    }

    //carga tipo venta
    private static void cargaDatosTipoVenta(final AutoCompleteTextView autoCompleteTextView){
        tipoVentaViewModel = ViewModelProviders.of((FragmentActivity) context).get(TipoVentaViewModel.class);
        tipoVentaViewModel.getAll().observe((LifecycleOwner) context, new Observer<List<TipoVenta>>() {
            @Override
            public void onChanged(List<TipoVenta> tipoVentas) {
                autoCompleteTextView.setAdapter(utils.regresaAdaptador(new ArrayList<Object>(tipoVentas), "getTipoVenta"));
            }
        });
    }

    // carga datos que hago?
    private static void cargadatosActividadTipo(final AutoCompleteTextView autoCompleteTextView) {
        actividadViewModel = ViewModelProviders.of((FragmentActivity) context).get(ActividadViewModel.class);
        actividadViewModel.getAll().observe((LifecycleOwner) context, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {
                autoCompleteTextView.setAdapter(utils.regresaAdaptador(new ArrayList<Object>(actividads), "getActividadTipo"));
            }
        });
    }

    /*  Termina Carga datos */

    @BindingAdapter(value = {"android:autoCompleteActividadTipo"}, requireAll = false)
    public static void autoCompleteActividadTipo(final AutoCompleteTextView autoCompleteTextView, Boolean mostrarTV){
        cargadatosActividadTipo(autoCompleteTextView);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Boolean mostrarTV = false;
                switch (((MaterialTextView) view).getText().toString()){
                    case "Vendo":
                        textInputLayoutTipoVentaDLL.setVisibility(View.VISIBLE);
                        textInputLayoutTipoServicioDDL.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioIT.setVisibility(View.GONE);
                        btnGuardar.setVisibility(View.GONE);
                        break;
                    case "Ofresco un servício":
                        textInputLayoutTipoVentaDLL.setVisibility(View.GONE);
                        textInputLayoutTipoServicioDDL.setVisibility(View.VISIBLE);
                        //textInputLayoutTipoNegocioIT.setVisibility(View.VISIBLE);
                        //textInputLayoutTipoNegocioIT.setHint("Que servicio ofresco?");
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnGuardar.setVisibility(View.VISIBLE);
                        break;
                    default:
                        textInputLayoutTipoVentaDLL.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioIT.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnGuardar.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @BindingAdapter("android:autoCompleteTipoServicio")
    public static void autoCompleteTipoServicio(final AutoCompleteTextView autoCompleteTextView, String text){
        cargaDatosTipoServicio(autoCompleteTextView);
    }

    @BindingAdapter("android:autoCompleteTipoVenta")
    public static void autoCompleteTipoVenta(final AutoCompleteTextView autoCompleteTextViewTV, String text){
        cargaDatosTipoVenta(autoCompleteTextViewTV);
        autoCompleteTextViewTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (((MaterialTextView) view).getText().toString()){
                    case "Negocio":
                        //Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                        textInputLayoutTipoNegocioIT.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioDDL.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocioDDL.setHint("Cual es tu negocio?");
                        btnGuardar.setVisibility(View.GONE);
                        break;
                    case "Personal":
                        textInputLayoutTipoNegocioIT.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocioIT.setHint("Que vendo?");
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnGuardar.setVisibility(View.VISIBLE);
                        break;
                    default:
                        Toast.makeText(context, "Default", Toast.LENGTH_SHORT).show();
                        textInputLayoutTipoNegocioIT.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnGuardar.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @BindingAdapter("android:autoCompleteTipoNegocio")
    public static void autoCompleteTipoNegocio(final AutoCompleteTextView autoCompleteTextView, String text){
        cargaDatosTipoNegocio(autoCompleteTextView);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnGuardar.setVisibility(View.VISIBLE);
            }
        });
    }

    @BindingAdapter("android:btnGuardar")
    public static void btnGuardar(final Button button, String text){
        btnGuardar = button;
        button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @BindingAdapter("android:botonAddFoto")
    public static void botonAddFoto(final Button button, String text){
        button.setVisibility(View.GONE);
        btnGuardar = button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(context.getClass().getName().contains("PrincipalActivity")){
                    if(takePictureIntent.resolveActivity(context.getPackageManager()) != null){
                        File photoFile = null;
                        photoFile = utils.createImageFile();
                        Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".dataBinding.VenderDataBinding",photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                        //REQUEST_IMAGE_CAPTURE
                    }
                }
            }
        });
    }


    public void removeImageView(String path){
        for(int i=0; i<linearLayoutFotos.getChildCount(); i++){
            View v = linearLayoutFotos.getChildAt(i);
            if(v instanceof ImageView){
                if(path.contains(v.getTransitionName().toString())){
                    v.setVisibility(View.GONE);
                }
            }
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public void activityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Uri uri = utils.galleryAddPic();
            ImageView imageView = utils.generateImageView();
            Bitmap bitmap = utils.setPic();
            imageView.setImageURI(uri);
            imageView.setImageBitmap(bitmap);
            //CharSequence cs = utils.getCurrentPhotoPath();
            imageView.setTransitionName(utils.getCurrentPhotoPath());
            linearLayoutFotos.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageViewTemp = (ImageView) v;
                    Intent intent = new Intent(context, GalleryActivity.class);
                    //String message = editText.getText().toString();
                    //v.buildDrawingCache();
                    intent.putExtra(EXTRA_FOTO, v.getTransitionName());
                    context.startActivity(intent);
                    //GalleryFragment galleryFragment = new GalleryFragment();
                    //((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_gallery, galleryFragment, "").addToBackStack("").commit();
                    //context.getActivity().getSupportFragmentManager().beginTransaction().replace(v.getRootView().getId(), scrollingFragment, "").addToBackStack("").commit();
                }
            });
            /*
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageViewFoto.setImageBitmap(imageBitmap);

            final ImageView image = new ImageView(context);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(500,500));
            image.setMaxHeight(500);
            image.setMaxWidth(500);
            image.setImageBitmap(imageBitmap);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isImageFitToScreen) {
                        isImageFitToScreen=false;
                        //image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        image.setAdjustViewBounds(true);
                    }else{
                        isImageFitToScreen=true;
                        image.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        image.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                }
            });

            // Adds the view to the layout
            linearLayoutFotos.addView(image);
             */
        }
    }
}
