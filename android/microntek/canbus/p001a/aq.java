package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class aq extends C0001b {
    public aq(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 74;
    }

    private void du(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4 = bArr[i + 2] & 255;
        byte[] bArr2 = new byte[(i4 + 2)];
        bArr2[0] = bArr[i + 1];
        bArr2[1] = (byte) i4;
        while (i3 < i4) {
            bArr2[i3 + 2] = bArr[(i + 3) + i3];
            i3++;
        }
        this.ej.oj(bArr2);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 36:
                if ((bArr[i + 3] & 1) != 0) {
                    dt(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 37:
                du(bArr, i, i2);
                return;
            case (byte) 38:
                du(bArr, i, i2);
                return;
            case (byte) 39:
                du(bArr, i, i2);
                return;
            default:
                return;
        }
    }

    public void mo36d() {
    }

    void dt(byte[] bArr) {
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
