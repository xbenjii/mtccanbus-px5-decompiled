package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;

public class bh extends C0001b {
    private int ay;

    public bh(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.ay = 0;
        this.eh = 4;
        this.ei = new AirCondition();
    }

    private void fk(byte[] bArr) {
        this.ei.seatShow = false;
        if (bArr[2] != (byte) 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[3] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[3] & 4) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[3] & 2) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        this.ei.windFrontMax = false;
        if ((bArr[3] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[3] & 64) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[3] & 8) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[3] & 16) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[2];
        if (this.ei.windLevel == 8) {
            this.ei.windLevel = 7;
        }
        this.ei.windLevelMax = 7;
        int i = bArr[0] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 30) {
            this.ei.tempLeft = 65535;
        } else if (i <= 0 || i >= 30) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = (i + 34) * 5;
        }
        i = bArr[1] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 30) {
            this.ei.tempRight = 65535;
        } else if (i <= 0 || i >= 30) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = (i + 34) * 5;
        }
        if ((bArr[3] & 128) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        this.ei.acMax = -1;
    }

    private void fl(byte[] bArr) {
        this.ei.seatShow = false;
        if (bArr[2] != (byte) 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[3] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[3] & 4) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[3] & 2) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        this.ei.windFrontMax = false;
        if ((bArr[3] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[3] & 64) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[3] & 8) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[3] & 16) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[2];
        if (this.ei.windLevel == 8) {
            this.ei.windLevel = 7;
        }
        this.ei.windLevelMax = 7;
        int i = bArr[0] & 255;
        this.ei.tempUnit = i >= 62;
        this.ei.tempLeft = fn(i);
        this.ei.tempRight = fn(bArr[1] & 255);
        if ((bArr[3] & 128) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        this.ei.acMax = -1;
        if (this.ay == 1) {
            this.ei.wind_FrameShow = false;
            this.ei.modeDual = -1;
            this.ei.ariStateShow = false;
            this.ei.modeAc = true;
            return;
        }
        this.ei.wind_FrameShow = true;
        this.ei.ariStateShow = true;
    }

    private void fo(String str) {
        Intent intent = new Intent("com.canbus.temperature");
        intent.putExtra("temperature", str);
        this.el.sendBroadcast(intent);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        int i4;
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgHeight /*1*/:
                String str;
                String str2;
                if (bArr[i + 2] == (byte) 1) {
                    i4 = bArr[i + 3] & 255;
                    str = "";
                    if (i4 != 255) {
                        if ((i4 & 128) != 0) {
                            i4 = 0 - (i4 & 127);
                        }
                        str2 = " " + i4 + this.el.getString(R.string.c_dan);
                    } else {
                        str2 = str;
                    }
                    fo(str2);
                    return;
                } else if (bArr[i + 2] == (byte) 2) {
                    i4 = bArr[i + 3] & 255;
                    int i5 = bArr[i + 4] & 255;
                    str = "";
                    if (i4 == 255 && i5 == 255) {
                        str2 = str;
                    } else if (i4 != 255) {
                        if ((i4 & 128) != 0) {
                            i4 = 0 - (i4 & 127);
                        }
                        str2 = " " + i4 + this.el.getString(R.string.c_dan);
                    } else {
                        str2 = i5 != 255 ? " " + i5 + this.el.getString(R.string.f_dan) : str;
                    }
                    fo(str2);
                    return;
                } else {
                    return;
                }
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    while (i3 < 4) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    fk(bArr2);
                    this.ej.od(this.ei);
                    return;
                } else if ((bArr[i + 2] & 15) >= 6) {
                    byte[] bArr3 = new byte[6];
                    for (i4 = 0; i4 < 6; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    this.ay = System.getInt(this.ej.getContentResolver(), "com.microntek.control4settings", 0);
                    if (this.ay == 0) {
                        fl(bArr3);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] == (byte) 2) {
                    i4 = bArr[i + 3] & 255;
                    i3 = bArr[i + 4] & 255;
                    this.ek.max = 3;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = (byte) ((i4 >> 6) & 3);
                    this.ek.f2 = (byte) ((i4 >> 4) & 3);
                    this.ek.f3 = (byte) ((i4 >> 2) & 3);
                    this.ek.f4 = (byte) (i4 & 3);
                    this.ek.back_cnt = 4;
                    this.ek.b1 = (byte) ((i3 >> 6) & 3);
                    this.ek.b2 = (byte) ((i3 >> 4) & 3);
                    this.ek.b3 = (byte) ((i3 >> 2) & 3);
                    this.ek.b4 = (byte) (i3 & 3);
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] == (byte) 1) {
                    bArr2 = new byte[]{bArr[(i + 3) + 0]};
                    if (this.ay == 0) {
                        fm(bArr2);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 7:
                if (bArr[i + 2] == (byte) 2) {
                    this.ej.pd(-1, bArr[i + 3] * 2);
                    this.ej.pd(-1, bArr[i + 4] * 2);
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] == (byte) 3) {
                    this.ej.pe(-1, -1, bArr[i + 3]);
                    this.ej.pe(-1, bArr[i + 4], -1);
                    this.ej.pe(bArr[i + 5], -1, -1);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        mo48p();
    }

    public void mo37e() {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) 11;
        this.ej.on((byte) 9, bArr, 2);
    }

    public void mo38f() {
        this.ej.on((byte) 9, new byte[1], 1);
    }

    void fm(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 1) != 0;
        boolean z3 = (bArr[0] & 2) != 0;
        boolean z4 = (bArr[0] & 4) != 0;
        boolean z5 = (bArr[0] & 8) != 0;
        boolean z6 = (bArr[0] & 16) != 0;
        if ((bArr[0] & 32) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    int fn(int i) {
        return !this.ei.tempUnit ? i == 0 ? 0 : i == 30 ? 65535 : (i <= 0 || i >= 30) ? -1 : (i + 34) * 5 : (i < 62 || i > 90) ? -1 : i * 10;
    }

    public void mo39g() {
        this.ej.on((byte) 9, new byte[1], 1);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) 11;
        this.ej.on((byte) 9, bArr, 2);
    }

    public void mo41i() {
        this.ej.on((byte) 9, new byte[1], 1);
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.on((byte) 9, new byte[1], 1);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        r0 = new byte[6];
        int i5 = i3 / 1000;
        r0[3] = (byte) ((i5 / 60) / 60);
        r0[4] = (byte) ((i5 / 60) % 60);
        r0[5] = (byte) (i5 % 60);
        this.ej.on((byte) 9, r0, 6);
    }

    public void mo44l() {
        this.ej.on((byte) 9, new byte[1], 1);
    }

    public void mo45m(int i, int i2, int i3) {
        r0 = new byte[6];
        int i4 = i3 / 1000;
        r0[3] = (byte) ((i4 / 60) / 60);
        r0[4] = (byte) ((i4 / 60) % 60);
        r0[5] = (byte) (i4 % 60);
        this.ej.on((byte) 9, r0, 6);
    }

    public void mo46n() {
        this.ej.on((byte) 9, new byte[1], 1);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[4];
        bArr[0] = (byte) 2;
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[1] = (byte) (b + 0);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) 9;
            bArr[1] = (byte) 3;
        }
        bArr[2] = (byte) ((i / 100) & 255);
        bArr[3] = (byte) ((i % 100) & 255);
        this.ej.on((byte) 9, bArr, 4);
    }

    public void mo48p() {
        if (this.ej.getResources().getConfiguration().locale.getLanguage().equals("zh")) {
            this.ej.on((byte) 3, new byte[]{(byte) 1, (byte) 0}, 2);
            return;
        }
        this.ej.on((byte) 3, new byte[]{(byte) 1, (byte) 1}, 2);
    }

    public void mo49q() {
        int i = 1;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[3];
        bArr[0] = (byte) (this.eo & 255);
        bArr[1] = (byte) (this.en & 255);
        if (!is24HourFormat) {
            i = 0;
        }
        bArr[2] = (byte) i;
        this.ej.on((byte) 6, bArr, 3);
    }

    public void mo50r(int i) {
    }
}
