package xinQing.WebSocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by xinQing on 2016/8/20.
 */
public class JsonUtil {

    public static <T> String parse(T t) {
        Gson gson = new Gson();
        return gson.toJson(t);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
