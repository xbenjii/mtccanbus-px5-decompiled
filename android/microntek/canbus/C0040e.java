package android.microntek.canbus;

import android.util.Log;

class C0040e implements Runnable {
    final /* synthetic */ CanBusServer this$0;

    C0040e(CanBusServer canBusServer) {
        this.this$0 = canBusServer;
    }

    public void run() {
        int i = 0;
        while (i >= 0) {
            try {
                this.this$0.kq.clear();
                i = this.this$0.kv.read(this.this$0.kq);
                if (i > 0) {
                    Object obj = new byte[i];
                    this.this$0.kq.get(obj, 0, i);
                    this.this$0.kp.sendMessage(this.this$0.kp.obtainMessage(5, i, i, obj));
                }
            } catch (Exception e) {
                Log.i("CanBusServer", "mtc >>> read canbus data Exception !");
                if (this.this$0.kv == null) {
                    return;
                }
            }
        }
    }
}
