package com.lamarrulla.reproductosservicios.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.dao.TipoServicioDao;
import com.lamarrulla.reproductosservicios.database.REPSDatabase;
import com.lamarrulla.reproductosservicios.entity.TipoServicio;

import java.util.List;

public class TipoServicioRepository {
    private TipoServicioDao tipoServicioDao;
    private LiveData<List<TipoServicio>> allTipoServicio;

    public TipoServicioRepository(Application application){
        REPSDatabase database = REPSDatabase.getInstance(application);
        tipoServicioDao = database.tipoServicioDao();
        allTipoServicio = tipoServicioDao.getAllTiposServicios();
    }
    public void insert(TipoServicio tipoServicio){
        new TipoServicioRepository.InsertTipoServicioAsyncTask(tipoServicioDao).execute(tipoServicio);
    }

    public void update(TipoServicio tipoServicio){
        new TipoServicioRepository.UpdateTipoServicioAsyncTask(tipoServicioDao).execute(tipoServicio);
    }

    public void delete(TipoServicio tipoServicio){
        new TipoServicioRepository.DeleteTipoServicioAsyncTask(tipoServicioDao).execute(tipoServicio);
    }

    public void deleteAllTipoServicio(){
        new TipoServicioRepository.DeleteAllTipoServicioAsyncTask(tipoServicioDao).execute();
    }

    public LiveData<List<TipoServicio>> selectAllTipoServicio(){
        return allTipoServicio;
    }


    private static class InsertTipoServicioAsyncTask extends AsyncTask<TipoServicio, Void, Void> {
        private TipoServicioDao tipoServicioDao;

        private InsertTipoServicioAsyncTask(TipoServicioDao tipoServicioDao){
            this.tipoServicioDao = tipoServicioDao;
        }

        @Override
        protected Void doInBackground(TipoServicio... tipoServicios) {
            this.tipoServicioDao.insert(tipoServicios[0]);
            return null;
        }
    }

    private static class UpdateTipoServicioAsyncTask extends AsyncTask<TipoServicio, Void, Void> {
        private TipoServicioDao tipoServicioDao;

        private UpdateTipoServicioAsyncTask(TipoServicioDao tipoServicioDao){
            this.tipoServicioDao = tipoServicioDao;
        }

        @Override
        protected Void doInBackground(TipoServicio... tipoServicios) {
            this.tipoServicioDao.update(tipoServicios[0]);
            return null;
        }
    }

    private static class DeleteTipoServicioAsyncTask extends AsyncTask<TipoServicio, Void, Void> {
        private TipoServicioDao tipoServicioDao;

        private DeleteTipoServicioAsyncTask(TipoServicioDao tipoServicioDao){
            this.tipoServicioDao = tipoServicioDao;
        }

        @Override
        protected Void doInBackground(TipoServicio... tipoServicios) {
            this.tipoServicioDao.delete(tipoServicios[0]);
            return null;
        }
    }

    private static class DeleteAllTipoServicioAsyncTask extends AsyncTask<Void, Void, Void> {
        private TipoServicioDao tipoServicioDao;

        private DeleteAllTipoServicioAsyncTask(TipoServicioDao tipoServicioDao){
            this.tipoServicioDao = tipoServicioDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.tipoServicioDao.deleteAllTipoServicio();
            return null;
        }
    }
}
