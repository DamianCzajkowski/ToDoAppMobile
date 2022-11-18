package com.example.todoapp1;

import static java.time.LocalDateTime.now;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Task> tasks = new ArrayList<>();
    private ArrayAdapter<Task> taskAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasks.add(new Task("Zakupy"));
        tasks.add(new Task("Sprzątanie"));
        tasks.add(new Task("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley"));

        listView = (ListView) findViewById(R.id.taskView);
        taskAdapter = new ArrayAdapter<>(this, R.layout.row, R.id.textView, tasks);

        listView.setAdapter(taskAdapter);
        setupListViewListener();

    }
    public void setupListViewListener() {
        listView.setOnItemClickListener(
                (adapter, item, pos, id) -> {
                    Task task = tasks.get(pos);
                    if (task.getComplete()){
                        Toast.makeText(listView.getContext(), "TASK SUCCESSFULLY REMOVED", Toast.LENGTH_SHORT).show();
                        removeItem(pos);
                        taskAdapter.notifyDataSetChanged();
                    }
                    else {
                        if (task.getName().toLowerCase().contains("http")) {
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(tasks.get(pos).getName()));
                            startActivity(i);
                        }
                        tasks.get(pos).setComplete(true);
                        taskAdapter.notifyDataSetChanged();
                    }
                });
    }
    public void removeItem(int pos) {
        tasks.remove(pos);
        taskAdapter.notifyDataSetChanged();
    }
    public void addItem(View view) {
        EditText newItem = findViewById(R.id.newItem);
        newItem.setFilters(new InputFilter[]{
                new InputFilter.AllCaps()
        });
        String text = newItem.getText().toString();
        if (text.length() > 1) {
            taskAdapter.add(new Task(text));
            Snackbar.make(listView, "SUCCESFULLY ADDED NEW TASK", 1000).show();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove:
                tasks.removeAll(Collections.unmodifiableList(tasks));
                taskAdapter.notifyDataSetChanged();
            case R.id.add:
                final EditText taskEdit = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task").setView(taskEdit)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEdit.getText());
                                taskAdapter.add(new Task(task));
                            }
                        }).create();
                dialog.show();
                taskAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifeCycleOnStart", now().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifeCycleOnResume", now().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifeCycleOnPause",  now().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifeCycleOnStop", now().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lifeCycleOnRestart", now().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("lifeCycleOnDestroy", now().toString());
    }
}