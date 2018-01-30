package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;

public class ct extends C0001b {
    private byte[] cv;
    private boolean cw;

    public ct(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.cv = new byte[6];
        this.cw = false;
        this.eh = 55;
        this.ei = new AirCondition();
    }

    private void jz(byte[] bArr) {
        if ((bArr[0] & 128) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        this.ei.modeDual = -1;
        if ((bArr[0] & 16) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[1] & 15) {
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.modeAuto = 1;
                this.ei.windFrontMax = false;
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = true;
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 7:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case 8:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case 9:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            default:
                this.ei.modeAuto = 0;
                this.ei.windFrontMax = false;
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[0] & 7;
        if (this.ej.jg == 1) {
            this.ei.windLevelMax = 6;
        } else {
            if ((bArr[1] & 16) != 0) {
                this.ei.windLevel = 8;
            }
            this.ei.windLevelMax = 8;
        }
        int i = bArr[2] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 30) {
            this.ei.tempLeft = 65535;
        } else if (i >= 1 && i <= 28) {
            this.ei.tempLeft = (i + 33) * 5;
        } else if (i == 29) {
            this.ei.tempLeft = 160;
        } else if (i == 31) {
            this.ei.tempLeft = 165;
        } else if (i == 32) {
            this.ei.tempLeft = 150;
        } else if (i == 33) {
            this.ei.tempLeft = 155;
        } else if (i == 34) {
            this.ei.tempLeft = 310;
        } else {
            this.ei.tempLeft = -1;
        }
        i = bArr[3] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 30) {
            this.ei.tempRight = 65535;
        } else if (i >= 1 && i <= 28) {
            this.ei.tempRight = (i + 33) * 5;
        } else if (i == 29) {
            this.ei.tempRight = 160;
        } else if (i == 31) {
            this.ei.tempRight = 165;
        } else if (i == 32) {
            this.ei.tempRight = 150;
        } else if (i == 33) {
            this.ei.tempRight = 155;
        } else if (i == 34) {
            this.ei.tempRight = 310;
        } else {
            this.ei.tempRight = -1;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.windLoop = 2;
        } else if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        this.ei.acMax = -1;
        this.ei.seatHotLeft = (bArr[4] & 48) >> 4;
        this.ei.seatHotRight = bArr[4] & 3;
    }

    private void kb(byte b) {
        this.ej.ob((byte) -121, new byte[]{b}, 1);
    }

    public void at() {
        this.ej.ob((byte) -112, new byte[]{(byte) 38}, 1);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        int i4;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case (byte) -46:
                if (bArr[i + 2] != (byte) 2) {
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] >= (byte) 7) {
                    bArr2 = new byte[6];
                    for (i4 = 0; i4 < 6; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                        if (i4 != 5) {
                            if (bArr2[i4] != this.cv[i4]) {
                                i3 = 1;
                            }
                            this.cv[i4] = bArr2[i4];
                        }
                    }
                    if (i3 != 0 && this.cw) {
                        jz(bArr2);
                        this.ej.od(this.ei);
                    }
                    this.cw = true;
                    if (this.ej.jg != 1) {
                        byte b = bArr2[5];
                        String str = "";
                        if (b >= (byte) -40 && b <= (byte) 87) {
                            str = " " + b + this.el.getString(R.string.c_dan);
                        }
                        md(str);
                        return;
                    }
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] == (byte) 1) {
                    bArr3 = new byte[3];
                    bArr3[0] = (byte) 4;
                    bArr3[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[4];
                    bArr3[0] = (byte) 5;
                    bArr3[1] = (byte) 1;
                    while (i3 < 2) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[4];
                    bArr3[0] = (byte) 6;
                    bArr3[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 7:
                if (bArr[i + 2] == (byte) 1) {
                    bArr3 = new byte[3];
                    bArr3[0] = (byte) 7;
                    bArr3[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] == (byte) 10) {
                    bArr3 = new byte[12];
                    bArr3[0] = (byte) 8;
                    bArr3[1] = (byte) 10;
                    while (i3 < 10) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 9:
                if (bArr[i + 2] == (byte) 1) {
                    bArr3 = new byte[3];
                    bArr3[0] = (byte) 9;
                    bArr3[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 10:
                if (bArr[i + 2] == (byte) 3) {
                    bArr3 = new byte[5];
                    bArr3[0] = (byte) 10;
                    bArr3[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 11:
                if (bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[4];
                    bArr3[0] = (byte) 11;
                    bArr3[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 12:
                if (bArr[i + 2] == (byte) 1) {
                    bArr3 = new byte[3];
                    bArr3[0] = (byte) 12;
                    bArr3[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 13:
                if (bArr[i + 2] == (byte) 1) {
                    bArr3 = new byte[3];
                    bArr3[0] = (byte) 13;
                    bArr3[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 26:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[6];
                    bArr3[0] = (byte) 26;
                    bArr3[1] = (byte) 4;
                    while (i3 < 4) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 7;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[1];
                    this.ek.b3 = bArr2[2];
                    this.ek.b4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 7;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ek.f4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    ka(new byte[]{bArr[i + 3], bArr[i + 4]});
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    lz(0 - ((i3 * 35) / 7800));
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] == (byte) 8) {
                    bArr3 = new byte[16];
                    bArr3[0] = (byte) 49;
                    bArr3[1] = (byte) 8;
                    while (i3 < 8) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] == (byte) 5) {
                    bArr3 = new byte[7];
                    bArr3[0] = (byte) 50;
                    bArr3[1] = (byte) 5;
                    while (i3 < 5) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 51:
                if (bArr[i + 2] == (byte) 15) {
                    bArr3 = new byte[17];
                    bArr3[0] = (byte) 51;
                    bArr3[1] = (byte) 15;
                    while (i3 < 15) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        mo48p();
        this.ej.oa(1);
        this.ej.oh(1);
        this.ej.ob((byte) -112, new byte[]{(byte) 3}, 1);
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

    void ka(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[1] & 128) == 0) {
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

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            kb((byte) 0);
        } else if (language.equals("en")) {
            kb((byte) 1);
        } else if (language.equals("de")) {
            kb((byte) 2);
        } else if (language.equals("it")) {
            kb((byte) 3);
        } else if (language.equals("fr")) {
            kb((byte) 4);
        } else if (language.equals("sv")) {
            kb((byte) 5);
        } else if (language.equals("es")) {
            kb((byte) 6);
        } else if (language.equals("nl")) {
            kb((byte) 7);
        } else if (language.equals("pt")) {
            kb((byte) 8);
        } else if (language.equals("nb")) {
            kb((byte) 9);
        } else if (language.equals("fi")) {
            kb((byte) 10);
        } else if (language.equals("da")) {
            kb((byte) 11);
        } else if (language.equals("pl")) {
            kb((byte) 12);
        } else if (language.equals("tr")) {
            kb((byte) 13);
        } else if (language.equals("ar")) {
            kb((byte) 14);
        } else if (language.equals("ru")) {
            kb((byte) 15);
        }
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
