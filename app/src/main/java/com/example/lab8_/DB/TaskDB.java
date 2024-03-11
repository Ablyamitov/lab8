package com.example.lab8_.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lab8_.DAO.TaskDao;
import com.example.lab8_.Models.TaskModel;

@Database(entities = {TaskModel.class}, version = 6)
public abstract class TaskDB extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static volatile TaskDB INSTANCE;

    public static TaskDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TaskDB.class, "taskDBase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
