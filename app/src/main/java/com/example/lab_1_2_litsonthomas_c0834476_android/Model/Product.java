package com.example.lab_1_2_litsonthomas_c0834476_android.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(
  tableName = "products"
)
public class Product {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "product_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "product_name")
  private String productName;

  @ColumnInfo(name = "product_desc")
  private String productDesc;

  @ColumnInfo(name = "product_price")
  private Double productPrice;

  @ColumnInfo(name = "provider_latitude")
  private double providerLat;

  @ColumnInfo(name = "provider_longitude")
  private double providerLong;

  public Product(@NonNull String productName, String productDesc, Double productPrice, double providerLat, double providerLong) {
//    this.id = id;
    this.productName = productName;
    this.productDesc = productDesc;
    this.productPrice = productPrice;
    this.providerLat = providerLat;
    this.providerLong = providerLong;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getProductName() {
    return productName;
  }

  public void setProductName(@NonNull String productName) {
    this.productName = productName;
  }

  public String getProductDesc() {
    return productDesc;
  }

  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }

  public Double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(Double productPrice) {
    this.productPrice = productPrice;
  }

  public double getProviderLat() {
    return providerLat;
  }

  public void setProviderLat(double providerLat) {
    this.providerLat = providerLat;
  }

  public double getProviderLong() {
    return providerLong;
  }

  public void setProviderLong(double providerLong) {
    this.providerLong = providerLong;
  }


}
