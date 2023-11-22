package quyettvph35419.fpoly.cafebuipho.SerVice;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import quyettvph35419.fpoly.cafebuipho.R;


public class ConfigNotification extends Application {

    public static final String CHANNEL_ID = "QUYETDZ";

    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }

    private void config() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);

            Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            AudioAttributes attributes=new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
            channel.setSound(uri,attributes);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

}
