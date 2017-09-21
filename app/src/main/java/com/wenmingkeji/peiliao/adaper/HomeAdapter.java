package com.wenmingkeji.peiliao.adaper;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenmingkeji.peiliao.R;
import com.wenmingkeji.peiliao.activities.HomeActivity;
import com.wenmingkeji.peiliao.fragment.CategoryFragment;
import com.wenmingkeji.peiliao.model.Ad;
import com.wenmingkeji.peiliao.model.HomeItem;
import com.wenmingkeji.peiliao.model.ServiceItem;
import com.wenmingkeji.peiliao.utils.ToastUtil;
import com.wenmingkeji.peiliao.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bevis on 2016/6/24.
 */
public class HomeAdapter extends BaseAdapter {


    private final static int CATEGORY = 0;
    private final static int AD = 1;
    private final static int SERVICE = 2;
    public Context mContext;
    private List<HomeItem> mHomeItems;
    private LayoutInflater mInflater;
    private FragmentManager mManager;

    public HomeAdapter(Context context, List<HomeItem> homeItems, FragmentManager manager) {
        this.mHomeItems = homeItems;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mManager = manager;
    }


    @Override
    public int getCount() {
        return mHomeItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mHomeItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        HomeItem homeItem = mHomeItems.get(position);

//        CategoryHolder categoryHolder;
        AdHolder adHolder;
        ServiceHolder serviceHolder;
        ViewHolder viewHolder;

//        int type = homeItem.getItemType().getValue();
        int type = 0;

        switch (type) {
            case CATEGORY:
                if (convertView == null) {
//                    view = mInflater.inflate(R.layout.view_home_category, null);
//                    viewHolder = new ViewHolder(view);
//                    view.setTag(viewHolder);

//                    view = mInflater.inflate(R.layout.view_home_category, null);
                    viewHolder = new ViewHolder();
                    view.setTag(viewHolder);

//                    categoryHolder = new CategoryHolder(view);
//                    view.setTag(categoryHolder);
                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) convertView.getTag();
//                    categoryHolder = (CategoryHolder) view.getTag();
                }
                viewHolder.pager = (ViewPager) view.findViewById(R.id.viewpager_category);
                viewHolder.setViewPager(homeItem);
                
                
                break;
            case AD:
                if (convertView == null) {
                    view = mInflater.inflate(R.layout.view_home_ad, null);
                    adHolder = new AdHolder(view);
                    view.setTag(adHolder);
                } else {
                    view = convertView;
                    adHolder = (AdHolder) convertView.getTag();
                }
                adHolder.setViewPager(homeItem);
                break;

            case SERVICE:
                if (convertView == null) {
                    view = mInflater.inflate(R.layout.item_list_service, null);
                    serviceHolder = new ServiceHolder(view);
                    view.setTag(serviceHolder);
                } else {
                    view = convertView;
                    serviceHolder = (ServiceHolder) convertView.getTag();
                }

                serviceHolder.refreshUI(homeItem);
                break;
        }

