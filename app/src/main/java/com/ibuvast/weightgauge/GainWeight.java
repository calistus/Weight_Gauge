package com.ibuvast.weightgauge;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ibuvast.Weight_Gauge.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GainWeight extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.underweight_advice, container, false);
    }
}
