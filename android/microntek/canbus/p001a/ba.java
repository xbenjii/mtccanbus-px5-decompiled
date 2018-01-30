package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class ba extends C0001b {
    public ba(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 30;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] == (byte) 3) {
                    byte[] bArr2 = new byte[3];
                    while (i3 < 3) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] >= (byte) 1) {
                    i3 = bArr[i + 3] & 255;
                    lz(i3 >= 128 ? i3 - 128 : i3 - 127);
                    return;
                }
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] >= (byte) 1) {
                    er(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oh(1);
    }

    public void mo37e() {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    void er(byte[] bArr) {
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

    public void mo38f() {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo39g() {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo41i() {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -112, new byte[]{(byte) 2}, 1);
        this.ej.ob((byte) -110, new byte[]{(byte) (i & 255), (byte) ((i2 / 60) % 60), (byte) (i2 % 60)}, 3);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo44l() {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo46n() {
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
    }

    public void mo47o(byte b, int i, byte b2) {
        this.ej.ob((byte) -112, new byte[]{(byte) 1}, 1);
        byte[] bArr = new byte[4];
        int i2;
        if (b >= (byte) 0 && b <= (byte) 2) {
            i2 = ((i - 8750) / 10) + 1;
            bArr[0] = (byte) 0;
            bArr[1] = (byte) ((i2 >> 8) & 255);
            bArr[2] = (byte) (i2 & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            i2 = ((i - 522) / 9) + 1;
            bArr[0] = (byte) 1;
            bArr[1] = (byte) ((i2 >> 8) & 255);
            bArr[2] = (byte) (i2 & 255);
        }
        this.ej.ob((byte) -111, bArr, 4);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
