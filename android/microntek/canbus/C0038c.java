package android.microntek.canbus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.p000c.C0001b;
import android.text.TextUtils;

class C0038c extends BroadcastReceiver {
    final /* synthetic */ CanBusServer this$0;

    C0038c(CanBusServer canBusServer) {
        this.this$0 = canBusServer;
    }

    public void onReceive(Context context, Intent intent) {
        int i = 1;
        String action = intent.getAction();
        if (action.equals("com.microntek.canbusdisplay")) {
            this.this$0.ou(intent);
        } else if (action.equals("com.microntek.canbus.send")) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("com.microntek.sendkey");
            if (byteArrayExtra != null && byteArrayExtra.length > 4) {
                this.this$0.oo(byteArrayExtra);
            }
        } else if (action.equals("com.microntek.bt.report")) {
            if (intent.hasExtra("connect_state")) {
                int intExtra = intent.getIntExtra("connect_state", 0);
                if (intExtra == 2 || intExtra == 3 || intExtra == 5) {
                    this.this$0.kk = true;
                    this.this$0.ks = intent.getStringExtra("phone_number");
                    if (TextUtils.isEmpty(this.this$0.ks)) {
                        this.this$0.ks = "00000000000";
                    }
                    if (this.this$0.kl != null) {
                        this.this$0.kl.mo40h(this.this$0.ks, 1);
                    }
                    if (this.this$0.ku != null) {
                        this.this$0.ku.mo40h(this.this$0.ks, 1);
                    }
                } else if (this.this$0.kk) {
                    this.this$0.kk = false;
                    if (this.this$0.kl != null) {
                        this.this$0.kl.mo40h(this.this$0.ks, 2);
                    }
                    if (this.this$0.ku != null) {
                        this.this$0.ku.mo40h(this.this$0.ks, 2);
                    }
                    if (this.this$0.ko != null) {
                        this.this$0.kt = -1;
                        this.this$0.ou(this.this$0.ko);
                    } else {
                        if (this.this$0.kl != null) {
                            this.this$0.kl.mo46n();
                        }
                        if (this.this$0.ku != null) {
                            this.this$0.ku.mo46n();
                        }
                    }
                }
            }
        } else if (action.equals("com.microntek.screenOnOff")) {
            action = intent.getStringExtra("state");
            if (!(this.this$0.kl == null || action == null)) {
                C0001b pl = this.this$0.kl;
                if (!action.equals("true")) {
                    i = 0;
                }
                pl.jo(i);
            }
        }
    }
}
