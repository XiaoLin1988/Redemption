package com.swagscan.mobile.redemption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.swagscan.mobile.redemption.Model.ProductModel;
import com.swagscan.mobile.redemption.Utils.GlobalVariable;

/**
 * Created by emerald on 7/1/2017.
 */
public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_PRODUCT = "KEY_PRODUCT";
    public static final String EXTRA_PREVIEW = "KEY_PREVIEW";

    public static void navigate(AppCompatActivity activity, View transitionImage, ProductModel obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT, json);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_PREVIEW);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private ProductModel productModel;
    private ActionBar actionBar;
    private GlobalVariable global;
    private View parent_view;
    private boolean in_cart=false;

    protected void onCreate(Bundle savedBundle) {
        super.onCreate(savedBundle);
        setContentView(R.layout.activity_product_detail);
        parent_view = findViewById(android.R.id.content);

        ViewCompat.setTransitionName(findViewById(R.id.img_product_detail_preview), EXTRA_PREVIEW);
        global = (GlobalVariable) getApplication();

        Gson gson = new Gson();
        String json = getIntent().getStringExtra(EXTRA_PRODUCT);
        productModel = gson.fromJson(json, ProductModel.class);
        //productModel = (ProductModel) getIntent().getSerializableExtra(EXTRA_OBJCT);

        initToolbar();
        initViewVariables();

        if (global.isProductExist(productModel)) {

        }
    }

    private void initViewVariables() {
        ((TextView)findViewById(R.id.txt_product_detail_points)).setText("1234");
        ((TextView)findViewById(R.id.txt_product_detail_description)).setText(productModel.getDescription());
        ((TextView)findViewById(R.id.txt_product_detail_items)).setText(Integer.toString(productModel.getPoint()));

        (findViewById(R.id.btn_product_detail_order)).setOnClickListener(this);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(productModel.getName());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_product_detail_order:
                break;
        }
    }
}
