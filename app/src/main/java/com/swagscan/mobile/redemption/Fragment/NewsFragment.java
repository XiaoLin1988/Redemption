package com.swagscan.mobile.redemption.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swagscan.mobile.redemption.R;

/**
 * Created by emerald on 6/30/2017.
 */
public class NewsFragment extends Fragment {

    public NewsFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }
}
