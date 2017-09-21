package com.wenmingkeji.peiliao.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10564 on 2016/8/3.
 */
public class MyCareer {
    public Boolean success;
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * action_url : http://www.kongge.com/support/skill_upgrade.html?id=120
         * exp : 0
         * gap : 50
         * id : 1
         * is_default_job : false
         * level_icon : http://img.shenghuozhe.net/shz/2016/04/18/45w_45h_0101B1460963503.png
         * level_title : 美食家
         * name : 美食家
         * next_level_icon : http://img.shenghuozhe.net/shz/2016/04/20/45w_45h_836221461138314.png
         * next_level_title : 美食家
         */

        private List<JobsBean> jobs;

        public List<JobsBean> getJobs() {
            return jobs;
        }

        public void setJobs(List<JobsBean> jobs) {
            this.jobs = jobs;
        }

        public static class JobsBean {
            private String action_url;
            private int exp;
            private int gap;
            private int id;
            private boolean is_default_job;
            private String level_icon;
            private String level_title;
            private String name;
            private String next_level_icon;
            private String next_level_title;

            public String getAction_url() {
                return action_url;
            }

            public void setAction_url(String action_url) {
                this.action_url = action_url;
            }

            public int getExp() {
                return exp;
            }

            public void setExp(int exp) {
                this.exp = exp;
            }

            public int getGap() {
                return gap;
            }

            public void setGap(int gap) {
                this.gap = gap;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIs_default_job() {
                return is_default_job;
            }

            public void setIs_default_job(boolean is_default_job) {
                this.is_default_job = is_default_job;
            }

            public String getLevel_icon() {
                return level_icon;
            }

            public void setLevel_icon(String level_icon) {
                this.level_icon = level_icon;
            }

            public String getLevel_title() {
                return level_title;
            }

            public void setLevel_title(String level_title) {
                this.level_title = level_title;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNext_level_icon() {
                return next_level_icon;
            }

            public void setNext_level_icon(String next_level_icon) {
                this.next_level_icon = next_level_icon;
            }

            public String getNext_level_title() {
                return next_level_title;
            }

            public void setNext_level_title(String next_level_title) {
                this.next_level_title = next_level_title;
            }
        }
    }


}
