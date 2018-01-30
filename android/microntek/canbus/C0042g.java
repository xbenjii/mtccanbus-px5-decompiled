package android.microntek.canbus;

import android.os.Handler;
import android.os.Message;

class C0042g extends Handler {
    final /* synthetic */ DoorServer ow;

    C0042g(DoorServer doorServer) {
        this.ow = doorServer;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            this.ow.stopSelf();
        } else if (1 == message.what) {
            if (this.ow.ll.getVisibility() == 0) {
                this.ow.ll.setVisibility(8);
                this.ow.lp.setVisibility(0);
            } else {
                this.ow.ll.setVisibility(0);
                this.ow.lp.setVisibility(8);
            }
            this.ow.lj.sendEmptyMessageDelayed(1, 5000);
        }
    }
}
