package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;

public class as extends C0001b {
    public as(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 3;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 7:
                if (bArr[i + 2] == (byte) 2) {
                    this.ej.pd(bArr[i + 4] * 2, bArr[i + 3] * 2);
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
