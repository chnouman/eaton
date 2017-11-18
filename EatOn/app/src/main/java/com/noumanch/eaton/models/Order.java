package com.noumanch.eaton.models;

/**
 * Created by Nouman01 on 10/2/2017.
 */

public class Order {

    private String productId;
    private String productName;
    private String Quantity;
    private String Price;
    private String Discount;


    public Order() {
    }

    public Order(String productId, String productName, String quantity, String price, String discount) {
        this.productId = productId;
        this.productName = productName;
        Quantity = quantity;
        Price = price;
        Discount = discount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
