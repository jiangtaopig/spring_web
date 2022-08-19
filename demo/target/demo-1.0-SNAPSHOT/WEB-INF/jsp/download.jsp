<%--
  Created by IntelliJ IDEA.
  User: zhujiangtao
  Date: 2022/8/15
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SpringMVC文件下载</title>
</head>
<body>
<h2>SpringMVC文件下载</h2>
<!--不加后面的 ${pageContext.request.contextPath} 会报错，找不到资源-->
个人照片<a href="${pageContext.request.contextPath}/my_download/zjt.jpg">zjt.jpg</a><br>
文档 <a href="${pageContext.request.contextPath}/my_download/flutter 调用Android 微信分享.docx">调用Android 微信分享.docx</a>
</body>
</html>

