package com.example.lab8_.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.lab8_.Fragments.ListFragment;
import com.example.lab8_.Models.TaskViewModel;
import com.example.lab8_.R;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    private final int EDIT_ACTIVITY_REQUEST_CODE = 1;

    /*private EditText noteName, noteDescription;
    private Button addNoteButton, saveNoteButton, showNoteButton;
    private ImageButton prevNote, nextNote;*/
    TaskViewModel taskViewModel;

    private Intent newIntent(String name, String description, boolean isNewTask){
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("isNewTask", isNewTask);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Configuration configuration = getResources().getConfiguration();
        if ((configuration.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)>=Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Intent intent = new Intent(this, MainTabletActivity.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
        }

        frameLayout = findViewById(R.id.listFrameLayout);
        Fragment listViewFragment = new ListFragment();






        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listFrameLayout,listViewFragment)

                .commit();


        /*noteName = (EditText) findViewById(R.id.noteNameEditText);
        noteDescription = (EditText)
                findViewById(R.id.noteDescriptionEditText);
        addNoteButton = (Button) findViewById(R.id.addNoteButton);
        saveNoteButton = (Button) findViewById(R.id.saveNoteButton);
        showNoteButton = (Button) findViewById(R.id.showNoteButton);
        prevNote = (ImageButton) findViewById(R.id.prevNoteImageButton);
        nextNote = (ImageButton) findViewById(R.id.nextNoteImageButton);

        //По дефолту
        noteName.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getName());
        noteDescription.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getDescription());


        //Добавить
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = newIntent(
                        "Новая заметочка",
                        "",
                        true);
                startActivityForResult(intent,1);



            }
        });

        //Сохранить - Редактировать
        saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = newIntent(
                        taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getName(),
                        taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getDescription(),
                        false);
                startActivityForResult(intent,1);

            }
        });

        //Показать последнюю заметку
        showNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastIndex = taskViewModel.getNotes().size()-1;
                if(taskViewModel.getNotes().get(lastIndex).getName().isEmpty()||
                        taskViewModel.getNotes().get(lastIndex).getDescription().isEmpty()) taskViewModel.setNoteIndex(lastIndex-1);
                taskViewModel.setNoteIndex(taskViewModel.getNotes().size()-1);
                noteName.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getName());
                noteDescription.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getDescription());
                showToast("Последняя заметка");
            }
        });

        //Предыдущая
        prevNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskViewModel.setNoteIndex(taskViewModel.getNoteIndex()==0?0:taskViewModel.getNoteIndex()-1);
                noteName.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getName());
                noteDescription.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getDescription());
                showToast("Предыдущая заметка");
            }
        });

        //Следующая
        nextNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskViewModel.setNoteIndex(taskViewModel.getNoteIndex()==taskViewModel.getNotes().size()-1?taskViewModel.getNotes().size()-1:taskViewModel.getNoteIndex()+1);
                noteName.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getName());
                noteDescription.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getDescription());
                showToast("Следующая заметка");
            }
        });
*/
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                String name = data.getStringExtra("name");
                String description = data.getStringExtra("description");
                boolean isAddTask = data.getBooleanExtra("isNewTask",true);
                if (isAddTask) addTask(name,description);
                else editTask(name, description);
            }
        }
    }

    private void editTask(String name, String description) {
        taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).setName(name);
        taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).setDescription(description);

        noteName.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getName());
        noteDescription.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getDescription());

        showToast("Отредактировано");


    }

    private void addTask(String name, String description) {
        taskViewModel.setNoteIndex(taskViewModel.getNotes().size());
        taskViewModel.getNotes().add(new TaskModel(taskViewModel.getNoteIndex(),name,description));

        noteName.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getName());
        noteDescription.setText(taskViewModel.getNotes().get(taskViewModel.getNoteIndex()).getDescription());

        showToast("Добавлено");
    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text,
                Toast.LENGTH_LONG).show();
    }

 */
}