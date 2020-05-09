package com.lamarrulla.reproductosservicios.dataBinding;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.textview.MaterialTextView;

public class VenderDataBinding {

    static Context context;

    public VenderDataBinding(Context context) {
        this.context = context;
    }

    private static Boolean ft;
    private String tituloSpinner;
    static TextView textViewG;

    private static int visibleSpinner;
    private static String visibleSpinnerStr;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static void setFt(Boolean ft) {
        VenderDataBinding.ft = ft;
    }

    public static int getVisibleSpinner() {
        return visibleSpinner;
    }

    public static void setVisibleSpinner(int visibleSpinner) {
        VenderDataBinding.visibleSpinner = visibleSpinner;
    }

    public static String getVisibleSpinnerStr() {
        return visibleSpinnerStr;
    }

    public static void setVisibleSpinnerStr(String visibleSpinnerStr) {
        VenderDataBinding.visibleSpinnerStr = visibleSpinnerStr;
    }

    @BindingAdapter("android:Titulo")
    public static void cambiaTitulo(TextView  textView, String text){
        textViewG = textView;
        textView.setText(text);
    }

    @BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner, String newSelectedValue, final InverseBindingListener newTextAttrChanged) {
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }
    }
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        textViewG.setText((String) pAppCompatSpinner.getSelectedItem());
        return (String) pAppCompatSpinner.getSelectedItem();
    }

    /*@BindingAdapter("android:ddlActividades")
    public static void spinneRemoveTitulo(final Spinner spinner, final String tituloSpinner){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                        Toast.makeText(context, "otro", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    String se = ((AdapterView) parent).getAdapter().getItem(position).toString();
                    //visibleSpinner = View.GONE;
                    //textViewG.setText(se);
                    Toast.makeText(context, se, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();
            }
        });
        spinner.setOnTouchListener(new View.OnTouchListener() {
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
    @InverseBindingAdapter(attribute = "android:spinneRemoveTitulo")
    public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return (String) pAppCompatSpinner.getSelectedItem();
    }*/
}
