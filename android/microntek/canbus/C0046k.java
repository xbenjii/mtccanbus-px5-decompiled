package android.microntek.canbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class C0046k extends BroadcastReceiver {
    final /* synthetic */ Canbus20Activity pb;

    C0046k(Canbus20Activity canbus20Activity) {
        this.pb = canbus20Activity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(this.pb.me)) {
            this.pb.mf = intent.getByteArrayExtra("syncdata");
            if (this.pb.mf != null) {
                this.pb.rl(this.pb.mf);
            }
        }
    }
}
