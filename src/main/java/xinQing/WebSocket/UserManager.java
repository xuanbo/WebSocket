package xinQing.WebSocket;

import java.util.Collection;
import java.util.HashMap;

/**
 * 管理在线用户
 *
 * Created by xinQing on 2016/8/20.
 */
public class UserManager {

    private HashMap<String, User> users = new HashMap<>();

    private static UserManager userManager = new UserManager();

    private UserManager() {

    }

    public static UserManager getInstance() {
        return userManager;
    }

    public synchronized void add(String sessionId, User user) {
        users.put(sessionId, user);
    }

    public synchronized void remove(String sessionId) {
        users.remove(sessionId);
    }

    public User get(String sessionId) {
        return users.get(sessionId);
    }

    public int size() {
        return users.size();
    }

    public synchronized Collection<User> getUserList() {
        return users.values();
    }

}
