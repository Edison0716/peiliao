package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by bevis on 2016/7/13.
 */
public class ChatLayout extends LinearLayout {

    int mOld, mNew;
    LayoutListener mLayoutListener;

    public ChatLayout(Context context) {
        this(context, null);
    }

    public ChatLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mOld = mNew;
        mNew = b - t;
        if (mNew - mOld > 0) {
            if (mLayoutListener != null) {
                mLayoutListener.onKeyboardClose();
            }
        } else if (mNew - mOld < 0) {
            if (mLayoutListener != null) {
                mLayoutListener.onKeyboardPop();
            }
        }


    }

    public LayoutListener getLayoutListener() {
        return mLayoutListener;
    }

    public void setLayoutListener(LayoutListener layoutListener) {
        mLayoutListener = layoutListener;
    }

    public interface LayoutListener {
        public void onKeyboardPop();

        public void onKeyboardClose();
    }
}
