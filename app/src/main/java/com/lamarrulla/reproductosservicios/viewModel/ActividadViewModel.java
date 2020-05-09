package com.lamarrulla.reproductosservicios.viewModel;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.repository.ActividadRepository;

import java.util.List;

public class ActividadViewModel extends AndroidViewModel {

    private ActividadRepository repository;
    private LiveData<List<Actividad>> allActividades;
    private final String TAG = getClass().getSimpleName();

    public ActividadViewModel(@NonNull Application application) {
        super(application);
        repository = new ActividadRepository(application);
        allActividades = repository.getAllActividades();
    }

    public void insert(Actividad actividad){
        repository.insert(actividad);
    }

    public void update(Actividad actividad){
        repository.update(actividad);
    }

    public void delete(Actividad actividad){
        repository.delete(actividad);
    }

    public void deleteAll(){
        repository.deleteAllActividades();
    }

    public LiveData<List<Actividad>> getAll(){
        return allActividades;
    }
}
