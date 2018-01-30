package android.microntek.canbus.p001a;

import android.os.Handler;
import android.os.Message;

class dk extends Handler {
    final /* synthetic */ C0027y df;

    dk(C0027y c0027y) {
        this.df = c0027y;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.df.f28s.removeMessages(0);
        this.df.f28s.sendEmptyMessageDelayed(0, 180);
        byte[] bArr = new byte[2];
        bArr[0] = (byte) 38;
        this.df.ej.ob((byte) -112, bArr, 2);
    }
}
