package android.microntek.canbus.p001a;

import android.os.Handler;
import android.os.Message;

class dn extends Handler {
    final /* synthetic */ bm di;

    dn(bm bmVar) {
        this.di = bmVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.di.bb.removeMessages(0);
        this.di.bd = true;
    }
}
