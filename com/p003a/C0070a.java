package com.p003a;

import java.lang.reflect.Method;

public class C0070a {
    private static Class<?> pm = null;
    private static Method pn = null;
    private static Method po = null;
    private static Method pp = null;

    public static String tv(String str, String str2) {
        C0070a.tw();
        try {
            return (String) po.invoke(pm, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    private static void tw() {
        try {
            if (pm == null) {
                pm = Class.forName("android.os.SystemProperties");
                po = pm.getDeclaredMethod("get", new Class[]{String.class});
                pn = pm.getDeclaredMethod("getInt", new Class[]{String.class, Integer.TYPE});
                pp = pm.getDeclaredMethod("set", new Class[]{String.class, String.class});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
