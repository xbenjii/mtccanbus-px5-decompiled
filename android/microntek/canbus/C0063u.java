package android.microntek.canbus;

import android.os.Handler;
import android.os.Message;

class C0063u extends Handler {
    final /* synthetic */ AirServer pl;

    C0063u(AirServer airServer) {
        this.pl = airServer;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            this.pl.stopSelf();
        }
    }
}
