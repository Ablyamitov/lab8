package com.example.lab8_.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.lab8_.Fragments.TaskInfoFragment;
import com.example.lab8_.R;

public class TaskInfoActivity extends AppCompatActivity {

    String name, description;
    boolean isChecked;
    int position;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);


        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            name = arguments.getString("name");
            description = arguments.getString("description");
            isChecked = arguments.getBoolean("isChecked");
            position = arguments.getInt("position");


        }


        Fragment taskInfoFragment = new TaskInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("description", description);
        bundle.putBoolean("isChecked", isChecked);
        bundle.putInt("position", position);

        taskInfoFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.taskInfoFrameLayout, taskInfoFragment)
                .commit();






    }
}