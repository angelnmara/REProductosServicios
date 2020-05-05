package com.lamarrulla.reproductosservicios.utils;

import android.content.Context;
import android.content.Intent;

import com.lamarrulla.reproductosservicios.PSActivity;
import com.lamarrulla.reproductosservicios.PrincipalActivity;
import com.lamarrulla.reproductosservicios.UserActivity;

public class UtilsActivity {

    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void CallPSActivity(){
        Intent intent = new Intent(context, PrincipalActivity.class);
/*
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
*/
        context.startActivity(intent);

    }
}
