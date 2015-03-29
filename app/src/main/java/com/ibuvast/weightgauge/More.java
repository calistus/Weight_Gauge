package com.ibuvast.weightgauge;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ibuvast.Weight_Gauge.R;

/**
 * Created by calistus on 6/30/2014.
 */
public class More extends Activity {
    private WebView moreWebView;
    private TextView moreTitle;
    //public ProgressBar progressBar;
    //public static String title = "WELCOME TO HELP CENTER...";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffda7369")));
        moreWebView = (WebView) findViewById(R.id.moreWebView);
        //moreTitle = (TextView) findViewById(R.id.moreTitle);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);

        moreWebView.setWebViewClient(new WebViewClient());
        moreWebView.getSettings().setBuiltInZoomControls(true);
      //  moreTitle.setText(title);

        moreWebView.getSettings().setJavaScriptEnabled(true);
        moreWebView.loadUrl(Result.adviceLink);

    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        //    progressBar.setVisibility(View.GONE);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && moreWebView.canGoBack()) {
            moreWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
