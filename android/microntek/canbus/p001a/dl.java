package android.microntek.canbus.p001a;

import android.os.Handler;
import android.os.Message;

class dl extends Handler {
    final /* synthetic */ af dg;

    dl(af afVar) {
        this.dg = afVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.dg.aa.removeMessages(0);
        this.dg.aa.sendEmptyMessageDelayed(0, 20000);
        this.dg.mo49q();
    }
}
