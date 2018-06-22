<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>文章发布</title>
    <meta name="keywords" content="前端模板">
    <meta name="description" content="前端模板">

    <script src="static/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script src="static/layui/layui.js" type="text/javascript"></script>
    <script src="static/js/index/index.js" type="text/javascript"></script>
    <script src="static/js/index/freezeheader.js" type="text/javascript"></script>
    <script src="static/layui/lay/modules/layer.js" type="text/javascript"></script>
    <script src="static/js/index/sliders.js" type="text/javascript"></script>
    <script src="static/js/index/html5.js" type="text/javascript"></script>
    <script src="static/js/index/article_pub.js" type="text/javascript"></script>
    <script src="static/editor/editormd.min.js"></script>
    <link rel="stylesheet" href="static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="static/layui/css/modules/layer/default/layer.css"/>
    <link rel="stylesheet" href="static/css/global.css"/>
    <link rel="stylesheet"href="static/editor/css/editormd.css" />
    <script type="text/javascript">
        $(function() {
            editormd("my-editormd", {//注意1：这里的就是上面的DIV的id属性值
                width   : "100%",
                height  : 640,
                syncScrolling : "single",
                path    : "<%=request.getContextPath()%>/static/editor/lib/",//注意2：你的路径
                saveHTMLToTextarea : true//注意3：这个配置，方便post提交表单
            });
        });

        function submitBlog() {
            var title = $("#title").val();
            var content = $("#my-editormd-html-code").val();
            var type = $("#type option:selected").val();
            alert("title:" + title + " ; content:" + content + " ; type:" + type);
            $.ajax({
                url: "<%=request.getContextPath()%>/saveBlog.do",
                type: "post",
                dataType: "json",
                data: {
                    "blog_title": title,
                    "blog_type": type,
                    "blog_content": content,
                },
                success: function (data) {
                },
                error: function () {

                }
            });
        }
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
            <li class="layui-nav-item">
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
                    <dd><a href="pb_blog_edit_page.jsp">文章发布</a></dd>
                    <dd><a href="pb_me_setting_page.jsp">修改信息</a></dd>
                    <dd><a href="javascript:;">退了</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>

<div class="layui-container container">
    <div class="layui-form layui-form-pane">
        <form action="#" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">标题</label>
                <div class="layui-input-block">
                    <input type="text" name="title" id="title" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
                </div>
            </div>

            <div id="my-editormd" >
                <!-- 注意：name属性的值-->
                <textarea id="my-editormd-html-code" name="my-editormd-html-code" style="display:none;"></textarea>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">文章分类</label>
                    <div class="layui-input-block">
                        <select id="type" name="type" lay-verify="type">
                            <option value=""></option>
                            <option value="1">技术随笔</option>
                            <option value="2">生活感悟</option>
                            <%--<option value="2">游戏</option>--%>
                            <%--<option value="3">音乐</option>--%>
                            <%--<option value="4">旅行</option>--%>
                        </select>
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <button class="layui-btn" lay-filter="*" lay-submit onclick="submitBlog()">立即发布</button>
            </div>
        </form>
    </div>
</div>
<div class="footer">
    <hr class="layui-bg-red">
    <p><a href="http://itdaima.com/">layui框架模板</a> 2017 &copy; <a href="#">itdaima.com</a></p>
    <p>layui框架模板</p>
</div>
</body>
</html>