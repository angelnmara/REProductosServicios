package com.lamarrulla.reproductosservicios.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lamarrulla.reproductosservicios.dao.ActividadDao;
import com.lamarrulla.reproductosservicios.dao.UserDao;
import com.lamarrulla.reproductosservicios.entity.Actividad;
import com.lamarrulla.reproductosservicios.entity.User;

@Database(entities = {User.class, Actividad.class}, version = 1)
public abstract class REPSDatabase extends RoomDatabase {
    private static REPSDatabase instance;
    public abstract UserDao userDao();
    public abstract ActividadDao actividadDao();
    public static synchronized REPSDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), REPSDatabase.class, "reps_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;
        private ActividadDao actividadDao;

        private PopulateDbAsyncTask(REPSDatabase db){
            actividadDao = db.actividadDao();
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("dave rincon", "dr@gmail.com", "55555555", "sierra dorada", "https://graph.facebook.com/3123179174379593/picture"));
            userDao.insert(new User("angel rincon", "ar@gmail.com", "55555555", "sierra dorada", "https://graph.facebook.com/3123179174379593/picture"));
            userDao.insert(new User("andres rincon", "andr@gmail.com", "55555555", "sierra dorada", "https://graph.facebook.com/3123179174379593/picture"));

            actividadDao.insert(new Actividad("Vendo", true));
            actividadDao.insert(new Actividad("Ofresco un servício", true));
            return null;
        }
    }

}
