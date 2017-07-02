package com.swagscan.mobile.redemption.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
import com.swagscan.mobile.redemption.Interface.OnNewsClickListener;
import com.swagscan.mobile.redemption.Interface.OnProductClickListener;
import com.swagscan.mobile.redemption.Model.MessageModel;
import com.swagscan.mobile.redemption.Model.ProductModel;
import com.swagscan.mobile.redemption.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emerald on 7/1/2017.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> implements Filterable {
    private List<MessageModel> original_items = new ArrayList<>();
    private List<MessageModel> filtered_items = new ArrayList<>();
    private NewsFilter mFilter = new NewsFilter();

    private final TypedValue mTypedValue = new TypedValue();

    private Context ctx;

    private OnNewsClickListener mOnNewsClickListener;

    public void setOnNewsClickListener(final OnNewsClickListener mItemClickListener) {
        this.mOnNewsClickListener = mItemClickListener;
    }

    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;
        public TextView content;
        public RelativeLayout lyt_parent;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.txt_news_title);
            date = (TextView) v.findViewById(R.id.txt_product_point);
            content = (TextView) v.findViewById(R.id.txt_news_content);
            lyt_parent = (RelativeLayout) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ViewHolder holder, final int position) {
        final MessageModel m = filtered_items.get(position);
        holder.title.setText(m.getTitle());
        holder.date.setText(Integer.toString(m.getCreated_at()));
        holder.content.setText(m.getContent());

        setAnimation(holder.itemView, position);

        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnNewsClickListener != null){
                    mOnNewsClickListener.onNewsClick(view, m, position);
                }
            }
        });
    }

    private int lastPosition = -1;
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(ctx, R.anim.slide_in_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return filtered_items.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class NewsFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String query = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();
            final List<MessageModel> list = original_items;
            final List<MessageModel> result_list = new ArrayList<>(list.size());

            for (int i = 0; i < list.size(); i++) {
                String str_title = list.get(i).getTitle();
                String str_content = list.get(i).getContent();
                if (str_title.toLowerCase().contains(query) || str_content.toLowerCase().contains(query)) {
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
            filtered_items = (List<MessageModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
