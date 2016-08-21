/**
 * Created by XUAN on 2016/8/20.
 */
(function ($) {

    $(function () {

        var users = [];

        var ws = null;
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            ws = new WebSocket("ws://localhost:8080/chat");

            getUsers();
            function getUsers() {
                $.get('/users', function (data) {
                    for (var i = 0; i < data.length; i ++) {
                        var $div = $('<div id="user_' +data[i].id + '" class="user"></div>');
                        var $span = $('<span></span>');
                        $span.html(data[i].username);
                        $span.appendTo($div);

                        $div.appendTo($('#users'));
                    }
                }, 'json');
            }
        }
        else {
            alert('当前浏览器不支持WebSocket协议')
        }

        //连接发生错误的回调方法
        ws.onerror = function () {
            setMessageInnerHTML("系统消息: 系统错误", true, false, false);
        };

        //连接成功建立的回调方法
        ws.onopen = function (event) {
            console.log(event);
            setMessageInnerHTML("系统消息: 您已经建立连接", true, false, false);
        };

        //接收到消息的回调方法
        ws.onmessage = function (event) {
            var message = JSON.parse(event.data);
            if (message.isSystemMessage) {
                setMessageInnerHTML("系统消息:" + message.msg, true, false, false);

                if (message.isIn) {
                    users.push(message.user);

                    var $div = $('<div id="user_' + message.user.id + '" class="user"></div>');
                    var $span = $('<span></span>');
                    $span.html(message.user.username);
                    $span.appendTo($div);

                    $div.appendTo($('#users'));
                }
                if (message.isOut) {
                    var outId = 0;
                    for (var i = 0; i < users.length; i ++) {
                        if (users[i].id == message.user.id) {
                            outId = i;
                            break;
                        }
                    }
                    if (outId == 0) {
                        users.shift();
                    } else {
                        users.splice(outId, 1);
                    }


                    var user_node = document.getElementById("user_" + message.user.id);
                    if (user_node != null)
                        user_node.parentNode.removeChild(user_node);
                }

            }else {
                setMessageInnerHTML(message.user.username + ":" + message.msg, false, true, false);
            }
            console.log(users);
        };

        //连接关闭的回调方法
        ws.onclose = function () {
            setMessageInnerHTML("系统消息: 您已经断开连接", true, false, false);
        };

        //监听窗口关闭事件，当窗口关闭时，主动去关闭ws连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            ws.close();
        };

        //将消息显示在网页上
        function setMessageInnerHTML(msg, isSystemMessage, isReceive, isOwnSend) {

            if (isSystemMessage) {
                var $msgContainer = $('#msgContainer');
                var $div = $('<div class="msg"></div>');

                var $span = $('<span class="systemMessage"></span>');
                $span.html(msg);
                $span.appendTo($div);

                $div.appendTo($msgContainer);

                $div.css('height', $span.height()+ "px");
            }
            if (isReceive) {
                var $msgContainer = $('#msgContainer');
                var $div = $('<div class="msg"></div>');

                var $span = $('<span class="msgLeft"></span>');
                $span.html(msg);
                $span.appendTo($div);

                $div.appendTo($msgContainer);

                $div.css('height', $span.height()+ "px");
            }
            if (isOwnSend){
                var $msgContainer = $('#msgContainer');
                var $div = $('<div class="msg"></div>');

                var $span = $('<span class="msgRight"></span>');
                $span.html(msg);

                $span.appendTo($div);
                $div.appendTo($msgContainer);

                $div.css('height', $span.height()+ "px");
            }
            scrollBottom();
        };

        //发送消息
        function send(message) {
            ws.send(message);
        };

        console.log(ws);

        //为发送消息输入框绑定键盘事件
        $('#message').on('keypress', function (event) {
            if (event.keyCode == 13) {
                event.preventDefault();
                var message = $(this).val().trim();
                if (message != '') {
                    send(JSON.stringify({msg: message}));
                    setMessageInnerHTML(message, false, false, true);
                    scrollBottom();
                }
                $(this).val('');
            }
        });

        // 消息自动让滚动条到最底
        function scrollBottom() {
            var $win = $('#win');
            $win[0].scrollTop = $win[0].scrollHeight + 20;
        }


    });

})(jQuery);





