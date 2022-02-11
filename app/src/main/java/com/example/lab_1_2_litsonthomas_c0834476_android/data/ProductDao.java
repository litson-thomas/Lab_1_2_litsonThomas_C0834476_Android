package com.example.lab_1_2_litsonthomas_c0834476_android.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab_1_2_litsonthomas_c0834476_android.Model.Product;

import java.util.List;

@Dao
public abstract class ProductDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public abstract void insert(Product product);

  @Delete
  public abstract void delete(Product product);

  @Query("DELETE FROM products")
  public abstract void deleteAll();

  @Query("SELECT * FROM products WHERE product_id = :id")
  public abstract Product getProductById(int id);

  @Query("UPDATE products SET product_name = :productName, product_desc = :productDescription, product_price = :productPrice, provider_latitude = :providerLatitude, provider_longitude = :providerLongitude  WHERE product_id = :productId")
  public abstract void updateProduct(int productId, String productName, String productDescription, Double productPrice, Double providerLatitude, Double providerLongitude);

  @Query("SELECT * FROM products order by product_id DESC")
  public abstract List<Product> getAll();

  @Query("SELECT * FROM products WHERE product_name LIKE '%' || :query || '%' or product_desc LIKE '%' || :query || '%' order by product_id DESC")
  public abstract List<Product> getSearchResults(String query);

  @Query("SELECT count(*) as totalProducts FROM products")
  public abstract int getCount();

}
