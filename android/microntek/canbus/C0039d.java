package android.microntek.canbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

class C0039d extends BroadcastReceiver {
    final /* synthetic */ CanBusServer this$0;

    C0039d(CanBusServer canBusServer) {
        this.this$0 = canBusServer;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.TIME_SET") || action.equals("android.intent.action.TIME_TICK")) {
            this.this$0.kp.removeMessages(2);
            this.this$0.kp.sendEmptyMessageDelayed(2, 10);
        } else if (action.equals("com.microntek.VOLUME_CHANGED")) {
            int intExtra = intent.getIntExtra("volume", 15);
            int intExtra2 = intent.getIntExtra("curvolume", 15);
            Message obtainMessage = this.this$0.kp.obtainMessage();
            obtainMessage.what = 3;
            obtainMessage.arg1 = intExtra;
            obtainMessage.arg2 = intExtra2;
            this.this$0.kp.removeMessages(3);
            this.this$0.kp.sendMessageDelayed(obtainMessage, 10);
        } else if (action.equals("android.intent.action.LOCALE_CHANGED")) {
            if (this.this$0.kl != null) {
                this.this$0.kl.mo48p();
            }
        } else if (action.equals("com.microntek.irkeyDown")) {
            this.this$0.ow(intent.getIntExtra("keyCode", -1));
        }
    }
}
