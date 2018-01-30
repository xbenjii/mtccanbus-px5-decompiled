package android.microntek.canbus.p002b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import java.util.List;

public class C0034a {
    public static void lm(String[] strArr, boolean z, PackageManager packageManager) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (C0034a.lo(strArr[i], packageManager)) {
                packageManager.setApplicationEnabledSetting(strArr[i], z ? 1 : 2, 1);
            }
        }
    }

    public static boolean ln(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int dimensionPixelSize = displayMetrics.heightPixels + context.getResources().getDimensionPixelSize(17104920);
        return (i == 800 && dimensionPixelSize == 1280) || (i == 768 && dimensionPixelSize == 1024);
    }

    public static boolean lo(String str, PackageManager packageManager) {
        List installedPackages = packageManager.getInstalledPackages(8192);
        int size = installedPackages.size();
        for (int i = 0; i < size; i++) {
            if (str.equalsIgnoreCase(((PackageInfo) installedPackages.get(i)).packageName)) {
                return true;
            }
        }
        return false;
    }
}
