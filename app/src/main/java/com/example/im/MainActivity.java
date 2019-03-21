package com.example.im;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.im.service.ImCenter;
import com.example.im.service.MessageSending;
import com.example.im.widget.ViewHelper;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "IM_LOGGER";
    private final String IP = "wss://50.lrlz.com:8080";
    private final StringBuilder mBuilder = new StringBuilder();
    private ViewHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ImCenter mIm = new ImCenter(IP);
        mIm.setLogListener(this::log);
        mHelper = new ViewHelper(this, getContentView());
        mHelper.setClick(R.id.disconnect, v -> mIm.disconnect());
        mHelper.setClick(R.id.connect, v -> mIm.connect());
        mHelper.setClick(R.id.clear, v -> clear());
        mHelper.setClick(R.id.send, v -> mIm.send(new MessageSending("测试一下!")));
        log("大俊子来了!");
    }

    private View getContentView() {
        return this.getWindow().getDecorView().findViewById(android.R.id.content);
    }

    private void log(String content) {
        mBuilder.append(content);
        mHelper.setText(R.id.log_content, mBuilder.toString());
    }

    private void clear() {
        mBuilder.delete(0, mBuilder.length());
        log("已清除完毕!");
    }
}
