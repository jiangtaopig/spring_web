<%--
  Created by IntelliJ IDEA.
  User: zhujiangtao
  Date: 2022/8/15
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>单文件上传</title>
</head>
<body>
<h2>单文件上传</h2>
<form  action="onfile" method="post" enctype='multipart/form-data'>
    <input type="file" name="zjt_file" ><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
