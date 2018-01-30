package android.microntek.canbus.p001a;

import android.os.Handler;
import android.os.Message;

class dm extends Handler {
    final /* synthetic */ bg dh;

    dm(bg bgVar) {
        this.dh = bgVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.dh.ax <= 5) {
            bg bgVar = this.dh;
            bgVar.ax = bgVar.ax + 1;
            this.dh.av.removeMessages(0);
            this.dh.av.sendEmptyMessageDelayed(0, 1000);
            this.dh.ej.ol((byte) -14, new byte[]{(byte) 7, (byte) 1}, 2);
            return;
        }
        this.dh.av.removeMessages(0);
    }
}
