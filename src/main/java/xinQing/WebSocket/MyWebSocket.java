package xinQing.WebSocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/chat")
public class MyWebSocket {

    private String id;

    private Session session;

    private static final Set<MyWebSocket> connections = new CopyOnWriteArraySet<>();


    /**
     * 打开连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        this.id = session.getId();
        connections.add(this);

        UserManager.getInstance().add(id, new User(id, id));

        String msg = UserManager.getInstance().get(id).getUsername() + "进入聊天室";
        Message message = new Message(UserManager.getInstance().get(id), msg, true, false, true);

        broadCast(message);

    }

    /**
     * 接收信息
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        Message msg = JsonUtil.parse(message, Message.class);
        broadCast(new Message(UserManager.getInstance().get(this.id), msg.getMsg(), false, false, false));
    }

    /**
     * 错误信息响应
     *
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @OnClose
    public void onClose(){
        String message = UserManager.getInstance().get(id).getUsername() + "断开连接";
        Message m = new Message(UserManager.getInstance().get(this.id), message, true, true, false);
        broadCast(m);

        UserManager.getInstance().remove(id);
        connections.remove(this);
    }

    private void broadCast(Message msg) {
        for (MyWebSocket chat : connections) {
            if (chat == this)
                continue;
            try {
                    synchronized (chat) {
                        chat.session.getBasicRemote().sendText(JsonUtil.parse(msg));
                    }
            } catch (IOException e) {
                try {
                    chat.session.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}