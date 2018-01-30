package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class C0017n extends C0001b {
    public C0017n(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 14;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        int i4;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[1];
                    this.ek.b3 = bArr2[2];
                    this.ek.b4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ek.f4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2 && ((byte) (bArr[i + 3] & 1)) == (byte) 1) {
                    bArr3 = new byte[2];
                    while (i3 < 2) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    be(bArr3);
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] == (byte) 3) {
                    bArr3 = new byte[5];
                    bArr3[0] = (byte) 50;
                    bArr3[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 51:
                if (bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[4];
                    bArr3[0] = (byte) 51;
                    bArr3[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    void be(byte[] bArr) {
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
