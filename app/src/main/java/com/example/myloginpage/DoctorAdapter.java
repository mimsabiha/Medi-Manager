package com.example.myloginpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DemoViewHolder> implements Filterable {
    Context context;
    String [] titleName;
    String [] designation;
    List<User> list;
    List<User>listFull;


    public DoctorAdapter(Context context,List<User> list) {
        this.context = context;
        this.list = list;
        listFull = new ArrayList<>(list);
    }

    private static ClickListener clickListener;


    public DoctorAdapter(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }
   /* public DemoAdapter(Context context, String[] titleName, String[] designation ) {
        this.context = context;
        this.titleName = titleName;
        this.designation = designation;

    }*/

    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sample_doctor,parent,false);
        return new DemoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DemoViewHolder holder, int position) {
        holder.docNameView.setText(list.get(position).getDoctorName());
        holder.docSpecialistView.setText(list.get(position).getDoctorSpecialist());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //filtering doctor from a list
    @Override
    public Filter getFilter() {
        return FilterUser;
    }
    private Filter FilterUser = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String text = charSequence.toString().toLowerCase();
            List<User>tempList = new ArrayList<>();
            if(text.length()==0 || text.isEmpty())
            {
                tempList.addAll(listFull);
            }else{
                for(User item:listFull){
                    if(item.getDoctorName().toLowerCase().contains(text) || item.getDoctorSpecialist().toLowerCase().contains(text)){
                        tempList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = tempList;
            return filterResults;
        }

        //publishing filtered results
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((Collection<? extends User>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    class DemoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView docNameView,docSpecialistView;

        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);
            docNameView = itemView.findViewById(R.id.docName);
            docSpecialistView = itemView.findViewById(R.id.docSpecialist);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(),view);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(),view);
            return false;
        }
    }

    public interface ClickListener{
        public void onItemClick(int position, View v);
        public void onItemLongClick(int position , View v);


    }

    public void setOnItemClickListener(ClickListener clickListener){
        DoctorAdapter.clickListener = clickListener;
    }
}
