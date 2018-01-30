package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class al extends C0001b {
    public al(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 51;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        int i3;
        switch (bArr[i + 1]) {
            case (byte) 38:
                if (bArr[i + 2] >= (byte) 4) {
                    bArr2 = new byte[4];
                    for (i3 = 0; i3 < 4; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
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
            case (byte) 39:
                if (bArr[i + 2] >= (byte) 4) {
                    bArr2 = new byte[4];
                    for (i3 = 0; i3 < 4; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
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
            case (byte) 40:
                if (bArr[i + 2] >= (byte) 2) {
                    dh(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 35) / 9829;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
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

    void dh(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo37e() {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 11;
        bArr[1] = (byte) 18;
        this.ej.ob((byte) -64, bArr, 6);
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        this.ej.ob((byte) -64, new byte[]{(byte) 7}, 1);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[6];
        switch (i) {
        }
        mo39g();
    }

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        mo39g();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 8;
        bArr[1] = (byte) 18;
        this.ej.ob((byte) -64, bArr, 6);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[3], 3);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 1;
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[1] = (byte) (b + 1);
            bArr[2] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
            bArr[4] = (byte) (b2 + 1);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[1] = (byte) ((b - 2) + 16);
            bArr[2] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
            bArr[4] = (byte) (b2 + 1);
        }
        this.ej.ob((byte) -64, bArr, 5);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
