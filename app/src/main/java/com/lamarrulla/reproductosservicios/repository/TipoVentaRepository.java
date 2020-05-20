package com.lamarrulla.reproductosservicios.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.dao.TipoVentaDao;
import com.lamarrulla.reproductosservicios.database.REPSDatabase;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.entity.TipoVenta;

import java.util.List;

public class TipoVentaRepository {
    private TipoVentaDao tipoVentaDao;
    private LiveData<List<TipoVenta>> allTipoVenta;

    public TipoVentaRepository(Application application) {
        REPSDatabase database = REPSDatabase.getInstance(application);
        tipoVentaDao = database.tipoVentaDao();
        allTipoVenta = tipoVentaDao.getAllTipoVenta();
    }

    public void insert(TipoVenta tipoVenta){
        new InsertTipoVentaAsyncTask(tipoVentaDao).execute(tipoVenta);
    }

    public void update(TipoVenta tipoVenta){
        new UpdateTipoVentaAsyncTask(tipoVentaDao).execute(tipoVenta);
    }

    public void delete(TipoVenta tipoVenta){
        new DeleteTipoVentaAsyncTask(tipoVentaDao).execute(tipoVenta);
    }

    public void deleteAllTipoVenta(){
        new DeleteAllTipoVentaAsyncTask(tipoVentaDao).execute();
    }

    public LiveData<List<TipoVenta>> getAllTipoVenta(){
        return allTipoVenta;
    }

    private static class InsertTipoVentaAsyncTask extends AsyncTask<TipoVenta, Void, Void>{
        private TipoVentaDao tipoVentaDao;

        private InsertTipoVentaAsyncTask(TipoVentaDao tipoVentaDao){
            this.tipoVentaDao = tipoVentaDao;
        }

        @Override
        protected Void doInBackground(TipoVenta... tipoVentas) {
            this.tipoVentaDao.insert(tipoVentas[0]);
            return null;
        }
    }

    private static class UpdateTipoVentaAsyncTask extends AsyncTask<TipoVenta, Void, Void>{
        private TipoVentaDao tipoVentaDao;

        private UpdateTipoVentaAsyncTask(TipoVentaDao tipoVentaDao){
            this.tipoVentaDao = tipoVentaDao;
        }

        @Override
        protected Void doInBackground(TipoVenta... tipoVentas) {
            tipoVentaDao.update(tipoVentas[0]);
            return null;
        }
    }

    private static class DeleteTipoVentaAsyncTask extends AsyncTask<TipoVenta, Void, Void>{
        private TipoVentaDao tipoVentaDao;

        private DeleteTipoVentaAsyncTask(TipoVentaDao tipoVentaDao){
            this.tipoVentaDao = tipoVentaDao;
        }

        @Override
        protected Void doInBackground(TipoVenta... tipoVentas) {
            tipoVentaDao.delete(tipoVentas[0]);
            return null;
        }
    }

    private static class DeleteAllTipoVentaAsyncTask extends AsyncTask<Void, Void, Void>{
        private TipoVentaDao tipoVentaDao;

        private DeleteAllTipoVentaAsyncTask(TipoVentaDao tipoVentaDao){
            this.tipoVentaDao = tipoVentaDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tipoVentaDao.deleteAllTipoVenta();
            return null;
        }
    }
}
