package com.swagscan.mobile.redemption.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swagscan.mobile.redemption.Interface.OnProductClickListener;
import com.swagscan.mobile.redemption.Model.ProductModel;
import com.swagscan.mobile.redemption.R;

import java.util.ArrayList;
import java.util.List;

public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ViewHolder> implements Filterable {

    private final int mBackground;
    private List<ProductModel> original_items = new ArrayList<>();
    private List<ProductModel> filtered_items = new ArrayList<>();
    private ItemFilter mFilter = new ItemFilter();

    private final TypedValue mTypedValue = new TypedValue();

    private Context ctx;

    // for item click listener
    private OnProductClickListener mOnProductClickListener;

    public void setOnProductClickListener(final OnProductClickListener mItemClickListener) {
        this.mOnProductClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView like;
        public TextView point;
        public ImageView image;
        public RelativeLayout lyt_parent;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.txt_product_title);
            point = (TextView) v.findViewById(R.id.txt_product_point);
            image = (ImageView) v.findViewById(R.id.img_product_preview);
            lyt_parent = (RelativeLayout) v.findViewById(R.id.lyt_parent);
        }
    }

    public Filter getFilter() {
        return mFilter;
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductGridAdapter(Context ctx, List<ProductModel> items) {
        this.ctx = ctx;
        original_items = items;
        filtered_items = items;
        ctx.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }

    @Override
    public ProductGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        v.setBackgroundResource(mBackground);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ProductModel p = filtered_items.get(position);
        holder.title.setText(p.getName());
        holder.point.setText(Integer.toString(p.getPoint()));
        Glide.with(ctx).load(R.drawable.sample_preview).into(holder.image);

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);

        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnProductClickListener != null){
                    mOnProductClickListener.onProductClick(view, p, position);
                }
            }
        });
    }

    /**
     * Here is the key method to apply the animation
     */
    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return filtered_items.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String query = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();
            final List<ProductModel> list = original_items;
            final List<ProductModel> result_list = new ArrayList<>(list.size());

            for (int i = 0; i < list.size(); i++) {
                String str_title = list.get(i).getName();
                if (str_title.toLowerCase().contains(query)) {
                    result_list.add(list.get(i));
                }
            }

            results.values = result_list;
            results.count = result_list.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered_items = (List<ProductModel>) results.values;
            notifyDataSetChanged();
        }

    }
}