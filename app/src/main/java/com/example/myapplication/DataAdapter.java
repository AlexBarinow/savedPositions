package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Anything> anythings;

    DataAdapter(Context context, List<Anything> anythings){
        this.anythings = anythings;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return  new  ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Anything anything = anythings.get(position);
        holder.nameView.setText(anything.getName());
        holder.textView.setText(anything.getText());
    }

    @Override
    public int getItemCount() {
        return anythings.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        final TextView textView;
            TextView nameView;

        ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.text);
            nameView = view.findViewById(R.id.name);
        }
    }

}