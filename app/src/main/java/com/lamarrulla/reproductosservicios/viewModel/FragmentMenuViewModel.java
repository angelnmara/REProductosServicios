package com.lamarrulla.reproductosservicios.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.entity.FragmentMenu;
import com.lamarrulla.reproductosservicios.repository.FragmentMenuRepository;

import java.util.List;

public class FragmentMenuViewModel extends AndroidViewModel {

    private final String TAG = getClass().getSimpleName();
    private FragmentMenuRepository repository;
    private LiveData<List<FragmentMenu>> allFragmentMenus;

    public FragmentMenuViewModel(@NonNull Application application) {
        super(application);
        repository = new FragmentMenuRepository(application);
        allFragmentMenus = repository.getAllFragmentMenus();
    }

    public void insert(FragmentMenu fragmentMenu){
        repository.insert(fragmentMenu);
    }

    public void update(FragmentMenu fragmentMenu){
        repository.update(fragmentMenu);
    }

    public void delete(FragmentMenu fragmentMenu){
        repository.delete(fragmentMenu);
    }

    public void deleteAll(){
        repository.deleteAllFragmentMenu();
    }

    public LiveData<List<FragmentMenu>> getAll(){
        return allFragmentMenus;
    }

}
