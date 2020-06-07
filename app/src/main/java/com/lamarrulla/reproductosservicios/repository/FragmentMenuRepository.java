package com.lamarrulla.reproductosservicios.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.lamarrulla.reproductosservicios.dao.FragmentMenuDao;
import com.lamarrulla.reproductosservicios.database.REPSDatabase;

import com.lamarrulla.reproductosservicios.entity.FragmentMenu;

import java.util.List;

public class FragmentMenuRepository {
    private FragmentMenuDao fragmentMenuDao;
    private LiveData<List<FragmentMenu>> allFragmentMenus;

    public FragmentMenuRepository(Application application){
        REPSDatabase database = REPSDatabase.getInstance(application);
        fragmentMenuDao = database.fragmentMenuDao();
        allFragmentMenus = fragmentMenuDao.getAllFragmentMenu();
    }

    public void insert(FragmentMenu fragmentMenu){
        new FragmentMenuRepository.InsertFragmentMenuAsyncTask(fragmentMenuDao).execute(fragmentMenu);

    }
    public void update(FragmentMenu fragmentMenu){
        new FragmentMenuRepository.UpdateFragmentMenuAsyncTask(fragmentMenuDao).execute(fragmentMenu);
    }
    public void delete(FragmentMenu fragmentMenu){
        new FragmentMenuRepository.DeleteFragmentMenuAsyncTask(fragmentMenuDao).execute(fragmentMenu);
    }
    public void deleteAllFragmentMenu(){
        new FragmentMenuRepository.DeleteAllFragmentMenuAsyncTask(fragmentMenuDao).execute();
    }
    public LiveData<List<FragmentMenu>> getAllFragmentMenus(){
        return allFragmentMenus;
    }

    private static class InsertFragmentMenuAsyncTask extends AsyncTask<FragmentMenu, Void, Void> {

        private FragmentMenuDao fragmentMenuDao;

        private InsertFragmentMenuAsyncTask(FragmentMenuDao fragmentMenuDao){
            this.fragmentMenuDao = fragmentMenuDao;
        }

        @Override
        protected Void doInBackground(FragmentMenu... fragmentMenus) {
            fragmentMenuDao.insert(fragmentMenus[0]);
            return null;
        }
    }

    private static class UpdateFragmentMenuAsyncTask extends AsyncTask<FragmentMenu, Void, Void>{

        private FragmentMenuDao fragmentMenuDao;

        private UpdateFragmentMenuAsyncTask(FragmentMenuDao fragmentMenuDao){
            this.fragmentMenuDao = fragmentMenuDao;
        }

        @Override
        protected Void doInBackground(FragmentMenu... fragmentMenus) {
            fragmentMenuDao.update(fragmentMenus[0]);
            return null;
        }
    }

    private static class DeleteFragmentMenuAsyncTask extends AsyncTask<FragmentMenu, Void, Void>{

        private FragmentMenuDao fragmentMenuDao;

        private DeleteFragmentMenuAsyncTask(FragmentMenuDao fragmentMenuDao){
            this.fragmentMenuDao = fragmentMenuDao;
        }

        @Override
        protected Void doInBackground(FragmentMenu... fragmentMenus) {
            fragmentMenuDao.delete(fragmentMenus[0]);
            return null;
        }
    }

    private static class DeleteAllFragmentMenuAsyncTask extends AsyncTask<Void, Void, Void>{

        private FragmentMenuDao fragmentMenuDao;

        private DeleteAllFragmentMenuAsyncTask(FragmentMenuDao fragmentMenuDao){
            this.fragmentMenuDao = fragmentMenuDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            fragmentMenuDao.deleteAllFragmentMenu();
            return null;
        }
    }
}
