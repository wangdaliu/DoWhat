package com.comnawa.dowhat.insang;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class AlarmService extends Service {
  boolean isRunning;

  @Override
  public void onCreate() {
    super.onCreate();
    Toast.makeText(this, "서비스 시작", Toast.LENGTH_SHORT).show();

    /*
      DateChangedReceiver로 서비스가 재생성됨.
      당일에 해당되는 일정자료 reload 코드작성해야함
      preference 에 aCountSchedule 리셋 후 reload진행 :: new PrefManager(this).resetScheduleCount();
     */

  } //서비스 생성될시 (최초 1회)

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);

    isRunning = true;
    Thread th = new Thread(new MyThread());
    th.start();

    return Service.START_REDELIVER_INTENT;
  } //서비스 시작

  @Override
  public void onDestroy() {
    super.onDestroy();
    isRunning = false;
    Toast.makeText(this, "서비스 종료", Toast.LENGTH_SHORT).show();
  } // 서비스가 종료될때

  class MyThread extends Thread {
    @Override
    public void run() {
      while (isRunning) {
        handler.sendEmptyMessage(0); //서비스 실행중 이라는 Toast메세지 출력 반복
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      Toast.makeText(AlarmService.this, "서비스 실행중", Toast.LENGTH_SHORT).show();
    }
  };


  public AlarmService() {

  }

  @Override
  public IBinder onBind(Intent intent) {
    // TODO: Return the communication channel to the service.
    throw new UnsupportedOperationException("Not yet implemented");
  }

}