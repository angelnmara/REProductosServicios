package com.lamarrulla.reproductosservicios.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.dao.ActividadDao;
import com.lamarrulla.reproductosservicios.database.REPSDatabase;
import com.lamarrulla.reproductosservicios.entity.Actividad;

import java.util.List;

public class ActividadRepository {
    private ActividadDao actividadDao;
    private LiveData<List<Actividad>> allActividades;

    public ActividadRepository(Application application){
        REPSDatabase database = REPSDatabase.getInstance(application);
        actividadDao = database.actividadDao();
        allActividades = actividadDao.getAllActividades();
    }

    public void insert(Actividad actividad){
        new InsertActividadAsyncTask(actividadDao).execute(actividad);
    }
    public void update(Actividad actividad){
        new UpdateActividadAsyncTask(actividadDao).execute(actividad);
    }
    public void delete(Actividad actividad){
        new DeleteActividadAsyncTask(actividadDao).execute(actividad);
    }
    public void deleteAllUsers(){
        new DeleteAllActividadesAsyncTask(actividadDao).execute();
    }
    public LiveData<List<Actividad>> getAllActividades(){
        return allActividades;
    }

    private static class InsertActividadAsyncTask extends AsyncTask<Actividad, Void, Void>{

        private ActividadDao actividadDao;

        private InsertActividadAsyncTask(ActividadDao actividadDao){
            this.actividadDao = actividadDao;
        }

        @Override
        protected Void doInBackground(Actividad... actividads) {
            actividadDao.insert(actividads[0]);
            return null;
        }
    }

    private static class UpdateActividadAsyncTask extends AsyncTask<Actividad, Void, Void>{

        private ActividadDao actividadDao;

        private UpdateActividadAsyncTask(ActividadDao actividadDao){
            this.actividadDao = actividadDao;
        }

        @Override
        protected Void doInBackground(Actividad... actividads) {
            actividadDao.update(actividads[0]);
            return null;
        }
    }

    private static class DeleteActividadAsyncTask extends AsyncTask<Actividad, Void, Void>{

        private ActividadDao actividadDao;

        private DeleteActividadAsyncTask(ActividadDao actividadDao){
            this.actividadDao = actividadDao;
        }

        @Override
        protected Void doInBackground(Actividad... actividads) {
            actividadDao.delete(actividads[0]);
            return null;
        }
    }

    private static class DeleteAllActividadesAsyncTask extends AsyncTask<Void, Void, Void>{

        private ActividadDao actividadDao;

        private DeleteAllActividadesAsyncTask(ActividadDao actividadDao){
            this.actividadDao = actividadDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            actividadDao.deleteAllActividades();
            return null;
        }
    }
}
