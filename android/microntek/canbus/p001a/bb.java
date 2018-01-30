package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.provider.Settings.System;

public class bb extends C0001b {
    public bb(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private boolean et(byte[] bArr, int i) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (bArr[i2] == i) {
                return true;
            }
            if (bArr[i2] == (byte) -1) {
                break;
            }
        }
        return false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc /*2*/:
                if (bArr[i + 2] >= (byte) 24) {
                    byte[] bArr2 = new byte[24];
                    for (int i3 = 0; i3 < 24; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    es(bArr2);
                    break;
                }
                break;
        }
        ly(bArr, i, i2);
    }

    public void bg() {
        this.ej.ob((byte) -126, new byte[]{(byte) 0}, 1);
    }

    public void bh() {
        this.ej.ob((byte) -126, new byte[]{Byte.MIN_VALUE}, 1);
    }

    public void mo36d() {
        int i = System.getInt(this.ej.getContentResolver(), "canbus92settings", 0);
        this.ej.ob((byte) -125, new byte[]{(byte) i}, 1);
    }

    public void mo37e() {
    }

    void es(byte[] bArr) {
        if (this.em.la(et(bArr, 0), et(bArr, 2), et(bArr, 1), et(bArr, 3), et(bArr, 4), et(bArr, 5))) {
            this.ej.of(this.em);
        }
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

    public void mo47o(byte b, int i, byte b2) {
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
