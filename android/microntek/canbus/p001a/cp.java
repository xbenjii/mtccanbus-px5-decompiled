package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class cp extends C0001b {
    public cp(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 19;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgWidth /*0*/:
                if (bArr[i + 2] != (byte) 2) {
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] == (byte) 3) {
                    jr(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] != (byte) 5) {
                    return;
                }
                return;
            case (byte) 11:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[4];
                    bArr2[0] = (byte) 11;
                    bArr2[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 31:
                if (bArr[i + 2] == (byte) 3) {
                    i3 = ((((bArr[i + 4] & 255) * 256) + (bArr[i + 5] & 255)) * 30) / 450;
                    if (bArr[i + 3] == (byte) 0) {
                        i3 = 0 - i3;
                    }
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 8) {
                    byte[] bArr3 = new byte[8];
                    for (int i4 = 0; i4 < 8; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = bArr3[2];
                    this.ek.b2 = bArr3[6];
                    this.ek.b3 = bArr3[3];
                    this.ek.front_cnt = 3;
                    this.ek.f1 = bArr3[0];
                    this.ek.f2 = bArr3[4];
                    this.ek.f3 = bArr3[1];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 37:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[4];
                    bArr2[0] = (byte) 31;
                    bArr2[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 54:
                if (bArr[i + 2] == (byte) 7) {
                    bArr2 = new byte[9];
                    bArr2[0] = (byte) 54;
                    bArr2[1] = (byte) 7;
                    while (i3 < 7) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 66:
                if (bArr[i + 2] == (byte) 31) {
                    bArr2 = new byte[33];
                    bArr2[0] = (byte) 66;
                    bArr2[1] = (byte) 31;
                    while (i3 < 31) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 113:
                if (bArr[i + 2] == (byte) 9) {
                    bArr2 = new byte[11];
                    bArr2[0] = (byte) 113;
                    bArr2[1] = (byte) 9;
                    while (i3 < 9) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
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

    void jr(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
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
