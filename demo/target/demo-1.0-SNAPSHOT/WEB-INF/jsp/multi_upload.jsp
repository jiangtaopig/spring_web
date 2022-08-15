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
    <title>多文件上传</title>
</head>
<body>
<h2>同一类别多个文件上传</h2>
<form name="onfile"  action="multi_upload" method="post" enctype="multipart/form-data">
    图片：
    <input type="file" name="img"><br>
    <input type="file" name="img"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
