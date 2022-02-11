package com.example.lab_1_2_litsonthomas_c0834476_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab_1_2_litsonthomas_c0834476_android.DB.ProductRoomDB;
import com.example.lab_1_2_litsonthomas_c0834476_android.Model.Product;

public class AddNewProduct extends AppCompatActivity {

    EditText productName, productDesc, productPrice, productLongitude, productLatitude;
    Button submitBtn;
    ProductRoomDB database;
    Boolean isEditMode;
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        // connect the elements
        database = ProductRoomDB.getInstance(this);

        productName = findViewById(R.id.product_name);
        productDesc = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productLatitude = findViewById(R.id.provider_latitude);
        productLongitude = findViewById(R.id.provider_longitude);
        submitBtn = findViewById(R.id.submit_button);

        if(isEditMode == true){
          prepareEditForm();
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if(
              !isEditMode &&
              !productName.getText().equals("") && !productDesc.getText().equals("") && !productPrice.getText().equals("") && !productLongitude.getText().equals("")
                && !productLatitude.getText().equals("")
            ){
              Product product = new Product(productName.getText().toString(), productDesc.getText().toString(), Double.valueOf(productPrice.getText().toString()), Double.valueOf(productLatitude.getText().toString()), Double.valueOf(productLongitude.getText().toString()));
              Log.i("PRODUCT=> ", ""+product.toString());
              database.productDao().insert(product);
            }
          }
        });

    }

    public void prepareEditForm(){
      Product product = database.productDao().getProductById(productId);
      productName.setText(product.getProductName());
      productDesc.setText(product.getProductDesc());
      productPrice.setText(""+product.getProductPrice());
      productLatitude.setText(""+product.getProviderLat());
      productLongitude.setText(""+product.getProviderLong());
      submitBtn.setText("Update");
    }
}
