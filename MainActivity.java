
package com.example.taskeasy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView taskRecyclerView;
    FloatingActionButton addTaskButton;
    TaskAdapter taskAdapter;
    List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        addTaskButton = findViewById(R.id.addTaskButton);

        taskAdapter = new TaskAdapter(taskList);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(taskAdapter);

        addTaskButton.setOnClickListener(v -> {
            showAddTaskDialog();
        });
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        builder.setView(view);

        EditText titleField = view.findViewById(R.id.titleField);
        EditText descriptionField = view.findViewById(R.id.descriptionField);
        Button saveButton = view.findViewById(R.id.saveButton);

        AlertDialog dialog = builder.create();
        saveButton.setOnClickListener(v -> {
            String title = titleField.getText().toString();
            String description = descriptionField.getText().toString();

            if (!title.isEmpty()) {
                taskList.add(new Task(title, description));
                taskAdapter.notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Título não pode ser vazio", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
        