<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<script type="text/javascript">
    $.ajax({
        url: "<%=request.getContextPath()%>/showTagList.do",
        type: "get",
        dataType: "json",
        success: function (data) {
            var html = "";
            var blogList = $('.tag-cloud');
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href=\"#\">" + data[i].label + "(" + data[i].count + ")</a></li>";
            }
            blogList.append(html);
        },
        error: function (e) {

        }
    });
    $.ajax({
        url: "<%=request.getContextPath()%>/showBlogRank.do",
        type: "get",
        dataType: "json",
        success: function (data) {
            var html = "";
            var blogList = $('.blog-hot');
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href=\"#\"><img src=\""+ data[i].thumbPic +"\"><b>"+ data[i].title +"</b></a>\n"+
                        "<p><span class=\"tulanmu\"><a href=\"#\">"+ data[i].tag +"</a></span><span class=\"tutime\">"+ data[i].createDate +"</span></p></li>";
            }
            blogList.append(html);
        },
        error: function (e) {

        }
    });
    $.ajax({
        url: "<%=request.getContextPath()%>/hotBlogList.do",
        type: "get",
        dataType: "json",
        success: function (data) {
            var html = "";
            var blogList = $('.hot-blog');
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href=\"showBlogDetail.do?id="+ data[i].id +"\" target=\"_blank\">"+ data[i].title +"</a></li>";
            }
            blogList.append(html);
        },
        error: function (e) {

        }
    });
    $.ajax({
        url: "<%=request.getContextPath()%>/shortNewsList.do",
        type: "get",
        dataType: "json",
        success: function (data) {
            var html = "";
            var blogList = $('.short-news');
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href=\"showBlogDetail.do?type=3&id="+ data[i].id +"\" target=\"_blank\">"+ data[i].title +"</a></li>";
            }
            blogList.append(html);
        },
        error: function (e) {

        }
    });
    $.ajax({
        url: "<%=request.getContextPath()%>/recommendBlogList.do",
        type: "get",
        dataType: "json",
        success: function (data) {
            var html = "";
            var blogList = $('.recommend-blog');
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href=\"showBlogDetail.do?id="+ data[i].id +"\" target=\"_blank\">"+ data[i].title +"</a></li>";
            }
            blogList.append(html);
        },
        error: function (e) {

        }
    });
    $.ajax({
        url: "<%=request.getContextPath()%>/replyBlogList.do",
        type: "get",
        dataType: "json",
        success: function (data) {
            var html = "";
            var blogList = $('.new-reply');
            for (var i = 0; i < data.length; i++) {
                html += "<li><a href=\"showBlogDetail.do?id="+ data[i].id +"\" target=\"_blank\">"+ data[i].title +"</a></li>";
            }
            blogList.append(html);
        },
        error: function (e) {

        }
    });
</script>
<div class="layui-col-md4">
    <form class="layui-form" method="post" action="pb_me_message_page.jsp">
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width:76%;">
                <input type="text" name="title" lay-verify="required" placeholder="请输入关键字" class="layui-input">
            </div>
            <button class="layui-btn" lay-filter="*" lay-submit>搜索</button>
        </div>
    </form>
    <div class="ad"> <img src="<%=request.getContextPath()%>/static/images/ad.jpg"> </div>
    <div class="ms-top">
        <ul class="hd" id="tab">
            <li class="cur"><a>点击排行</a></li>
            <li><a>最新资讯</a></li>
            <li><a>站长推荐</a></li>
            <li><a>最新评论</a></li>
        </ul>
    </div>
    <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
            <ul class="hot-blog">
                <%--<li><a href="#" target="_blank">住在手机里的朋友</a></li>--%>
                <%--<li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>--%>
                <%--<li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>--%>
                <%--<li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>--%>
                <%--<li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>--%>
                <%--<li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>--%>
            </ul>
        </div>
        <div  class="bd bd-news">
            <ul class="short-news">
                <%--<li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>--%>
                <%--<li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>--%>
                <%--<li><a href="#" target="_blank">住在手机里的朋友</a></li>--%>
                <%--<li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>--%>
                <%--<li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>--%>
                <%--<li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>--%>
            </ul>
        </div>
        <div class="bd bd-news">
            <ul class="recommend-blog">
                <%--<li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>--%>
                <%--<li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>--%>
                <%--<li><a href="#" target="_blank">住在手机里的朋友</a></li>--%>
                <%--<li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>--%>
                <%--<li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>--%>
                <%--<li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>--%>
            </ul>
        </div>
        <div  class="bd bd-news">
            <ul class="new-reply">
                <%--<li><a href="#" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>--%>
                <%--<li><a href="#" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>--%>
                <%--<li><a href="#" target="_blank">住在手机里的朋友</a></li>--%>
                <%--<li><a href="#" target="_blank">教你怎样用欠费手机拨打电话</a></li>--%>
                <%--<li><a href="#" target="_blank">你面对的是生活而不是手机</a></li>--%>
                <%--<li><a href="#" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>--%>
            </ul>
        </div>
    </div>
    <div class="cloud">
        <h3>标签云</h3>
        <ul class="tag-cloud">
        </ul>
    </div>
    <div class="tuwen">
        <h3>图文推荐</h3>
        <ul class="blog-hot">

        </ul>
    </div>
    <div class="article-fenlei">
        <h3>文章分类</h3>
        <button class="layui-btn layui-btn-warm">专业技术</button>
        <button class="layui-btn layui-btn-primary">生活感悟</button>
        <button class="layui-btn layui-btn-danger">资讯头条</button>
        <br/>
<%--        <br/>
        <button class="layui-btn layui-btn-primary">javascript</button>
        <button class="layui-btn layui-btn-normal">SQL</button>
        <button class="layui-btn layui-btn-primary">其他</button>--%>
    </div>
    <div class="links">
        <h3><span>[<a href="#">申请友情链接</a>]</span>友情链接</h3>
        <ul>
            <li><a href="<%=request.getContextPath()%>/pb_editor_temp_page.jsp">编辑器</a></li>
            <li><a href="#">web开发</a></li>
            <li><a href="#">前端设计</a></li>
            <li><a href="#">Html</a></li>
            <li><a href="#">CSS3</a></li>
            <li><a href="#">Html5+css3</a></li>
            <li><a href="#">百度</a></li>
        </ul>
    </div>
</div>