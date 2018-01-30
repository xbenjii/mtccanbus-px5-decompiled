package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class cx extends C0001b {
    public cx(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private int kg(int i) {
        int i2 = 10;
        int i3 = i & 255;
        if (i3 > 150 || i3 == 0) {
            i2 = 0;
        } else if (i3 >= 10) {
            i2 = i3;
        }
        return i2 / 10;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 18:
                if (bArr[i + 2] >= (byte) 10) {
                    kf(new byte[]{bArr[i + 5]});
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] >= (byte) 12) {
                    for (int i3 = 0; i3 < 12; i3++) {
                        this.ep[i3] = bArr[(i + 3) + i3] & 255;
                        this.ep[i3] = kg(this.ep[i3]);
                    }
                    mb();
                    this.ek.max = 15;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = this.ep[0];
                    this.ek.b2 = this.ep[1];
                    this.ek.b3 = this.ep[2];
                    this.ek.b4 = this.ep[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
    }

    void kf(byte[] bArr) {
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
}
