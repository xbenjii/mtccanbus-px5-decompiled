package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class cw extends C0001b {
    private int cx = 0;

    public cw(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void kc(byte[] bArr) {
        if ((bArr[0] & 128) == 0 || (bArr[4] & 128) == 0) {
            this.ei.onOff = false;
        } else {
            this.ei.onOff = true;
        }
        if ((bArr[0] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[1] & 128) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[1] & 32) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[1] & 15;
        this.ei.windLevelMax = 8;
        int i = bArr[2] & 255;
        this.ei.tempLeft = ke(i);
        i = bArr[3] & 255;
        this.ei.tempRight = ke(i);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    private int ke(int i) {
        int i2;
        if (i >= 1 && i <= 7) {
            i2 = (i * 10) + 190;
            this.cx = 1;
            System.putInt(this.ej.getContentResolver(), "tempMode", this.cx);
            return i2;
        } else if (i < 36 || i > 64) {
            return i == 255 ? 65535 : (i == 0 && this.cx == 0) ? 0 : -1;
        } else {
            i2 = i * 5;
            this.cx = 0;
            System.putInt(this.ej.getContentResolver(), "tempMode", this.cx);
            return i2;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = bArr[i + 2] & 255;
        int i4;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc /*2*/:
                if (i3 >= 6) {
                    String str;
                    byte[] bArr2 = new byte[i3];
                    for (i4 = 0; i4 < i3 - 1; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                        if (i4 == 4) {
                            bArr2[i4] = (byte) (bArr2[i4] & 252);
                        }
                    }
                    if (lx(bArr2)) {
                        kc(bArr2);
                        this.ej.od(this.ei);
                    }
                    float f = (float) (bArr[i + 8] & 127);
                    String str2 = "";
                    if ((bArr[i + 7] & 2) != 0) {
                        f += 0.5f;
                    }
                    if ((bArr[i + 8] & 128) != 0) {
                        f = 0.0f - f;
                    }
                    if (f < -20.0f || f > 60.0f) {
                        str = str2;
                    } else {
                        str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                    }
                    md(str);
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                kd(new byte[]{bArr[i + 3], bArr[i + 4]});
                System.putInt(this.ej.getContentResolver(), "Can114RightViewState", (bArr[i + 4] & 64) >> 5);
                ly(bArr, i, i2);
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                i4 = ((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255);
                if (i4 >= 32768) {
                    i4 -= 65536;
                }
                lz((i4 * 30) / 5376);
                return;
            case (byte) 11:
                ly(bArr, i, i2);
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
        this.cx = System.getInt(this.ej.getContentResolver(), "tempMode", 0);
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

    void kd(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
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
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 3;
        if (!is24HourFormat) {
            if (this.en > 12) {
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
        }
        bArr[4] = (byte) (this.en & 127);
        bArr[5] = (byte) (this.eo & 255);
        this.ej.ob((byte) -124, bArr, 6);
    }

    public void mo50r(int i) {
    }
}
