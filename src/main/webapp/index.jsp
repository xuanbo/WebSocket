<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>chat</title>
    <script src="/assets/jQuery/jQuery.min.js"></script>
    <link href="/assets/custom/css/index.css" type="text/css" rel="stylesheet" />
    <script src="/assets/custom/js/index.js"></script>
</head>
<body>

<div id="chat">
    <div id="sideBar">
        <div id="title">
            <div id="img">
                <img src="/assets/img/1.jpg" />
            </div>
            <div id="userInfo">
                Hello World
            </div>
        </div>
        <div id="users"></div>
    </div>
    <div id="main">
        <div id="win">
            <div id="msgContainer"></div>
        </div>
        <div id="footer">
            <textarea id="message" class="form-control" placeholder="按enter发送"></textarea>
        </div>
    </div>
</div>

</body>
</html>
