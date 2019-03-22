package com.example.im.widget;

import android.widget.Toast;

import com.example.im.AppApplication;

public class ToastEx {
    public static void show(String content) {
        Toast.makeText(AppApplication.Companion.getInstance(), content, Toast.LENGTH_SHORT).show();
    }
}
