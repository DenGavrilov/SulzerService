package com.gavriden.sulzerservice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Report> arrayList = new ArrayList<>();


    public RecyclerAdapter(ArrayList<Report> arrayList){

        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.Pump.setText(arrayList.get(position).getPump());
        int sync_status = arrayList.get(position).getSync_status();
        if(sync_status==DBHelper.SYNC_STATUS_OK){

            holder.Sync_Status.setImageResource(R.drawable.ok);

        }
        else {

            holder.Sync_Status.setImageResource(R.drawable.sync);

        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView Sync_Status;
        TextView Pump;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Sync_Status = itemView.findViewById(R.id.imgSync);
            Pump = itemView.findViewById(R.id.textPump);
        }
    }

}
