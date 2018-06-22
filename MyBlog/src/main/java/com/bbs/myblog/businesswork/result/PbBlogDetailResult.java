package com.bbs.myblog.businesswork.result;

/**
 * User: baibisen
 * Date: 2018/4/22
 */
public class PbBlogDetailResult {

    private String id;
    private String title;
    private String tag;
    private String content;
    private String createDate;
    private String userName;
    private Integer blogPv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBlogPv() {
        return blogPv;
    }

    public void setBlogPv(Integer blogPv) {
        this.blogPv = blogPv;
    }
}
