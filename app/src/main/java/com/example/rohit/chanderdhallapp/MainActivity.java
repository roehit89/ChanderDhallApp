package com.example.rohit.chanderdhallapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rohit.chanderdhallapp.Model.TodoData;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList <TodoData> todoArrayList = new ArrayList<>();
    ListView listView;
    static CustomAdapter customAdapter;
    CustomActionBar customActionBar = new CustomActionBar();
    TextView barTitle = null;
    ImageButton deleteUser;
    ImageButton editUser;
    ImageButton addTodo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customActionBar.customActionBar(getSupportActionBar(), this);
        barTitle = (TextView) findViewById(R.id.actionBarTitle);
        deleteUser = (ImageButton) findViewById(R.id.deleteButtonId);
        editUser = (ImageButton) findViewById(R.id.editButtonId);

        addTodo = (ImageButton) findViewById(R.id.add_button);

        TodoData todo1 = new TodoData("title1","2016-1-25",1,"pending");

        TodoData todo2 = new TodoData("title2","2016-5-4",2,"pending");
        TodoData todo3 = new TodoData("title3","2016-3-1",3,"pending");
        TodoData todo4 = new TodoData("title4","2016-6-2",4,"pending");
        TodoData todo5 = new TodoData("title5","2016-3-13",5,"pending");
        TodoData todo6 = new TodoData("title6","2016-2-12",6,"pending");
        TodoData todo7 = new TodoData("title7","2016-5-22",7,"pending");
        TodoData todo8 = new TodoData("title8","2016-7-24",8,"pending");
        TodoData todo9 = new TodoData("title9","2016-9-25",9,"pending");

        todoArrayList.add(todo1);
        todoArrayList.add(todo2);
        todoArrayList.add(todo3);
        todoArrayList.add(todo4);
        todoArrayList.add(todo5);
        todoArrayList.add(todo6);
        todoArrayList.add(todo7);
        todoArrayList.add(todo8);
        todoArrayList.add(todo9);

//        HashSet<TodoData> hashset = new HashSet<>();
//        hashset.addAll(todoArrayList);
//        todoArrayList.clear();
//        todoArrayList.addAll(hashset);


        customAdapter = new CustomAdapter(this,todoArrayList);
        listView = (ListView)findViewById(R.id.listview_id);
        listView.setAdapter(customAdapter);

        deleteUser.setVisibility(View.INVISIBLE);
        editUser.setVisibility(View.INVISIBLE);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                editUser.setVisibility(View.INVISIBLE);
                deleteUser.setVisibility(View.INVISIBLE);
                // barTitle.setText("ToDo List");
                //  customActionBar.setActionBarColor("#831919");
                barTitle.setText("ChanderDhall app");

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CreateTodo.class);
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });



            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final TodoData temp = (TodoData) customAdapter.getItem(position);
                barTitle.setText(temp.getTitle());
                deleteUser.setVisibility(View.VISIBLE);
                editUser.setVisibility(View.VISIBLE);

                editUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      Intent intent = new Intent(getBaseContext(),CreateTodo.class);
                      intent.putExtra("editTodo", temp);
                        //startActivityForResult(intent, 2);
                      startActivity(intent);
                    }
                });

                deleteUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        AlertDialog.Builder adb = new AlertDialog.Builder(getWindow().getContext());
                                        adb.setTitle("Delete?");
                                        adb.setMessage("Are you sure you want to delete " + todoArrayList.get(position).getTitle());
                                        final int positionToRemove = position;
                                        adb.setNegativeButton("Cancel", new AlertDialog.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                deleteUser.setVisibility(View.INVISIBLE);
                                                editUser.setVisibility(View.INVISIBLE);
                                                barTitle.setText("ChanderDhall app");
                                            }
                                        });
                                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                todoArrayList.remove(position);
                                                customAdapter.notifyDataSetChanged();
                                                deleteUser.setVisibility(View.INVISIBLE);
                                                editUser.setVisibility(View.INVISIBLE);
                                                barTitle.setText("ChanderDhall app");
                                            }
                                        });
                                        adb.show();
                                    }
                                });
                            }
                        }).run();
                    }
                }); // delete on click ends



                return false;
            }
        });


    }

    public static void dataEdited(){
        customAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                dataEdited();
            }
        }
    }
}
