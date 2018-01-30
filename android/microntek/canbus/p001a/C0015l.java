package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;

public class C0015l extends C0001b {
    public C0015l(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 25;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[12];
        for (int i3 = 0; i3 < 12; i3++) {
            bArr2[i3] = bArr[(i + 1) + i3];
        }
        this.ej.oj(bArr2);
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
