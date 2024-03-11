package com.example.lab8_.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.lab8_.Activity.TaskInfoActivity;
import com.example.lab8_.Adapter.TaskAdapter;
import com.example.lab8_.Models.TaskModel;
import com.example.lab8_.Models.TaskRecyclerModel;
import com.example.lab8_.Models.TaskViewModel;
import com.example.lab8_.MyApplication;
import com.example.lab8_.R;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment {

    TaskViewModel taskViewModel;
    RecyclerView taskRecyclerView;
    Button addButton;

    CheckBox taskCheckBox, taskInfoCheckBox;
    ArrayList<TaskRecyclerModel> taskRecyclerModels;
    TaskAdapter adapter;
    TaskAdapter.OnItemClickListener itemClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskViewModel = ((MyApplication) getActivity().getApplication()).getTaskViewModel();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskCheckBox = view.findViewById(R.id.taskCheckBox);
        addButton = view.findViewById(R.id.addButton);
        taskInfoCheckBox = view.findViewById(R.id.taskInfoCheckbox);

        addButton.setOnClickListener(v -> {

            TaskModel newTask = new TaskModel(
                    "Поставьте 5, пожалуйста",
                    "Пожалуйста",
                    false);
            taskViewModel.insert(newTask);
        });


        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), taskModels -> {
            if (taskModels != null) {
                taskRecyclerModels = getTaskRecyclerModelList(taskModels);
                updateRecyclerView(taskRecyclerView);
            }
        });

        taskRecyclerView = view.findViewById(R.id.taskRecyclerView);
        itemClickListener = (taskRecyclerModel, position) -> {

            Configuration configuration = getResources().getConfiguration();
            if ((configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)>=Configuration.SCREENLAYOUT_SIZE_LARGE) {
                Fragment taskInfoFragment = new TaskInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", taskViewModel.getAllTasks().getValue().get(position).getName());
                bundle.putString("description", taskViewModel.getAllTasks().getValue().get(position).getDescription());
                bundle.putBoolean("isChecked", taskViewModel.getAllTasks().getValue().get(position).isChecked());
                bundle.putInt("position",position);
                taskInfoFragment.setArguments(bundle);
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.tabletTaskInfoFrameLayout, taskInfoFragment)
                        .commit();
            } else {
                Intent intent = newIntent(
                        taskViewModel.getAllTasks().getValue().get(position).getName(),
                        taskViewModel.getAllTasks().getValue().get(position).getDescription(),
                        taskViewModel.getAllTasks().getValue().get(position).isChecked(),
                        position);
                startActivity(intent);
            }


        };

    }

    public void updateRecyclerView(RecyclerView taskRecyclerView) {
        taskRecyclerModels = getTaskRecyclerModelList(taskViewModel.getAllTasks().getValue());
        adapter = new TaskAdapter(getActivity(), taskRecyclerModels,itemClickListener,taskViewModel);
        taskRecyclerView.setAdapter(adapter);
    }


    private Intent newIntent(String name, String description, boolean isChecked, int position){
        Intent intent = new Intent(getActivity(), TaskInfoActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("isChecked",isChecked);
        intent.putExtra("position",position);

        return intent;
    }


    ArrayList<TaskRecyclerModel> getTaskRecyclerModelList(List<TaskModel> notes){
        ArrayList<TaskRecyclerModel> taskRecyclerModels = new ArrayList<>();
        for (int i = 0; i < notes.size(); i++){
            TaskRecyclerModel taskRecyclerModel = new TaskRecyclerModel(notes.get(i).getFlagResource(),
                    notes.get(i).getName(),
                    notes.get(i).isChecked());
            taskRecyclerModels.add(taskRecyclerModel);
        }
        return taskRecyclerModels;
    }

}