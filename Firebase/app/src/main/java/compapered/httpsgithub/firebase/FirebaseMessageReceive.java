package compapered.httpsgithub.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by dwg76 on 2017-09-01.
 */

public class FirebaseMessageReceive extends com.google.firebase.messaging.FirebaseMessagingService{

    public static final String TAG = "FirebaseeMessageReceive";
    public String msg;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Map<String ,String > data = remoteMessage.getData();
//        msg = remoteMessage.getNotification().getBody();
        msg = data.get("body");

        Intent notifiIntent = new Intent(this,MainActivity.class);
        notifiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contentIndent = PendingIntent.getActivity(this,0,
                new Intent(this,MainActivity.class),0);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("알림이에요")
                .setContentText(msg)
                .setAutoCancel(true)
                .setVibrate(new long[]{1,500});

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,nBuilder.build());

        nBuilder.setContentIntent(contentIndent);
    }

}
