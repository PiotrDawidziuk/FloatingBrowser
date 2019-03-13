package pl.piotrdawidziuk.floatingbrowser;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    public void setUrlToWebView(String s){
        webView.loadUrl(s);
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

                builder = new AlertDialog.Builder(this);
                builder.setTitle("Do you want to exit?");
                builder.setMessage("Remember: the music will stop! If you want it to continue, choose FLOAT.");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                homeIntent.addCategory( Intent.CATEGORY_HOME );
                                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(homeIntent);

                                dialog.cancel();
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();

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
        setUrlToWebView("https://www.youtube.com/");
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
