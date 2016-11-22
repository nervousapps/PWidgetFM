package nrv.pwidgetfm;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class PWidgetFM extends AppWidgetProvider {

    public static MediaPlayer mediaPlayer;

    String radio = "http://195.154.185.139/radio/3616/stream/11021";

    Uri uri = Uri.parse(radio);

    private static final String SYNC_CLICKED    = "automaticWidgetSyncButtonClick";

    Context contextPlay;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews;
        ComponentName watchWidget;

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.pwidget_fm);
        watchWidget = new ComponentName(context, PWidgetFM.class);

        remoteViews.setOnClickPendingIntent(R.id.widget_button_play, getPendingSelfIntent(context, SYNC_CLICKED));
        appWidgetManager.updateAppWidget(watchWidget , remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        contextPlay = context;

        final String action = intent.getAction();

        if (SYNC_CLICKED.equals(action)) {

            if (mediaPlayer == null) {
                radioInit(context);
            }

            radioPlayPause(context);

        }

        super.onReceive(context, intent);
    }


    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


    public void radioInit(Context context){

        mediaPlayer = MediaPlayer.create(context, uri);

    }

    public void radioPlayPause(Context context){

        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.pwidget_fm);

        if (mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            remoteView.setImageViewResource(R.id.widget_button_play, R.drawable.play);
            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, PWidgetFM.class),remoteView);
            Toast.makeText(context, "PWFM is off", Toast.LENGTH_LONG).show();
        }
        else {
            mediaPlayer.start();
            remoteView.setImageViewResource(R.id.widget_button_play, R.drawable.pause);
            AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, PWidgetFM.class),remoteView);
            Toast.makeText(context, "Let the techno out !!!", Toast.LENGTH_LONG).show();
        }
    }

    public static void radioPause(){

        if (mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}




