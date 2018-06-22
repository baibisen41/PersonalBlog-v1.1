/**
 * Created by 大森 on 2018/5/21.
 */
//分页模块
function pageLimit(data) {
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
        //只显示上一页、下一页
        laypage.render({
            elem: 'page'
            , count: data.totalPages
            , curr: data.pageNum
            , limit: 20
            //['count', 'prev', 'page', 'next', 'limit', 'skip']
            , layout: ['prev', 'page', 'next']
            , jump: function (obj, first) {
                console.log(obj)
                if (!first) {
                    var currentPage = obj.curr;//获取点击的页码
                    loadPage(currentPage);
                }
            }
        });
    });
}
