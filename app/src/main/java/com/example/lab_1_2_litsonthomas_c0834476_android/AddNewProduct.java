package com.example.lab_1_2_litsonthomas_c0834476_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lab_1_2_litsonthomas_c0834476_android.DB.ProductRoomDB;
import com.example.lab_1_2_litsonthomas_c0834476_android.Model.Product;

public class AddNewProduct extends AppCompatActivity {

    EditText productName, productDesc, productPrice, productLongitude, productLatitude;
    Button submitBtn;
    ImageView backButton;
    ProductRoomDB database;
    Boolean isEditMode;
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        database = ProductRoomDB.getInstance(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          Log.i("Extras => " ,""+extras);
          isEditMode = true;
          Log.i("Extras ID => " ,""+extras.get("id"));
          productId = Integer.parseInt(String.valueOf(extras.get("id")));
        }
        else{
          isEditMode = false;
        }

        // connect the elements
        backButton = findViewById(R.id.back_button);
        productName = findViewById(R.id.product_name);
        productDesc = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productLatitude = findViewById(R.id.provider_latitude);
        productLongitude = findViewById(R.id.provider_longitude);
        submitBtn = findViewById(R.id.submit_button);

        if(isEditMode){
          prepareEditForm();
        }

        // back button click
        backButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            finish();
          }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if(!isEditMode && isAllValuesEntered()){
              Product product = new Product(productName.getText().toString(), productDesc.getText().toString(), Double.valueOf(productPrice.getText().toString()), Double.valueOf(productLatitude.getText().toString()), Double.valueOf(productLongitude.getText().toString()));
              database.productDao().insert(product);
              Intent intent = new Intent(AddNewProduct.this, HomePage.class);
              startActivity(intent);
            }
            else if(isEditMode && isAllValuesEntered()){
              Product product = database.productDao().getProductById(productId);
              database.productDao().updateProduct(product.getId(), product.getProductName(), product.getProductDesc(), product.getProductPrice(), product.getProviderLat(), product.getProviderLong());
              Intent intent = new Intent(AddNewProduct.this, HomePage.class);
              startActivity(intent);
            }
          }
        });

    }

    public void prepareEditForm(){
      Product product = database.productDao().getProductById(productId);
      if(product != null){
        productName.setText(product.getProductName());
        productDesc.setText(product.getProductDesc());
        productPrice.setText(""+product.getProductPrice());
        productLatitude.setText(""+product.getProviderLat());
        productLongitude.setText(""+product.getProviderLong());
        submitBtn.setText("Update");
      }
    }

    public Boolean isAllValuesEntered(){
      return !productName.getText().equals("") && !productDesc.getText().equals("") && !productPrice.getText().equals("") && !productLongitude.getText().equals("") && !productLatitude.getText().equals("");
    }
}
