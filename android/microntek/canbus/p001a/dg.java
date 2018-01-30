package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class dg extends C0001b {
    public dg(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 66;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case (byte) -92:
                if (bArr[i + 2] == (byte) 11) {
                    bArr2 = new byte[13];
                    bArr2[0] = (byte) -92;
                    bArr2[1] = (byte) 11;
                    while (i3 < 11) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.om(bArr2);
                    return;
                }
                return;
            case (byte) -79:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[4];
                    bArr2[0] = (byte) -79;
                    bArr2[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.om(bArr2);
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
        this.ej.ok((byte) -31, new byte[]{(byte) 10, (byte) 32, (byte) 65, (byte) 50, (byte) 68, (byte) 80, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo38f() {
        this.ej.ok((byte) -31, new byte[]{(byte) 8, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo39g() {
        this.ej.ok((byte) -31, new byte[]{(byte) 12, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo40h(String str, int i) {
        this.ej.ok((byte) -31, new byte[]{(byte) 10, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo41i() {
        this.ej.ok((byte) -31, new byte[]{(byte) 8, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 7, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (i3 == 0) {
            bArr[0] = (byte) 6;
        }
        bArr[5] = (byte) (((i2 / 60) / 10) + 48);
        bArr[6] = (byte) (((i2 % 60) % 10) + 48);
        bArr[8] = (byte) (((i2 / 60) / 10) + 48);
        bArr[9] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.ok((byte) -31, bArr, 13);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        bArr[0] = (byte) 13;
        bArr[1] = (byte) (((i2 / 100) % 10) + 48);
        bArr[2] = (byte) (((i2 / 10) % 10) + 48);
        bArr[3] = (byte) ((i2 % 10) + 48);
        int i4 = i3 / 1000;
        bArr[5] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        bArr[6] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        bArr[7] = (byte) (((i4 % 60) / 10) + 48);
        bArr[8] = (byte) (((i4 % 60) % 10) + 48);
        this.ej.ok((byte) -31, bArr, 13);
    }

    public void mo46n() {
        this.ej.ok((byte) -31, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) 1;
            if (i > 9999) {
                bArr[4] = (byte) ((i / 10000) + 48);
                bArr[5] = (byte) (((i / 1000) % 10) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 1000) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
            }
            bArr[9] = (byte) 0;
            bArr[10] = (byte) 0;
            bArr[11] = (byte) 0;
            bArr[12] = (byte) 0;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) 4;
            if (i > 999) {
                bArr[4] = (byte) ((i / 1000) + 48);
                bArr[5] = (byte) (((i / 100) % 10) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            } else {
                bArr[4] = (byte) ((i / 100) + 48);
                bArr[5] = (byte) (((i / 10) % 10) + 48);
                bArr[6] = (byte) ((i % 10) + 48);
                bArr[7] = (byte) 0;
            }
            bArr[8] = (byte) 0;
            bArr[9] = (byte) 0;
            bArr[10] = (byte) 0;
            bArr[11] = (byte) 0;
            bArr[12] = (byte) 0;
        }
        this.ej.ok((byte) -31, bArr, 13);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo49q() {
        /*
        r7 = this;
        r0 = 12;
        r6 = 3;
        r5 = 0;
        r2 = new android.text.format.Time;
        r2.<init>();
        r2.setToNow();
        r1 = r2.hour;
        r2 = r2.minute;
        r3 = r7.el;
        r3 = android.text.format.DateFormat.is24HourFormat(r3);
        r4 = new byte[r6];
        if (r3 != 0) goto L_0x002f;
    L_0x001a:
        if (r1 <= r0) goto L_0x001e;
    L_0x001c:
        r1 = r1 + -12;
    L_0x001e:
        if (r1 != 0) goto L_0x002f;
    L_0x0020:
        r0 = (byte) r0;
        r4[r5] = r0;
        r0 = (byte) r2;
        r1 = 1;
        r4[r1] = r0;
        r0 = r7.ej;
        r1 = -75;
        r0.ok(r1, r4, r6);
        return;
    L_0x002f:
        r0 = r1;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.microntek.canbus.a.dg.q():void");
    }

    public void mo50r(int i) {
    }
}
