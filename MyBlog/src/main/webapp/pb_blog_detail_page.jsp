<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="zh-CN">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>文章-个人博客-【心路历程】请不要在设计这条路上徘徊啦</title>
  <meta name="keywords" content="前端模板">
  <meta name="description" content="前端模板">
  <meta name="referrer" content="no-referrer" />
  <script src="static/js/jquery/jquery-2.1.4.min.js" type="text/javascript"></script>
  <script src="static/layui/layui.js" type="text/javascript"></script>
  <script src="static/js/index/index.js" type="text/javascript"></script>
  <script src="static/js/index/freezeheader.js" type="text/javascript"></script>
  <script src="static/layui/lay/modules/layer.js" type="text/javascript"></script>
  <script src="static/js/index/sliders.js" type="text/javascript"></script>
  <script src="static/js/index/html5.js" type="text/javascript"></script>
  <script src="static/js/index/article_details.js" type="text/javascript"></script>
  <%-- editor.md  start --%>
  <script src="<%=request.getContextPath()%>/static/editor/lib/marked.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/editor/lib/prettify.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/editor/lib/raphael.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/editor/lib/underscore.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/editor/lib/sequence-diagram.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/editor/lib/flowchart.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/editor/lib/jquery.flowchart.min.js"></script>
  <script src="<%=request.getContextPath()%>/static/editor/editormd.min.js"></script>
  <link rel="stylesheet" href="static/editor/css/editormd.preview.css"/>
  <link rel="stylesheet" href="static/layui/css/layui.css" media="all"/>
  <link rel="stylesheet" href="static/layui/css/modules/layer/default/layer.css"/>
  <link rel="stylesheet" href="static/css/global.css"/>
  <script type="text/javascript">
      $(function () {
          editormd.markdownToHTML("detail-content", {
              htmlDecode: "style,script,iframe",  // you can filter tags decode
              emoji: true,
              taskList: true,
              tex: true  // 默认不解析
          });
      });
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
  <div class="layui-row layui-col-space20">
    <div class="layui-col-md8">
      <div>
          <span class="layui-breadcrumb">
                <a><cite>文章</cite></a>
              <a><cite>${blogDetail.title}</cite></a>
    	</span>
        <hr class="layui-bg-red">
        <div class="content" id="photos">
        	<h2 class="c_titile">${blogDetail.title}</h2>
            <p class="box_c"><span class="d_time">发布时间：${blogDetail.createDate}</span><span>作者：${blogDetail.userName}</span><span>阅读（${blogDetail.blogPv}）  </span></p>

          <div id="detail-content" class="detail-body">
                <textarea style="display:none;" placeholder="markdown">${blogDetail.content}</textarea>
          </div>
        
        <fieldset class="layui-elem-field layui-field-title" style="margin: 0px 0px; text-align: center;">
          <legend>评论</legend>
        </fieldset>
	<div class="detail-box">
        <a name="comment"></a>
        <ul class="jieda" id="jieda">
          <li data-id="12" class="jieda-daan">
            <a name="item-121212121212"></a>
            <div class="detail-about detail-about-reply">
              <a class="jie-user" href="">
                <img src="static/images/default.png" alt="">
                <cite>
                  <i>纸飞机</i>
                  <!-- <em>(楼主)</em>
                  <em style="color:#5FB878">(管理员)</em>
                  <em style="color:#FF9E3F">（活雷锋）</em>-->
                  <em style="color:#999">活跃</em>
                </cite>
              </a>
              <div class="detail-hits">
                <span>3分钟前</span>
              </div>
              <i class="iconfont icon-caina" title="最佳答案"></i>
            </div>
            <div class="detail-body jieda-body">
              <p>好文章，学习了</p>
              <%--<img lay-src="../static/images/5.jpg" layer-src="../static/images/5.jpg" alt="Fly社区">--%>
            </div>
            <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan"><i class="iconfont icon-zan"></i><em>12</em></span>
              <span type="reply"><i class="iconfont icon-svgmoban53"></i>回复</span>
              <!-- <div class="jieda-admin">
                <span type="edit">编辑</span>
                <span type="del">删除</span>
                <span class="jieda-accept" type="accept">采纳</span>
              </div> -->
            </div>
          </li>
        </ul>
        <!--分页-->
      	<div id="page_reply"></div>
        <div class="layui-form layui-form-pane">
            <div class="layui-form-item layui-form-text">
              <div class="layui-input-block">
                <textarea id="reply" name="reply" lay-verify="reply" class="layui-textarea fly-editor"></textarea>
              </div>
            </div>
            <div class="layui-form-item">
              <input type="button" class="layui-btn" onclick="getReply();" lay-filter="*" lay-submit value="提交回复"/>
            </div>
        </div>
      </div>
      </div>
      </div>
    </div>
    <c:import url="pb_sider_bar.jsp"/>
  </div>
</div>
<c:import url="pb_footer_bar.jsp"/>
<script>
    //图片放大 预览显示
    layer.photos({
        photos: '#photos'
        , anim: 0 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
    });
</script>
</body>
</html>
