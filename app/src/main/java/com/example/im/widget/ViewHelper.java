package com.example.im.widget;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class ViewHelper {
    private SparseArray<WeakReference<View>> mViews = new SparseArray<>();
    private Context mContext;
    private View mContentView;

    public ViewHelper(Context context, View contentView) {
        this.mContext = context;
        this.mContentView = contentView;
    }

    public <T extends View> T getView(@IdRes int id) {
        T view;
        WeakReference<View> weakView = mViews.get(id);
        if (weakView == null || weakView.get() == null) {
            view = mContentView.findViewById(id);
            mViews.put(id, new WeakReference<View>(view));
        } else {
            view = (T) weakView.get();
        }
        return view;
    }

    public void setClick(@IdRes int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
    }

    public void clearClick(@IdRes int id) {
        getView(id).setOnClickListener(null);
    }

    public void setText(@IdRes int id, String content) {
        TextView textView = getView(id);
        textView.setText(content);
    }

    public void clearText(@IdRes int id) {
        TextView textView = getView(id);
        textView.setText("");
    }

    public void onDestory() {
        mViews.clear();
        mViews = null;
        mContext = null;
        mContentView = null;
    }
}
