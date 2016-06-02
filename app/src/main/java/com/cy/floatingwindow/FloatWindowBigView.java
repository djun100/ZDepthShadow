package com.cy.floatingwindow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cy.app.Log;
import com.cy.testapp.R;

public class FloatWindowBigView extends LinearLayout {
    private static Context mContext;
    /**
     * 记录大悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录大悬浮窗的高度
     */
    public static int viewHeight;

    private RelativeLayout mRlBigWindow, mRlBigCenter;

    public RelativeLayout getmRlBigWindow() {
        return mRlBigWindow;
    }

    public RelativeLayout getmRlBigCenter() {
        return mRlBigCenter;
    }

    public FloatWindowBigView(final Context context) {
        super(context);

        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.float_window_big, this);
        //eg how to find widget
        mRlBigWindow = (RelativeLayout) findViewById(R.id.float_big_rl);

        findViewById(R.id.mbtnTest).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"哈哈",Toast.LENGTH_LONG).show();
            }
        });
        viewWidth = mRlBigWindow.getLayoutParams().width;
        viewHeight = mRlBigWindow.getLayoutParams().height;


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            Log.w("onTouchEvent.ACTION_OUTSIDE");
            MyWindowManager.removeBigWindow(mContext);
            MyWindowManager.createSmallWindow(mContext);
        }
        return true;
    }


}
