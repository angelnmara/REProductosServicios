package com.lamarrulla.reproductosservicios.dataBinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.entity.TipoNegocio;
import com.lamarrulla.reproductosservicios.entity.TipoVenta;
import com.lamarrulla.reproductosservicios.utils.Utils;
import com.lamarrulla.reproductosservicios.viewModel.ActividadViewModel;
import com.lamarrulla.reproductosservicios.viewModel.TipoNegocioViewModel;
import com.lamarrulla.reproductosservicios.viewModel.TipoVentaViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class VenderDataBinding {

    public final String TAG = this.getClass().getSimpleName();
    static final int REQUEST_IMAGE_CAPTURE = 1;

    static Utils utils;

    static Context context;
    static ActividadViewModel actividadViewModel;
    static TipoNegocioViewModel tipoNegocioViewModel;
    static TipoVentaViewModel tipoVentaViewModel;

    //static AutoCompleteTextView autoCompleteTextViewG;
    //static AutoCompleteTextView autoCompleteTextViewTVG;
    static TextInputLayout textInputLayoutTipoVentaDLL;
    static TextInputLayout textInputLayoutTipoNegocioDDL;
    static TextInputLayout textInputLayoutTipoNegocioIT;
    static ImageView imageViewFoto;

    static Button btnAddFoto;

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

    @BindingAdapter("android:textInputEditTipoNegocio")
    public static void bindingTextInputEditTipoNegocio(TextInputLayout textInputLayout, Boolean mostrarTV){
        textInputLayoutTipoNegocioIT = textInputLayout;
        textInputLayout.setVisibility(View.GONE);
    }

    @BindingAdapter("android:imageViewCamara")
    public static void imageViewCamara(ImageView imageView, String text){
        imageViewFoto = imageView;
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
                        textInputLayoutTipoNegocioIT.setVisibility(View.GONE);
                        break;
                    case "Ofresco un servício":
                        textInputLayoutTipoVentaDLL.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioIT.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocioIT.setHint("Que servicio ofresco?");
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnAddFoto.setVisibility(View.GONE);
                        break;
                    default:
                        textInputLayoutTipoVentaDLL.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioIT.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnAddFoto.setVisibility(View.GONE);
                        break;
                }
            }
        });
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
                        btnAddFoto.setVisibility(View.GONE);
                        break;
                    case "Personal":
                        textInputLayoutTipoNegocioIT.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocioIT.setHint("Que vendo?");
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnAddFoto.setVisibility(View.VISIBLE);
                        break;
                    default:
                        Toast.makeText(context, "Default", Toast.LENGTH_SHORT).show();
                        textInputLayoutTipoNegocioIT.setVisibility(View.GONE);
                        textInputLayoutTipoNegocioDDL.setVisibility(View.GONE);
                        btnAddFoto.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @BindingAdapter("android:autoCompleteTipoNegocio")
    public static void autoCompleteTipoNegocio(final AutoCompleteTextView autoCompleteTextView, String text){
        cargaDatosTipoNegocio(autoCompleteTextView);
    }

    @BindingAdapter("android:botonAddFoto")
    public static void botonAddFoto(final Button button, String text){
        button.setVisibility(View.GONE);
        btnAddFoto = button;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(context.getClass().getName().contains("PrincipalActivity")){
                    if(takePictureIntent.resolveActivity(context.getPackageManager()) != null){
                        ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });
    }

    public void activityResult(int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imageBitmap);
        }

    }
}
