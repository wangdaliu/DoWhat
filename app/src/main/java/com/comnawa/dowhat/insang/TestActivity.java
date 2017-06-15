package com.comnawa.dowhat.insang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.comnawa.dowhat.R;

public class TestActivity extends AppCompatActivity {

  Button btnLogin;
  Button btnSwitch;
  PrefManager prefManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_insang);

    btnLogin = (Button) findViewById(R.id.btnLogin);
    btnSwitch = (Button) findViewById(R.id.btnSwitch);
    prefManager = new PrefManager(TestActivity.this);

    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(TestActivity.this, prefManager.getAutoLogin() + "", Toast.LENGTH_SHORT).show();
      }
    });

    btnSwitch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        boolean result = false;
        if (prefManager.getAutoLogin()) {
          result = prefManager.setAutoLogin(false);
        } else {
          result = prefManager.setAutoLogin(true);
        }
        if (result) {
          Toast.makeText(TestActivity.this, "성공하였습니다.", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(TestActivity.this, "실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }
}