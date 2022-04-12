package com.example.practive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practive.entity.Product;

import java.util.List;

public class ListProductAdapter extends RecyclerView.Adapter<ListProductAdapter.ViewHolder> {

    List<Product> products;
    Context context;

    public ListProductAdapter(List<Product> list, Context context){
        this.products = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product current = products.get(position);
        holder.quantity.setText(current.getQuantity());
        holder.pName.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView pName, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pName = itemView.findViewById(R.id.pName);
            quantity = itemView.findViewById(R.id.pQuantity);
        }
    }
}
