package com.getmythings.admin2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.getmythings.admin2.Activities.DetailedActivity;
import com.getmythings.admin2.Models.ShowAllModel;
import com.getmythings.admin2.R;

import java.util.ArrayList;
import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {

    private Context context;
    private List<ShowAllModel>list;

    public ShowAllAdapter (Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(parent.getContext()).inflate( R.layout.show_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.mItemImg);
        holder.mCost.setText("Rs"+(list.get(position).getPrice()));
        holder.mName.setText(list.get(position).getName());


        holder.itemView.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra( "detailed",list.get(position));
                context.startActivity(intent);
            }
        } );

    }

    @Override
    public int getItemCount ( ) {
        return list.size();
    }

    public void filteredlist (ArrayList<ShowAllModel> filterList) {
        list = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mItemImg;
        TextView mCost,mName;
        public ViewHolder (@NonNull View itemView) {
            super( itemView );

            mItemImg = itemView.findViewById(R.id.item_image);
            mCost = itemView.findViewById(R.id.item_cost);
            mName = itemView.findViewById(R.id.item_nam);

        }
    }
}
