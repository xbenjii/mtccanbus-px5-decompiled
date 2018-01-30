package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class cf extends C0001b {
    public cf(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 39;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] == (byte) 5) {
                    byte[] bArr2 = new byte[5];
                    while (i3 < 5) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 6) {
                    byte[] bArr3 = new byte[6];
                    for (int i4 = 0; i4 < 6; i4++) {
                        byte b = bArr[(i + 3) + i4] == (byte) 5 ? (byte) 2 : bArr[(i + 3) + i4] == (byte) 4 ? (byte) 4 : bArr[(i + 3) + i4] == (byte) 3 ? (byte) 6 : bArr[(i + 3) + i4] == (byte) 2 ? (byte) 8 : bArr[(i + 3) + i4] == (byte) 1 ? (byte) 10 : (byte) 0;
                        bArr3[i4] = b;
                        this.ek.max = 10;
                        this.ek.back_cnt = 3;
                        this.ek.b1 = bArr3[0];
                        this.ek.b2 = bArr3[1];
                        this.ek.b3 = bArr3[2];
                        this.ek.front_cnt = 3;
                        this.ek.f1 = bArr3[3];
                        this.ek.f2 = bArr3[4];
                        this.ek.f3 = bArr3[5];
                        this.ej.oe(this.ek);
                    }
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 1) {
                    int i5 = bArr[i + 3] & 255;
                    if (i5 >= 128) {
                        i5 -= 256;
                    }
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i5);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
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
