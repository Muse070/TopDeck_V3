package com.example.topdeckv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList card_id, full_name, email_address, physical_address, phone_number;

    CustomAdapter(Context context,
                  ArrayList card_id,
                  ArrayList full_name,
                  ArrayList email_address,
                  ArrayList physical_address,
                  ArrayList phone_number){
        this.context = context;
        this.card_id = card_id;
        this.full_name = full_name;
        this.email_address = email_address;
        this.physical_address = physical_address;
        this.phone_number = phone_number;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.full_name_txt.setText(String.valueOf(full_name.get(position)));
        holder.email_address_txt.setText(String.valueOf(email_address.get(position)));
        holder.physical_address_txt.setText(String.valueOf(physical_address.get(position)));
        holder.phone_number_txt.setText(String.valueOf(phone_number.get(position)));
    }

    @Override
    public int getItemCount() {
        return card_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView full_name_txt, email_address_txt, physical_address_txt, phone_number_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            full_name_txt = itemView.findViewById(R.id.name);
            email_address_txt = itemView.findViewById(R.id.email);
            physical_address_txt = itemView.findViewById(R.id.physical_addy);
            phone_number_txt = itemView.findViewById(R.id.phone_num);

        }
    }
}
