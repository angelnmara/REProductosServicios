package com.lamarrulla.reproductosservicios.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.entity.TipoNegocio;
import com.lamarrulla.reproductosservicios.repository.TipoNegocioRepository;

import java.util.List;

public class TipoNegocioViewModel extends AndroidViewModel {

    private TipoNegocioRepository repository;
    private LiveData<List<TipoNegocio>> allTipoNegocio;

    public TipoNegocioViewModel(@NonNull Application application) {
        super(application);
        repository = new TipoNegocioRepository(application);
        allTipoNegocio = repository.selectAllTipoNegocio();
    }

    public void insert(TipoNegocio tipoNegocio){
        repository.insert(tipoNegocio);
    }

    public void delete(TipoNegocio tipoNegocio){
        repository.delete(tipoNegocio);
    }

    public void update(TipoNegocio tipoNegocio){
        repository.update(tipoNegocio);
    }

    public void deleteAll(){
        repository.deleteAllTipoNegocio();
    }

    public LiveData<List<TipoNegocio>> getAll(){
        return allTipoNegocio;
    }

}
