package com.lamarrulla.reproductosservicios.ui.vender;

import android.content.Intent;
import android.graphics.Bitmap;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.login.LoginResult;
import com.lamarrulla.reproductosservicios.R;
import com.lamarrulla.reproductosservicios.dataBinding.VenderDataBinding;
import com.lamarrulla.reproductosservicios.databinding.FragmentVenderBindingImpl;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.interfaces.MustVisitItemListener;
import com.lamarrulla.reproductosservicios.utils.SpinnerBindingUtil;
import com.lamarrulla.reproductosservicios.viewModel.ActividadViewModel;
import com.lamarrulla.reproductosservicios.viewModel.UserViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static android.app.Activity.RESULT_OK;

public class VenderFragment extends Fragment {

    private VenderViewModel venderViewModel;
    private ActividadViewModel actividadViewModel;
    private UserViewModel userViewModel;
    private MustVisitItemListener mItemListener;

    private final String TAG = getClass().getSimpleName();
    static final int REQUEST_IMAGE_CAPTURE = 1;

    //SpinnerBindingUtil spinnerBindingUtil;
    VenderDataBinding venderDataBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //View root = inflater.inflate(R.layout.fragment_vender, container, false);

        /*spinnerBindingUtil = new SpinnerBindingUtil(getContext());
        spinnerBindingUtil.setFt(true);*/

        venderDataBinding = new VenderDataBinding(getContext());

        //venderDataBinding.setFt(true);

        final FragmentVenderBindingImpl binding = DataBindingUtil.inflate(inflater, R.layout.fragment_vender, container, false);
        View view = binding.getRoot();


        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        /*actividadViewModel = ViewModelProviders.of(this).get(ActividadViewModel.class);

        actividadViewModel.getAll().observe(this, new Observer<List<Actividad>>() {
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
                    binding.setTiposActividades(tiposActividades);
                }
            }
        });*/

        mItemListener = new MustVisitItemListener() {
            @Override
            public void onSaveClick() {
                Log.i(TAG, "Vender fragment click");
            }

            @Override
            public void onSpinnerClick() {
                Log.i(TAG, "Spinner click");
            }
        };

        binding.setVender(venderDataBinding);
        //binding.setHandler(mItemListener);
        //binding.setFirstTime(View.GONE);

        /*venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);

        //final TextView textView = root.findViewById(R.id.text_vender);
        //final TextView textViewPS = root.findViewById(R.id.textProductoServicio);
        venderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
                //textViewPS.setText(s);
            }
        });*/
        //return root;
        return view;
    }
}