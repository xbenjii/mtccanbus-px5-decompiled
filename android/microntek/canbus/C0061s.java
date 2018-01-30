package android.microntek.canbus;

import android.os.Handler;
import android.os.Message;

class C0061s extends Handler {
    final /* synthetic */ RadarServer pj;

    C0061s(RadarServer radarServer) {
        this.pj = radarServer;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            this.pj.stopSelf();
        }
    }
}
