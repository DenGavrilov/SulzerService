package com.gavriden.sulzerservice;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<UnsentReports> mArrayList;
    //private ArrayList pump_number, date_stamp;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    /*CustomAdapter(Context context, ArrayList pump_number, ArrayList date_stamp){

        this.context = context;
        this.pump_number = pump_number;
        this.date_stamp = date_stamp;

    }*/

    public CustomAdapter(ArrayList<UnsentReports> arrayList){

        mArrayList = arrayList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rep_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v, mListener);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UnsentReports unsentReports = mArrayList.get(position);

        //holder.text_pump_number.setText(String.valueOf(pump_number.get(position)));
        //holder.text_date.setText(String.valueOf(date_stamp.get(position)));
        holder.text_pump_number.setText(unsentReports.getPump());
        holder.text_date.setText(unsentReports.getDate());

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_pump_number, text_date;
        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            text_pump_number = itemView.findViewById(R.id.text_pump_number);
            text_date = itemView.findViewById(R.id.text_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }

                }
            });
        }
    }
}
