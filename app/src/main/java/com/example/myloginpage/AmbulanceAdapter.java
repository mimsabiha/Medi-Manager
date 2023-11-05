package com.example.myloginpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AmbulanceAdapter extends RecyclerView.Adapter<AmbulanceAdapter.AmbulanceViewHolder> {

    Context context;
    List<UserAmbulance> userlist;
    private static ClickListener clickListener;

    public AmbulanceAdapter(Context context, List<UserAmbulance> userlist) {
        this.context = context;
        this.userlist = userlist;
    }
    /*public AmbulanceAdapter(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }*/

    @NonNull
    @Override
    public AmbulanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_item,parent,false);
        return new AmbulanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmbulanceViewHolder holder, int position) {
        holder.ambulance_Name_Id.setText(userlist.get(position).getAmName());
        holder.ambulance_Location_Id.setText(userlist.get(position).getAmLoaction());
        holder.ambulance_Number_Id.setText(userlist.get(position).getAmNumber());

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
    //Viewholder class through which we can find each item member
    public class AmbulanceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView ambulance_Name_Id,ambulance_Location_Id,ambulance_Number_Id;
        public AmbulanceViewHolder(@NonNull View itemView) {
            super(itemView);
            ambulance_Name_Id = itemView.findViewById(R.id.textView_ambulanceName);
            ambulance_Location_Id = itemView.findViewById(R.id.textView_ambulanceLocation);
            ambulance_Number_Id = itemView.findViewById(R.id.textView_ambulanceNumber);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View view) {clickListener.onItemClick(getAdapterPosition(),view);

        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemClick(getAdapterPosition(),view);
            return false;
        }
    }

    public interface ClickListener{
        public void onItemClick(int position, View v);
        public void onItemLongClick(int position , View v);


    }

    public void setOnItemClickListener(ClickListener clickListener){
        AmbulanceAdapter.clickListener = clickListener;
    }
}
