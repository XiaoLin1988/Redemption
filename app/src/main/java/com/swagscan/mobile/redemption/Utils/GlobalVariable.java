package com.swagscan.mobile.redemption.Utils;

import android.app.Application;

import com.swagscan.mobile.redemption.Model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class GlobalVariable extends Application {
    private List<ProductModel> products = new ArrayList<>();

    public void addCart(ProductModel model) {
        products.add(model);
    }
    public void removeCart(ProductModel model) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId()==model.getId()){
                products.remove(i);
                break;
            }
        }
    }
    public void clearProducts() {
        products.clear();
    }
    public List<ProductModel> getProducts() {
        return products;
    }

    public int getProductsPointTotal() {
        int total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getPoint();
        }
        return total;
    }
    public int getProductsCount() {
        return products.size();
    }

    public void updateProductTotal(ProductModel model) {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId()==model.getId()){
                products.remove(i);
                products.add(i, model);
                break;
            }
        }
    }

    public boolean isProductExist(ProductModel model){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId()==model.getId()){
                return true;
            }
        }
        return false;
    }
}
