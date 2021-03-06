package com.comnawa.dowhat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.comnawa.dowhat.insang.DBManager;
import com.comnawa.dowhat.insang.DoWhat;
import com.comnawa.dowhat.insang.Preferences;
import com.comnawa.dowhat.sangjin.CalendarActivity;
import com.comnawa.dowhat.sungwon.LoginActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    DoWhat.fixedScreen(this, DoWhat.sero); //세로화면 고정
    super.onCreate(savedInstanceState);
    ActionBar actionBar= getSupportActionBar();
    DoWhat.setTitleBar(this, "DoWhat 개발자 디버깅전용화면");
    setContentView(R.layout.activity_main);

    FirebaseApp.initializeApp(this);
    String token= FirebaseInstanceId.getInstance().getToken();
    Log.d("FCM_Token", token);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_pref, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId()== R.id.action_settings) {
      startActivity(new Intent(MainActivity.this, Preferences.class));
    }
    return false;
  }

  public void onClick(View v) {
    Intent intent = null;
    switch (v.getId()) {
      case R.id.btnInsang:
        Toast.makeText(this, "테스트중인거없습니다 ㅡ ㅡ", Toast.LENGTH_SHORT).show();
//        intent = add Intent(this, Preferences.class);
//        break;
//        add PrefManager(this).resetScheduleCount();
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = 6;
//        int date = cal.get(Calendar.DATE);
//        int hour = cal.get(Calendar.HOUR_OF_DAY);
//        int min = cal.get(Calendar.MINUTE);
//        DoWhat.setAlarm(this, year, month, date, hour, min, "");
//        return;
      case R.id.btnSangjin:
        intent = new Intent(this, CalendarActivity.class);
        break;
      case R.id.btnSungwon:
        intent = new Intent(this, LoginActivity.class);
        break;
      case R.id.btnInsang2:
        DBManager dbManager= new DBManager(this);
        dbManager.testDelete(1);
        dbManager.testDelete(2);

        return;
      case R.id.btninsang3:
        return;
    }
    startActivity(intent);
  } //onClick

}
