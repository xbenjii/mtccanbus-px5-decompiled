package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class bn extends C0001b {
    public bn(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 9;
    }

    private void ge() {
        this.ej.on((byte) 4, new byte[]{(byte) 0}, 1);
    }

    public void ak(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 86, (byte) 73, (byte) 68, (byte) 69, (byte) 79, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32};
        bArr[6] = (byte) (((((i3 / 1000) / 60) % 60) / 10) + 48);
        bArr[7] = (byte) (((((i3 / 1000) / 60) % 60) % 10) + 48);
        bArr[9] = (byte) ((((i3 / 1000) % 60) / 10) + 48);
        bArr[10] = (byte) ((((i3 / 1000) % 60) % 10) + 48);
        this.ej.on((byte) 0, bArr, 11);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
    }

    public void bu(byte[] bArr) {
        ge();
        this.ej.oo(bArr);
    }

    public void mo36d() {
        mo46n();
    }

    public void mo37e() {
        this.ej.on((byte) 0, new byte[]{(byte) 65, (byte) 50, (byte) 68, (byte) 80, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 9);
    }

    public void mo38f() {
        this.ej.on((byte) 0, new byte[]{(byte) 84, (byte) 86}, 2);
    }

    public void mo39g() {
        this.ej.on((byte) 0, new byte[]{(byte) 65, (byte) 86, (byte) 73, (byte) 90}, 4);
    }

    public void mo40h(String str, int i) {
        this.ej.on((byte) 0, new byte[]{(byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104}, 9);
    }

    public void mo41i() {
        this.ej.on((byte) 0, new byte[]{(byte) 84, (byte) 86}, 2);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 68, (byte) 86, (byte) 68, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32};
        bArr[4] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        bArr[5] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        bArr[7] = (byte) ((((i2 / 60) % 60) / 10) + 48);
        bArr[8] = (byte) ((((i2 / 60) % 60) % 10) + 48);
        bArr[10] = (byte) (((i2 % 60) / 10) + 48);
        bArr[11] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.on((byte) 0, bArr, 12);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.on((byte) 0, new byte[]{(byte) 73, (byte) 80, (byte) 79, (byte) 68}, 4);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 77, (byte) 85, (byte) 83, (byte) 73, (byte) 67, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32};
        bArr[6] = (byte) (((((i3 / 1000) / 60) % 60) / 10) + 48);
        bArr[7] = (byte) (((((i3 / 1000) / 60) % 60) % 10) + 48);
        bArr[9] = (byte) ((((i3 / 1000) % 60) / 10) + 48);
        bArr[10] = (byte) ((((i3 / 1000) % 60) % 10) + 48);
        this.ej.on((byte) 0, bArr, 11);
    }

    public void mo46n() {
        this.ej.on((byte) 0, new byte[]{(byte) 111, (byte) 116, (byte) 104, (byte) 101, (byte) 114}, 5);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[12];
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) 70;
            bArr[1] = (byte) 77;
            bArr[2] = (byte) (b + 49);
            bArr[3] = (byte) 58;
            int i2 = i / 10;
            if (i2 > 999) {
                bArr[4] = (byte) ((i2 / 1000) + 48);
                bArr[5] = (byte) (((i2 / 100) % 10) + 48);
                bArr[6] = (byte) (((i2 / 10) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) ((i2 % 10) + 48);
            } else {
                bArr[4] = (byte) 32;
                bArr[5] = (byte) ((i2 / 100) + 48);
                bArr[6] = (byte) (((i2 / 10) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) ((i2 % 10) + 48);
            }
            bArr[9] = (byte) 77;
            bArr[10] = (byte) 72;
            bArr[11] = (byte) 90;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) 65;
            bArr[1] = (byte) 77;
            bArr[2] = (byte) ((b + 49) - 3);
            bArr[3] = (byte) 58;
            if (i > 999) {
                bArr[4] = (byte) ((i / 1000) + 48);
                bArr[5] = (byte) (((i / 100) % 10) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            } else {
                bArr[4] = (byte) 32;
                bArr[5] = (byte) ((i / 100) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            }
            bArr[8] = (byte) 75;
            bArr[9] = (byte) 72;
            bArr[10] = (byte) 90;
            bArr[11] = (byte) 32;
        }
        this.ej.on((byte) 0, bArr, 12);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
