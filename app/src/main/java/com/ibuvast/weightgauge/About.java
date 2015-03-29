package com.ibuvast.weightgauge;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.ibuvast.Weight_Gauge.R;

/**
 * Created by calistus on 12/25/2014.
 */
public class About extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setContentView(R.layout.about);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffda7369")));

    }
}