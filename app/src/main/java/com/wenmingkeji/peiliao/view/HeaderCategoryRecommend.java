package com.wenmingkeji.peiliao.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.adaper.RecommendShowAdapter;
import com.wenmingkeji.peiliao.model.NameAndIcon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/26.
 */
public class HeaderCategoryRecommend extends HeaderInterface {


    @BindView(R.id.rv_recommend)
    RecyclerView mRvRecommend;

    private RecommendShowAdapter mShowAdapter;
    private List<NameAndIcon> mNameAndIconList;
    private Context mContext;
    private LinearLayoutManager mLayoutManager;

    public HeaderCategoryRecommend(Context context, List<NameAndIcon> nameAndIconList) {
        super(context);
        this.mNameAndIconList = nameAndIconList;
        this.mContext = context;
    }

    @Override
    protected void getView(ListView listView) {
        View view = mInflate.inflate(R.layout.view_category_recommand, null);
        ButterKnife.bind(this, view);

        setCategoryRecommend();
        listView.addHeaderView(view);
    }

    private void setCategoryRecommend() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvRecommend.setLayoutManager(mLayoutManager);
        mShowAdapter = new RecommendShowAdapter(R.layout.item_category_recommend, mNameAndIconList);
        mRvRecommend.setAdapter(mShowAdapter);
    }
}