        return view;
    }


    //当前Item 的类型
    @Override
    public int getItemViewType(int position) {
        if (mHomeItems != null && position < mHomeItems.size()) {
//            return mHomeItems.get(position).getItemType().getValue();
        }
        return super.getItemViewType(position);
    }


    //获取item 的类型数
    @Override
    public int getViewTypeCount() {
        return 3;
    }


    /*//首页分类 CategoryHolder
   class CategoryHolder {
        @BindView(R.id.rv_category)
        PageRecyclerView mRvCategory;
        @BindView(R.id.rv_indicator)
        PageIndicatorView mRvIndicator;

        PageRecyclerView.PageAdapter mPageAdapter = null;

        CategoryHolder(View view) {
            ButterKnife.bind(this, view);
        }


        public void refreshUI(final HomeItem homeItem) {

            System.out.println(homeItem.getNameWithIconList().size());
            //设置指示器
            mRvCategory.setIndicator(mRvIndicator);

            // 设置行数和列数
            mRvCategory.setPageSize(2, 5);
            // 设置页间距
            mRvCategory.setPageMargin(30);

            //初始化适配器
            mPageAdapter = mRvCategory.new PageAdapter(homeItem.getNameWithIconList(), new PageRecyclerView.CallBack() {
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_category, parent,false);
                    return new MyHolder(view);
                }

                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                    ((MyHolder) holder).mTextView.setText(homeItem.getNameWithIconList().get(position).getNames());
                    ((MyHolder) holder).mImageView.setImageResource(homeItem.getNameWithIconList().get(position).getIcons());
                }
                
                public void onItemClickListener(View view, int position) {
                    Toast.makeText(MyApplication.getContext(), "点击："
                            + position, Toast.LENGTH_SHORT).show();
                }

               
                public void onItemLongClickListener(View view, int position) {
                    mPageAdapter.remove(position);
                }
            });
            
            mRvCategory.setAdapter(mPageAdapter);

        }

        public class MyHolder extends RecyclerView.ViewHolder {

            public ImageView mImageView;
            public TextView mTextView;

            public MyHolder(View itemView) {
                super(itemView);

                mImageView = (ImageView) itemView.findViewById(R.id.iv_img);
                mTextView = (TextView) itemView.findViewById(R.id.tv_name);
            }

        }
    }
*/

    //轮播广告 AdHolder
    static class AdHolder {
        //请求更新显示的View
        protected static final int MSG_UPDATE_IMAGE = 1;
        //请求暂停轮播
        protected static final int MSG_KEEP_SILENT = 2;
        //请求恢复轮播
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;
        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        Context context;
        int currentItem = 0;
        ImageAdapter imgAdapter;

        @BindView(R.id.viewpager_ad)
        ViewPager mViewpagerAd;


        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
                if (handler.hasMessages(MSG_UPDATE_IMAGE)) {
                    handler.removeMessages(MSG_UPDATE_IMAGE);
                }
                switch (msg.what) {
                    case MSG_UPDATE_IMAGE:
                        currentItem++;
                        mViewpagerAd.setCurrentItem(currentItem);
                        //准备下次播放
                        handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                        break;
                    case MSG_KEEP_SILENT:
                        //只要不发送消息就暂停了
                        break;
                    case MSG_BREAK_SILENT:
                        handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                        break;
                    case MSG_PAGE_CHANGED:
                        //记录当前的页号，避免播放的时候页面显示不正确。
                        currentItem = msg.arg1;
                        break;
                    default:
                        break;
                }
            }
        };


        AdHolder(View view) {
            ButterKnife.bind(this, view);
        }


        public void setViewPager(HomeItem homeItem) {
            final Ad ad = homeItem.getAd();
            if (imgAdapter == null) {
                imgAdapter = new ImageAdapter(context, ad.getImgIds());
                mViewpagerAd.setAdapter(imgAdapter);
                mViewpagerAd.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        handler.sendMessage(Message.obtain(handler, MSG_PAGE_CHANGED, position, 0));
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                        switch (state) {
                            case ViewPager.SCROLL_STATE_DRAGGING:
                                handler.sendEmptyMessage(MSG_KEEP_SILENT);
                                break;
                            case ViewPager.SCROLL_STATE_IDLE:
                                handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                                break;
                            default:
                                break;
                        }
                    }
                });
                mViewpagerAd.setCurrentItem(Integer.MAX_VALUE / 2);//默认在中间，使用户看不到边界
                //开始轮播效果
                handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);

                imgAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        ToastUtil.show("i am ad " + position);
                    }
                });
            }
        }


    }


    //主页服务列表项 ServiceHolder
    static class ServiceHolder {
        @BindView(R.id.icon_head)
        CircleImageView mIconHead;
        @BindView(R.id.tv_user_name)
        TextView mTvUserName;
        @BindView(R.id.tv_user_level)
        TextView mTvUserLevel;
        @BindView(R.id.iv_level_img)
        ImageView mIvLevel;
        @BindView(R.id.iv_follow)
        ImageView mIvFollow;
        @BindView(R.id.tv_fans_num)
        TextView mTvFansNum;
        @BindView(R.id.iv_preview1)
        ImageView mIvPreview1;
        @BindView(R.id.iv_preview2)
        ImageView mIvPreview2;
        @BindView(R.id.iv_preview3)
        ImageView mIvPreview3;
        @BindView(R.id.tv_user_skill)
        TextView mTvUserSkill;
        @BindView(R.id.tv_like_num)
        TextView mTvLikeNum;
        @BindView(R.id.tv_comment_num)
        TextView mTvCommentNum;
        @BindView(R.id.tv_refer_num)
        TextView mTvReferNum;
        @BindView(R.id.tv_service_details)
        TextView mTvServiceDetails;
        @BindView(R.id.tv_service_sales)
        TextView mTvServiceSales;
        @BindView(R.id.iv_comment_level)
        ImageView mIvCommentLevel;
        @BindView(R.id.tv_comment_level_text)
        TextView mTvCommentLevelText;
        @BindView(R.id.tv_service_price)
        TextView mTvServicePrice;

        ServiceHolder(View view) {
            ButterKnife.bind(this, view);
        }


        /**
         * serviceItem
         *
         * @param : ServiceItem(int imgId, String userName, String skills, String serviceIntro, String price)
         */
        public void refreshUI(HomeItem homeItem) {
            ServiceItem item = homeItem.getServiceItem();
            mIconHead.setImageResource(item.getImgId());
            mTvUserName.setText(item.getUserName());
            mTvUserSkill.setText(item.getSkills());
            mTvServiceDetails.setText(item.getServiceIntroduce());
            mTvServicePrice.setText(item.getPrice());
        }

    }


  /*  static class ViewHolder {
        @BindView(R.id.viewpager_category)
        ViewPager mViewpagerCategory;


        ViewPager viewPager;

        ViewHolder(View view) {

            ButterKnife.bind(this, view);
        }

        private List<Fragment> mFragments = new ArrayList<Fragment>();

        void refreshUI(HomeItem homeItem) {
            CategoryFragment fragment1 = CategoryFragment.newInstance(homeItem.getNameWithIcon());
            CategoryFragment fragment2 = CategoryFragment.newInstance(homeItem.getNameWithIcon());

            mFragments.add(fragment1);
            mFragments.add(fragment2);

            FragmentManager manager = HomeActivity.instacne.getSupportFragmentManager();

            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(manager);
            mViewpagerCategory.setAdapter(sectionsPagerAdapter);

        }


        *//**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         *//*
        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                // getItem is called to instantiate the fragment for the given page.
                // Return a PlaceholderFragment (defined as a static inner class below).
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return mFragments.size();
            }


        }


    }
*/

    class ViewHolder {
        
        
        Context context;
        ViewPager pager;
        SectionsPagerAdapter sectionsAdapter;
        FragmentManager manager;
        private List<Fragment> mFragments = new ArrayList<Fragment>();


        ViewHolder() {
        }
        
        public void setViewPager(HomeItem homeItem) {
            CategoryFragment fragment1 = CategoryFragment.newInstance(homeItem.getNameWithIcon());
            CategoryFragment fragment2 = CategoryFragment.newInstance(homeItem.getNameWithIcon());

            mFragments.add(fragment1);
            mFragments.add(fragment2);
            
            manager = HomeActivity.instacne.getSupportFragmentManager();
            sectionsAdapter = new SectionsPagerAdapter(manager);
            pager.setCurrentItem(0);
            sectionsAdapter.notifyDataSetChanged();
            pager.setAdapter(sectionsAdapter);
        }

        void initView(HomeItem homeItem) {
            CategoryFragment fragment1 = CategoryFragment.newInstance(homeItem.getNameWithIcon());
            CategoryFragment fragment2 = CategoryFragment.newInstance(homeItem.getNameWithIcon());

            mFragments.add(fragment1);
            mFragments.add(fragment2);
            
        }


        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {


            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        }

    }
}
