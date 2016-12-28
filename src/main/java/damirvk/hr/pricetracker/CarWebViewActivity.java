package damirvk.hr.pricetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class CarWebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_web_view);

        String url = this.getIntent().getExtras().getString("url");

        //setTitle(title);

// 3
        mWebView = (WebView) findViewById(R.id.detail_web_view);

// 4
        mWebView.loadUrl(url);

    }
}
