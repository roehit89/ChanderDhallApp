package com.example.rohit.chanderdhallapp.Model;

import java.io.Serializable;

/**
 * Created by Rohit on 3/23/2016.
 */

public class TodoData implements Serializable{

    private String title;
    private String dueDate;
    private Integer id;
    private String completed;

    public  TodoData(){

    }
    public TodoData(String title, String dueDate, Integer id, String completed){
        this.title = title;
        this.dueDate = dueDate;
        this.id = id;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }


}

