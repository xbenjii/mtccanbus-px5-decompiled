package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class cc extends C0001b {
    public cc(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 28;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] == (byte) 1) {
                    im(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] == (byte) 2) {
                    String str;
                    float f = (float) (bArr[i + 3] & 255);
                    String str2 = "";
                    if (f == 0.0f) {
                        str = "---";
                    } else {
                        f = (f / 2.0f) - 40.0f;
                        if (((byte) (bArr[i + 4] & 1)) == (byte) 1) {
                            f = ((f * 9.0f) / 5.0f) + 32.0f;
                            str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.f_dan);
                        } else {
                            str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                        }
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void bg() {
        this.ej.ob((byte) -126, new byte[5], 5);
    }

    public void mo36d() {
    }

    public void mo37e() {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo38f() {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo39g() {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo41i() {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    void im(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -126, new byte[]{(byte) 2, (byte) 32, (byte) (i & 99), (byte) 32, (byte) 32}, 5);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo44l() {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo46n() {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 1;
        bArr[2] = (byte) 32;
        if (b >= (byte) 0 && b <= (byte) 2) {
            if (b == (byte) 2) {
                bArr[1] = (byte) 6;
            } else {
                bArr[1] = b;
            }
            int i2 = i / 10;
            bArr[3] = (byte) ((i2 >> 8) & 255);
            bArr[4] = (byte) (i2 & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[1] = (byte) 5;
            bArr[3] = (byte) ((i >> 8) & 255);
            bArr[4] = (byte) (i & 255);
        }
        this.ej.ob((byte) -126, bArr, 5);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
