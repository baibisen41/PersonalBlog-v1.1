<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>关于</title>
    <meta name="keywords" content="PersonalBlog">
    <meta name="description" content="baibisen personalBlog">
    <script src="static/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script src="static/layui/layui.js" type="text/javascript"></script>
    <script src="static/js/index/index.js" type="text/javascript"></script>
    <script src="static/js/index/freezeheader.js" type="text/javascript"></script>
    <script src="static/layui/lay/modules/layer.js" type="text/javascript"></script>
    <script src="static/js/index/sliders.js" type="text/javascript"></script>
    <script src="static/js/index/html5.js" type="text/javascript"></script>
    <script src="static/js/index/article.js" type="text/javascript"></script>
    <link rel="stylesheet" href="static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="static/layui/css/modules/layer/default/layer.css"/>
    <link rel="stylesheet" href="static/css/global.css"/>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="layui-header header">
    <div class="main">
        <ul class="layui-nav layui-nav-left" lay-filter="filter">
            <a class="logo" href="index.html" title="Fly">Fly</a>
            <li class="layui-nav-item nav-left">
                <a href="index.jsp">首页</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_blog_page.jsp">技术随笔</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_life_page.jsp">生活记录</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_news_page.jsp">24h头条</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_mall_page.jsp">资源商城</a>
            </li>
            <li class="layui-nav-item">
                <a href="pb_comment_page.jsp">留言板</a>
            </li>
            <li class="layui-nav-item layui-this">
                <a href="pb_about_page.jsp">关于</a>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right layui-nav-right" lay-filter="filter">

            <li class="layui-nav-item">
                <a href="pb_me_page.jsp">我的主页<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;"><img src="<%=request.getContextPath()%>/static/images/head.jpg" class="layui-nav-img">我</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">修改信息</a></dd>
                    <dd><a href="javascript:;">安全管理</a></dd>
                    <dd><a href="javascript:;">退了</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>
<div class="layui-container container">
    <div class="layui-row layui-col-space20">
        <div class="layui-col-md8">
            <div>
              <span class="layui-breadcrumb">
          <a href="index.jsp">首页</a>
          <a><cite>关于</cite></a>
        </span>
                <hr class="layui-bg-red">
            </div>
        </div>
        <c:import url="pb_sider_bar.jsp"/>
    </div>
</div>
<c:import url="pb_footer_bar.jsp"/>
</body>
</html>