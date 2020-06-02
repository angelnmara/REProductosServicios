package com.lamarrulla.reproductosservicios.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentActivity;

import com.lamarrulla.reproductosservicios.GalleryActivity;
import com.lamarrulla.reproductosservicios.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

    private static Context context;
    private static ArrayAdapter<String> adapter;
    private static String currentPhotoPath;
    //public static final String EXTRA_FOTO = "extra_foto";


    public Utils(Context context) {
        this.context = context;
    }

    public static String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public static void setCurrentPhotoPath(String currentPhotoPath) {
        Utils.currentPhotoPath = currentPhotoPath;
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

    public static File createImageFile() {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,   /*prefix*/
                    ".jpg",          /*suffix*/
                    storageDir       /*directory*/
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public Uri galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
        return contentUri;
    }

    public ImageView generateImageView(){
        int targetW = 400;
        int targetH = 400;

        final ImageView image = new ImageView(context);
        image.setLayoutParams(new android.view.ViewGroup.LayoutParams(targetW,targetH));
        image.setMaxHeight(targetH);
        image.setMaxWidth(targetW);

        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(image.getLayoutParams());
        marginParams.setMargins(10, 10, 10, 10);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
        image.setLayoutParams(layoutParams);
        return image;
    }

    public Bitmap setPic() {

        //int targetW = 400;
        //int targetH = 400;

        // se genera bitmap
        /*final ImageView image = new ImageView(context);
        image.setLayoutParams(new android.view.ViewGroup.LayoutParams(targetW,targetH));
        image.setMaxHeight(targetH);
        image.setMaxWidth(targetW);

        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(image.getLayoutParams());
        marginParams.setMargins(10, 10, 10, 10);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginParams);
        image.setLayoutParams(layoutParams);*/

        // Get the dimensions of the View
        //int targetW = image.getWidth();
        //int targetH = image.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        //int photoW = bmOptions.outWidth;
        //int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        //int scaleFactor = Math.min(targetW/targetW, targetH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        //bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        //image.setImageBitmap(bitmap);
        /*image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GalleryActivity.class);
                //String message = editText.getText().toString();
                intent.putExtra(EXTRA_FOTO, currentPhotoPath);
                context.startActivity(intent);
                //GalleryFragment galleryFragment = new GalleryFragment();
                //((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_gallery, galleryFragment, "").addToBackStack("").commit();
                //context.getActivity().getSupportFragmentManager().beginTransaction().replace(v.getRootView().getId(), scrollingFragment, "").addToBackStack("").commit();
            }
        });*/
        //linearLayoutFotos.addView(image);
        return bitmap;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

}
