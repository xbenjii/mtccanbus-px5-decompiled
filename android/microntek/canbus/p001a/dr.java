package android.microntek.canbus.p001a;

import android.os.Handler;
import android.os.Message;

class dr extends Handler {
    final /* synthetic */ ch dn;

    dr(ch chVar) {
        this.dn = chVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.dn.ej.ok((byte) -3, new byte[]{(byte) 1, (byte) 0}, 2);
    }
}
