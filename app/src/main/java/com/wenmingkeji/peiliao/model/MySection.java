package com.wenmingkeji.peiliao.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by bevis on 2016/7/21.
 */
public class MySection extends SectionEntity<Skill> {

    public MySection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySection(Skill skill) {
        super(skill);
    }
}
