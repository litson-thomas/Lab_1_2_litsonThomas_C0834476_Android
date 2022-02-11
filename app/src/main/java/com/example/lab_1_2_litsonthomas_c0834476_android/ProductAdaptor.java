package com.example.lab_1_2_litsonthomas_c0834476_android;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1_2_litsonthomas_c0834476_android.DB.ProductRoomDB;
import com.example.lab_1_2_litsonthomas_c0834476_android.Model.Product;

import java.util.List;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {

  // variables
  private List<Product> productsList;
  private Activity context;
  private ProductRoomDB database;

  public ProductAdaptor(Activity context, List<Product> list){
    this.productsList = list;
    this.context = context;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Product product = productsList.get(position);
    database = ProductRoomDB.getInstance(context);
    holder.title.setText(product.getProductName());
    holder.price.setText("Price: $"+product.getProductPrice());

    holder.wrapperLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, ProductDetails.class);
        intent.putExtra("id", product.getId());
        context.startActivity(intent);
      }
    });

    // edit button click
    holder.btn_edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, AddNewProduct.class);
        intent.putExtra("id", product.getId());
        context.startActivity(intent);
      }
    });
    // delete button click
    holder.btn_delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Product product = productsList.get(holder.getAdapterPosition());
        database.productDao().delete(product);
        int position = holder.getAdapterPosition();
        productsList.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position);
      }
    });

  }

  @Override
  public int getItemCount() {
    return productsList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    LinearLayout wrapperLayout;
    TextView title, price;
    ImageView btn_edit, btn_delete;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      wrapperLayout = itemView.findViewById(R.id.actions_wrapper);
      title = itemView.findViewById(R.id.title);
      price = itemView.findViewById(R.id.price);
      btn_edit = itemView.findViewById(R.id.edit_btn);
      btn_delete = itemView.findViewById(R.id.delete_btn);
    }
  }
}
