package android.microntek.canbus.log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class C0048a extends BroadcastReceiver {
    final /* synthetic */ CanBusLogActivity hr;

    C0048a(CanBusLogActivity canBusLogActivity) {
        this.hr = canBusLogActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.microntek.canbus.receiver")) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("com.microntek.receiverkey");
            if (byteArrayExtra != null && byteArrayExtra.length > 2) {
                this.hr.na(byteArrayExtra);
            }
        }
    }
}
