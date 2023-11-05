package com.example.smartbasket;

public class Product {

    private String productBarcode, productName, productPrice, productCategory, productDescription;
    public Product() {}

    public Product(String productBarcode, String productName, String productPrice, String productCategory, String productDescription) {
        this.productBarcode = productBarcode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        this.productDescription =  productDescription;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

}
