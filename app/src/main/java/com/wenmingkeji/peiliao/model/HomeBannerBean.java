package com.wenmingkeji.peiliao.model;

import java.util.List;

/**
 * Created by bevis on 2016/7/29.
 */
public class HomeBannerBean {

    /**
     * success : true
     * msg :
     * data : {"homeTopBlockAdList":[{"title":"6.26大促分会场空虚寂寞老用户","url":"http://static1.kongge.com/kongge/6ydcjmkxehz.html?h5src=21_0_48_1_2_0","pic":"http://img.shenghuozhe.net/shz/2016/06/25/375w_174h_301661466848076.jpg"},{"title":"6.21大促分会场黄赌毒老用户","url":"http://static1.kongge.com/kongge/6ydchddhz.html?h5src=21_0_73_1_2_0","pic":"http://img.shenghuozhe.net/shz/2016/06/27/375w_174h_61F451467030603.jpg"}],"categories":[{"icon":"http://img.shenghuozhe.net/shz/2016/06/28/100w_100h_2EF651467085597.png","title":"美食","id":48},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_6B1BC1465723079.png","title":"陪聊陪玩","id":98},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_933E71465721966.png","title":"手绘","id":55},{"icon":"http://img.shenghuozhe.net/shz/2016/06/14/100w_100h_7CA201465893087.png","title":"宠物咨询","id":113},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_87BA51465722282.png","title":"声优","id":103},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_2203A1465721459.png","title":"LOL","id":99},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_C45D21465723049.png","title":"签名设计","id":56},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_31A8A1465721647.png","title":"占卜","id":73},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_5FB211465723307.png","title":"斗图","id":51},{"icon":"http://img.shenghuozhe.net/shz/2016/06/28/100w_100h_65F821467096686.png","title":"情感咨询","id":63},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_A795A1465721945.png","title":"营销必备","id":67},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_EF7C51465723284.png","title":"画作","id":54},{"icon":"http://img.shenghuozhe.net/shz/2016/06/13/100w_100h_05AE61465805542.png","title":"旅游咨询","id":112},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_B7AF01465722986.png","title":"摄影影音","id":62},{"icon":"http://img.shenghuozhe.net/shz/2016/06/20/100w_100h_7A61A1466407619.png","title":"职场咨询","id":115},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_8ECF41465722924.png","title":"生活咨询","id":109},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_BD8681465721478.png","title":"专业咨询","id":49},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_CD04E1465723218.png","title":"线上课程","id":58},{"icon":"http://img.shenghuozhe.net/shz/2016/06/14/100w_100h_44EA31465893175.png","title":"减肥塑形","id":114},{"icon":"http://img.shenghuozhe.net/shz/2016/06/15/100w_100h_003DF1465973056.png","title":"大杂烩","id":68}],"homeBeltAdList":{"title":"一元抢约老用户2.6.5版本","url":"http://m.kongge.com/one?h5src=15_3","pic":"http://img.shenghuozhe.net/shz/2016/06/13/270w_240h_F90661465813953.png"}}
     */

    private boolean success;
    private String msg;
    /**
     * homeTopBlockAdList : [{"title":"6.26大促分会场空虚寂寞老用户","url":"http://static1.kongge.com/kongge/6ydcjmkxehz.html?h5src=21_0_48_1_2_0","pic":"http://img.shenghuozhe.net/shz/2016/06/25/375w_174h_301661466848076.jpg"},{"title":"6.21大促分会场黄赌毒老用户","url":"http://static1.kongge.com/kongge/6ydchddhz.html?h5src=21_0_73_1_2_0","pic":"http://img.shenghuozhe.net/shz/2016/06/27/375w_174h_61F451467030603.jpg"}]
     * categories : [{"icon":"http://img.shenghuozhe.net/shz/2016/06/28/100w_100h_2EF651467085597.png","title":"美食","id":48},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_6B1BC1465723079.png","title":"陪聊陪玩","id":98},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_933E71465721966.png","title":"手绘","id":55},{"icon":"http://img.shenghuozhe.net/shz/2016/06/14/100w_100h_7CA201465893087.png","title":"宠物咨询","id":113},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_87BA51465722282.png","title":"声优","id":103},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_2203A1465721459.png","title":"LOL","id":99},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_C45D21465723049.png","title":"签名设计","id":56},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_31A8A1465721647.png","title":"占卜","id":73},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_5FB211465723307.png","title":"斗图","id":51},{"icon":"http://img.shenghuozhe.net/shz/2016/06/28/100w_100h_65F821467096686.png","title":"情感咨询","id":63},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_A795A1465721945.png","title":"营销必备","id":67},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_EF7C51465723284.png","title":"画作","id":54},{"icon":"http://img.shenghuozhe.net/shz/2016/06/13/100w_100h_05AE61465805542.png","title":"旅游咨询","id":112},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_B7AF01465722986.png","title":"摄影影音","id":62},{"icon":"http://img.shenghuozhe.net/shz/2016/06/20/100w_100h_7A61A1466407619.png","title":"职场咨询","id":115},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_8ECF41465722924.png","title":"生活咨询","id":109},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_BD8681465721478.png","title":"专业咨询","id":49},{"icon":"http://img.shenghuozhe.net/shz/2016/06/12/100w_100h_CD04E1465723218.png","title":"线上课程","id":58},{"icon":"http://img.shenghuozhe.net/shz/2016/06/14/100w_100h_44EA31465893175.png","title":"减肥塑形","id":114},{"icon":"http://img.shenghuozhe.net/shz/2016/06/15/100w_100h_003DF1465973056.png","title":"大杂烩","id":68}]
     * homeBeltAdList : {"title":"一元抢约老用户2.6.5版本","url":"http://m.kongge.com/one?h5src=15_3","pic":"http://img.shenghuozhe.net/shz/2016/06/13/270w_240h_F90661465813953.png"}
     */

    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 一元抢约老用户2.6.5版本
         * url : http://m.kongge.com/one?h5src=15_3
         * pic : http://img.shenghuozhe.net/shz/2016/06/13/270w_240h_F90661465813953.png
         */

        private HomeBeltAdListBean homeBeltAdList;
        /**
         * title : 6.26大促分会场空虚寂寞老用户
         * url : http://static1.kongge.com/kongge/6ydcjmkxehz.html?h5src=21_0_48_1_2_0
         * pic : http://img.shenghuozhe.net/shz/2016/06/25/375w_174h_301661466848076.jpg
         */

        private List<HomeTopBlockAdListBean> homeTopBlockAdList;
        /**
         * icon : http://img.shenghuozhe.net/shz/2016/06/28/100w_100h_2EF651467085597.png
         * title : 美食
         * id : 48
         */

        private List<CategoriesBean> categories;

        public HomeBeltAdListBean getHomeBeltAdList() {
            return homeBeltAdList;
        }

        public void setHomeBeltAdList(HomeBeltAdListBean homeBeltAdList) {
            this.homeBeltAdList = homeBeltAdList;
        }

        public List<HomeTopBlockAdListBean> getHomeTopBlockAdList() {
            return homeTopBlockAdList;
        }

        public void setHomeTopBlockAdList(List<HomeTopBlockAdListBean> homeTopBlockAdList) {
            this.homeTopBlockAdList = homeTopBlockAdList;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

    }
}
