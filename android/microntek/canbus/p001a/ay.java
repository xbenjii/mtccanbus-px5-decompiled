package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class ay extends C0001b {
    private byte[] an;
    private byte[] ao;
    private byte[] ap;
    private byte[] aq;
    private int ar;
    private boolean as;

    public ay(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.ar = 0;
        this.ap = new byte[11];
        this.aq = new byte[16];
        this.an = new byte[14];
        this.ao = new byte[13];
        this.eh = 58;
    }

    private void ek(byte[] bArr) {
        this.ei.modeDual = -1;
        this.ei.acMax = -1;
        this.ei.rearLock = -1;
        this.ei.wind_FrameShow = false;
        this.ei.windRearShow = false;
        if ((bArr[0] & 128) == 0 || (bArr[0] & 64) == 0) {
            this.ei.onOff = false;
        } else {
            this.ei.onOff = true;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        byte b = bArr[4];
        if (b == (byte) 2) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if (b == (byte) 3) {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if (b == (byte) 5) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else if (b == (byte) 6) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if (b == (byte) 11) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = false;
        } else if (b == (byte) 12) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if (b == (byte) 13) {
            this.ei.windUp = true;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if (b == (byte) 14) {
            this.ei.windUp = true;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[5] & 7;
        this.ei.windLevelMax = 7;
        if (this.ar == 0) {
            this.ei.tempUnit = false;
        } else {
            this.ei.tempUnit = true;
        }
        int i = bArr[6] & 255;
        if (i == 254) {
            this.ei.tempLeft = 0;
        } else if (i == 255) {
            this.ei.tempLeft = 65535;
        } else if (i > 199) {
            this.ei.tempLeft = -1;
        } else if (this.ei.tempUnit) {
            this.ei.tempLeft = ((((i * 10) / 2) * 9) / 5) + 320;
        } else {
            this.ei.tempLeft = (i * 10) / 2;
        }
        i = bArr[7] & 255;
        if (i == 254) {
            this.ei.tempRight = 0;
        } else if (i == 255) {
            this.ei.tempRight = 65535;
        } else if (i > 199) {
            this.ei.tempRight = -1;
        } else if (this.ei.tempUnit) {
            this.ei.tempRight = ((((i * 10) / 2) * 9) / 5) + 320;
        } else {
            this.ei.tempRight = (i * 10) / 2;
        }
        this.ei.seatHotLeft = bArr[2] & 3;
        this.ei.seatHotRight = (bArr[2] & 12) >> 2;
    }

    private int em(byte b) {
        return (b & 255) >= 166 ? 0 : (((b & 255) - 1) / 15) + 1;
    }

    private int en(byte b) {
        int i = (b & 255) >= 166 ? 0 : (((b & 255) - 1) / 15) + 1;
        return i >= 8 ? 8 : i;
    }

    private int eo(int i) {
        return i == 1 ? -1 : i == 2 ? -256 : -65536;
    }

    private int ep(byte b) {
        int i = (b & 255) >= 166 ? 0 : (((b & 255) - 1) / 15) + 1;
        return i >= 4 ? 4 : i;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        Intent intent;
        byte[] bArr2;
        int i5;
        switch (bArr[i + 1]) {
            case (byte) 17:
                if (bArr[i + 2] == (byte) 10) {
                    if ((bArr[i + 3] & 32) != 0) {
                        this.as = true;
                    } else {
                        this.as = false;
                    }
                    i4 = (bArr[i + 10] & 255) + ((bArr[i + 9] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 450;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] == (byte) 10) {
                    byte[] bArr3 = new byte[2];
                    bArr3[0] = bArr[i + 5];
                    el(bArr3);
                    bArr3 = new byte[12];
                    bArr3[0] = (byte) 18;
                    bArr3[1] = (byte) 10;
                    while (i3 < 10) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] == (byte) 12) {
                    this.ar = System.getInt(this.ej.getContentResolver(), "com.microntek.controlsettings.unit", 0);
                    bArr2 = new byte[11];
                    i4 = 0;
                    for (i5 = 0; i5 < 11; i5++) {
                        bArr2[i5] = bArr[(i + 3) + i5];
                        if (bArr2[i5] != this.ap[i5]) {
                            i4 = true;
                        }
                        this.ap[i5] = bArr2[i5];
                    }
                    if (i4 != 0) {
                        ek(bArr2);
                        this.ej.od(this.ei);
                    }
                    float f = ((float) (((double) (bArr[(i + 3) + 11] & 255)) * 0.5d)) - 40.0f;
                    String str = "";
                    if (f >= -40.0f && f <= 85.0f) {
                        if (this.ar == 1) {
                            str = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf(((f * 9.0f) / 5.0f) + 32.0f)}) + this.el.getString(R.string.f_dan);
                        } else {
                            str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                        }
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] == (byte) 16) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    bArr2 = new byte[16];
                    i4 = 0;
                    for (i5 = 0; i5 < 16; i5++) {
                        bArr2[i5] = bArr[(i + 3) + i5];
                        if (bArr2[i5] != this.aq[i5]) {
                            i4 = true;
                        }
                        this.aq[i5] = bArr2[i5];
                    }
                    if (i4 != 0 && this.as) {
                        this.ek.mode = 1;
                        this.ek.front_cnt = 4;
                        this.ek.fmax1 = 4;
                        this.ek.fmax2 = 8;
                        this.ek.fmax3 = 8;
                        this.ek.fmax4 = 4;
                        this.ek.f1 = ep(bArr2[4]);
                        this.ek.f2 = en(bArr2[5]);
                        this.ek.f3 = en(bArr2[6]);
                        this.ek.f4 = ep(bArr2[7]);
                        this.ek.fc1 = eo(bArr2[12] & 3);
                        this.ek.fc2 = eo(bArr2[13] & 3);
                        this.ek.fc3 = eo(bArr2[14] & 3);
                        this.ek.fc4 = eo(bArr2[15] & 3);
                        this.ek.back_cnt = 4;
                        this.ek.bmax1 = 4;
                        this.ek.bmax2 = 11;
                        this.ek.bmax3 = 11;
                        this.ek.bmax4 = 4;
                        this.ek.b1 = ep(bArr2[0]);
                        this.ek.b2 = em(bArr2[1]);
                        this.ek.b3 = em(bArr2[2]);
                        this.ek.b4 = ep(bArr2[3]);
                        this.ek.bc1 = eo(bArr2[8] & 3);
                        this.ek.bc2 = eo(bArr2[9] & 3);
                        this.ek.bc3 = eo(bArr2[10] & 3);
                        this.ek.bc4 = eo(bArr2[11] & 3);
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            default:
                if (bArr[i + 2] >= (byte) 2) {
                    byte[] bArr4 = new byte[((bArr[i + 2] & 255) + 2)];
                    bArr4[0] = (byte) (bArr[i + 1] & 255);
                    bArr4[1] = (byte) (bArr[i + 2] & 255);
                    for (byte b = (byte) 0; b < bArr[i + 2]; b++) {
                        bArr4[b + 2] = bArr[(i + 3) + b];
                    }
                    this.ej.oj(bArr4);
                    return;
                }
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
    }

    public void mo37e() {
        eq();
        this.ao[1] = (byte) 65;
        this.ao[2] = (byte) 50;
        this.ao[3] = (byte) 68;
        this.ao[4] = (byte) 80;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    void el(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    void eq() {
        int i = 0;
        for (int i2 = 0; i2 < 14; i2++) {
            this.an[i2] = (byte) 32;
        }
        while (i < 13) {
            this.ao[i] = (byte) 32;
            i++;
        }
    }

    public void mo38f() {
        eq();
        this.ao[1] = (byte) 84;
        this.ao[2] = (byte) 86;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo39g() {
        eq();
        this.ao[1] = (byte) 65;
        this.ao[2] = (byte) 86;
        this.ao[3] = (byte) 73;
        this.ao[4] = (byte) 78;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo40h(String str, int i) {
        eq();
        this.ao[1] = (byte) 66;
        this.ao[2] = (byte) 108;
        this.ao[3] = (byte) 117;
        this.ao[4] = (byte) 101;
        this.ao[5] = (byte) 84;
        this.ao[6] = (byte) 111;
        this.ao[7] = (byte) 111;
        this.ao[8] = (byte) 116;
        this.ao[9] = (byte) 104;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo41i() {
        eq();
        this.ao[1] = (byte) 84;
        this.ao[2] = (byte) 86;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo42j(int i, int i2, int i3) {
        eq();
        this.an[2] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        this.an[3] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        this.an[4] = (byte) 58;
        this.an[5] = (byte) ((((i2 / 60) % 60) / 10) + 48);
        this.an[6] = (byte) ((((i2 / 60) % 60) % 10) + 48);
        this.an[7] = (byte) 58;
        this.an[8] = (byte) (((i2 % 60) / 10) + 48);
        this.an[9] = (byte) (((i2 % 60) % 10) + 48);
        this.ao[1] = (byte) 68;
        this.ao[2] = (byte) 86;
        this.ao[3] = (byte) 68;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        eq();
        int i5 = i3 / 1000;
        this.an[2] = (byte) ((i2 / 100) + 48);
        this.an[3] = (byte) (((i2 / 10) % 10) + 48);
        this.an[4] = (byte) (((i2 % 100) % 10) + 48);
        this.an[6] = (byte) ((((i5 / 60) / 60) / 10) + 48);
        this.an[7] = (byte) ((((i5 / 60) / 60) % 10) + 48);
        this.an[8] = (byte) 58;
        this.an[9] = (byte) ((((i5 / 60) % 60) / 10) + 48);
        this.an[10] = (byte) ((((i5 / 60) % 60) % 10) + 48);
        this.an[11] = (byte) 58;
        this.an[12] = (byte) (((i5 % 60) / 10) + 48);
        this.an[13] = (byte) (((i5 % 60) % 10) + 48);
        this.ao[1] = (byte) 73;
        this.ao[2] = (byte) 80;
        this.ao[3] = (byte) 79;
        this.ao[4] = (byte) 68;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo44l() {
        eq();
        this.ao[1] = (byte) 83;
        this.ao[2] = (byte) 68;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo45m(int i, int i2, int i3) {
        eq();
        int i4 = i3 / 1000;
        this.an[2] = (byte) ((i2 / 100) + 48);
        this.an[3] = (byte) (((i2 / 10) % 10) + 48);
        this.an[4] = (byte) (((i2 % 100) % 10) + 48);
        this.an[6] = (byte) ((((i4 / 60) / 60) / 10) + 48);
        this.an[7] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        this.an[8] = (byte) 58;
        this.an[9] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        this.an[10] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        this.an[11] = (byte) 58;
        this.an[12] = (byte) (((i4 % 60) / 10) + 48);
        this.an[13] = (byte) (((i4 % 60) % 10) + 48);
        this.ao[1] = (byte) 83;
        this.ao[2] = (byte) 68;
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo46n() {
        eq();
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo47o(byte b, int i, byte b2) {
        eq();
        if (b >= (byte) 0 && b <= (byte) 2) {
            if (i > 9999) {
                this.an[2] = (byte) ((i / 10000) + 48);
                this.an[3] = (byte) (((i / 1000) % 10) + 48);
                this.an[4] = (byte) (((i / 100) % 10) + 48);
                this.an[5] = (byte) 46;
                this.an[6] = (byte) (((i / 10) % 10) + 48);
                this.an[7] = (byte) ((i % 10) + 48);
            } else {
                this.an[3] = (byte) ((i / 1000) + 48);
                this.an[4] = (byte) (((i / 100) % 10) + 48);
                this.an[5] = (byte) 46;
                this.an[6] = (byte) (((i / 10) % 10) + 48);
                this.an[7] = (byte) ((i % 10) + 48);
            }
            this.an[8] = (byte) 77;
            this.an[9] = (byte) 72;
            this.an[10] = (byte) 90;
            this.ao[1] = (byte) 70;
            this.ao[2] = (byte) 77;
            this.ao[3] = (byte) (b + 49);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            if (i > 999) {
                this.an[2] = (byte) ((i / 1000) + 48);
                this.an[3] = (byte) (((i / 100) % 10) + 48);
                this.an[4] = (byte) (((i / 10) % 10) + 48);
                this.an[5] = (byte) ((i % 10) + 48);
            } else {
                this.an[3] = (byte) ((i / 100) + 48);
                this.an[4] = (byte) (((i / 10) % 10) + 48);
                this.an[5] = (byte) ((i % 10) + 48);
            }
            this.an[6] = (byte) 75;
            this.an[7] = (byte) 72;
            this.an[8] = (byte) 90;
            this.ao[1] = (byte) 65;
            this.ao[2] = (byte) 77;
            this.ao[3] = (byte) ((b + 49) - 3);
        }
        this.ej.ol((byte) -110, this.ao, 13);
        this.ej.ol((byte) -111, this.an, 14);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.endsWith("zh")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 2}, 2);
        } else if (language.endsWith("de")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 3}, 2);
        } else if (language.endsWith("it")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 4}, 2);
        } else if (language.endsWith("fr")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 5}, 2);
        } else if (language.endsWith("sv")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 6}, 2);
        } else if (language.endsWith("es")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 7}, 2);
        } else if (language.endsWith("pt")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 9}, 2);
        } else {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 1}, 2);
        }
    }

    public void mo49q() {
        int i = 1;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[10];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) (this.en & 255);
        bArr[2] = (byte) (this.eo & 255);
        if (!is24HourFormat) {
            i = 0;
        }
        bArr[5] = (byte) i;
        bArr[6] = (byte) ((time.year - 2000) & 255);
        bArr[7] = (byte) ((time.month + 1) & 255);
        bArr[8] = (byte) (time.monthDay & 255);
        bArr[9] = (byte) 2;
        this.ej.ol((byte) -53, bArr, 10);
    }

    public void mo50r(int i) {
    }
}
