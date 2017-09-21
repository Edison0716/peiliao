package com.wenmingkeji.peiliao.adaper;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.model.HomeItem;
import com.wenmingkeji.peiliao.view.AdHolder;
import com.wenmingkeji.peiliao.view.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bevis on 2016/7/4.
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<HomeItem> {


    AdHolder adHolder;
    private Context mContext;
    private FragmentManager mManager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private RecommendShowAdapter mShowAdapter;
    private int selectedPosition = -1;
    private List<Fragment> mFragments;
    private MagicIndicator mMagicIndicator;


    public MultipleItemQuickAdapter(Context context, List<HomeItem> data, FragmentManager manager) {
        super(data);
        this.mContext = context;
        this.mManager = manager;
        addItemType(HomeItem.SERVICE_ITEM, R.layout.item_list_service);
//        addItemType(HomeItem.CATEGORY, R.layout.view_category_pager);
        addItemType(HomeItem.RECOMMEND, R.layout.view_category_recommand);
        addItemType(HomeItem.AD, R.layout.view_home_ad);
        addItemType(HomeItem.TAG, R.layout.item_home_tag);

    }

    /**
     * public ServiceItem
     * (int imgId, String userName, String skills,
     * String serviceIntro, String price)
     */

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final HomeItem homeItem) {
//        int i = baseViewHolder.getLayoutPosition();
        switch (baseViewHolder.getItemViewType()) {
            case HomeItem.SERVICE_ITEM:

                baseViewHolder.setImageResource(R.id.icon_head, homeItem.getServiceItem().getImgId())
                        .setText(R.id.tv_user_skill, homeItem.getServiceItem().getSkills())
                        .setText(R.id.tv_service_details, homeItem.getServiceItem().getServiceIntroduce())
                        .setText(R.id.tv_service_price, homeItem.getServiceItem().getPrice());

                baseViewHolder.setOnClickListener(R.id.icon_head, new OnItemChildClickListener())
                        .setOnClickListener(R.id.iv_follow, new OnItemChildClickListener());


                if (selectedPosition == baseViewHolder.getLayoutPosition()) {
                    baseViewHolder.setImageResource(R.id.icon_head, R.drawable.icon_common_doutu);
                }
                break;

            /**
             * 加载分类viewPager
             * 当界面中滑动至隐藏是，再次显示的时候回重新调用convert方法去寻找case
             * 所以，如果不清中mFragments中的数据的话，初始化操作会重新执行一遍
             * 此时mFragments相当于赋值了两遍，会造成viewPager重复显示的问题
             * 由于删除mFragments中的数据太麻烦，还需要动态更新
             * 所以此处采取每次都初始化mFragments的方法
             * 保证ViewPager不会重复加载
             */

            case HomeItem.CATEGORY:

                /*mFragments = new ArrayList<Fragment>();


                initView(homeItem);

                mSectionsPagerAdapter = new SectionsPagerAdapter(mManager);
                ViewPager viewPager = (ViewPager) baseViewHolder.getConvertView().findViewById(R.id.viewpager_category);
                viewPager.setAdapter(mSectionsPagerAdapter);*/

                break;
            case HomeItem.HEADER_BANNER:

                break;
            case HomeItem.TAG:

                Logger.d("HomeItem.TAG");
                mMagicIndicator = (MagicIndicator) baseViewHolder.getConvertView().findViewById(R.id.magic_indicator);
                // 贝塞尔曲线
                final CommonNavigator commonNavigator = new CommonNavigator(mContext);
                commonNavigator.setEnablePivotScroll(true);
                commonNavigator.setAdapter(new CommonNavigatorAdapter() {
                    @Override
                    public int getCount() {
                        return homeItem.getTag() == null ? 0 : homeItem.getTag().length;
                    }

                    @Override
                    public IPagerTitleView getTitleView(Context context, final int index) {
                        ScaleTransitionPagerTitleView colorTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
                        colorTransitionPagerTitleView.setText(homeItem.getTag()[index]);
                        colorTransitionPagerTitleView.setTextSize(18);
                        colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                        colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                        colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                mMagicIndicator.onPageSelected(index);
                            }
                        });
                        return colorTransitionPagerTitleView;
                    }

                    @Override
                    public IPagerIndicator getIndicator(Context context) {
                        BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                        List<String> colorList = new ArrayList<String>();
                        colorList.add("#ff4a42");
                        colorList.add("#fcde64");
                        colorList.add("#73e8f4");
                        colorList.add("#76b0ff");
                        colorList.add("#c683fe");
                        indicator.setColorList(colorList);
                        return indicator;
                    }
                });
                mMagicIndicator.setNavigator(commonNavigator);


                break;

            case HomeItem.AD:

                /*adHolder = new AdHolder(mContext, baseViewHolder.getConvertView());
                adHolder.setViewPager(homeItem);*/
                break;

            case HomeItem.RECOMMEND:
                mShowAdapter = new RecommendShowAdapter(R.layout.item_category_recommend, homeItem.getNameAndIconList());
                RecyclerView recyclerView = (RecyclerView) baseViewHolder.getConvertView().findViewById(R.id.rv_recommend);
                LinearLayoutManager manager = new LinearLayoutManager(mContext);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(mShowAdapter);

                break;

        }

    }


}
    
