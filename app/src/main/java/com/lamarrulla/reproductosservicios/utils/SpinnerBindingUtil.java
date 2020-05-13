package com.lamarrulla.reproductosservicios.utils;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textview.MaterialTextView;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.viewModel.ActividadViewModel;

import java.util.List;

import static com.lamarrulla.reproductosservicios.dataBinding.VenderDataBinding.setFt;

public class SpinnerBindingUtil {

    static Context context;

    static AppCompatTextView viewDDLG;

    public SpinnerBindingUtil(Context context) {
        this.context = context;
    }

    public String text;

    public String valor;

    public boolean ft;

    public static boolean firstTime;

    public boolean isFt() {
        return ft;
    }

    public void setFt(boolean ft) {
        //this.ft = ft;
        firstTime = ft;
    }

    public static void setFirstTime(boolean firstTime) {
        SpinnerBindingUtil.firstTime = firstTime;
    }

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };


    @BindingAdapter(value = {"android:selectedValueQ"}, requireAll = false)
    public static void bindSpinnerData(final AppCompatSpinner pAppCompatSpinner, final String newSelectedValue) {
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(firstTime){
                    viewDDLG = ((AppCompatTextView) view);
                    viewDDLG.setText(pAppCompatSpinner.getPrompt());
                }
                /*else{
                    newTextAttrChanged.onChange();
                }*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        pAppCompatSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(firstTime){
                    //((AppCompatSpinner) v).setSelection(1);
                    //textViewG.setText(((AppCompatSpinner) v).getAdapter().getItem(0).toString());
                    viewDDLG.setText(((AppCompatSpinner) v).getAdapter().getItem(0).toString());
                    setFirstTime(false);
                }
                return false;
            }
        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }
    }
    /*@InverseBindingAdapter(attribute = "android:selectedValueQ", event = "android:selectedValueAttrChangedQ")
    public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        //textViewG.setText((String) pAppCompatSpinner.getSelectedItem());
        return (String) pAppCompatSpinner.getSelectedItem();
    }*/

    /*@BindingAdapter("android:spinneRemoveTitulo")
    public static void ddlActividades(AppCompatSpinner pAppCompatSpinner, final String tituloSpinner){
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(ft){
                    if(view.getClass().toString().contains("MaterialTextView")){
                        ((MaterialTextView) view).setText(tituloSpinner);
                    }else if(view.getClass().toString().contains("AppCompatCheckedTextView")){
                        Toast.makeText(context, ((AppCompatCheckedTextView) view).getText(), Toast.LENGTH_SHORT).show();
                    }else if(view.getClass().toString().contains("AppCompatTextView")){
                        ((AppCompatTextView) view).setText(tituloSpinner);
                    }else{
                        Toast.makeText(context, "Otro", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Adapter adapter = ((ArrayAdapter<String>) parent.getAdapter());
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        *//*if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }*//*
        pAppCompatSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(ft){
                    ((AppCompatSpinner) v).setSelection(1);
                    ft = false;
                }

                return false;
            }
        });
    }
    @InverseBindingAdapter(attribute = "android:spinneRemoveTitulo", event = "selectedValueAttrChanged")
    public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return (String) pAppCompatSpinner.getSelectedItem();
    }*/
}
