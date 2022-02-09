package utils;

import com.google.gson.Gson;

public class JsonUtils {
    private static Gson parser = new Gson();

    public static String toJson(Object o) {
        return parser.toJson(o);
    }
}
