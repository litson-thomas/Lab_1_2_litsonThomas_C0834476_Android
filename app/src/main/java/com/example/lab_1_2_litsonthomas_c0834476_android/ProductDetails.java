package com.example.lab_1_2_litsonthomas_c0834476_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab_1_2_litsonthomas_c0834476_android.DB.ProductRoomDB;
import com.example.lab_1_2_litsonthomas_c0834476_android.Model.Product;

import org.w3c.dom.Text;

public class ProductDetails extends AppCompatActivity {

    ImageView backButton;
    TextView productTitle, productId, productPrice, productDetails;
    ProductRoomDB database;
    int productIdValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        database = ProductRoomDB.getInstance(this);

        // connect the elements
        backButton = findViewById(R.id.back_button);
        productTitle = findViewById(R.id.product_name);
        productDetails = findViewById(R.id.product_description);
        productId = findViewById(R.id.product_id);
        productPrice = findViewById(R.id.product_price);
        backButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            finish();
          }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
          productIdValue = Integer.parseInt(String.valueOf(extras.get("id")));
          Log.i("ID: ", ""+productIdValue);
          assignValues();
        }
    }

    public void assignValues(){
      Product product = database.productDao().getProductById(productIdValue);
      if(product != null){
        productTitle.setText(product.getProductName());
        productPrice.setText("$"+product.getProductPrice());
        productDetails.setText(product.getProductDesc());
        productId.setText("Product ID: "+product.getId());
      }
    }
}
