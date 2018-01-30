package android.microntek.canbus;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.microntek.canbus.air.AirControlActivity;
import android.microntek.canbus.p002b.C0034a;
import android.provider.Settings.System;
import android.util.Log;

public class C0029a {
    private static final String[] ix = new String[]{"com.hiworld.carcomputer"};
    private static final String[] iy = new String[]{"com.hiworld.carset.noencry"};
    private static final String[] iz = new String[]{"com.hiworld.canbus.services"};
    private static final String[] ja = new String[]{"com.microntek.carcd"};
    private static final String[] jb = new String[]{"com.microntek.sync"};
    private static final String[] jc = new String[]{"com.microntek.civxusb"};
    private static final String[] jd = new String[]{"com.microntek.controlinfo"};
    private static final String[] je = new String[]{"com.microntek.controlsettings"};
    private static final String[] jf = new String[]{"com.microntek.mtcztcarsettings"};

    private void nz(Context context, PackageManager packageManager, boolean z) {
        ComponentName componentName = new ComponentName(context.getPackageName(), AirControlActivity.class.getName());
        if (z) {
            packageManager.setComponentEnabledSetting(componentName, 0, 1);
        } else {
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
        }
    }

    public void ny(Context context) {
        int pz = BaseApplication.py().pz();
        int qa = BaseApplication.py().qa();
        int i = System.getInt(context.getContentResolver(), "canbus_updata", 1);
        Log.i("CanBusServer", "CanBus initCanBusApp ");
        if (i != 0 && BaseApplication.py().qb()) {
            BaseApplication.py().qc(false);
            System.putInt(context.getContentResolver(), "canbus_updata", 0);
            PackageManager packageManager = context.getPackageManager();
            if (pz == 8 || pz == 33 || pz == 42 || pz == 5 || pz == 9 || pz == 14 || pz == 17 || pz == 35 || pz == 19 || pz == 26 || pz == 27 || pz == 24 || pz == 44 || pz == 47 || pz == 50 || pz == 49 || pz == 55 || pz == 4 || pz == 58 || pz == 60 || ((pz == 62 && qa != 1) || pz == 61 || pz == 63 || pz == 64 || pz == 36 || pz == 21 || pz == 68 || pz == 69 || pz == 75 || pz == 76 || ((pz == 7 && qa == 1) || pz == 79 || pz == 82 || pz == 83 || pz == 84 || pz == 85 || pz == 37 || pz == 88 || pz == 92 || pz == 94 || pz == 98 || pz == 78 || pz == 99 || pz == 100 || pz == 103 || pz == 105 || pz == 107))) {
                C0034a.lm(je, true, packageManager);
            } else {
                C0034a.lm(je, false, packageManager);
            }
            if (pz == 1 || pz == 8 || pz == 33 || pz == 42 || pz == 19 || pz == 21 || pz == 36 || pz == 26 || pz == 27 || pz == 30 || pz == 24 || pz == 17 || pz == 35 || pz == 47 || pz == 55 || pz == 58 || pz == 60 || pz == 61 || pz == 50 || pz == 64 || pz == 76 || pz == 80 || pz == 81 || pz == 82 || pz == 83 || pz == 84 || pz == 85 || pz == 54 || pz == 99 || pz == 104 || pz == 107 || (pz == 78 && (qa == 2 || qa == 4))) {
                C0034a.lm(jd, true, packageManager);
            } else {
                C0034a.lm(jd, false, packageManager);
            }
            if (pz == 7 || pz == 66) {
                C0034a.lm(jc, true, packageManager);
            } else {
                C0034a.lm(jc, false, packageManager);
            }
            if (pz == 6 || pz == 49 || pz == 62 || pz == 72) {
                C0034a.lm(jb, true, packageManager);
            } else {
                C0034a.lm(jb, false, packageManager);
            }
            if (pz == 25) {
                C0034a.lm(jf, true, packageManager);
            } else {
                C0034a.lm(jf, false, packageManager);
            }
            if (pz == 59) {
                C0034a.lm(iy, true, packageManager);
            } else {
                C0034a.lm(iy, false, packageManager);
            }
            if (pz == 59) {
                C0034a.lm(ix, true, packageManager);
            } else {
                C0034a.lm(ix, false, packageManager);
            }
            if ((pz == 75 && qa == 0) || pz == 74 || (pz == 78 && qa == 0)) {
                C0034a.lm(ja, true, packageManager);
            } else {
                C0034a.lm(ja, false, packageManager);
            }
            if (BaseApplication.py().qd(pz)) {
                C0034a.lm(iz, true, packageManager);
            } else {
                C0034a.lm(iz, false, packageManager);
            }
            if (pz != 103 && ((pz != 78 || (qa != 0 && qa != 3)) && pz != 98 && pz != 105 && ((pz != 61 || (qa != 1 && qa != 9)) && pz != 110))) {
                nz(context, packageManager, false);
            } else if (C0034a.ln(context)) {
                nz(context, packageManager, false);
            } else {
                nz(context, packageManager, true);
            }
            Log.i("CanBusServer", "CanBus initCanBusApp end !");
        }
    }
}
