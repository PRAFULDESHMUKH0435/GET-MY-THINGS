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
import com.getmythings.admin2.Models.PopularProductModel;
import com.getmythings.admin2.R;


import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder> {

    private Context context;
    private List<PopularProductModel> popularProductModelList;

    public PopularProductsAdapter (Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate( R.layout.popularitems, parent, false ));
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(popularProductModelList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductModelList.get(position).getPrice()));

        holder.itemView.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent( context, DetailedActivity.class);
                intent.putExtra( "detailed",popularProductModelList.get(position));
                context.startActivity(intent);
            }
        } );
    }

    @Override
    public int getItemCount ( ) {
        return popularProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public ViewHolder (@NonNull View itemView) {
            super( itemView );

            imageView= itemView.findViewById(R.id.all_img);
            name =  itemView.findViewById(R.id.all_product_name);
            price= itemView.findViewById(R.id.all_price);

        }
    }
}
