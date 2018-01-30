package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class cu extends C0001b {
    public cu(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 23;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 1) {
                    int i3 = bArr[i + 3] & 255;
                    if (i3 >= 128) {
                        i3 -= 128;
                    }
                    lz(0 - ((i3 * 30) / 50));
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
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 9;
        bArr[6] = (byte) 0;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo38f() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 0;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo39g() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 7;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 9;
        bArr[6] = (byte) 0;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo41i() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 10;
        bArr[1] = (byte) -1;
        bArr[2] = (byte) -1;
        bArr[3] = (byte) -1;
        bArr[4] = (byte) -1;
        bArr[5] = (byte) -1;
        bArr[6] = (byte) -1;
        bArr[7] = (byte) -1;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 2;
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) (i & 255);
        bArr[7] = (byte) ((i2 / 60) % 60);
        bArr[8] = (byte) (i2 % 60);
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 5;
        bArr[3] = (byte) ((i2 >> 8) & 255);
        bArr[4] = (byte) (i2 & 255);
        bArr[7] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[8] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo44l() {
        this.ej.ob((byte) -126, new byte[]{(byte) 3, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1}, 9);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 3;
        bArr[3] = (byte) ((i2 >> 8) & 255);
        bArr[4] = (byte) (i2 & 255);
        bArr[7] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[8] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo46n() {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 0;
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[9];
        bArr[0] = (byte) 1;
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[2] = (byte) (b + 17);
            bArr[4] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 3 && b <= (byte) 4) {
            bArr[2] = (byte) 33;
            bArr[4] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 4) {
            bArr[2] = (byte) 34;
            bArr[4] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -126, bArr, 9);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
