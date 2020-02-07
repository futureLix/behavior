package com.behavior.bottombehaviormaster.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.behavior.bottombehaviormaster.R;
import com.behavior.bottombehaviormaster.dialog.BaseBottomSheetDialog;
import com.behavior.bottombehaviormaster.dialog.BottomSheetDialog;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarUtils.setStatusBarVisibility(this, false);
        setContentView(R.layout.activity_test);
        videoView = findViewById(R.id.videoView);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.test;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();
        findViewById(R.id.floatBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View[] views = new View[1];
                BottomSheetDialog mBottomCommentSheetDialog = new BottomSheetDialog();
                mBottomCommentSheetDialog.setFragmentManager(getSupportFragmentManager())
                        .setLayoutRes(R.layout.view_dialog_comment)
                        .setCancelOutside(true)
                        .setViewListener(v1 -> {
                            views[0] = findCommentView(v1);
                        })
                        .show();
                mBottomCommentSheetDialog.setBehaviorChanged(new BaseBottomSheetDialog.IBehaviorChanged() {
                    @Override
                    public void changedState(View bottomSheet, int state) {
                        float width = ScreenUtils.getScreenWidth();
                        float height = ScreenUtils.getScreenHeight();
                        if (state == BottomSheetBehavior.STATE_EXPANDED) {
                            float x = width / 2f;
                            views[0].post(() -> {
                                float scale = height - (views[0].getHeight());
                                videoView.setScaleX(scale / height);
                                videoView.setScaleY(scale / height);
                                videoView.setPivotX(x);
                                videoView.setPivotY(0);
                            });
                        } else if (state == BottomSheetBehavior.STATE_COLLAPSED) {
                            videoView.setScaleX(1.0f);
                            videoView.setScaleY(1.0f);
                            videoView.setPivotX(0);
                            videoView.setPivotY(0);
                        }
                    }

                    @Override
                    public void changedOffset(View bottomSheet, float slideOffset) {
                        startAnimator(bottomSheet);
                    }
                });
            }
        });
    }

    /**
     * @param parent
     */
    private void startAnimator(View parent) {
        float width = ScreenUtils.getScreenWidth();
        float height = ScreenUtils.getScreenHeight();
        float x = width / 2f;
        float py = parent.getY() / height;
        LogUtils.e(parent.getY());
        LogUtils.e(parent.getY() + BarUtils.getStatusBarHeight());
        videoView.setScaleX(py);
        videoView.setScaleY(py);
        videoView.setPivotX(x);
        videoView.setPivotY(0);
    }

    /**
     * 评论
     * 绑定View
     *
     * @param contentView
     */
    private View findCommentView(View contentView) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("i" + i);
        }
        RelativeLayout mCommentParent = contentView.findViewById(R.id.dialog_comments_layout);
        mCommentParent.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (ScreenUtils.getScreenHeight() / 1.5)));
        RecyclerView mCommentRecycle = contentView.findViewById(R.id.dialog_comment_recycle);
        RelativeLayout mCloseCommentBtn = contentView.findViewById(R.id.dialog_close_comment);
        TextView mCommentNum = contentView.findViewById(R.id.dialog_comments_num);
        RelativeLayout mSendCommentLayout = contentView.findViewById(R.id.dialog_send_comment);
        RelativeLayout mCommentNull = contentView.findViewById(R.id.dialog_comment_null);
        mCommentNum.setText(list.size() + "条评论");
        mCommentRecycle.setLayoutManager(new LinearLayoutManager(this));
        mCommentRecycle.setAdapter(new BaseQuickAdapter(R.layout.view_comment_item, list) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }
        });

        mCloseCommentBtn.setOnClickListener(v -> {
        });

        mSendCommentLayout.setOnClickListener(v -> {

        });
        return mCommentParent;
    }

}
