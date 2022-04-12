package com.example.practive.utilss;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.practive.dao.ProductDao;
import com.example.practive.dao.ProductDao1;
import com.example.practive.entity.Product;
import com.example.practive.entity.Product;

import java.io.File;

@Database(entities =  {
        Product.class

}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //        private static AppDatabase appDatabase ;
//        public abstract SongRepo songRepo() ;
//        public static AppDatabase getAppDatabase(Context context){
//            if(appDatabase != null){
////                db name is android_exam
//                appDatabase = Room.databaseBuilder(context,
//                        AppDatabase.class , "android_exam").allowMainThreadQueries().build();
//            }
//            appDatabase.getOpenHelper().getWritableDatabase();
//            return appDatabase ;
//        }
    private static AppDatabase appDatabase;
    public abstract ProductDao productDao();
    public static final String TAG = "OPENINFO";
    public static AppDatabase getInstance(Context context, String dbName, boolean forceOpen) {
        if (null == dbName) {
            appDatabase = buildDatabaseInstance(context,dbName,forceOpen);
        }
        return appDatabase;
    }

    public static AppDatabase buildDatabaseInstance(Context context, String dbName, boolean forceOpen) {

        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class,
                dbName)
                .addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Log.d(TAG,"OnCreate callback invoked for " + dbName);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
                Log.d(TAG,"onOpen callback invoked for " + dbName);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }
        })
                .allowMainThreadQueries().build();
        if (forceOpen) {
            db.getOpenHelper().getWritableDatabase();
        }
        return db;
    }

    public static String doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        String path = dbFile.getAbsolutePath();
        return String.valueOf(dbFile.exists());
    }

    public void cleanUp(){
        if (appDatabase != null && appDatabase.isOpen()) {
            appDatabase.close();
        }
        appDatabase = null;
    }
}
