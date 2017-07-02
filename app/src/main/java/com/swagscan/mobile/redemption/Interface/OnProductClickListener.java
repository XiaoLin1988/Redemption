package com.swagscan.mobile.redemption.Interface;

import android.view.View;

import com.swagscan.mobile.redemption.Model.ProductModel;

/**
 * Created by emerald on 6/30/2017.
 */
public interface OnProductClickListener {
    void onProductClick(View view, ProductModel obj, int position);
}