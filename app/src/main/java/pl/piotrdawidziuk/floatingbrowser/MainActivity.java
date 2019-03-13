package pl.piotrdawidziuk.floatingbrowser;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public void pictureInPicture(View view){
        enterPictureInPictureMode();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);

        if (isInPictureInPictureMode){
            //going to picture in picture
            button.setVisibility(View.INVISIBLE);
            getSupportActionBar().hide();
            textView.setText("3,45$");
        }else{
            //going out from pip
            button.setVisibility(View.VISIBLE);
            getSupportActionBar().show();
            textView.setText("The price is: 3,45$");
        }

    }
}
