package com.wenmingkeji.peiliao.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by bevis on 2016/7/22.
 */
public class TimeManager extends SectionEntity<TimeItem> {
    public TimeManager(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public TimeManager(TimeItem timeItem) {
        super(timeItem);
    }
}
