package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class cy extends C0001b {
    public cy(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) -63:
                if (bArr[i + 2] != (byte) 14) {
                    return;
                }
                return;
            case (byte) 17:
                if (bArr[i + 2] != (byte) 14) {
                    return;
                }
                return;
            case (byte) 19:
                if (bArr[i + 2] != (byte) 14) {
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] == (byte) 12) {
                    int[] iArr = new int[12];
                    for (int i3 = 0; i3 < 12; i3++) {
                        iArr[i3] = bArr[(i + 3) + i3] & 255;
                        if (iArr[i3] >= 150) {
                            iArr[i3] = 0;
                        } else {
                            iArr[i3] = (iArr[i3] * 15) / 150;
                        }
                    }
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ek.max = 15;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    this.ek.front_cnt = 4;
                    this.ek.f1 = iArr[4];
                    this.ek.f2 = iArr[5];
                    this.ek.f3 = iArr[6];
                    this.ek.f4 = iArr[7];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] != (byte) 14) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
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
