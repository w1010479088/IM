package com.example.im;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.im.widget.ToastEx;
import com.example.im.widget.ViewHelper;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "IM_LOGGER";
    private final StringBuilder mBuilder = new StringBuilder();
    private ViewHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mHelper = new ViewHelper(this, getContentView());
        mHelper.setClick(R.id.paste, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastEx.show("点击有效!");
            }
        });
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
