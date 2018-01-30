package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;

public class cr extends C0001b {
    public cr(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 2;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) -124:
                if (bArr[i + 2] == (byte) 2) {
                    byte b = bArr[i + 3];
                    String str = "";
                    if (b >= (byte) -40 && b <= (byte) 50) {
                        str = " " + b + this.el.getString(R.string.c_dan);
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc /*2*/:
                if (bArr[i + 2] == (byte) 1) {
                    this.ej.pd(-1, bArr[i + 3] * 2);
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] == (byte) 1) {
                    this.ej.pd(bArr[i + 3] * 2, -1);
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] == (byte) 1) {
                    byte b2 = bArr[i + 3];
                    return;
                }
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] == (byte) 1) {
                    this.ej.pe(-1, -1, (bArr[i + 3] - 2) * 3);
                    return;
                }
                return;
            case (byte) 7:
                if (bArr[i + 2] == (byte) 1) {
                    this.ej.pe((bArr[i + 3] - 2) * 3, -1, -1);
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] == (byte) 1) {
                    this.ej.pe(-1, (bArr[i + 3] - 2) * 3, -1);
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
