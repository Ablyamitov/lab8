package com.example.lab8_.Models;

public class TaskRecyclerModel {
    private int flagResource;
    private String name;

    private boolean checked;


    public TaskRecyclerModel(int flag,String name, boolean checked){

        this.flagResource=flag;
        this.name=name;
        this.checked=checked;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
