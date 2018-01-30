package android.microntek.canbus.p001a;

import android.os.Handler;
import android.os.Message;

class dv extends Handler {
    final /* synthetic */ cz dr;

    dv(cz czVar) {
        this.dr = czVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.dr.da <= 5) {
            cz czVar = this.dr;
            czVar.da = czVar.da + 1;
            this.dr.cz.removeMessages(0);
            this.dr.cz.sendEmptyMessageDelayed(0, 1000);
            this.dr.ej.ol((byte) -14, new byte[]{(byte) 7, (byte) 1}, 2);
            return;
        }
        this.dr.cz.removeMessages(0);
    }
}
