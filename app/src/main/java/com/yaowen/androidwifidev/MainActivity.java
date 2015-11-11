package com.yaowen.androidwifidev;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button start, stop, check;
    WifiManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.startButton);
        stop = (Button) findViewById(R.id.stopButton);
        check = (Button) findViewById(R.id.checkButton);
        manager = (WifiManager) getSystemService(WIFI_SERVICE);

        start.setOnClickListener(new MyListener());
        stop.setOnClickListener(new MyListener());
        check.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener {
        private void initView() {
            Log.d("TAG", "wifi state>>>>>>>>" + manager.getWifiState());
            Toast.makeText(MainActivity.this, "wifi state>>>>>>>>" +
                    manager.getWifiState(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onClick(View v) {
            if (v == start) {
                manager.setWifiEnabled(true);
                initView();
            }
            if (v == stop) {
                manager.setWifiEnabled(false);
                initView();
            }
            if (v == check) {
                initView();
            }
        }
    }
}
