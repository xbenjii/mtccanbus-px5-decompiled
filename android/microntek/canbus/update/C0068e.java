package android.microntek.canbus.update;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class C0068e extends BroadcastReceiver {
    final /* synthetic */ UpdateService is;

    C0068e(UpdateService updateService) {
        this.is = updateService;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.microntek.canbus.receiver")) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("com.microntek.receiverkey");
            if (byteArrayExtra != null && byteArrayExtra.length > 2 && this.is.ic != null) {
                this.is.ic.nr(byteArrayExtra);
            }
        }
    }
}
