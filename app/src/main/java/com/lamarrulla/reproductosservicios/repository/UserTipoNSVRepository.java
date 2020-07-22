package com.lamarrulla.reproductosservicios.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.lamarrulla.reproductosservicios.dao.UserTipoNSVDao;
import com.lamarrulla.reproductosservicios.database.REPSDatabase;
import com.lamarrulla.reproductosservicios.entity.UserTipoNSV;


import java.util.List;

public class UserTipoNSVRepository {
    private UserTipoNSVDao userTipoNSVDao;
    private LiveData<List<UserTipoNSV>> allUserTipoNSV;

    public UserTipoNSVRepository(Application application){
        REPSDatabase database = REPSDatabase.getInstance(application);
        userTipoNSVDao = database.userTipoNSVDao();
        allUserTipoNSV = userTipoNSVDao.getAllUserTipoNSV();
    }

    public void insert(UserTipoNSV userTipoNSV){
        new UserTipoNSVRepository.InsertUserAsyncTask(userTipoNSVDao).execute(userTipoNSV);
    }

    public void update(UserTipoNSV userTipoNSV){
        new UserTipoNSVRepository.UpdateUserAsyncTask(userTipoNSVDao).execute(userTipoNSV);
    }

    public void delete(UserTipoNSV userTipoNSV){
        new UserTipoNSVRepository.DeleteUserAsyncTask(userTipoNSVDao).execute(userTipoNSV);
    }

    public void deleteAllUsers(){
        new UserTipoNSVRepository.DeleteAllUsersAsyncTask(userTipoNSVDao).execute();
    }

    public LiveData<List<UserTipoNSV>> getAllUserTipoNSV(){
        return allUserTipoNSV;
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserTipoNSV, Void, Void> {
        private UserTipoNSVDao userTipoNSVDao;

        private InsertUserAsyncTask(UserTipoNSVDao userTipoNSVDao){
            this.userTipoNSVDao = userTipoNSVDao;
        }

        @Override
        protected Void doInBackground(UserTipoNSV... users) {
            userTipoNSVDao.insert(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<UserTipoNSV, Void, Void>{
        private UserTipoNSVDao userTipoNSVDao;

        private UpdateUserAsyncTask(UserTipoNSVDao userTipoNSVDao){
            this.userTipoNSVDao = userTipoNSVDao;
        }

        @Override
        protected Void doInBackground(UserTipoNSV... users) {
            userTipoNSVDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<UserTipoNSV, Void, Void>{
        private UserTipoNSVDao userTipoNSVDao;

        private DeleteUserAsyncTask(UserTipoNSVDao userTipoNSVDao){
            this.userTipoNSVDao = userTipoNSVDao;
        }

        @Override
        protected Void doInBackground(UserTipoNSV... users) {
            userTipoNSVDao.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserTipoNSVDao userTipoNSVDao;

        private DeleteAllUsersAsyncTask(UserTipoNSVDao userTipoNSVDao){
            this.userTipoNSVDao = userTipoNSVDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userTipoNSVDao.deleteAllUserTipoNSV();
            return null;
        }
    }
}
