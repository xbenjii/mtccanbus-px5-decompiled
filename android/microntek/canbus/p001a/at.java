package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class at extends C0001b {
    public at(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void dz(byte[] bArr) {
        this.ei.onOff = true;
        if ((bArr[4] & 2) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[4] & 1) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[4] & 32) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[4] & 64) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[3] & 15) {
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.windMid = true;
                this.ei.windUp = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windMid = true;
                this.ei.windUp = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windMid = false;
                this.ei.windUp = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windMid = false;
                this.ei.windUp = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windMid = false;
                this.ei.windUp = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windMid = true;
                this.ei.windUp = true;
                this.ei.windDown = true;
                break;
            case 7:
                this.ei.windMid = true;
                this.ei.windUp = true;
                this.ei.windDown = false;
                break;
            default:
                this.ei.windMid = false;
                this.ei.windUp = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[1] & 15;
        this.ei.windLevelMax = 7;
        this.ei.tempLeft = eb(bArr[0] & 255);
        this.ei.tempRight = eb(bArr[1] & 255);
        if ((bArr[0] & 12) == 0) {
            this.ei.windLoop = -1;
        } else if ((bArr[0] & 4) != 0) {
            this.ei.windLoop = 0;
        } else {
            this.ei.windLoop = 1;
        }
        if ((bArr[4] & 16) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatShow = false;
    }

    private int eb(int i) {
        return i <= 36 ? 0 : i >= 66 ? 65535 : ((i >> 1) * 10) + ((i & 1) != 0 ? 5 : 0);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] >= (byte) 6) {
                    byte[] ma = ma(bArr, i + 3, i + 8);
                    if (lx(ma)) {
                        dz(ma);
                        this.ej.od(this.ei);
                    }
                    int i3 = (bArr[i + 8] & 255) - 40;
                    String str = "";
                    if (i3 >= -40 && i3 <= 86) {
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                    }
                    md(str);
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] >= (byte) 4) {
                    mb();
                    int[] iArr = new int[3];
                    for (int i4 = 0; i4 < 3; i4++) {
                        iArr[i4] = bArr[(i + 4) + i4] & 255;
                        if (iArr[i4] >= 5) {
                            iArr[i4] = 0;
                        } else {
                            iArr[i4] = iArr[i4] + 1;
                        }
                    }
                    this.ek.max = 5;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 56:
                if (bArr[i + 2] >= (byte) 2) {
                    ea(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
    }

    public void mo37e() {
    }

    void ea(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 1) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
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

    public void mo46n() {
    }

    public void mo47o(byte b, int i, byte b2) {
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
