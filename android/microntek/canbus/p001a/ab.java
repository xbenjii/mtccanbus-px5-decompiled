package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import java.util.Locale;

public class ab extends C0001b {
    public ab(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 31;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 65:
                if (bArr[i + 2] == (byte) 1) {
                    float f = (float) (bArr[i + 3] & 126);
                    String str = "";
                    String str2 = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
    }

    public void mo37e() {
    }

    public void mo38f() {
    }

    public void mo39g() {
    }

    public void mo40h(String str, int i) {
    }

    public void mo41i() {
    }

    public void mo42j(int i, int i2, int i3) {
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
    }

    public void mo44l() {
    }

    public void mo45m(int i, int i2, int i3) {
    }

    public void mo46n() {
    }

    public void mo47o(byte b, int i, byte b2) {
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
