<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!--web路径：
        不以/开始的相对路径找资源，以当前资源的路径为基准，经常容易出问题。
        以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
        http://localhost:3306/ssm整合
        -->
    <%--    <%--%>
    <%--        pageContext.setAttribute("APP_PATH", request.getContextPath());--%>
    <%--    %>--%>
    <script src="../../static/jquery/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="../../static/layui/css/layui.css">
    <script src="../../static/layui/layui.js"></script>
</head>
<body>
<div style="width: 1000px;border: 1px solid;margin: auto">
    <h1>CRUD-SSM</h1>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-btn-container">
                <button type="button" class="layui-btn">新增</button>
                <button type="button" class="layui-btn layui-btn-danger">删除</button>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>empId</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>
                <c:forEach items="${pageInfo.list}" var="item">
                <tbody>
                <th>${item.empId}</th>
                <th>${item.empName}</th>
                <th>${item.gender == "1" ? "男" : "女"}</th>
                <th>${item.email}</th>
                <th>${item.department.deptName}</th>
                <th>
                    <div style="display: flex;height: 60px;justify-content: space-around;
                    align-items: center;" class="layui-btn-container">
                        <button type="button" class="layui-btn">按钮一</button>
                        <button type="button" class="layui-btn layui-btn-danger">按钮二</button>
                    </div>
                </th>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div>当前第${pageInfo.pageNum}页,总共${pageInfo.pages}页，总共${pageInfo.total}条记录</div>
            <div id="page"></div>
        </div>
    </div>
</div>
</body>
<script>
    layui.use('laypage', function(){
        let laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
            ,count: ${pageInfo.total} //数据总数，从服务端得到
            ,limit: 5
            ,first: "首页"
            ,last: "尾页"
        });
    });
</script>
</html>