package com.oversea.ab_firstarea.haiwai;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oversea.ab_firstarea.channel.PTypeUrl;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes.dex */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            if (remoteMessage.getNotification() != null) {
                sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
        super.onNewToken(str);
        LLog.i_Control("onNewToken s=" + str);
        AppsFlyerControl.getInstance().updateServerUninstallToken(str);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    private void sendNotification(String str, String str2) {
        try {
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this, PTypeUrl.TYPENAME).setTicker(str).setSmallIcon(getAppIcon(this)).setContentTitle(str).setContentIntent(PendingIntent.getBroadcast(this, 0, new Intent(), 67108864)).setContentText(str2).setAutoCancel(true);
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel(PTypeUrl.TYPENAME, "ff_notification", 3));
            }
            notificationManager.notify(1123, autoCancel.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getAppIcon(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.icon;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
