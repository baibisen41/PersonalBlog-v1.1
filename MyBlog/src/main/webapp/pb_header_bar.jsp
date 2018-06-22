<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<div class="layui-header header">
    <div class="main">
        <ul class="layui-nav layui-nav-left" lay-filter="filter">
            <a class="logo" href="index.jsp" title="Fly">Fly</a>
            <li class="layui-nav-item layui-this nav-left">
                <a href="index.jsp">首页</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_blog_page.jsp">文章</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_blog_page.jsp">时间轴</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_blog_page.jsp">相册</a>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right layui-nav-right" lay-filter="filter">

            <li class="layui-nav-item">
                <a href="pb_me_page.jsp">我的主页<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><img src="static/images/head.jpg" class="layui-nav-img">我</a>
                <dl class="layui-nav-child">
                    <dd><a href="pb_blog_edit_page.jsp">文章发布</a></dd>
                    <dd><a href="pb_me_setting_page.jsp">修改信息</a></dd>
                    <dd><a href="javascript:;">退了</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>
