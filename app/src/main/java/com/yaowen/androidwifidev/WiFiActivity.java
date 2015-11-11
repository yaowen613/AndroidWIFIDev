package com.yaowen.androidwifidev;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yaowen.androidwifidev.com.yaowen.wifiutil.WiFiAdmin;

import java.util.List;

public class WiFiActivity extends AppCompatActivity {

    private Button scan, start, stop, check;
    private TextView allNetWork;
    private WiFiAdmin wiFiAdmin;
    // 扫描结果列表
    private List<ScanResult> list;
    private ScanResult mScanResult;
    private StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        wiFiAdmin = new WiFiAdmin(WiFiActivity.this);
        init();
    }

    private void init() {
        allNetWork = (TextView) findViewById(R.id.allNetWork);
        scan = (Button) findViewById(R.id.scan);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        check = (Button) findViewById(R.id.check);
        scan.setOnClickListener(new MyListener());
        start.setOnClickListener(new MyListener());
        stop.setOnClickListener(new MyListener());
        check.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.scan://扫描网络
                    getAllNetWorkList();
                    break;
                case R.id.start://打开Wifi
                    wiFiAdmin.openWifi();
                    Toast.makeText(WiFiActivity.this, "当前wifi状态为：" + wiFiAdmin.checkState()
                            , Toast.LENGTH_SHORT).show();
                    break;
                case R.id.stop://关闭Wifi
                    wiFiAdmin.closeWifi();
                    Toast.makeText(WiFiActivity.this, "当前wifi状态为：" + wiFiAdmin.checkState()
                            , Toast.LENGTH_SHORT).show();
                    break;
                case R.id.check://Wifi状态
                    Toast.makeText(WiFiActivity.this, "当前wifi状态为：" + wiFiAdmin.checkState()
                            , Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    public void getAllNetWorkList() {
        // 每次点击扫描之前清空上一次的扫描结果
        if (sb != null) {
            sb = new StringBuffer();
        }
        //开始扫描网络
        wiFiAdmin.startScan();
        list = wiFiAdmin.getWifiList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                //得到扫描结果
                mScanResult = list.get(i);
                sb = sb.append("扫描到的WiFi的ssid：" + mScanResult.SSID + "\n")
                        .append("该WiFi的Mac地址：" + mScanResult.BSSID + "\n")
                        .append("该WiFi的加密方式：" + mScanResult.capabilities + "\n")
                        .append("该WiFi的使用的频率：" + mScanResult.frequency + "\n")
                        .append("该WiFi的信号强度:" + mScanResult.level + "\n\n");
            }
            allNetWork.setText("扫描到的wifi网络：\n\n" + sb.toString());
        }
    }
}
