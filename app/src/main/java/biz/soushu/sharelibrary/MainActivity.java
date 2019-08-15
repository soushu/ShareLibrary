package biz.soushu.sharelibrary;


import android.os.Bundle;

import biz.soushu.sharemenu_bar.ShareMenuActivity;

public class MainActivity extends ShareMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
