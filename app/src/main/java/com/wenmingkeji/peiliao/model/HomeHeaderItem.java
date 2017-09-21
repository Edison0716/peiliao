package com.wenmingkeji.peiliao.model;

import java.util.List;

/**
 * Created by bevis on 2016/7/24.
 */
public class HomeHeaderItem {

    private AdHome mAdHome;
    private List<NameWithIcon> mNameWithIcons;
    private Ad mAd;

    public Ad getAd() {
        return mAd;
    }

    public void setAd(Ad ad) {
        mAd = ad;
    }

    public AdHome getAdHome() {
        return mAdHome;
    }

    public void setAdHome(AdHome adHome) {
        mAdHome = adHome;
    }

    public List<NameWithIcon> getNameWithIcons() {
        return mNameWithIcons;
    }

    public void setNameWithIcons(List<NameWithIcon> nameWithIcons) {
        mNameWithIcons = nameWithIcons;
    }
}
