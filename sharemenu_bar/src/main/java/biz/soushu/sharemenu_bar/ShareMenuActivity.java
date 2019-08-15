package biz.soushu.sharemenu_bar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ShareMenuActivity extends AppCompatActivity {

    protected String shareUrl;
    protected WebView webView;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == R.id.action_line) {
            this.shareAction(1);
        } else if (i == R.id.action_twitter) {
            this.shareAction(2);
        } else if (i == R.id.action_facebook) {
            this.shareAction(3);
        } else if (i == R.id.action_other) {
            this.shareActionOther();
        } else if (i == R.id.action_browser) {
            this.shareActionBrowser();
        } else if (i == R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * メニューボタン生成
     */
    @Override
    @SuppressLint("RestrictedApi")
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_mini, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }

        return true;
    }

    /**
     * @param div 1:LINE 2:Twitter 3:Facebook
     */
    private void shareAction(int div) {

        String pakageName = getPakageName(div);
        String msg = getMsg(div);

        // Check installed App
        if (!appInstalled(getApplicationContext(), pakageName)) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            if (div == 1) {
                shareActionLine();
            } else {
                shareActionTwitterFacebook(pakageName);
            }
        }
    }

    private void shareActionTwitterFacebook(String pakageName) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, makeShareText());
        intent.setPackage(pakageName);
        startActivity(intent);
    }

    private void shareActionLine() {
        // URL Scheme
        String urlScheme = getString(R.string.scheme_line);

        // メッセージをURLエンコード
        String encodedMsg = null;
        try {
            encodedMsg = URLEncoder.encode(makeShareText(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (encodedMsg != null) {
            Uri uri = Uri.parse(urlScheme + "/" + encodedMsg);
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(i);
        }
    }

    private String getPakageName(int div) {
        String pakageName = "";
        if (div == 1) {
            pakageName = getString(R.string.line_pakage_name);
        } else if (div == 2) {
            pakageName = getString(R.string.twitter_pakage_name);

        } else if (div == 3) {
            pakageName = getString(R.string.facebook_pakage_name);
        }
        return pakageName;
    }

    private String getMsg(int div) {
        String msg = "";
        if (div == 1) {
            msg = getString(R.string.chk_line_message);
        } else if (div == 2) {
            msg = getString(R.string.chk_twitter_message);
        } else if (div == 3) {
            msg = getString(R.string.chk_facebook_message);
        }
        return msg;
    }

    private void shareActionBrowser() {
        String currentUrl = webView.getUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentUrl));
        startActivity(intent);
    }

    private String makeShareText() {
        // メッセージ
        List list = new ArrayList();
        list.add(this.shareUrl);
        list.add(" ");
        list.add(getString(R.string.app_name) + getString(R.string.colon_zenkaku) + getString(R.string.menu_share_message));
        list.add(getString(R.string.google_play) + getPackageName());

        return join(list, "\n");
    }

    private void shareActionOther() {
        startActivity(getDefaultIntent());
    }

    public static boolean appInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.setType("image/*");
        createShareText(intent);

        return intent;
    }

    private void createShareText(Intent intent) {
        List list = new ArrayList();
        list.add(this.shareUrl);
        list.add(" ");
        list.add(getString(R.string.app_name) + getString(R.string.colon_zenkaku) + getString(R.string.menu_share_message));
        list.add(getString(R.string.google_play) + getPackageName());
        intent.putExtra(Intent.EXTRA_TEXT, join(list, "\n"));
    }

    public static String join(List<String> list, String delimiter) {
        StringBuilder ret = new StringBuilder();
        for (String str : list) {
            ret.append(str);
            ret.append(delimiter);
        }
        return ret.toString();
    }

}