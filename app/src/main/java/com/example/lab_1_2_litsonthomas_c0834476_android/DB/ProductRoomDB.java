package com.example.lab_1_2_litsonthomas_c0834476_android.DB;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.lab_1_2_litsonthomas_c0834476_android.Model.Product;
import com.example.lab_1_2_litsonthomas_c0834476_android.data.ProductDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductRoomDB extends RoomDatabase {

  public abstract ProductDao productDao();

  private static volatile ProductRoomDB INSTANCE;

  private static final int NUMBER_OF_THREADS = 4;
  // executor service helps to do tasks in background thread
  public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

  public static ProductRoomDB getInstance(final Context context) {
    if (INSTANCE == null) {
      synchronized (ProductRoomDB.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              ProductRoomDB.class,
              "products_database")
            .addCallback(sRoomDatabaseCallback)
            .allowMainThreadQueries()
            .build();
        }
      }
    }
    return INSTANCE;
  }

  private static final RoomDatabase.Callback sRoomDatabaseCallback =
    new RoomDatabase.Callback() {
      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase db) {
        super.onCreate(db);

        databaseWriteExecutor.execute(() -> {
          ProductDao productDao = INSTANCE.productDao();
          productDao.deleteAll();

        });
      }
    };
}
