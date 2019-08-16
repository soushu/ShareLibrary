package biz.soushu.sharelibrary;


import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

import biz.soushu.sharemenu_bar.ShareMenuActivity;

public class MainActivity extends ShareMenuActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWebView(webview);

        setTitle("ShareLibrary");

        setAppName("SampleApp");

        setColor("white");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add("sample_menu1");
        menu.add("sample_menu2");
        menu.add("sample_menu3");

        return true;
    }

}
