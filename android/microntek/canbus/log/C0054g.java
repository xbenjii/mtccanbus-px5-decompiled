package android.microntek.canbus.log;

import android.content.Intent;
import android.os.UserHandle;
import android.view.View;
import android.view.View.OnClickListener;

class C0054g implements OnClickListener {
    final /* synthetic */ CanBusLogActivity hx;

    C0054g(CanBusLogActivity canBusLogActivity) {
        this.hx = canBusLogActivity;
    }

    public void onClick(View view) {
        this.hx.hk = true;
        byte[] bArr = new byte[]{(byte) 46, (byte) -1, (byte) 1, (byte) 0, (byte) -1};
        Intent intent = new Intent("com.microntek.canbus.send");
        intent.putExtra("com.microntek.sendkey", bArr);
        this.hx.sendBroadcastAsUser(intent, UserHandle.CURRENT_OR_SELF);
    }
}
