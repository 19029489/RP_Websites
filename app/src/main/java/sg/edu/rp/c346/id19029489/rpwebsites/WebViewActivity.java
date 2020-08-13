package sg.edu.rp.c346.id19029489.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wvPage = findViewById(R.id.webView);

        Intent intentReceived = getIntent();

        String url = intentReceived.getStringExtra("url");

        wvPage.setWebViewClient(new WebViewClient());

        WebSettings wsMyPage = wvPage.getSettings();
        wsMyPage.setJavaScriptEnabled(true);
        wsMyPage.setBuiltInZoomControls(true);

        wvPage.loadUrl(url);


    }

}