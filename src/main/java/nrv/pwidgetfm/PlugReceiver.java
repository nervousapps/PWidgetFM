package nrv.pwidgetfm;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by achillepenet on 31/05/16.
*/

public class PlugReceiver extends ContentProvider {

    @Override
    public boolean onCreate() {

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                PWidgetFM.radioPause();
                //Log.d("Mesage","Unplug");

                RemoteViews remoteView = new RemoteViews(getContext().getPackageName(), R.layout.pwidget_fm);

                remoteView.setImageViewResource(R.id.widget_button_play, R.drawable.play);
                AppWidgetManager.getInstance(getContext()).updateAppWidget(new ComponentName(getContext(), PWidgetFM.class),remoteView);
                Toast.makeText(getContext(), "PWFM is off", Toast.LENGTH_LONG).show();
            }
        };
        IntentFilter filter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        getContext().registerReceiver(receiver, filter);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
