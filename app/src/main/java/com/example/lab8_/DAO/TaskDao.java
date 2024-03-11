package com.example.lab8_.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab8_.Models.TaskModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM taskmodel")
    LiveData<List<TaskModel>> getAllTasks();


    @Insert
    void insert(TaskModel task);

    @Delete
    void delete(TaskModel task);

    @Query("DELETE FROM taskmodel")
    void deleteAll();

    @Update
    void update(TaskModel task);
}
