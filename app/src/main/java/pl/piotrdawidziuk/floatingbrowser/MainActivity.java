package pl.piotrdawidziuk.floatingbrowser;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    String url = "";
    AlertDialog.Builder builder;
    AlertDialog dialog;
    public void setUrlToWebView(String s){
        url=s;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.settings:
                // setup the alert builder
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Settings");
                builder.setMessage("There are no settings to show, for now.");

                // add a button
                builder.setPositiveButton("OK", null);

                // create and show the alert dialog
                dialog = builder.create();
                dialog.show();
                return true;
            case R.id.help:
                // setup the alert builder
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Need help?");
                builder.setMessage("This application is not finished and I am still updating it! " +
                        "If you want to know more visit my Github at https://github.com/PiotrDawidziuk :)");

                // add a button
                builder.setPositiveButton("OK", null);

                // create and show the alert dialog
                dialog = builder.create();
                dialog.show();
                return true;
            case R.id.floatWindow:
                Toast.makeText(this, "Float window selected", Toast.LENGTH_SHORT).show();
                enterPictureInPictureMode();
                return true;
            case R.id.exit:
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            case R.id.vimeo:
                setUrlToWebView("https://vimeo.com/");
                return true;
            case R.id.youtube:
                setUrlToWebView("https://www.youtube.com/");
                return true;

            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        url = "https://www.youtube.com/";

        webView.loadUrl(url);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);


        if (isInPictureInPictureMode){
            //going to picture in picture
            getSupportActionBar().hide();
        }else{
            //going out from pip
            getSupportActionBar().show();
        }

    }
}
