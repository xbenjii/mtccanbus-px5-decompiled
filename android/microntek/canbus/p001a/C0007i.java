package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class C0007i extends C0001b {
    public C0007i(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 16;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) -90:
                if (bArr[i + 2] != (byte) 8) {
                    return;
                }
                return;
            case (byte) 114:
                if (bArr[i + 2] != (byte) 14) {
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
        this.ej.ok((byte) -46, new byte[]{(byte) 10, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo38f() {
        this.ej.ok((byte) -46, new byte[]{(byte) 8, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo39g() {
        this.ej.ok((byte) -46, new byte[]{(byte) 12, (byte) 65, (byte) 86, (byte) 73, (byte) 78, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo40h(String str, int i) {
        this.ej.ok((byte) -46, new byte[]{(byte) 10, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo41i() {
        this.ej.ok((byte) -46, new byte[]{(byte) 8, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 7, (byte) 68, (byte) 86, (byte) 68, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        bArr[5] = (byte) (((i2 / 60) / 10) + 48);
        bArr[6] = (byte) (((i2 % 60) % 10) + 48);
        bArr[8] = (byte) (((i2 / 60) / 10) + 48);
        bArr[9] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.ok((byte) -46, bArr, 13);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ok((byte) -46, new byte[]{(byte) 11, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo44l() {
        this.ej.ok((byte) -46, new byte[]{(byte) 14, (byte) 77, (byte) 101, (byte) 100, (byte) 105, (byte) 97, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 14, (byte) 77, (byte) 85, (byte) 83, (byte) 73, (byte) 67, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32, (byte) 32};
        bArr[7] = (byte) (((((i3 / 1000) / 60) % 60) / 10) + 48);
        bArr[8] = (byte) (((((i3 / 1000) / 60) % 60) % 10) + 48);
        bArr[10] = (byte) ((((i3 / 1000) % 60) / 10) + 48);
        bArr[11] = (byte) ((((i3 / 1000) % 60) % 10) + 48);
        this.ej.ok((byte) -46, bArr, 13);
    }

    public void mo46n() {
        this.ej.ok((byte) -46, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 1, (byte) 45, (byte) 1, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) 48;
            bArr[2] = (byte) (b2 + 49);
            if (this.eh == 26) {
                bArr[1] = (byte) 32;
                bArr[2] = (byte) 32;
            }
            if (i > 9999) {
                bArr[4] = (byte) ((i / 10000) + 48);
                bArr[5] = (byte) (((i / 1000) % 10) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
                bArr[9] = (byte) ((i % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 1000) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
                bArr[9] = (byte) ((i % 10) + 48);
            }
            bArr[10] = (byte) 77;
            bArr[11] = (byte) 72;
            bArr[12] = (byte) 90;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) (b - 2);
            bArr[1] = (byte) 48;
            bArr[2] = (byte) (b2 + 49);
            if (this.eh == 26) {
                bArr[0] = (byte) (b + 1);
                bArr[1] = (byte) 32;
                bArr[2] = (byte) 32;
            }
            if (i > 999) {
                bArr[4] = (byte) ((i / 1000) + 48);
                bArr[5] = (byte) (((i / 100) % 10) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 100) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            }
            bArr[9] = (byte) 75;
            bArr[10] = (byte) 72;
            bArr[11] = (byte) 90;
        }
        this.ej.ok((byte) -46, bArr, 13);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
