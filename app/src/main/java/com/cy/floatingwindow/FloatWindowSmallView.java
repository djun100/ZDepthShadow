package com.cy.floatingwindow;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

import com.cy.System.UtilSysInfo;
import com.cy.testapp.R;

import java.lang.reflect.Field;

public class FloatWindowSmallView extends LinearLayout {

    /**
     * 记录小悬浮窗的宽度
     */
    public static int viewWidth;

    /**
     * 记录小悬浮窗的高度
     */
    public static int viewHeight;

    /**
     * 记录系统状态栏的高度
     */
    private static int statusBarHeight;

    /**
     * 用于更新小悬浮窗的位置
     */
    private WindowManager windowManager;

    /**
     * 小悬浮窗的参数
     */
    private WindowManager.LayoutParams mParams;

    /**
     * 记录当前手指位置在屏幕上的横坐标值
     */
    private float xInScreen;

    /**
     * 记录当前手指位置在屏幕上的纵坐标值
     */
    private float yInScreen;

    /**
     * 记录手指按下时在屏幕上的横坐标的值
     */
    private float xDownInScreen;

    /**
     * 记录手指按下时在屏幕上的纵坐标的值
     */
    private float yDownInScreen;

    /**
     * 记录手指按下时在小悬浮窗的View上的横坐标的值
     */
    private float xInView;

    /**
     * 记录手指按下时在小悬浮窗的View上的纵坐标的值
     */
    private float yInView;

    private ValueAnimator mMoveEdgeAnimator;
    private final View view;
    private int currentX;

    public FloatWindowSmallView(Context context) {
        super(context);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_small, this);
        view = findViewById(R.id.small_window_layout);
        viewWidth = view.getLayoutParams().width + UtilSysInfo.dip2px(context, 20);
        viewHeight = view.getLayoutParams().height + UtilSysInfo.dip2px(context, 20);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下时记录必要数据,纵坐标的值都需要减去状态栏高度
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                // 手指移动的时候更新小悬浮窗的位置
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                // 如果手指离开屏幕时，xDownInScreen和xInScreen相等，且yDownInScreen和yInScreen相等，则视为触发了单击事件。
                // 增加滑动距离判断，防止用户想单击的时候误滑动
                if (Math.abs(xDownInScreen - xInScreen) < 50 && Math.abs(yDownInScreen - yInScreen) < 50)
                    openBigWindow();
                moveToEdge();
                break;
            default:
                break;
        }
        return true;
    }

    // 手指离开屏幕时悬浮窗移动到屏幕边缘
    private void moveToEdge() {
        currentX = (int) (xInScreen - xInView);
        final int goalPositionX;
        final boolean isMoveRightEdge = xInScreen > (MyWindowManager.getScreenWidth()) / 2;
        goalPositionX = isMoveRightEdge ? MyWindowManager.getScreenWidth() - viewWidth : 0;
        moveTo(currentX, goalPositionX);
    }

    // 悬浮窗口移动动画
    private void moveTo(int currentX, int goalPositionX) {
        mMoveEdgeAnimator = ValueAnimator.ofInt(currentX, goalPositionX);
        mMoveEdgeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mParams.x = (Integer) animation.getAnimatedValue();
                try {
                    windowManager.updateViewLayout(FloatWindowSmallView.this, mParams);
                } catch (Exception e) {
                }
            }
        });
        mMoveEdgeAnimator.setDuration(500);
        mMoveEdgeAnimator.setInterpolator(new BounceInterpolator());
        mMoveEdgeAnimator.start();
    }

    /**
     * 将小悬浮窗的参数传入，用于更新小悬浮窗的位置。
     *
     * @param params 小悬浮窗的参数
     */
    public void setParams(WindowManager.LayoutParams params) {
        mParams = params;
    }

    /**
     * 更新小悬浮窗在屏幕中的位置。
     */
    private void updateViewPosition() {
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        windowManager.updateViewLayout(this, mParams);
    }

    /**
     * 打开大悬浮窗，同时关闭小悬浮窗。
     */
    private void openBigWindow() {
        MyWindowManager.createBigWindow(getContext());
        MyWindowManager.removeSmallWindow(getContext());
    }

    /**
     * 用于获取状态栏的高度。
     *
     * @return 返回状态栏高度的像素值。
     */
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

}
