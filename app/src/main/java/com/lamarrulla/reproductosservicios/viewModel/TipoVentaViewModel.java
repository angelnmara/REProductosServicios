package com.lamarrulla.reproductosservicios.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.entity.TipoVenta;
import com.lamarrulla.reproductosservicios.repository.ActividadRepository;
import com.lamarrulla.reproductosservicios.repository.TipoVentaRepository;

import java.util.List;

public class TipoVentaViewModel extends AndroidViewModel {

    private TipoVentaRepository repository;
    private LiveData<List<TipoVenta>> allTipoVentas;

    public TipoVentaViewModel(@NonNull Application application) {
        super(application);
        repository = new TipoVentaRepository(application);
        allTipoVentas = repository.getAllTipoVenta();
    }

    public void insert(TipoVenta tipoVenta){
        repository.insert(tipoVenta);
    }

    public void update(TipoVenta tipoVenta){
        repository.update(tipoVenta);
    }

    public void delete(TipoVenta tipoVenta){
        repository.delete(tipoVenta);
    }
    public void deleteAll(){
        repository.deleteAllTipoVenta();
    }
    public LiveData<List<TipoVenta>> getAll(){
        return allTipoVentas;
    }
}
