package com.swagscan.mobile.redemption.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.swagscan.mobile.redemption.Adapter.ProductGridAdapter;
import com.swagscan.mobile.redemption.Interface.OnProductClickListener;
import com.swagscan.mobile.redemption.MainActivity;
import com.swagscan.mobile.redemption.Model.ProductModel;
import com.swagscan.mobile.redemption.ProductDetailActivity;
import com.swagscan.mobile.redemption.R;
import com.swagscan.mobile.redemption.Utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emerald on 6/30/2017.
 */
public class ShopFragment extends Fragment implements OnProductClickListener {
    private View view;
    private RecyclerView recyclerView;
    private ProductGridAdapter mAdapter;
    private LinearLayout lyt_notfound;

    private List<ProductModel> products;

    public ShopFragment() {
        prepareData();
    }

    private void prepareData() {
        products = new ArrayList<>();

        ProductModel p1 = new ProductModel();
        p1.setId(1);
        p1.setName("Testing");
        p1.setDescription("This is testing product");
        p1.setPhoto("http://img5.imgtn.bdimg.com/it/u=226401854,843359145&fm=26&gp=0.jpg");
        p1.setPoint(13);
        p1.setOrganization_id(1);

        products.add(p1);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_cart_products);
        lyt_notfound = (LinearLayout) view.findViewById(R.id.lyt_notfound);

        LinearLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), Tools.getGridSpanCount(getActivity()));
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ProductGridAdapter(getActivity(), products);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnProductClickListener(this);

        if(mAdapter.getItemCount()==0){
            lyt_notfound.setVisibility(View.VISIBLE);
        }else{
            lyt_notfound.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void onProductClick(View view, ProductModel obj, int position) {
        ProductDetailActivity.navigate((MainActivity)getActivity(), view.findViewById(R.id.img_product_preview), obj);
    }
}
