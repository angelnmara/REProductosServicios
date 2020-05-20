package com.lamarrulla.reproductosservicios.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.dao.TipoNegocioDao;
import com.lamarrulla.reproductosservicios.database.REPSDatabase;
import com.lamarrulla.reproductosservicios.entity.TipoNegocio;
import com.lamarrulla.reproductosservicios.entity.TipoVenta;

import java.util.List;

public class TipoNegocioRepository {
    private TipoNegocioDao tipoNegocioDao;
    private LiveData<List<TipoNegocio>> allTipoNegocio;

    public TipoNegocioRepository(Application application) {
        REPSDatabase database = REPSDatabase.getInstance(application);
        tipoNegocioDao = database.tipoNegocioDao();
        allTipoNegocio = tipoNegocioDao.getAllTipoNegocios();
    }

    public void insert(TipoNegocio tipoNegocio){
        new InsertTipoNegocioAsyncTask(tipoNegocioDao).execute(tipoNegocio);
    }

    public void update(TipoNegocio tipoNegocio){
        new UpdateTipoNegocioAsyncTask(tipoNegocioDao).execute(tipoNegocio);
    }

    public void delete(TipoNegocio tipoNegocio){
        new DeleteTipoNegocioAsyncTask(tipoNegocioDao).execute(tipoNegocio);
    }

    public void deleteAllTipoNegocio(){
        new DeleteAllTipoNegocioAsyncTask(tipoNegocioDao).execute();
    }

    public LiveData<List<TipoNegocio>> selectAllTipoNegocio(){
        return allTipoNegocio;
    }


    private static class InsertTipoNegocioAsyncTask extends AsyncTask<TipoNegocio, Void, Void> {
        private TipoNegocioDao tipoNegocioDao;

        private InsertTipoNegocioAsyncTask(TipoNegocioDao tipoNegocioDao){
            this.tipoNegocioDao = tipoNegocioDao;
        }

        @Override
        protected Void doInBackground(TipoNegocio... tipoNegocios) {
            this.tipoNegocioDao.insert(tipoNegocios[0]);
            return null;
        }
    }

    private static class UpdateTipoNegocioAsyncTask extends AsyncTask<TipoNegocio, Void, Void> {
        private TipoNegocioDao tipoNegocioDao;

        private UpdateTipoNegocioAsyncTask(TipoNegocioDao tipoNegocioDao){
            this.tipoNegocioDao = tipoNegocioDao;
        }

        @Override
        protected Void doInBackground(TipoNegocio... tipoNegocios) {
            this.tipoNegocioDao.update(tipoNegocios[0]);
            return null;
        }
    }

    private static class DeleteTipoNegocioAsyncTask extends AsyncTask<TipoNegocio, Void, Void> {
        private TipoNegocioDao tipoNegocioDao;

        private DeleteTipoNegocioAsyncTask(TipoNegocioDao tipoNegocioDao){
            this.tipoNegocioDao = tipoNegocioDao;
        }

        @Override
        protected Void doInBackground(TipoNegocio... tipoNegocios) {
            this.tipoNegocioDao.delete(tipoNegocios[0]);
            return null;
        }
    }

    private static class DeleteAllTipoNegocioAsyncTask extends AsyncTask<Void, Void, Void> {
        private TipoNegocioDao tipoNegocioDao;

        private DeleteAllTipoNegocioAsyncTask(TipoNegocioDao tipoNegocioDao){
            this.tipoNegocioDao = tipoNegocioDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.tipoNegocioDao.deleteAllTipoNegocio();
            return null;
        }
    }
}
