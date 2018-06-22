package com.bbs.myblog.businesswork.result;

import java.util.List;

/**
 * User: baibisen
 * Date: 2018/6/9
 */
public class PbBlogSideTabResult {

    private List<HotBlog> hotBlogList;
//    private List<ShortNews> shortNewsList;

    public List<HotBlog> getHotBlogList() {
        return hotBlogList;
    }

    public void setHotBlogList(List<HotBlog> hotBlogList) {
        this.hotBlogList = hotBlogList;
    }

//    public List<ShortNews> getShortNewsList() {
//        return shortNewsList;
//    }
//
//    public void setShortNewsList(List<ShortNews> shortNewsList) {
//        this.shortNewsList = shortNewsList;
//    }

    public final static class HotBlog {
        private String hotBlogId;
        private String hotBlogTitle;
        private Integer type;

        public String getHotBlogId() {
            return hotBlogId;
        }

        public void setHotBlogId(String hotBlogId) {
            this.hotBlogId = hotBlogId;
        }

        public String getHotBlogTitle() {
            return hotBlogTitle;
        }

        public void setHotBlogTitle(String hotBlogTitle) {
            this.hotBlogTitle = hotBlogTitle;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }

    public final static class ShortNews {
        private String shortBlogId;
        private String shortBlogTitle;
        private Integer type;

        public String getShortBlogId() {
            return shortBlogId;
        }

        public void setShortBlogId(String shortBlogId) {
            this.shortBlogId = shortBlogId;
        }

        public String getShortBlogTitle() {
            return shortBlogTitle;
        }

        public void setShortBlogTitle(String shortBlogTitle) {
            this.shortBlogTitle = shortBlogTitle;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }
    }
}
