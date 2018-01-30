package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class C0012g extends C0001b {
    public C0012g(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 38;
    }

    void aq(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) 22:
                if (bArr[i + 2] == (byte) 2) {
                    byte[] bArr2 = new byte[3];
                    bArr2[0] = (byte) 22;
                    for (i3 = 0; i3 < 2; i3++) {
                        bArr2[i3 + 1] = bArr[(i + 3) + i3];
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    aq(new byte[]{(byte) ((((((byte) (bArr[i + 3] & 128)) + ((byte) (bArr[i + 3] & 64))) + ((byte) (bArr[i + 3] & 32))) + ((byte) (bArr[i + 3] & 16))) + ((byte) (bArr[i + 3] & 8)))});
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] == (byte) 2) {
                    String str;
                    i3 = bArr[i + 3] & 255;
                    String str2 = "";
                    if (((byte) (bArr[i + 4] & 1)) == (byte) 1) {
                        if (((byte) (bArr[i + 4] & 2)) == (byte) 2) {
                            i3 = 0 - i3;
                        }
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + " " + this.el.getString(R.string.f_dan);
                    } else {
                        if (((byte) (bArr[i + 4] & 2)) == (byte) 2) {
                            i3 -= 256;
                        }
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + " " + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 38) / 8500;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 57:
                if (bArr[i + 2] == (byte) 1) {
                    this.ej.oj(new byte[]{(byte) 57, bArr[i + 3]});
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
