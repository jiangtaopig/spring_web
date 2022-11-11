<%--
  Created by IntelliJ IDEA.
  User: zhujiangtao
  Date: 2022/8/15
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
</head>
<body>
<h2>登录页</h2>
<!--userName 和 pwd 要和 zjt_login 接口中的 LoginReq 对象的字段名一致-->
<form action="${pageContext.request.contextPath}/zjt_login" method="post">
    <table align="center">
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="text" name="pwd"></td>
        </tr>
        <tr>
            <td>生日：</td>
            <td><input type="text" name="date" value="1988-08-22"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交">
            </td>
        </tr>
    </table>

</form>
</body>
</html>

