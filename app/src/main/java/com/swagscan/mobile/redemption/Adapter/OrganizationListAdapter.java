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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swagscan.mobile.redemption.Interface.OnNewsClickListener;
import com.swagscan.mobile.redemption.Interface.OnOrganizationClickListener;
import com.swagscan.mobile.redemption.Model.MessageModel;
import com.swagscan.mobile.redemption.Model.OrganizationModel;
import com.swagscan.mobile.redemption.Model.ProductModel;
import com.swagscan.mobile.redemption.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emerald on 7/2/2017.
 */
public class OrganizationListAdapter extends RecyclerView.Adapter<OrganizationListAdapter.ViewHolder> {

    private List<OrganizationModel> organizations = new ArrayList<>();

    private Context ctx;

    private final int mBackground;
    private final TypedValue mTypedValue = new TypedValue();

    public OrganizationListAdapter(Context ctx, List<OrganizationModel> items) {
        this.ctx = ctx;
        organizations = items;
        ctx.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }

    private OnOrganizationClickListener mOnOrganizationClickListener;

    public void OnOrganizationClickListener(final OnOrganizationClickListener mOnOrganizationClickListener) {
        this.mOnOrganizationClickListener = mOnOrganizationClickListener;
    }

    @Override
    public OrganizationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organization, parent, false);
        // set the view's size, margins, paddings and layout parameters
        v.setBackgroundResource(mBackground);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(OrganizationListAdapter.ViewHolder holder, final int position) {
        final OrganizationModel g = organizations.get(position);
        holder.name.setText(g.getName());
        Glide.with(ctx).load(g.getLogo()).into(holder.logo);

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);

        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnOrganizationClickListener != null){
                    mOnOrganizationClickListener.onOrganizationClick(view, g, position);
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
        return organizations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView logo;
        public RelativeLayout lyt_parent;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
