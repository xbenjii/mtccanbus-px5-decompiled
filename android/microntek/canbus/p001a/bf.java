package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class bf extends C0001b {
    public bf(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 46;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        switch (bArr[i + 1]) {
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    byte[] bArr2 = new byte[2];
                    for (i3 = 0; i3 < 2; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    if ((bArr2[0] & 1) != 0) {
                        fb(bArr2);
                        return;
                    }
                    return;
                }
                break;
            case (byte) 37:
                if (bArr[i + 2] != (byte) 2) {
                    return;
                }
                return;
            case (byte) 41:
                break;
            default:
                return;
        }
        if (bArr[i + 2] == (byte) 2) {
            i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
            if (i3 >= 32768) {
                i3 -= 65536;
            }
            i3 = (i3 * 35) / 1000;
            Intent intent = new Intent("com.microntek.canbusbackview");
            intent.putExtra("canbustype", this.eh);
            intent.putExtra("lfribackview", i3);
            this.el.sendBroadcast(intent);
        }
    }

    public void mo36d() {
        this.ej.oh(1);
    }

    public void mo37e() {
        this.ej.ob((byte) -64, new byte[8], 8);
    }

    public void mo38f() {
        mo39g();
    }

    void fb(byte[] bArr) {
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

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -64, new byte[8], 8);
    }

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        mo39g();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo39g();
    }

    public void mo44l() {
        mo39g();
    }

    public void mo45m(int i, int i2, int i3) {
        mo39g();
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[8], 8);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 1;
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[2] = (byte) (b + 1);
            bArr[3] = (byte) (i & 255);
            bArr[4] = (byte) ((i >> 8) & 255);
        }
        if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[2] = (byte) (b + 14);
            bArr[3] = (byte) (i & 255);
            bArr[4] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
        this.ej.ob((byte) -124, new byte[]{(byte) 2, (byte) (((i * 38) / 30) & 255)}, 2);
    }
}
