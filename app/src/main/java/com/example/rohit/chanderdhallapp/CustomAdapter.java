package com.example.rohit.chanderdhallapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rohit.chanderdhallapp.Model.TodoData;

import java.util.ArrayList;

/**
 * Created by Rohit on 3/23/2016.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<TodoData> arrayList = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList <TodoData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final TodoData todoData = arrayList.get(position);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.single_list_view,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.title.setText(todoData.getTitle());
        viewHolder.status.setText(todoData.getCompleted());
        viewHolder.date.setText(todoData.getDueDate());

        return convertView;
    }

    private class ViewHolder {

        TextView title;
        TextView status;
        TextView date;


        public ViewHolder(View item)
        {
            title = (TextView)item.findViewById(R.id.toDoTitle);
            status = (TextView)item.findViewById(R.id.toDoCompleted);
            date = (TextView) item.findViewById(R.id.toDoDueDate);
        }
    }
}
