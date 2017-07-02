package com.swagscan.mobile.redemption.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.swagscan.mobile.redemption.Adapter.OrganizationListAdapter;
import com.swagscan.mobile.redemption.Model.OrganizationModel;
import com.swagscan.mobile.redemption.R;
import com.swagscan.mobile.redemption.Widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emerald on 6/30/2017.
 */
public class BadgeFragment extends Fragment implements View.OnClickListener {
    private List<OrganizationModel> organizations;

    private Button btnSwitch;
    private ImageView imgQr;

    public BadgeFragment() {
        organizations = new ArrayList<>();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_badge, container, false);

        btnSwitch = (Button)view.findViewById(R.id.btn_badge_switch);
        btnSwitch.setOnClickListener(this);

        imgQr = (ImageView)view.findViewById(R.id.img_badge_qr);
        imgQr.setOnClickListener(this);
        return view;
    }

    private void dialogSwitchOrganization() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_switch_organization);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LinearLayout lyt_notfound = (LinearLayout) dialog.findViewById(R.id.lyt_notfound);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_dialog_organization);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        //set data and list adapter
        OrganizationListAdapter mAdapter = new OrganizationListAdapter(getContext(), new ArrayList<OrganizationModel>());
        recyclerView.setAdapter(mAdapter);
        dialog.findViewById(R.id.btn_dialog_organization_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if(mAdapter.getItemCount() == 0){
            lyt_notfound.setVisibility(View.VISIBLE);
        }else{
            lyt_notfound.setVisibility(View.GONE);
        }
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void loadQr() {

        ImageView tempImageView = imgQr;

        final AlertDialog.Builder imageDialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.dialog_qr_detail, null);
        ImageView image = (ImageView) layout.findViewById(R.id.img_dialog_qr);
        image.setImageDrawable(tempImageView.getDrawable());
        //Glide.with(getContext()).load()
        imageDialog.setView(layout);

        imageDialog.create();
        imageDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_badge_switch:
                dialogSwitchOrganization();
                break;
            case R.id.img_badge_qr:
                loadQr();
                break;
        }
    }
}
