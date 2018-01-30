package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class bo extends C0001b {
    public bo(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc /*2*/:
                if (bArr[i + 2] >= (byte) 3) {
                    gf(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 7:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = ((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    lz((i3 * 30) / 4608);
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] >= (byte) 7) {
                    mb();
                    int[] iArr = new int[7];
                    for (i3 = 0; i3 < 7; i3++) {
                        iArr[i3] = bArr[(i + 3) + i3] & 255;
                        if (iArr[i3] > 4) {
                            iArr[i3] = 0;
                        }
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = iArr[4];
                    this.ek.b2 = iArr[5];
                    this.ek.b3 = iArr[6];
                    this.ek.front_cnt = 3;
                    this.ek.f1 = iArr[1];
                    this.ek.f2 = iArr[2];
                    this.ek.f3 = iArr[3];
                    if (me() != 1) {
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            default:
                ly(bArr, i, i2);
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

    void gf(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
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
