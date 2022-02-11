package com.example.lab_1_2_litsonthomas_c0834476_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.example.lab_1_2_litsonthomas_c0834476_android.DB.ProductRoomDB;
import com.example.lab_1_2_litsonthomas_c0834476_android.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    EditText searchInput;
    Button addNewBtn;
    RecyclerView recyclerView;
    List<Product> productsList = new ArrayList<Product>();
    LinearLayoutManager linearLayoutManager;
    ProductRoomDB database;
    ProductAdaptor listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // connect the components
        searchInput = findViewById(R.id.searchInput);
        addNewBtn = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.products_list);

        database = ProductRoomDB.getInstance(this);
        productsList = database.productDao().getAll();
        Log.i("Products are => ", ""+ productsList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter = new ProductAdaptor(HomePage.this, productsList);
        recyclerView.setAdapter(listAdapter);

        // add new product page
        addNewBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(HomePage.this, AddNewProduct.class);
            startActivity(intent);
          }
        });

        // add dummy data
        addDummy();
    }

    String dummy_desc = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
    public void addDummy(){
      int count = database.productDao().getCount();
      Log.i("count", ""+count);
      if(count < 10){
        database.productDao().deleteAll();
        Product product = new Product("Product #1", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product);
        Product product1 = new Product("Product #2", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product1);
        Product product2 = new Product("Product #3", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product2);
        Product product3 = new Product("Product #4", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product3);
        Product product4 = new Product("Product #5", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product4);
        Product product5 = new Product("Product #6", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product5);
        Product product6 = new Product("Product #7", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product6);
        Product product7 = new Product("Product #8", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product7);
        Product product8 = new Product("Product #9", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product8);
        Product product9 = new Product("Product #10", dummy_desc, 45.0, 43.800, 43.800);
        database.productDao().insert(product9);
      }
    }


}
