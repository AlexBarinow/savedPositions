package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public  class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Anything> anythings;
    private OnRecycleListener  onERecycleListener;



    DataAdapter(Context context, List<Anything> anythings, OnRecycleListener onRecycleListener){
        this.anythings = anythings;
        this.inflater = LayoutInflater.from(context);
        this.onERecycleListener = onRecycleListener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return  new  ViewHolder(view, onERecycleListener);
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

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView textView;
            TextView nameView;

            OnRecycleListener onRecycleListener;



        ViewHolder(View view, OnRecycleListener onRecycleListener){
            super(view);
            textView = view.findViewById(R.id.text);
            nameView = view.findViewById(R.id.name);

            this.onRecycleListener = onRecycleListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onRecycleListener.onRecycleClick(getAdapterPosition() );
        }
    }

    public interface OnRecycleListener{
        void onRecycleClick(int position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}