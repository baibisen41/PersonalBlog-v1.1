package com.bbs.entity;

/**
 * User: baibisen
 * Date: 2018/5/21
 */
public class News {

    private String id;
    private String title;
    private String tag;
    private String thumbPic;
    private String summary;
    private String content;
    private String createDate;

    private Integer tagCount;//标签数
    private Integer blogPv;//文章访问量

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

    public String getThumbPic() {
        return thumbPic;
    }

    public void setThumbPic(String thumbPic) {
        this.thumbPic = thumbPic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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


    public Integer getTagCount() {
        return tagCount;
    }

    public void setTagCount(Integer tagCount) {
        this.tagCount = tagCount;
    }

    public Integer getBlogPv() {
        return blogPv;
    }

    public void setBlogPv(Integer blogPv) {
        this.blogPv = blogPv;
    }
}
