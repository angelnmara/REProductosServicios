package com.lamarrulla.reproductosservicios.ui.configuracion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lamarrulla.reproductosservicios.R;
//import com.lamarrulla.reproductosservicios.data.User;
import com.lamarrulla.reproductosservicios.dataBinding.VenderDataBinding;
import com.lamarrulla.reproductosservicios.databinding.FragmentConfiguracionBindingImpl;
import com.lamarrulla.reproductosservicios.viewModel.UserViewModel;
import com.lamarrulla.reproductosservicios.viewPagerAdapter.ViewPagerAdapterConfiguracion;

import java.util.List;

public class ConfiguracionFragment extends Fragment {

    //private ConfiguracionViewModel configuracionViewModel;
    private UserViewModel userViewModel;
    private VenderDataBinding venderDataBinding;
    ViewPager viewPagerConfig;
    TabLayout tabLayoutConfig;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_configuracion, container, false);

        /*funcionalidad ok*/
        /*final FragmentConfiguracionBindingImpl binding = DataBindingUtil.inflate(inflater, R.layout.fragment_configuracion, container, false);
        View view = binding.getRoot();

        venderDataBinding = new VenderDataBinding(getContext());
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        //binding.setUser(userViewModel.getUser());


        userViewModel.getAllUsers().observe(this, new Observer<List<com.lamarrulla.reproductosservicios.entity.User>>() {
            @Override
            public void onChanged(List<com.lamarrulla.reproductosservicios.entity.User> users) {
                //Toast.makeText(context, "user view model", Toast.LENGTH_LONG).show();
                //User user = new User(users.get(0).getNombre(), users.get(0).getEmail(), users.get(0).getTelefono(), users.get(0).getDireccion(), users.get(0).getAvatar());
                if(users.size()>0){
                    binding.setUser(users.get(0));
                }
            }
        });*/

        return root;
        /*configuracionViewModel =
                new ViewModelProvider(this).get(ConfiguracionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_configuracion, container, false);
        //final TextView textView = root.findViewById(R.id.section_label);
        configuracionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPagerConfig = view.findViewById(R.id.view_pagerConfg);
        viewPagerConfig.setAdapter(new ViewPagerAdapterConfiguracion(getFragmentManager()));
        tabLayoutConfig = view.findViewById(R.id.tabLayoutConfg);
        tabLayoutConfig.setupWithViewPager(viewPagerConfig);
    }
}