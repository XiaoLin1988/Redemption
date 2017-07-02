package com.swagscan.mobile.redemption.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;
import com.swagscan.mobile.redemption.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by emerald on 6/30/2017.
 */
public class QrFragment extends Fragment implements ZXingScannerView.ResultHandler {
    public QrFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ZXingScannerView mScannerView = new ZXingScannerView(getContext());   // Programmatically initialize the scanner view

        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();

        return mScannerView;
    }

    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
    }
}
