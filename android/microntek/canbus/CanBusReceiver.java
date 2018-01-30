package android.microntek.canbus;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.provider.Settings.System;
import android.util.Log;

public class CanBusReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        boolean z = true;
        Log.i("CanBusServer", intent.getAction() + "CanBus onReceive " + BaseApplication.py());
        Intent intent2 = new Intent();
        intent2.setComponent(new ComponentName("android.microntek.canbus", "android.microntek.canbus.CanBusServer"));
        context.startServiceAsUser(intent2, UserHandle.OWNER);
        if (BaseApplication.py() != null) {
            String action = intent.getAction();
            Log.i("chun", "CanBusReceiver action:" + action);
            if (action.equals("android.intent.action.USER_INITIALIZE")) {
                BaseApplication.py().qc(true);
                System.putInt(context.getContentResolver(), "canbus_updata", 1);
            } else if (!(action.equals("android.intent.action.PRE_BOOT_COMPLETED") || action.equals("android.intent.action.BOOT_COMPLETED"))) {
                z = false;
            }
            if (z) {
                BaseApplication.py().qf();
            }
        }
    }
}
