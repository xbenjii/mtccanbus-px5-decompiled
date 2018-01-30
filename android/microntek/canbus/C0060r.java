package android.microntek.canbus;

import android.os.Handler;
import android.os.Message;

class C0060r extends Handler {
    final /* synthetic */ ParkServer pi;

    C0060r(ParkServer parkServer) {
        this.pi = parkServer;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            this.pi.stopSelf();
        } else if (1 == message.what) {
            this.pi.sy(this.pi.nj);
            this.pi.nn.removeMessages(1);
            this.pi.nn.sendEmptyMessageDelayed(1, 500);
        }
    }
}
