package com.example.myloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MapActivity extends AppCompatActivity {
    private WebView web;

    //setting mapview as a webview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        web = (WebView) findViewById(R.id.mapId);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //web.setWebViewClient(new Callback());
        web.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });
        web.getSettings().setGeolocationDatabasePath( getApplicationContext().getFilesDir().getPath() );
        web.loadUrl("https://www.google.com/maps/@24.9106029,91.8475795,13z");
        web.getSettings().setAppCacheEnabled(true);
        web.getSettings().setDatabaseEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack())
        {
            web.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}