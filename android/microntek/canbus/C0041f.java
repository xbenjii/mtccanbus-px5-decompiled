package android.microntek.canbus;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class C0041f extends Handler {
    final /* synthetic */ CanBusServer this$0;

    C0041f(CanBusServer canBusServer) {
        this.this$0 = canBusServer;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        String str = (String) message.obj;
        Bundle data = message.getData();
        if (str.equals("CarEvent")) {
            if (data.getString("type").equals("screen_onoff") && this.this$0.kl != null) {
                this.this$0.kl.jo(data.getBoolean("value") ? 1 : 0);
            }
        } else if (str.equals("KeyDown") && data.getString("type").equals("key")) {
            this.this$0.ow(data.getInt("value"));
        }
    }
}
