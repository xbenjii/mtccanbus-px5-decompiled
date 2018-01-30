package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class C0003u extends C0001b {
    private boolean f17q;

    public C0003u(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.f17q = true;
        this.eh = 7;
    }

    private void bm() {
        if (this.f17q) {
            this.f17q = false;
            byte[] bArr = new byte[2];
            bArr[0] = (byte) 2;
            this.ej.ob((byte) -59, bArr, 2);
        }
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void at() {
        this.ej.ob((byte) -112, new byte[]{(byte) 41, (byte) 0}, 2);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        byte[] bArr3;
        int i4;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 11) {
                    bArr2 = new byte[13];
                    bArr2[0] = (byte) 33;
                    bArr2[1] = (byte) 11;
                    while (i3 < 11) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.om(bArr2);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 10;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr3[0];
                    this.ek.b2 = bArr3[1];
                    this.ek.b3 = bArr3[2];
                    this.ek.b4 = bArr3[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 10;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr3[0];
                    this.ek.f2 = bArr3[1];
                    this.ek.f3 = bArr3[2];
                    this.ek.f4 = bArr3[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2) {
                    bArr2 = new byte[2];
                    while (i3 < 2) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    bl(bArr2);
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] >= (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 450;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 5120;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] != (byte) 16) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    void bl(byte[] bArr) {
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

    public void mo36d() {
        bm();
        mo49q();
        if (this.ej.jg == 1) {
            this.ej.oa(1);
            this.ej.oh(1);
        }
    }

    public void mo37e() {
        this.f17q = true;
        this.ej.ob((byte) -64, new byte[]{(byte) 11, (byte) 48}, 2);
    }

    public void mo38f() {
        this.f17q = true;
        byte[] bArr = new byte[]{(byte) 1, (byte) 99, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.ej.ob((byte) -64, new byte[]{(byte) 3, (byte) 48}, 2);
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo39g() {
        this.f17q = true;
        byte[] bArr = new byte[]{(byte) 1, (byte) 90, (byte) 35, (byte) 5};
        this.ej.ob((byte) -64, new byte[]{(byte) 7, (byte) 48}, 2);
        this.ej.ob((byte) -61, bArr, 4);
    }

    public void mo40h(String str, int i) {
        this.f17q = true;
        byte[] bArr = new byte[]{(byte) 1, (byte) 99, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.ej.ob((byte) -64, new byte[]{(byte) 5, (byte) 48}, 2);
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo41i() {
        this.f17q = true;
        byte[] bArr = new byte[]{(byte) 1, (byte) 99, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        this.ej.ob((byte) -64, new byte[]{(byte) 10, (byte) 48}, 2);
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo42j(int i, int i2, int i3) {
        this.f17q = true;
        this.ej.ob((byte) -64, new byte[]{(byte) 2, (byte) 16}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) (i & 255);
        bArr[4] = (byte) ((i2 / 60) % 60);
        bArr[5] = (byte) (i2 % 60);
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.f17q = true;
        this.ej.ob((byte) -64, new byte[]{(byte) 6, (byte) 18}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((i >> 8) & 255);
        bArr[2] = (byte) (i2 & 255);
        bArr[3] = (byte) ((i2 >> 8) & 255);
        bArr[4] = b;
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 8, (byte) 17}, 2);
        this.ej.ob((byte) -61, new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) (((i3 / 1000) / 60) % 60), (byte) ((i3 / 1000) % 60)}, 6);
        bm();
    }

    public void mo46n() {
        this.f17q = true;
        this.ej.ob((byte) -64, new byte[]{(byte) 0, (byte) 0}, 2);
    }

    public void mo47o(byte b, int i, byte b2) {
        this.f17q = true;
        this.ej.ob((byte) -64, new byte[]{(byte) 1, (byte) 1}, 2);
        byte[] bArr = new byte[4];
        if (b == (byte) 0) {
            bArr[0] = (byte) 1;
        } else if (b == (byte) 1) {
            bArr[0] = (byte) 2;
        } else if (b == (byte) 2) {
            bArr[0] = (byte) 0;
        } else {
            bArr[0] = (byte) 16;
        }
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (b2 + 1);
        this.ej.ob((byte) -62, bArr, 4);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo49q() {
        /*
        r8 = this;
        r0 = 12;
        r3 = 4;
        r7 = 3;
        r6 = 1;
        r5 = 0;
        r1 = r8.ej;
        r1 = r1.jh;
        if (r1 == 0) goto L_0x0043;
    L_0x000c:
        r1 = r8.ej;
        r1 = r1.jh;
        r2 = "HH";
        r1 = r1.startsWith(r2);
        if (r1 == 0) goto L_0x0043;
    L_0x0019:
        r2 = new android.text.format.Time;
        r2.<init>();
        r2.setToNow();
        r1 = r2.hour;
        r2 = r2.minute;
        r3 = r8.el;
        r3 = android.text.format.DateFormat.is24HourFormat(r3);
        r4 = new byte[r7];
        if (r3 != 0) goto L_0x007b;
    L_0x002f:
        if (r1 <= r0) goto L_0x0033;
    L_0x0031:
        r1 = r1 + -12;
    L_0x0033:
        if (r1 != 0) goto L_0x007b;
    L_0x0035:
        r0 = (byte) r0;
        r4[r5] = r0;
        r0 = (byte) r2;
        r4[r6] = r0;
        r0 = r8.ej;
        r1 = -75;
        r0.ok(r1, r4, r7);
    L_0x0042:
        return;
    L_0x0043:
        r0 = new android.text.format.Time;
        r0.<init>();
        r0.setToNow();
        r1 = r0.hour;
        r8.en = r1;
        r0 = r0.minute;
        r8.eo = r0;
        r0 = r8.el;
        r0 = android.text.format.DateFormat.is24HourFormat(r0);
        r1 = new byte[r3];
        r1[r5] = r5;
        if (r0 == 0) goto L_0x0078;
    L_0x005f:
        r1[r6] = r5;
    L_0x0061:
        r0 = r8.en;
        r0 = r0 & 127;
        r0 = (byte) r0;
        r2 = 2;
        r1[r2] = r0;
        r0 = r8.eo;
        r0 = r0 & 255;
        r0 = (byte) r0;
        r1[r7] = r0;
        r0 = r8.ej;
        r2 = -56;
        r0.ob(r2, r1, r3);
        goto L_0x0042;
    L_0x0078:
        r1[r6] = r6;
        goto L_0x0061;
    L_0x007b:
        r0 = r1;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.microntek.canbus.a.u.q():void");
    }

    public void mo50r(int i) {
    }
}
