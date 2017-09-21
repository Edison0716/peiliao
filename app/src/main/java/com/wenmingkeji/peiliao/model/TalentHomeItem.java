package com.wenmingkeji.peiliao.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by bevis on 2016/7/15.
 */
public class TalentHomeItem extends MultiItemEntity {

    public static final int INDICATOR = 1;
    public static final int CONTENT = 2;

    private IndicatorText mIndicatorText;
    private TalentHomeContent mTalentHomeContent;

    public IndicatorText getIndicatorText() {
        return mIndicatorText;
    }

    public void setIndicatorText(IndicatorText indicatorText) {
        mIndicatorText = indicatorText;
    }


    public TalentHomeContent getTalentHomeContent() {
        return mTalentHomeContent;
    }

    public void setTalentHomeContent(TalentHomeContent talentHomeContent) {
        mTalentHomeContent = talentHomeContent;
    }
}
