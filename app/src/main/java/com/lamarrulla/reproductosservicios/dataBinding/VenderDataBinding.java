package com.lamarrulla.reproductosservicios.dataBinding;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.lamarrulla.reproductosservicios.R;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.entity.TipoNegocio;
import com.lamarrulla.reproductosservicios.entity.TipoVenta;
import com.lamarrulla.reproductosservicios.viewModel.ActividadViewModel;
import com.lamarrulla.reproductosservicios.viewModel.TipoNegocioViewModel;
import com.lamarrulla.reproductosservicios.viewModel.TipoVentaViewModel;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class VenderDataBinding {

    static Context context;
    static ActividadViewModel actividadViewModel;
    static TipoNegocioViewModel tipoNegocioViewModel;
    static TipoVentaViewModel tipoVentaViewModel;

    //static AutoCompleteTextView autoCompleteTextViewG;
    //static AutoCompleteTextView autoCompleteTextViewTVG;
    static TextInputLayout textInputLayoutTVG;
    static TextInputLayout textInputLayoutTipoNegocio;

    public VenderDataBinding(Context context) {
        this.context = context;
        cargadatos();
    }

    private static Boolean ft;
//    static TextView textViewG;

    private String text;
    public String value;
    public Boolean mostrarTV;

    static String[] tipoNegocio;
    static String[] ddlCargando;
    static String[] ddlTipoNegocio;

    static ArrayAdapter<String> adapter;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static void setFt(Boolean ft) {
        VenderDataBinding.ft = ft;
    }

    public void setMostrarTV(Boolean mostrarTV) {
        this.mostrarTV = mostrarTV;
    }

    @BindingAdapter("android:textInputTV")
    public static void textInputTV(TextInputLayout textInputLayout, Boolean mostrarTV){
        textInputLayoutTVG = textInputLayout;
        textInputLayout.setVisibility(View.GONE);
    }

    private static void cargadatos() {
        // carga ddl cargando
        ddlCargando = context.getResources().getStringArray(R.array.ddlCargando);

        // carga datos ddl tipo negocio
        tipoNegocio = context.getResources().getStringArray(R.array.TipoNegocio);
    }

    @BindingAdapter(value = {"android:autoCompleteDR"}, requireAll = false)
    public static void autoCompleteBinding(final AutoCompleteTextView autoCompleteTextView, Boolean mostrarTV){
        cargadatosDDLQC(autoCompleteTextView);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Boolean mostrarTV = false;
                switch (((MaterialTextView) view).getText().toString()){
                    case "Vendo":
                        textInputLayoutTVG.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocio.setVisibility(View.GONE);
                        break;
                    case "Ofresco un servício":
                        textInputLayoutTVG.setVisibility(View.GONE);
                        textInputLayoutTipoNegocio.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocio.setHint("Que servicio ofresco?");
                        break;
                    default:
                        textInputLayoutTVG.setVisibility(View.GONE);
                        textInputLayoutTipoNegocio.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    //carga tipos negocio
    private static void cargaDatosTipoNegocio(final AutoCompleteTextView autoCompleteTextView){
        tipoNegocioViewModel = ViewModelProviders.of((FragmentActivity) context).get(TipoNegocioViewModel.class);
        tipoNegocioViewModel.getAll().observe((LifecycleOwner) context, new Observer<List<TipoNegocio>>() {
            @Override
            public void onChanged(List<TipoNegocio> tipoNegocios) {
                autoCompleteTextView.setAdapter(regresaAdaptador(new ArrayList<Object>(tipoNegocios)));
            }
        });
    }

    //carga tipo venta
    private static void cargaDatosTipoVenta(final AutoCompleteTextView autoCompleteTextView){
        //adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, ddlTipoNegocio);
        //autoCompleteTextView.setAdapter(adapter);
        tipoVentaViewModel = ViewModelProviders.of((FragmentActivity) context).get(TipoVentaViewModel.class);
        tipoVentaViewModel.getAll().observe((LifecycleOwner) context, new Observer<List<TipoVenta>>() {
            @Override
            public void onChanged(List<TipoVenta> tipoVentas) {
                autoCompleteTextView.setAdapter(regresaAdaptador(new ArrayList<Object>(tipoVentas)));
            }
        });
    }

    //Adaptador generico
    private static ArrayAdapter<String> regresaAdaptador(List<Object> arregloString){
        int size = arregloString.size();
        if(size>0){
            String[] tipos = new String[size];
            int i = 0;
            while(size>i){
                try {
                    Object o = arregloString.get(i);
                    Class<?> clazz = o.getClass();
                    //Field[] campos = clazz.getDeclaredFields();
                    Method method = clazz.getDeclaredMethods()[1];
                    tipos[i] = method.invoke(o).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                i++;
            }
            adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, tipos);
        }
        return adapter;
    }

    // carga datos que hago?
    private static void cargadatosDDLQC(final AutoCompleteTextView autoCompleteTextView) {
        //autoCompleteTextViewG.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, ddlCargando);
        autoCompleteTextView.setAdapter(adapter);
        actividadViewModel = ViewModelProviders.of((FragmentActivity) context).get(ActividadViewModel.class);
        actividadViewModel.getAll().observe((LifecycleOwner) context, new Observer<List<Actividad>>() {
            @Override
            public void onChanged(List<Actividad> actividads) {
                int size = actividads.size();
                if(size>0){
                    String[] tiposActividades = new String[size];
                    int i = 0;
                    for (Actividad act: actividads
                    ) {
                        tiposActividades[i] = act.getActividadTipo();
                        i++;
                    }
                    adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, tiposActividades);
                    autoCompleteTextView.setAdapter(adapter);
                }
            }
        });
    }

    @BindingAdapter("android:autoCompleteTV")
    public static void bindingAutoCompleteTV(final AutoCompleteTextView autoCompleteTextViewTV, String text){
        cargaDatosTipoVenta(autoCompleteTextViewTV);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, tipoNegocio);
        //autoCompleteTextViewTV.setAdapter(adapter);
        autoCompleteTextViewTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (((MaterialTextView) view).getText().toString()){
                    case "Negocio":
                        //Toast.makeText(context, "click", Toast.LENGTH_SHORT).show();
                        textInputLayoutTipoNegocio.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocio.setHint("Que tipo de negocio?");
                        break;
                    case "Personal":
                        textInputLayoutTipoNegocio.setVisibility(View.VISIBLE);
                        textInputLayoutTipoNegocio.setHint("Que vendo?");
                        break;
                    default:
                        Toast.makeText(context, "Default", Toast.LENGTH_SHORT).show();
                        textInputLayoutTipoNegocio.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @BindingAdapter("android:textInputEditTipoNegocio")
    public static void bindingTextInputEditTipoNegocio(TextInputLayout textInputLayout, Boolean mostrarTV){
        textInputLayoutTipoNegocio = textInputLayout;
        textInputLayout.setVisibility(View.GONE);
    }

    /*@BindingAdapter("android:Titulo")
    public static void cambiaTitulo(TextView  textView, String text){
        //textViewG = textView;
        textView.setText(text);
    }*/



    /*@BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
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
        return (String) pAppCompatSpinner.getSelectedItem();
    }*/

    /*@BindingAdapter("android:text")
    public static void bindTextVal(EditText editText, String val){
        editText.setText(val);
    }

        @InverseBindingAdapter(attribute = "android:text")
    public static String getStringFromBinding(EditText editText){
        String value = editText.getText().toString();
        value = "Hola mundo " + value;
        Log.d("bindingInverse", value);
        //textViewG.setText(value);
        return value;
    }*/

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
