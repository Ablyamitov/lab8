package com.example.lab8_.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab8_.R;


public class EditActivity extends AppCompatActivity {

    private EditText noteName, noteDescription;
    private Button saveNoteButton;
    String name, description;
    boolean isNewTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);


        noteName = (EditText) findViewById(R.id.noteNameEditText);
        noteDescription = (EditText)
                findViewById(R.id.noteDescriptionEditText);

        saveNoteButton = (Button) findViewById(R.id.saveNoteButton);


        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            name = arguments.getString("name");
            description = arguments.getString("description");
            isNewTask = arguments.getBoolean("isNewTask");
            noteName.setText(name);
            noteDescription.setText(description);
        }




        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = newIntent(
                        noteName.getText().toString(),
                        noteDescription.getText().toString(),
                        isNewTask);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }


    private Intent newIntent(String name, String description, boolean isNewTask){
        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("isNewTask", isNewTask);
        return intent;
    }
}