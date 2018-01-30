package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class di extends C0001b {
    private String[] db;

    public di(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.db = new String[]{"", "P", "R", "N", "D", "E", "S", " ", " ", " "};
        this.eh = 45;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgWidth /*0*/:
                byte[] bArr2;
                if (bArr[i + 2] == (byte) 9) {
                    bArr2 = new byte[9];
                    while (i3 < 9) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    md(this.db[bArr2[4] & 7] + "   " + (bArr2[5] & 255) + this.el.getString(R.string.bfh));
                    return;
                } else if (bArr[i + 2] == (byte) 39) {
                    bArr2 = new byte[39];
                    while (i3 < 39) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    md(this.db[bArr2[36] & 7] + "   " + (bArr2[5] & 255) + this.el.getString(R.string.bfh));
                    return;
                } else {
                    return;
                }
            case R$styleable.MyButton_imgHeight /*1*/:
                if (bArr[i + 2] >= (byte) 13) {
                    kz(new byte[]{bArr[i + 13]});
                    mg(bArr[i + 3] & 255);
                    md("" + bArr[i + 12]);
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

    void kz(byte[] bArr) {
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
