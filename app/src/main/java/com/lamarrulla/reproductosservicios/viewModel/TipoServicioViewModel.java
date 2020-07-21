package com.lamarrulla.reproductosservicios.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.lamarrulla.reproductosservicios.entity.TipoServicio;
import com.lamarrulla.reproductosservicios.repository.TipoServicioRepository;

import java.util.List;

public class TipoServicioViewModel extends AndroidViewModel {
    private TipoServicioRepository repository;
    private LiveData<List<TipoServicio>> allTipoServicio;

    public TipoServicioViewModel(@NonNull Application application) {
        super(application);
        repository = new TipoServicioRepository(application);
        allTipoServicio = repository.selectAllTipoServicio();
    }

    public void insert(TipoServicio tipoServicio){
        repository.insert(tipoServicio);
    }

    public void delete(TipoServicio tipoServicio){
        repository.delete(tipoServicio);
    }

    public void update(TipoServicio tipoServicio){
        repository.update(tipoServicio);
    }

    public void deleteAll(){
        repository.deleteAllTipoServicio();
    }

    public LiveData<List<TipoServicio>> getAll(){
        return allTipoServicio;
    }
}
