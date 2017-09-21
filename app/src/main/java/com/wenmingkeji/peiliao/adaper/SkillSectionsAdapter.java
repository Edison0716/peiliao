package com.wenmingkeji.peiliao.adaper;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.MySection;
import com.wenmingkeji.peiliao.model.Skill;

import java.util.List;

/**
 * Created by bevis on 2016/7/21.
 */
public class SkillSectionsAdapter extends BaseSectionQuickAdapter<MySection> {
    private static boolean SKILL_LIST_ITEM_SELECTED = false;
    private int selectedPosition = -1;

    public SkillSectionsAdapter(int layoutResId, int sectionHeadResId, List<MySection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder baseViewHolder, MySection mySection) {
        baseViewHolder.setText(R.id.tv_skill_type, mySection.header);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MySection mySection) {
        Skill skill = mySection.t;
        baseViewHolder.setText(R.id.tv_skill_list, skill.getSkillName())
                .setOnClickListener(R.id.tv_skill_list, new OnItemChildClickListener());

        
        /*if (selectedPosition == baseViewHolder.getLayoutPosition()) {
            if (SKILL_LIST_ITEM_SELECTED) {
                SKILL_LIST_ITEM_SELECTED = false;
                baseViewHolder.getView(R.id.tv_skill_list).setSelected(false);
            } else {
                SKILL_LIST_ITEM_SELECTED = true;
                baseViewHolder.getView(R.id.tv_skill_list).setSelected(true);
            }
        }*/

    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
