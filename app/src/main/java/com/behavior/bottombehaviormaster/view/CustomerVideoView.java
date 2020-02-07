package com.behavior.bottombehaviormaster.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @author Lix
 * @date 2020-02-01 21:00
 */
public class CustomerVideoView extends VideoView {

    public CustomerVideoView(Context context) {
        super(context);
    }

    public CustomerVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
