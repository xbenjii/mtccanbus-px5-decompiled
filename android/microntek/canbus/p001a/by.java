package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.Time;
import java.util.Locale;

public class by extends C0001b {
    public by(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void id(byte[] bArr) {
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 3) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[1] & 8) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[2] & 16) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[2] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[4] & 15) {
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 11:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case 12:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case 13:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 14:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[5] & 15;
        this.ei.windLevelMax = 8;
        int i = bArr[6] & 255;
        this.ei.tempLeft = m750if(i);
        i = bArr[7] & 255;
        this.ei.tempRight = m750if(i);
        if ((bArr[1] & 16) == 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatHotLeft = bArr[2] & 3;
        this.ei.seatHotRight = (bArr[2] & 12) >> 2;
    }

    private int m750if(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : i < 100 ? i * 5 : -1;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        switch (bArr[i + 1]) {
            case (byte) -63:
            case (byte) -16:
            case (byte) 56:
            case (byte) 67:
            case (byte) 72:
            case (byte) 96:
            case (byte) 98:
                ly(bArr, i, i2);
                return;
            case (byte) 17:
                if (bArr[i + 2] >= (byte) 10) {
                    i3 = ((bArr[i + 9] & 255) << 8) | (bArr[i + 10] & 255);
                    if (i3 != 0) {
                        if (i3 >= 32768) {
                            i3 -= 65536;
                        }
                        lz((i3 * 30) / 540);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] >= (byte) 10) {
                    ie(new byte[]{bArr[i + 5]});
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] >= (byte) 12) {
                    byte[] ma = ma(bArr, i + 3, i2 - 1);
                    if (lx(ma)) {
                        id(ma);
                        this.ej.od(this.ei);
                    }
                    int i4 = bArr[i + 14] & 255;
                    String str = "";
                    if (i4 > 0 && i4 <= 214) {
                        str = String.format(Locale.US, "  %.1f", new Object[]{Float.valueOf((((float) i4) / 2.0f) - 40.0f)}) + this.el.getString(R.string.c_dan);
                    }
                    md(str);
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] >= (byte) 12) {
                    int[] iArr = new int[12];
                    for (i3 = 0; i3 < 12; i3++) {
                        iArr[i3] = bArr[(i + 3) + i3] & 255;
                        if (iArr[i3] >= 5) {
                            iArr[i3] = 0;
                        }
                    }
                    if (iArr[10] == 1) {
                        mb();
                        this.ek.max = 4;
                        this.ek.back_cnt = 4;
                        this.ek.b1 = iArr[0];
                        this.ek.b2 = iArr[1];
                        this.ek.b3 = iArr[2];
                        this.ek.b4 = iArr[3];
                        this.ek.front_cnt = 4;
                        this.ek.f1 = iArr[4];
                        this.ek.f2 = iArr[5];
                        this.ek.f3 = iArr[6];
                        this.ek.f4 = iArr[7];
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
    }

    void ie(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        boolean z7 = (bArr[0] & 0) != 0;
        if ((bArr[0] & 2) != 0) {
            z = false;
        }
        if (door.lb(z2, z3, z4, z5, z6, z7, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[10];
        bArr[1] = (byte) (this.en & 255);
        bArr[2] = (byte) (this.eo & 255);
        this.ej.ol((byte) -53, bArr, 10);
    }
}
