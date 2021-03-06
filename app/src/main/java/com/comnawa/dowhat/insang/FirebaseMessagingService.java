package com.comnawa.dowhat.insang;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

  private static final String TAG = "FirebaseMsgService";
  private Intent intent;
  private  PendingIntent sender;
  @Override
  public void onMessageReceived(RemoteMessage remoteMessage) {

    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    intent = new Intent(this, PushBroadcast.class);
//    intent.putExtra("remoteMessage", remoteMessage.getNotification().getBody());
    intent.putExtra("remoteMessage", remoteMessage.getData().get("body"));
    Log.i("mintest1", remoteMessage.getData().get("title"));
//    intent.putExtra("tag", remoteMessage.getNotification().getTag());
    intent.putExtra("tag", remoteMessage.getData().get("tag"));
    intent.putExtra("title", remoteMessage.getData().get("title"));

   sender = PendingIntent.getBroadcast(this, 1000, intent, 0);

    Calendar cal = Calendar.getInstance();
    cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE),
      cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), 0);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      am.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
    } else {
      am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), sender);
    }

  }
}