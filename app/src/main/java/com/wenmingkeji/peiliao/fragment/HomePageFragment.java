package com.wenmingkeji.peiliao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.ServiceDetailsActivity;
import com.wenmingkeji.peiliao.adaper.ServiceItemAdapter;
import com.wenmingkeji.peiliao.manager.MyLinearLayoutManager;
import com.wenmingkeji.peiliao.model.Ad;
import com.wenmingkeji.peiliao.model.AdHome;
import com.wenmingkeji.peiliao.model.HomeHeaderItem;
import com.wenmingkeji.peiliao.model.HomeItem;
import com.wenmingkeji.peiliao.model.NameAndIcon;
import com.wenmingkeji.peiliao.model.NameWithIcon;
import com.wenmingkeji.peiliao.model.ServiceItem;
import com.wenmingkeji.peiliao.view.AdHolder;
import com.wenmingkeji.peiliao.view.CircleImageView;
import com.wenmingkeji.peiliao.view.HeaderAdBanner;
import com.wenmingkeji.peiliao.view.HeaderTagView;
import com.wenmingkeji.peiliao.view.HeaderViewPager;
import com.wenmingkeji.peiliao.view.SmoothListView.SmoothListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/7/13.
 */
public class HomePageFragment extends Fragment {

    public static final String URL_HOME_BANNER = "http://192.168.1.199/get_cate/get_home_banner.json";
    public static final String URL_SERVICE_CONTENT = "http://192.168.1.199/get_cate/get_home_banner.json";
    @BindView(R.id.icon_head)
    CircleImageView mIconHead;
    @BindView(R.id.iv_bg_search)
    ImageView mIvBgSearch;
    @BindView(R.id.tv_title)
    TextView mTvSellerName;
    @BindView(R.id.img_message)
    ImageButton mImgMessage;
    @BindView(R.id.ll_home_tag)
    LinearLayout mLlHomeTag;
    @BindView(R.id.listView)
    SmoothListView mListView;
    @BindView(R.id.tv_recommend)
    TextView mTvRecommend;
    @BindView(R.id.tv_latest)
    TextView mTvLatest;
    @BindView(R.id.tv_today)
    TextView mTvToday;
    @BindView(R.id.tv_hot)
    TextView mTvHot;
    int[] icons = {
            R.drawable.icon_common_food, R.drawable.icon_common_play,
            R.drawable.icon_common_draw, R.drawable.icon_common_life_consult,
            R.drawable.icon_common_cv, R.drawable.icon_common_lol,
            R.drawable.icon_common_sign, R.drawable.icon_common_pedict,
            R.drawable.icon_common_emotion_consult, R.drawable.icon_common_doutu

    };
    String[] names = {
            "美食", "陪聊陪玩", "手绘", "生活咨询", "声优", "LOL",
            "签名设计", "占卜", "情感咨询", "斗图"
    };
    MyLinearLayoutManager mMyLinearLayoutManager;
    private List<HomeItem> homeItemList = new ArrayList<>();
    //!!! FragmentManager 不能直接初始化！！！！！！！！
    private FragmentManager mManager;
    private HomeHeaderItem mHomeHeaderItem;
    private ServiceItemAdapter mAdapter;// 主页数据
    private List<ServiceItem> mServiceItems = new ArrayList<ServiceItem>(); // ListView数据
    /**
     * 首页轮播广告（顶部）
     */
    private HeaderAdBanner mHeaderAdBanner;
    /**
     * 首页轮播广告，不在顶部，可以在首页任何位置
     */
    private AdHolder mAdHolder;
    /**
     * 首页分类里列表，二级菜单入口
     */
    private HeaderViewPager mHeaderViewPager;
    /**
     * 首页tag：推荐，最新，今日，热销 四个标签
     */
    private HeaderTagView mHeaderTagView;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.bind(this, view);

        initData();
        initView();

        return view;

    }


    public void initView() {


        //添加顶部轮播header
        mHeaderAdBanner = new HeaderAdBanner(getActivity(), mHomeHeaderItem);
        mHeaderAdBanner.fillView(mListView);


        //添加首页分类
        mHeaderViewPager = new HeaderViewPager(getActivity(), mHomeHeaderItem, getChildFragmentManager());
        mHeaderViewPager.fillView(mListView);


        //添加轮播header
        mAdHolder = new AdHolder(getActivity(), mHomeHeaderItem);
        mAdHolder.fillView(mListView);


        //添加tag
        mHeaderTagView = new HeaderTagView(getActivity());
        mHeaderTagView.fillView(mListView);


        // 设置ListView数据
        mAdapter = new ServiceItemAdapter(getActivity(), R.layout.item_list_service, mServiceItems);

        mListView.setAdapter(mAdapter);


        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem > 3) {
                    mLlHomeTag.setVisibility(View.VISIBLE);
                } else {
                    mLlHomeTag.setVisibility(View.GONE);
                }
            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), ServiceDetailsActivity.class));
            }
        });
        
    }


    void initData() {
        mHomeHeaderItem = new HomeHeaderItem();
        int[] imgIds = {R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4};
        AdHome adHome = new AdHome();
        Ad ad = new Ad();
        adHome.setImgIds(imgIds);
        ad.setImgIds(imgIds);


        List<NameWithIcon> nameWithIcons = new ArrayList<NameWithIcon>();
        NameWithIcon nameWithIcon = new NameWithIcon(names, icons);
        NameWithIcon nameWithIcon1 = new NameWithIcon(names, icons);

        nameWithIcons.add(nameWithIcon);
        nameWithIcons.add(nameWithIcon1);

        
        //设置分类列表
        mHomeHeaderItem.setNameWithIcons(nameWithIcons);

        //设置顶部轮播
        mHomeHeaderItem.setAdHome(adHome);

        //设置轮播
        mHomeHeaderItem.setAd(ad);

        // ListView数据
        for (int i = 0; i < 10; i++) {
            ServiceItem serviceItem = new ServiceItem();
            mServiceItems.add(serviceItem);
        }
    }


    //初始化首页服务列表
    public void initServiceItem() {

        for (int i = 0; i < 10; i++) {
            HomeItem homeItem = new HomeItem();
            ServiceItem serviceItem = new ServiceItem();
            serviceItem.setImgId(R.drawable.icon_common_food);
            serviceItem.setPrice("100");
            serviceItem.setServiceIntroduce("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
            serviceItem.setSkills("手绘");
            serviceItem.setUserName("呵呵呵");
            homeItem.setServiceItem(serviceItem);
            homeItem.setItemType(HomeItem.SERVICE_ITEM);
            homeItemList.add(homeItem);
        }
    }


    //初始化首页推荐
    public void initRecommend() {

        List<NameAndIcon> nameAndIconList = new ArrayList<NameAndIcon>();
        HomeItem homeItem = new HomeItem();
        for (int i = 0; i < 10; i++) {

            NameAndIcon nameAndIcon = new NameAndIcon();
            nameAndIcon.setIcon(icons[i]);
            nameAndIcon.setName("hhhhh");
            nameAndIconList.add(nameAndIcon);
        }
        homeItem.setItemType(HomeItem.RECOMMEND);
        homeItem.setNameAndIconList(nameAndIconList);
        homeItemList.add(homeItem);
    }


}
