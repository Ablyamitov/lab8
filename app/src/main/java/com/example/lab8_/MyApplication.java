package com.example.lab8_;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import com.example.lab8_.Models.TaskViewModel;

public class MyApplication extends Application {
    private TaskViewModel taskViewModel;


    @Override
    public void onCreate() {
        super.onCreate();

        taskViewModel = new ViewModelProvider.AndroidViewModelFactory(this).create(TaskViewModel.class);
    }

    public TaskViewModel getTaskViewModel() {
        return taskViewModel;
    }
}

