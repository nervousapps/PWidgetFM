package nrv.pwidgetfm;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Widget_Control_Panl extends AppCompatActivity {

    WebView currentTrack;

    public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_widget__control__panl);

        currentTrack = (WebView)findViewById(R.id.currenttrack);

        currentTrack.getSettings().setJavaScriptEnabled(true);
        currentTrack.loadUrl("file:///android_asset/index.html");
    }
}