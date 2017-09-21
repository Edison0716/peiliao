package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 10564 on 2016/7/11.
 */
public class ToggleSwitch extends View {
    Bitmap switchBackgroundBitmap;
    Bitmap switchSlideBitmap;
    Bitmap switchSlideBitmapOff;
    boolean mSwitchState = true;
    float x;
    boolean isTouchMode = false;
    private Paint paint;
    private OnSwitchStateUpdateListener onSwitchStateUpdateListener;

    /*
    * 用于代码创建控件
    *
    * */
    public ToggleSwitch(Context context) {
        super(context);
    }

    /*
    * 用于在xml里使用，可以指定自定义属性
    * 如果指定了自定义样式，走此方式
    * */
    public ToggleSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*
    *用于在xml里使用，可以指定自定义属性
    *
    * */
    public ToggleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(switchBackgroundBitmap.getWidth(), switchBackgroundBitmap.getHeight());
    }
    
    /*设置背景*/

    /*
    * canvas 画布，画板，在上边绘制的内容都会显示到界面上
    * */
    @Override
    protected void onDraw(Canvas canvas) {
        //1、绘制背景
        canvas.drawBitmap(switchBackgroundBitmap, 0, 0, paint);
        //2、绘制滑块
        //让滑块向左滑动自身一半的位置
        if (isTouchMode) {
            float newLeft = x - switchSlideBitmap.getWidth() / 2.0f;
            int maxLeft = switchBackgroundBitmap.getWidth() - switchSlideBitmap.getWidth();
            if (newLeft < 0) {
                newLeft = 0;//左边范围
            } else if (newLeft > maxLeft) {
                newLeft = maxLeft;//右边范围
            }
            canvas.drawBitmap(switchSlideBitmap, newLeft, 0, paint);
        } else {
            if (mSwitchState) {
                int newLeft1 = switchBackgroundBitmap.getWidth() - switchSlideBitmap.getWidth();
                canvas.drawBitmap(switchSlideBitmap, newLeft1, 0, paint);
            } else {
                canvas.drawBitmap(switchSlideBitmapOff, 0, 0, paint);
            }
        }
    }

    public void setSwitchBackgroundResource(int switchBackgroundResource) {
        switchBackgroundBitmap = BitmapFactory.decodeResource(getResources(), switchBackgroundResource);
    }

    /*设置滑块背景*/
    public void setSlideButtonResource(int slideButtonResource) {
        switchSlideBitmap = BitmapFactory.decodeResource(getResources(), slideButtonResource);
    }

    public void setSlideButtonResourceOff(int slideButtonResourceOff) {
        switchSlideBitmapOff = BitmapFactory.decodeResource(getResources(), slideButtonResourceOff);
    }

    /*设置开关状态*/
    public void setSwitchState(boolean mSwitchState) {
        this.mSwitchState = mSwitchState;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                isTouchMode = true;
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                isTouchMode = false;
                float center = switchBackgroundBitmap.getWidth() / 2.0f;
                //按下的位置与中心的位置比较
                boolean state = x > center;
                //如果开关状态变化了，通知界面，里边开关状态变化了
                if (state != mSwitchState && onSwitchStateUpdateListener != null) {
                    //把最新的Boolean传出
                    onSwitchStateUpdateListener.onStateUpdate(state);
                }
                mSwitchState = state;
                break;
        }
        invalidate();//引发onDraw 被调用里边的变量会重新调用，
        return true;
    }

    public void setOnSwitchStateUpdateListener(OnSwitchStateUpdateListener onSwitchStateUpdateListener) {
        this.onSwitchStateUpdateListener = onSwitchStateUpdateListener;
    }


    public interface OnSwitchStateUpdateListener {
        void onStateUpdate(boolean state);
    }
}
