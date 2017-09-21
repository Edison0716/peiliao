package com.wenmingkeji.peiliao.data;

/**
 * Created by bevis on 2016/7/14.
 */
public class DataServer {


    /**
     * success : true
     * msg :
     * data : {"user":{"user_id":10075806,"avatar_url":"http://img.shenghuozhe.net/shz/2016/05/09/660w_660h_A69DD1462756153.jpg","user_nick":"婷婷泥巴","user_gender":"F"},"comment_id":49046,"comment":"这个是什么包装？","create_time":1443332694,"quote":{"user_nick":"阿娇手工美食"}}
     */

    private boolean success;
    private String msg;
    /**
     * user : {"user_id":10075806,"avatar_url":"http://img.shenghuozhe.net/shz/2016/05/09/660w_660h_A69DD1462756153.jpg","user_nick":"婷婷泥巴","user_gender":"F"}
     * comment_id : 49046
     * comment : 这个是什么包装？
     * create_time : 1443332694
     * quote : {"user_nick":"阿娇手工美食"}
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
         * user_id : 10075806
         * avatar_url : http://img.shenghuozhe.net/shz/2016/05/09/660w_660h_A69DD1462756153.jpg
         * user_nick : 婷婷泥巴
         * user_gender : F
         */

        private UserBean user;
        private int comment_id;
        private String comment;
        private int create_time;
        /**
         * user_nick : 阿娇手工美食
         */

        private QuoteBean quote;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getComment_id() {
            return comment_id;
        }

        public void setComment_id(int comment_id) {
            this.comment_id = comment_id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public QuoteBean getQuote() {
            return quote;
        }

        public void setQuote(QuoteBean quote) {
            this.quote = quote;
        }

        public static class UserBean {
            private int user_id;
            private String avatar_url;
            private String user_nick;
            private String user_gender;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getUser_nick() {
                return user_nick;
            }

            public void setUser_nick(String user_nick) {
                this.user_nick = user_nick;
            }

            public String getUser_gender() {
                return user_gender;
            }

            public void setUser_gender(String user_gender) {
                this.user_gender = user_gender;
            }
        }

        public static class QuoteBean {
            private String user_nick;

            public String getUser_nick() {
                return user_nick;
            }

            public void setUser_nick(String user_nick) {
                this.user_nick = user_nick;
            }
        }
    }
}
