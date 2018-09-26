package com.example.smit.wallettracking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList Names;
    ArrayList userRs;
    ArrayList userPos;
    ArrayList curdate;
    Context context;

    public CustomAdapter(ArrayList personNames, ArrayList userRs, ArrayList userPos, ArrayList curDate, Context context) {
        this.Names = personNames;
        this.userRs = userRs;
        this.userPos = userPos;
        this.curdate = curDate;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText((CharSequence) Names.get(position));
        holder.rs.setText(String.valueOf((Double) userRs.get(position)));
        holder.pos.setText((CharSequence) userPos.get(position));
//        holder.date.setText((CharSequence) curdate.get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, (Integer) Names.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return Names.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,rs,pos,date;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvcompany);
            rs = (TextView) itemView.findViewById(R.id.money);
            pos = (TextView) itemView.findViewById(R.id.tvnum);
            date = (TextView) itemView.findViewById(R.id.tvdate);

        }

    }
}
