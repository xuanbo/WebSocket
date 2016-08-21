package xinQing.WebSocket;

/**
 * 消息
 *
 * Created by xinQing on 2016/8/20.
 */
public class Message {

    private User user;

    private String msg;

    private boolean isSystemMessage;

    private boolean isOut;

    private boolean isIn;

    public Message() {
    }

    public Message(User user, String msg, boolean isSystemMessage, boolean isOut, boolean isIn) {
        this.user = user;
        this.msg = msg;
        this.isSystemMessage = isSystemMessage;
        this.isOut = isOut;
        this.isIn = isIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSystemMessage() {
        return isSystemMessage;
    }

    public void setIsSystemMessage(boolean isSystemMessage) {
        this.isSystemMessage = isSystemMessage;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setIsOut(boolean isOut) {
        this.isOut = isOut;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setIsIn(boolean isIn) {
        this.isIn = isIn;
    }
}
