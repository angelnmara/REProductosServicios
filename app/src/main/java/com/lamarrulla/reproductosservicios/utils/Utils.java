package com.lamarrulla.reproductosservicios.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Utils {

    private static Context context;
    private static ArrayAdapter<String> adapter;

    public Utils(Context context) {
        this.context = context;
    }

    //Adaptador generico de un campo
    public static ArrayAdapter<String> regresaAdaptador(List<Object> arregloString, String metodo){
        int size = arregloString.size();
        if(size>0){
            String[] tipos = new String[size];
            int i = 0;
            while(size>i){
                try {
                    Object o = arregloString.get(i);
                    Class<?> clazz = o.getClass();
                    //Field[] campos = clazz.getDeclaredFields();
                    //Method method = clazz.getDeclaredMethods()[1];
                    Method method = clazz.getDeclaredMethod(metodo);
                    tipos[i] = method.invoke(o).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                i++;
            }
            adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, tipos);
        }
        return adapter;
    }
}
