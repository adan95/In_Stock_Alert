package com.example.instockalert;


import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter {
    List<String> type, info;
    List<Integer> images;
    LayoutInflater inflater;

public MyAdapter(Context context, List<String> type, List<String> info, List<Integer> images){
    this.type = type;
    this.info = info;
    this.images = images;
    this.inflater = LayoutInflater.from(context);
}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.display_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.type.setText(type.get(position));
        myViewHolder.info.setText(info.get(position));
        myViewHolder.images.setImageResource(images.get(position));

    }


    @Override
    public int getItemCount() {
        return type.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView type, info;
        ImageView images;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.itemTitle);
            info = itemView.findViewById(R.id.isItInStock);
            images = itemView.findViewById(R.id.itemImage);
        }
    }

}
