<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <%--        pageContext.setAttribute("APP_PATH", pageContext.request.contextPath);--%>
    <%--    %>--%>
    <style>
        .buttonContain {
            display: flex;
            height: 60px;
            justify-content: space-around;
            align-items: center
        }

        .pageContent {
            width: 400px;
        }

        .pageContent1 {
            width: 600px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/static/jquery/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
</head>
<body>
<div style="width: 1000px;border: 1px solid;margin: auto">
    <h1>CRUD-SSM</h1>
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-btn-container">
                <button id="createEmp" type="button" class="layui-btn">新增</button>
                <button id="deleteEmp" type="button" class="layui-btn layui-btn-danger">删除</button>
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-md12">
            <table id="table" class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>
                        <input id="check_all" type="checkbox">
                    </th>
                    <th>empId</th>
                    <th>empName</th>
                    <th>gender</th>
                    <th>email</th>
                    <th>deptName</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="info">
                </tbody>
            </table>
        </div>
    </div>
    <div class="layui-row">
        <div style="display: flex;
            justify-content: space-between;
            align-content: center;" id="pageDiv" class="layui-col-md12">
            <div id="pageInfo" class="pageContent"></div>
        </div>
    </div>
</div>
<div id="test" style="display: none">
    <form id="form" class="layui-form">
        <div class="layui-form-item">
            <label  class="layui-form-label" for="name">EmpName</label>
            <div class="layui-input-block">
                <input  class="layui-input" name="empName" id="name" type="text" placeholder="请输入员工姓名">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">gender</label>
            <div class="layui-input-block">
                <input class="layui-input" type="radio" name="gender" value="1" title="男" checked>
                <input class="layui-input" type="radio" name="gender" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="email">EmpEmail</label>
            <div class="layui-input-block">
                <input class="layui-input" name="email" id="email" type="text" placeholder="请输入员工邮箱">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="select">Department</label>
            <div class="layui-input-block">
                <select name="deptId" id="select">
                </select>
            </div>
        </div>
    </form>
</div>
<div id="test1" style="display: none">
    <form class="layui-form">
        <div class="layui-form-item">
            <label  class="layui-form-label">EmpName</label>
            <div class="layui-input-block">
                <label id="empNameLabel" class="layui-form-label"></label>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">gender</label>
            <div id="genderInput" class="layui-input-block">
                <input class="layui-input" type="radio" name="gender" value="1" title="男">
                <input class="layui-input" type="radio" name="gender" value="0" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="empemail">EmpEmail</label>
            <div class="layui-input-block">
                <input class="layui-input" name="email" id="empemail" type="text" placeholder="请输入员工邮箱">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" for="select1">Department</label>
            <div class="layui-input-block">
                <select name="deptId" id="select1">
                    <option value=""></option>
                </select>
            </div>
        </div>
    </form>
</div>
</body>
<%--//1、页面加载完成以后，直接去发送ajax请求，要到分页数据--%>
<script>
    $(function () {
        $.ajax({
        url: "${pageContext.request.contextPath}/emp",
        data: "page=1",
        type: "GET",
        success: function (result) {
            console.log(result)
            //1、解析并显示员工数据
            //2、解析并显示分页信息
            build_emp_table(result);
            build_page(result);
        }
    })
    })
    function flush_form(){
        layui.use('form', function(){
            let form = layui.form;
            form.render('select');
        });
    }
    function createEmp(){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                title: "添加新员工",
                area: ['500px', '500px'],
                btn: ['增加', '取消'],
                type: 1,
                content: $('#test'),
                //1、模态框中填写的表单数据提交给服务器进行保存
                //1、先对要提交给服务器的数据进行校验
                yes: function(index, layero){
                    $.ajax({
                        url: "${pageContext.request.contextPath}/emp",
                        type: "post",
                        data: $('#test form').serialize(),
                        success: function (result){
                            if (result.code === 100){
                                alert(result.message)
                                layer.close(index)
                                page_change(9999)
                            }else {
                                console.log(result.map.errorField)
                            }
                        }
                    })
                    // alert($('#test form').serialize())
                }
                ,btn2: function(index, layero){
                //按钮【按钮二】的回调

                //return false 开启该代码可禁止点击该按钮关闭
            }
            })
        });
    }
    function selectDept(){
        $.ajax({
            url: "${pageContext.request.contextPath}/dept",
            data: "",
            type: "GET",
            success: function (result) {
                console.log(result)
                let depts = result.map.dept
                $('<option></option>').appendTo("#select")
                for (let i = 0; i < depts.length; i++) {
                    $('#select')
                    let option = $('<option></option>').attr("value",depts[i].deptId).append(depts[i].deptName)
                    option.appendTo("#select")
                }
                flush_form();
            }
    })}

    $("#createEmp").click(function(){
        selectDept()
        createEmp()
    })


    function page_change(page) {
        $('#check_all').prop("checked", false)
        $.ajax({
            url: "${pageContext.request.contextPath}/emp",
            data: "page=" + page,
            type: "GET",
            success: function (result) {
                console.log(result)
                //1、解析并显示员工数据
                //2、解析并显示分页信息
                build_emp_table(result);
            }
        })
    }


    let currentPage;
    function build_emp_table(result) {
        $('#info').empty()
        let emps = result.map.pageInfo.list;
        for (let i = 0; i < emps.length; i++) {
            let  checkbox = $("<th></th>")
            let input = $('<input/>').attr("class", "check_item").attr("type", "checkbox").attr("emp_id", emps[i].empId)
            checkbox.append(input)
            let empId = $("<th></th>").append(emps[i].empId)
            let empName = $("<th></th>").append(emps[i].empName)
            let gender = $("<th></th>").append(emps[i].gender === "1" ? "男" : "女")
            let email = $("<th></th>").append(emps[i].email)
            let deptName = $("<th></th>").append(emps[i].department.deptName)

            let updateButton = $("<button></button>").attr("class","update").attr('emp_id',emps[i].empId).attr('emp_name',emps[i].empName).addClass("layui-btn").append("编辑");
            let deleteButton = $("<button></button>").attr("class", "delete").attr('emp_id',emps[i].empId).attr('emp_name',emps[i].empName).addClass("layui-btn  layui-btn-danger").append("删除");

            let buttonContain = $("<th></th>")
                .append(updateButton)
                .append(deleteButton)

            let empInfo = $("<tr></tr>")
                .append(checkbox)
                .append(empId)
                .append(empName)
                .append(gender)
                .append(email)
                .append(deptName)
                .append(buttonContain)
                .appendTo("#table tbody")

            build_pageInfo(result);
        }
    }

    function build_pageInfo(result){
        currentPage = result.map.pageInfo.pageNum
        $('#pageInfo').empty()
        $("<div></div>")
            .append("当前第" + result.map.pageInfo.pageNum + "页,总共" + Math.ceil(result.map.pageInfo.total / result.map.pageInfo.pageSize) + "页，总共" + result.map.pageInfo.total + "条记录")
            .addClass("pageContent")
            .appendTo('#pageInfo');
    }
    function build_page(result) {
        $("<div></div>").attr("id", "page").addClass("pageContent1").appendTo('#pageDiv');

        layui.use('laypage', function () {
            let laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'page', //注意，这里的 page 是 ID，不用加 # 号
                count: result.map.pageInfo.total, <%--//数据总数，从服务端得到--%>
                limit: 5,
                first: "首页",
                last: "尾页",
                jump: function (obj, first) {
                    //obj包含了当前分页的所有参数，比如：
                     //得到当前页，以便向服务端请求对应页的数据。
                     //得到每页显示的条数


                    //首次不执行
                    if (!first) {
                        //do something
                        page_change(obj.curr)
                    }
                }
            });
        });
    }

    $(document).on('click', '.update', function (){
        create_mune()
        updateEmp($(this).attr('emp_id'))
    })
    $(document).on('click', '.delete', function (){
        $(this).attr('emp_id')
        let deleteInfo = $(this).attr("emp_name")
        if (confirm("确认删除"+deleteInfo+"吗?")){
            $.ajax({
                url: "${pageContext.request.contextPath}/delete/" + $(this).attr("emp_id"),
                type: "DELETE",
                success: function (result){
                    alert(result.message)
                    page_change(currentPage)
                }
            })
        }
    })
    $(document).on('click', '#check_all', function (){
        $('.check_item').prop("checked", $(this).prop("checked"))
    })
    $(document).on('click', '.check_item', function (){
        let flag = $('.check_item:checked').length === $('.check_item').length
        $('#check_all').prop("checked", flag)
    })

    $('#deleteEmp').click(function (){
        let checkeds = $('.check_item:checked')
        let nums = "";
        for (let i = 0; i < checkeds.length; i++) {
            nums = nums + checkeds.eq(i).attr("emp_id") + "-"
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/delete/" + nums,
            type: "DELETE",
            success: function (result){
                alert(result.message)
                page_change(currentPage)
            }
        })
    })

    function flush_radio(){
        layui.use('form', function(){
            let form = layui.form;
            form.render('radio');
        });
    }
    function create_mune(){
        $.ajax({
            url: "${pageContext.request.contextPath}/dept",
            data: "",
            type: "GET",
            success: function (result) {
                let depts = result.map.dept
                $('#select1').empty();
                for (let i = 0; i < depts.length; i++) {
                    let option = $('<option></option>>').attr("value",depts[i].deptId).append(depts[i].deptName)
                    option.appendTo("#select1")
                }
                flush_form();
            }
        })
    }
    function updateEmp (id){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                title: "员工信息",
                area: ['500px', '500px'],
                btn: ['修改', '取消'],
                type: 1,
                content: $('#test1'),
                yes: function(index, layero){
                    $.ajax({
                        url: "${pageContext.request.contextPath}/update",
                        type: "PUT",
                        data: "empId=" + id + "&" + $('#test1 form').serialize(),
                        success: function (result){
                            console.log(result)
                            alert("处理成功")
                            page_change(currentPage);
                            layer.close(index)
                        }
                    })
                    // alert("empId=" + id + "&" + $('#test1 form').serialize())
                }
                ,btn2: function(index, layero){
                }
            })
        })
        $.ajax({
            url: "${pageContext.request.contextPath}/emp/" + id,
            type: 'put',
            success: function (result){
                $('#empNameLabel').text(result.map.emp.empName)
                // $("#genderInput input[name='gender'][value='"+result.map.emp.gender+"']").val([result.map.emp.gender])
                $("#genderInput input[name='gender'][value='"+result.map.emp.gender+"']").attr("checked","checked");
                flush_radio();
                $('#empemail').val(result.map.emp.email)
                $('#select1').val(result.map.emp.deptId)
            }
        })
    }
</script>
</html>