package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class C0021r extends C0001b {
    public C0021r(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
    }

    public void mo36d() {
    }

    public void mo37e() {
        this.ej.ob((byte) -64, new byte[]{(byte) 11, (byte) 34}, 2);
    }

    public void mo38f() {
        this.ej.ob((byte) -64, new byte[]{(byte) 3, (byte) 34}, 2);
    }

    public void mo39g() {
        this.ej.ob((byte) -64, new byte[]{(byte) 4, (byte) 48}, 2);
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -64, new byte[]{(byte) 5, (byte) 34}, 2);
    }

    public void mo41i() {
        this.ej.ob((byte) -64, new byte[]{(byte) 3, (byte) 34}, 2);
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 2, (byte) 34}, 2);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ob((byte) -64, new byte[]{(byte) 6, (byte) 18}, 2);
        byte[] bArr = new byte[4];
        bArr[0] = (byte) (i2 & 255);
        bArr[1] = (byte) ((i2 >> 8) & 255);
        this.ej.ob((byte) -61, bArr, 4);
    }

    public void mo44l() {
        this.ej.ob((byte) -64, new byte[]{(byte) 9, (byte) 34}, 2);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 9, (byte) 16}, 2);
        byte[] bArr = new byte[4];
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) (i2 & 255);
        this.ej.ob((byte) -61, bArr, 4);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[2], 2);
    }

    public void mo47o(byte b, int i, byte b2) {
        this.ej.ob((byte) -64, new byte[]{(byte) 1, (byte) 1}, 2);
        byte[] bArr = new byte[4];
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) (b + 14);
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -62, bArr, 4);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
