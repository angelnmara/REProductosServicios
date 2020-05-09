package com.lamarrulla.reproductosservicios.utils;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.textview.MaterialTextView;

public class SpinnerBindingUtil {

    static Context context;

    public SpinnerBindingUtil(Context context) {
        this.context = context;
    }

    public String tituloSpinner;

    public static boolean ft;

    public void setFirstTime(boolean firstTime) {
        ft = firstTime;
    }

    @BindingAdapter("android:spinneRemoveTitulo")
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
        /*if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }*/
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
    }
}
