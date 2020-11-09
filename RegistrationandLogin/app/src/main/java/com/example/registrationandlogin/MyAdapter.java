package com.example.registrationandlogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.HoldView> {
    Context ct;
    ArrayList<MyModel> list;

    public MyAdapter(Context ct, ArrayList<MyModel> list) {
        this.ct = ct;
        this.list = list;
    }

    @NonNull
    @Override
    public HoldView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HoldView(LayoutInflater.from(ct).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HoldView holder, final int position) {
        holder.address.setText(list.get(position).getAddress());
        holder.city.setText(list.get(position).getCity());
        holder.email.setText(list.get(position).getEmail());
        holder.name.setText(list.get(position).getName());
        holder.number.setText(list.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoldView extends RecyclerView.ViewHolder {
        TextView name, number, city, address, email;

        public HoldView(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            city = itemView.findViewById(R.id.city);
            email = itemView.findViewById(R.id.email);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);

        }
    }
}
