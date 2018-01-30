package android.microntek.canbus;

import android.os.Handler;
import android.os.Message;

class C0057o extends Handler {
    final /* synthetic */ Ajxserver pf;

    C0057o(Ajxserver ajxserver) {
        this.pf = ajxserver;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            this.pf.stopSelf();
        }
    }
}
