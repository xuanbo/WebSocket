#	`WebSocket`学习



##	1.依赖

```
<dependency>
    <groupId>javax.websocket</groupId>
    <artifactId>javax.websocket-api</artifactId>
    <version>1.1</version>
</dependency>
```



##	2.java如何使用api

```
@ServerEndpoint(value = "/chat")
public class MyWebSocket {

    /**
     * 打开连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {

    }

    /**
     * 接收信息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
       
    }

    /**
     * 错误信息响应
     *
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        
    }

    @OnClose
    public void onClose(){
        
    }
}
```



##	3.js如何使用

```
var ws = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    ws = new WebSocket("ws://localhost:8080/chat");
}
else {
    alert('当前浏览器不支持WebSocket协议')
}

//连接发生错误的回调方法
ws.onerror = function () {

};

//连接成功建立的回调方法
ws.onopen = function (event) {
    console.log(event);
};

//接收到消息的回调方法
ws.onmessage = function (event) {
    console.log(event.data);
};

//连接关闭的回调方法
ws.onclose = function () {
    setMessageInnerHTML("系统消息: 您已经断开连接", true, false, false);
};

//监听窗口关闭事件，当窗口关闭时，主动去关闭ws连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    ws.close();
};
```