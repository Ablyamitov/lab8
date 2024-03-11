package com.example.lab8_.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lab8_.Models.TaskModel;
import com.example.lab8_.Models.TaskViewModel;
import com.example.lab8_.MyApplication;
import com.example.lab8_.R;

import java.util.ArrayList;

public class TaskInfoFragment extends Fragment {

    private TextView noteName, noteDescription;
    private CheckBox taskInfoCheckBox;

    private CheckBox taskCheckBox;
    String name, description;
    boolean isChecked;
    int position;

    private ListFragment listFragment;





    private TaskViewModel taskViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
        taskViewModel = ((MyApplication) getActivity().getApplication()).getTaskViewModel();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        noteName = view.findViewById(R.id.noteNameTextView);
        noteDescription =
                view.findViewById(R.id.noteDescriptionTextView);
        taskInfoCheckBox = view.findViewById(R.id.taskInfoCheckbox);

        taskCheckBox = view.findViewById(R.id.taskCheckBox);

        Bundle arguments = this.getArguments();
        if(arguments!=null){
            name = arguments.getString("name");
            description = arguments.getString("description");
            isChecked = arguments.getBoolean("isChecked");
            position = arguments.getInt("position");
            noteName.setText(name);
            noteDescription.setText(description);
            taskInfoCheckBox.setVisibility(View.VISIBLE);
            taskInfoCheckBox.setChecked(isChecked);


            taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<TaskModel>>() {
                @Override
                public void onChanged(List<TaskModel> taskModels) {
                    TaskModel taskModel = taskModels.get(position);
                    taskInfoCheckBox.setChecked(taskModel.isChecked());
                    taskInfoCheckBox.setChecked(taskViewModel.getAllTasks().getValue().get(position).isChecked());
                }
            });


            taskInfoCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    /*taskViewModel.getNotes().getValue().get(position).setChecked(isChecked);
                    taskInfoCheckBox.setChecked(taskViewModel.getNotes().getValue().get(position).isChecked());
                    ArrayList<TaskModel> updatedNotes = new ArrayList<>(taskViewModel.getNotes().getValue());
                    updatedNotes.get(position).setChecked(isChecked);
                    taskViewModel.getNotes().setValue(updatedNotes);*/

                    TaskModel currentTask = taskViewModel.getAllTasks().getValue().get(position);
                    currentTask.setChecked(isChecked);
                    taskViewModel.update(currentTask);

                }
            });
        }
    }
}