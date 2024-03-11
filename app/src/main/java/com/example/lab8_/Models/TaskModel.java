package com.example.lab8_.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.lab8_.R;

@Entity
public class TaskModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;


    private int flagResource;
    private boolean checked;






    public TaskModel(String name, String description, boolean checked){
        //this.id = -1;
        this.name = name;
        this.description = description;

        this.flagResource=R.drawable.kot;
        this.checked=checked;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getFlagResource() {
        return this.flagResource;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }



}
