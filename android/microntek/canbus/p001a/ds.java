package android.microntek.canbus.p001a;

import android.os.Handler;
import android.os.Message;

class ds extends Handler {
    final /* synthetic */ cj f21do;

    ds(cj cjVar) {
        this.f21do = cjVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.f21do.cj.removeMessages(0);
        this.f21do.cl = true;
    }
}
