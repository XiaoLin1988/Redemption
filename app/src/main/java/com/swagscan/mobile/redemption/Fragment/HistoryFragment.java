package com.swagscan.mobile.redemption.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swagscan.mobile.redemption.R;

/**
 * Created by emerald on 6/30/2017.
 */
public class HistoryFragment extends Fragment {
    private RecyclerView recycler;

    public HistoryFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recycler = (RecyclerView)view.findViewById(R.id.recycler_history);

        return view;
    }
}
