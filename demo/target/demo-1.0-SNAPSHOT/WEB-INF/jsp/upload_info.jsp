<%--
  Created by IntelliJ IDEA.
  User: zhujiangtao
  Date: 2022/8/15
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息上传</title>
</head>
<body>
<h2>个人信息上传</h2>
<form name="onfile" action="info_upload" method="post" enctype="multipart/form-data">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"> <br>
    图片：<input type="file" name="img"> <br> <br>
    <input type="file" name="img"> <br> <br>
    简历：<input type="file" name="resume"><br> <br>
    <input type="submit" value="提交"> <br>
</form>
</body>
</html>
