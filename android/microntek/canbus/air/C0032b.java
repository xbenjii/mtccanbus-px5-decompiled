package android.microntek.canbus.air;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.microntek.canbus.aidl.CanBusServiceInf.Stub;
import android.os.IBinder;

class C0032b implements ServiceConnection {
    final /* synthetic */ AirControlActivity this$0;

    C0032b(AirControlActivity airControlActivity) {
        this.this$0 = airControlActivity;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.this$0.he = Stub.asInterface(iBinder);
            if (this.this$0.he != null) {
                this.this$0.he.registerCallback(this.this$0.hb);
            }
            this.this$0.hd.removeMessages(0);
            this.this$0.hd.sendEmptyMessageDelayed(0, 0);
            this.this$0.setAriFloatViewShow(false);
            this.this$0.sendRequestAirCMD();
        } catch (Exception e) {
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.this$0.he = null;
    }
}
