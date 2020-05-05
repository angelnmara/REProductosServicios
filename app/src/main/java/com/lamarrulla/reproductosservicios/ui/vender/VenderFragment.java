package com.lamarrulla.reproductosservicios.ui.vender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.lamarrulla.reproductosservicios.R;

public class VenderFragment extends Fragment {

    private VenderViewModel venderViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        venderViewModel =
                new ViewModelProvider(this).get(VenderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vender, container, false);
        //final TextView textView = root.findViewById(R.id.text_vender);
        //final TextView textViewPS = root.findViewById(R.id.textProductoServicio);
        venderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
                //textViewPS.setText(s);
            }
        });
        return root;
    }
}