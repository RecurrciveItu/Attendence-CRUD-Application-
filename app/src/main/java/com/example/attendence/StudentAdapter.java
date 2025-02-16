package com.example.attendence;

import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentviewHolder> {
    ArrayList<StudentItem> studentItems;
    Context context;
    private OnItemClickListner onItemClickListner;
    public interface OnItemClickListner{
        void onClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public StudentAdapter(Context context, ArrayList<StudentItem> studentItems) {
        this.studentItems = studentItems;
        this.context = context;
    }


    public static class StudentviewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView roll;
        TextView name;
        TextView status;
        CardView cardView;




        public StudentviewHolder(@NonNull View itemView,OnItemClickListner onItemClickListner) {
            super(itemView);
            roll = itemView.findViewById(R.id.roll);
            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            cardView = itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(v->onItemClickListner.onClick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            menu.add(getAdapterPosition(),0,0,"Edit");
            menu.add(getAdapterPosition(),1,0,"Delete");
        }
    }
    @NonNull
    @Override
    public StudentviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);
        return new StudentviewHolder(itemView,onItemClickListner);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentviewHolder holder, int position) {
        holder.roll.setText(studentItems.get(position).getRoll()+"");
        holder.name.setText(studentItems.get(position).getName());
        holder.status.setText(studentItems.get(position).getStatus());
        holder.cardView.setCardBackgroundColor(getColor(position));
    }

    private int getColor(int position) {
        String status = studentItems.get(position).getStatus();
        if (status.equals("P"))
            return Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, R.color.present)));
        else if(status.equals("A"))
            return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.absent)));

        return Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(context,R.color.normal)));
    }

    @Override
    public int getItemCount() {
        return studentItems.size();
    }



}
