package com.example.lab8_.Models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab8_.DAO.TaskDao;
import com.example.lab8_.DB.TaskDB;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskDao taskDao;
    private LiveData<List<TaskModel>> allTasks;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        TaskDB db = TaskDB.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<TaskModel>> getAllTasks() {
        return allTasks;
    }

    public void insert(TaskModel task) {
        new InsertTaskAsyncTask(taskDao).execute(task);
    }

    public void update(TaskModel task) {
        new UpdateTaskAsyncTask(taskDao).execute(task);
    }

    public void delete(TaskModel task) {
        new DeleteTaskAsyncTask(taskDao).execute(task);
    }






    private static class InsertTaskAsyncTask extends AsyncTask<TaskModel, Void, Void> {
        private TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskModel... tasks) {
            try {
                taskDao.insert(tasks[0]);
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<TaskModel, Void, Void> {
        private TaskDao taskDao;

        private UpdateTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskModel... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }




    private static class DeleteTaskAsyncTask extends AsyncTask<TaskModel, Void, Void> {
        private TaskDao taskDao;

        private DeleteTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(TaskModel... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }
}
