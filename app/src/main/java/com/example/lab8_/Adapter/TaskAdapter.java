package com.example.lab8_.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab8_.Models.TaskModel;
import com.example.lab8_.Models.TaskRecyclerModel;
import com.example.lab8_.Models.TaskViewModel;
import com.example.lab8_.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(TaskRecyclerModel taskRecyclerModel, int position);
    }

    private final OnItemClickListener onItemClickListener;

    private final LayoutInflater inflater;

    private final List<TaskRecyclerModel> taskRecyclerModels;

    private final TaskViewModel taskViewModel;

    public TaskAdapter(Context context, List<TaskRecyclerModel> taskRecyclerModels, OnItemClickListener onItemClickListener, TaskViewModel taskViewModel) {
        this.taskRecyclerModels = taskRecyclerModels;
        this.inflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
        this.taskViewModel = taskViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TaskRecyclerModel taskRecyclerModel = taskRecyclerModels.get(position);
        holder.flagView.setImageResource(taskRecyclerModel.getFlagResource());
        holder.nameView.setText(taskRecyclerModel.getName());
        //holder.taskCheckBox.setActivated(taskRecyclerModel.isChecked());

        holder.taskCheckBox.setChecked(taskRecyclerModel.isChecked());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListener.onItemClick(taskRecyclerModel, position);
            }
        });

        holder.taskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                TaskModel currentTask = taskViewModel.getAllTasks().getValue().get(position);
                currentTask.setChecked(isChecked);
                taskViewModel.update(currentTask);


            }
        });

    }

    @Override
    public int getItemCount() {
        return taskRecyclerModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView nameView;

        final CheckBox taskCheckBox;

        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.flag);
            nameView = view.findViewById(R.id.name);
            taskCheckBox = view.findViewById(R.id.taskCheckBox);
        }
    }
}
