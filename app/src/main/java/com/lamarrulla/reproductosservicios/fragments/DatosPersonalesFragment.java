package com.lamarrulla.reproductosservicios.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lamarrulla.reproductosservicios.R;
import com.lamarrulla.reproductosservicios.dataBinding.VenderDataBinding;
import com.lamarrulla.reproductosservicios.databinding.FragmentDatosPersonalesBinding;
import com.lamarrulla.reproductosservicios.entity.User;
import com.lamarrulla.reproductosservicios.viewModel.UserViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatosPersonalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosPersonalesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private VenderDataBinding venderDataBinding;
    private UserViewModel userViewModel;

    public DatosPersonalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosPersonalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosPersonalesFragment newInstance(String param1, String param2) {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FragmentDatosPersonalesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_datos_personales, container, false);
        View view = binding.getRoot();

        //venderDataBinding = new VenderDataBinding(getContext());
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        //binding.setUser(userViewModel.getUser());

        User user = new User(getContext());

        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<com.lamarrulla.reproductosservicios.entity.User> users) {
                //Toast.makeText(context, "user view model", Toast.LENGTH_LONG).show();
                //User user = new User(users.get(0).getNombre(), users.get(0).getEmail(), users.get(0).getTelefono(), users.get(0).getDireccion(), users.get(0).getAvatar());
                if(users.size()>0){
                    binding.setUser(users.get(0));
                }
            }
        });
        //return inflater.inflate(R.layout.fragment_datos_personales, container, false);
        return view;
    }
}