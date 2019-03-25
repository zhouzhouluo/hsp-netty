package com.cmcc.hsp.netty.invote;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class STBActionMapUtil {

    private static Map<String, STBAction> stbActionMap = new HashMap<>();

    public static Object invote(String key, Object... args) throws Exception {
        STBAction stbAction = stbActionMap.get(key);
        if (stbAction != null) {
            Method method = stbAction.getMethod();
            try {
                return method.invoke(stbAction.getObject(), args);
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    public static void put(String key, STBAction stbAction) {
        stbActionMap.put(key, stbAction);
    }

}
