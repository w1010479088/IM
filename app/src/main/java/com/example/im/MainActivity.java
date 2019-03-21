package com.example.im;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "IM_LOGGER";
    private SparseArray<WeakReference<View>> mViews = new SparseArray<>();
    StringBuilder mBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log("大俊子来了!");
    }

    private void log(String content) {
        mBuilder.append(content);
        setText(R.id.content, mBuilder.toString());
    }

    private void clear() {
        mBuilder.delete(0, mBuilder.length());
        log("已清除完毕!");
    }

    private <T extends View> T getView(@IdRes int id) {
        T view = null;
        WeakReference<View> weakView = mViews.get(id);
        if (weakView == null || weakView.get() == null) {
            view = getWindow().findViewById(id);
            mViews.put(id, new WeakReference<View>(view));
        } else {
            mViews.get(id);
        }
        return view;
    }

    private void setText(@IdRes int id, String content) {
        TextView textView = getView(id);
        textView.setText(content);
    }
}
