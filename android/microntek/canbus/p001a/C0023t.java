package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class C0023t extends C0001b {
    public C0023t(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 89;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 13:
                if (bArr[i + 2] == (byte) 1) {
                    int i3 = (((bArr[i + 3] & 255) - 127) * 30) / 127;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 15:
                if (bArr[i + 2] == (byte) 1) {
                    Intent intent2 = new Intent("com.microntek.canbus.speed");
                    intent2.putExtra("speed", "" + (bArr[i + 3] & 255));
                    this.el.sendBroadcast(intent2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oh(1);
    }
}
