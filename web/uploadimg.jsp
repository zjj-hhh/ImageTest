<%--
  Created by IntelliJ IDEA.
  User: 周佳杰1699
  Date: 2020/12/13
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图片上传预览点击放大</title>
    <meta name="viewport"content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="css/uploadimg.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="filterPop" onclick="toHide()"></div>
<div class="setPops">
    <img src="" alt="" width="100%" height="100%">
</div>   <!-- 添加遮罩层 -->

<div class="Box">图片上传</div>
<div class="Box">(最多可同时上传10张图片进行检测分类)</div>
<span class="Box" id="innerText"></span>
<div class="Box1">
    <div class="productImg">
        <div id="uploadBox"></div>
        <div class="uploadDIv">
            <span>+</span><input type="file" name="file" multiple="multiple" id="inputs" accept="image/*" class='fileTest' multiple="multiple" />
        </div>
    </div>
</div>
<div style="text-align:center">
    <button>上传</button>
</div>
</body>
</html>
<script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="js/uploadimg.js" type="text/javascript"></script>