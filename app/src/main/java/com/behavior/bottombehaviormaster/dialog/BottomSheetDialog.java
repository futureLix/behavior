package com.behavior.bottombehaviormaster.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.view.View;

public class BottomSheetDialog extends BaseBottomSheetDialog {

    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private static final String KEY_CANCEL_OUTSIDE = "bottom_cancel_outside";

    private FragmentManager mFragmentManager;

    private boolean mIsCancelOutside = super.getCancelOutside();

    private String mTag = super.getFragmentTag();


    @LayoutRes
    private int mLayoutRes;

    private ViewListener mViewListener;

    public static BottomSheetDialog create(FragmentManager manager) {
        BottomSheetDialog dialog = new BottomSheetDialog();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindView(View v) {
        if (mViewListener != null) {
            mViewListener.bindView(v);
        }
    }

    @Override
    public int getLayoutRes() {
        return mLayoutRes;
    }

    public BottomSheetDialog setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public BottomSheetDialog setViewListener(ViewListener listener) {
        mViewListener = listener;
        return this;
    }

    public BottomSheetDialog setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public BottomSheetDialog setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public BottomSheetDialog setTag(String tag) {
        mTag = tag;
        return this;
    }

    @Override
    public boolean getCancelOutside() {
        return mIsCancelOutside;
    }

    @Override
    public String getFragmentTag() {
        return mTag;
    }

    public interface ViewListener {
        void bindView(View v);
    }

    public BaseBottomSheetDialog show() {
        show(mFragmentManager);
        return this;
    }
}