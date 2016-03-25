package com.example.rohit.chanderdhallapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohit.chanderdhallapp.Model.TodoData;

import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by Rohit on 3/23/2016.
 */
public class CreateTodo extends AppCompatActivity {

    private Switch switch_id;
    private TextView date_id;

    private TextView add_Button;
    private TextView cancel_Button;
    private TextView todo_title;

    String todo_date;
    TodoData todoObject = new TodoData();
    TodoData editTodo;

    Integer count = 0;

    private String completed = "pending";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_todo);

        switch_id = (Switch) findViewById(R.id.switch_id);
        date_id = (TextView) findViewById(R.id.date_id);
        todo_title = (TextView)findViewById(R.id.todo_title);


        add_Button = (TextView) findViewById(R.id.ok_button);
        cancel_Button = (TextView) findViewById(R.id.cancel_button);

        editTodo = (TodoData) getIntent().getSerializableExtra("editTodo");
        if(editTodo!=null){
            Log.i("testingingn", editTodo.getTitle());
            todo_title.setText(editTodo.getTitle());
            date_id.setText(editTodo.getDueDate());
            add_Button.setText("Edit");

            if(editTodo.getCompleted().equals("completed")){
                switch_id.setChecked(true);
                switch_id.setText("Yes   ");
            }else
                switch_id.setChecked(false);


        }



        cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_Button.getText().equals("Add")) {

                    final String title_value = String.valueOf(todo_title.getText());
                    //user_id_value = getActivity().getIntent().getIntExtra("userId",-1);
                    todo_date = String.valueOf(date_id.getText());

                    if (title_value.isEmpty() || todo_date.isEmpty()) {
                        if (title_value.isEmpty())
                            todo_title.setError("Title cannot be empty");
                        if (todo_date.isEmpty())
                            date_id.setError("Date cannot be empty");
                    } else {
                        todoObject = new TodoData();

                        todoObject.setTitle(title_value);
                        todoObject.setDueDate(todo_date);
                        todoObject.setCompleted(completed);

                        MainActivity.todoArrayList.add(todoObject);
                        //  MainActivity.dataEdited();

                        Intent intent = new Intent(CreateTodo.this, MainActivity.class);
                        // startActivity(intent);
                        setResult(Activity.RESULT_OK, intent);
                        finish();

                }
            }
                else{
                    final String title_value = String.valueOf(todo_title.getText());
                    //user_id_value = getActivity().getIntent().getIntExtra("userId",-1);
                    todo_date = String.valueOf(date_id.getText());

                    if (title_value.isEmpty() || todo_date.isEmpty()) {
                        if (title_value.isEmpty())
                            todo_title.setError("Title cannot be empty");
                        if (todo_date.isEmpty())
                            date_id.setError("Date cannot be empty");
                    } else {
                        todoObject = new TodoData();

                        todoObject.setTitle(title_value);
                        todoObject.setDueDate(todo_date);
                        todoObject.setCompleted(completed);
                      //  MainActivity.todoArrayList.clear();

                        for(Iterator<TodoData> it = MainActivity.todoArrayList.iterator(); it.hasNext(); ){
                        //for(TodoData todoData : MainActivity.todoArrayList){
                            TodoData todoData = it.next();
                            if(todoData.getTitle().equals(editTodo.getTitle())){
                                //MainActivity.todoArrayList.remove(editTodo);
                                it.remove();
                                todoData.setTitle(title_value);
                                todoData.setDueDate(todo_date);
                                todoData.setCompleted(completed);

                                MainActivity.todoArrayList.add(count,todoData);
                                break;
                            }
                            count++;
                         //   else
                           //     MainActivity.todoArrayList.add(todoData);
                        }
                    }
                        //MainActivity.todoArrayList.add(todoObject);
                          MainActivity.dataEdited();

                     //   Intent intent = new Intent(CreateTodo.this, MainActivity.class);
                        // startActivity(intent);
                     //   setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
            }
        });
                    date_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(date_id.getWindowToken(), 0);
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                // Log.i(" testFragment "," date picker clicked");

                DatePickerDialog dialog = new DatePickerDialog(getWindow().getContext(),new mDateSetListener(), mYear, mMonth, mDay);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.setTitle("Select Date");
                dialog.show();
            }
        });

        switch_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_id.isChecked()){
                    completed = "completed";
            //        Log.i("switch value", completed.toString());
                    switch_id.setText("Yes   ");
                }
                else {
                    completed = "pending";
//                    Log.i("switch value",completed.toString());
                      switch_id.setText("No   ");
                }
            }
        });
    }

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            // getCalender();
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            date_id.setText(new StringBuilder()
                            // Month is 0 based so add 1
                            .append(mYear)
                            .append("-")
                            .append(mMonth + 1)
                            .append("-")
                            .append(mDay)
                            .append(" ")

            );
            System.out.println(date_id.getText().toString());


        }
    }

}