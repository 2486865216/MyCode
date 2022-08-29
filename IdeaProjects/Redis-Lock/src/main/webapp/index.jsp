<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form id="msform" action="${pageContext.request.contextPath}/SecKillServlet" enctype="application/x-www-form-urlencoded">
    <input type="text" id="prodid" name="prodid" value="0101">
    <input type="button" id="miaosha_btn" name="seckill_btn" value="点我秒杀">
</form>
</body>
<script type="text/javascript" src="script/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function () {
        $('#miaosha_btn').click(function (){
            let url = $('#msform').attr("action");
            $.post(url, $('#msform').serialize(), function (data) {
                if (data == false){
                    alert('抢光了!');
                    $('#miaosha_btn').attr("disabled",true);
                }
            })
        })
    })
</script>
</html>