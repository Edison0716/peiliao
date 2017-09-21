package com.wenmingkeji.peiliao.model;

/**
 * Created by 10564 on 2016/8/8.
 */
public class MineInfoBeen {

    /**
     * avatar_url : http://192.168.1.132:8080/ic_img_user_default.png
     * complete_percent : 完善度 20%
     * identify : false
     * user_gender : M
     * user_nick : 李俊龙
     * user_balance : ￥23.00
     */

    private DataBean data;
    /**
     * data : {"avatar_url":"http://192.168.1.132:8080/ic_img_user_default.png","complete_percent":"完善度 20%","identify":"false","user_gender":"M","user_nick":"李俊龙","user_balance":"￥23.00"}
     * success : true
     */

    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        private String avatar_url;
        private String complete_percent;
        private Boolean identify;
        private String user_gender;
        private String user_nick;
        private String user_balance;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getComplete_percent() {
            return complete_percent;
        }

        public void setComplete_percent(String complete_percent) {
            this.complete_percent = complete_percent;
        }

        public Boolean getIdentify() {
            return identify;
        }

        public void setIdentify(Boolean identify) {
            this.identify = identify;
        }

        public String getUser_gender() {
            return user_gender;
        }

        public void setUser_gender(String user_gender) {
            this.user_gender = user_gender;
        }

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
        }

        public String getUser_balance() {
            return user_balance;
        }

        public void setUser_balance(String user_balance) {
            this.user_balance = user_balance;
        }
    }
}
