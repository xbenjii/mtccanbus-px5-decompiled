package android.microntek.canbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class C0058p extends BroadcastReceiver {
    final /* synthetic */ Ajxserver pg;

    C0058p(Ajxserver ajxserver) {
        this.pg = ajxserver;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("com.microntek.ajx")) {
            this.pg.my.sendEmptyMessage(0);
        } else if (action.equals("com.microntek.sync")) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("syncdata");
            if (byteArrayExtra != null) {
                if (!(this.pg.mu == 5 || this.pg.mu == 55)) {
                    if (this.pg.mu != 69) {
                        return;
                    }
                }
                this.pg.sg(byteArrayExtra);
            }
        }
    }
}
