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

public class cl extends C0001b {
    byte[] cm;

    public cl(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.cm = new byte[31];
        this.eh = 78;
    }

    private void jg(byte[] bArr) {
        this.ei.viewShow = (bArr[1] & 16) != 0;
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
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[4] & 128) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[4] & 64) != 0) {
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
        this.ei.tempUnit = (bArr[4] & 1) != 0;
        int i = (bArr[4] >> 1) & 1;
        this.ei.tempLeft = jj(i, bArr[2] & 255);
        this.ei.tempRight = jj(i, bArr[3] & 255);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatHotLeft = (bArr[5] >> 4) & 3;
        this.ei.seatHotRight = (bArr[5] >> 0) & 3;
    }

    private void ji(byte b) {
        this.ej.ob((byte) -58, new byte[]{(byte) 0, b}, 2);
    }

    private void jk() {
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        this.ej.oe(this.ek);
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void al() {
        this.ej.ob((byte) -112, new byte[]{(byte) 33, (byte) 0, (byte) 0, (byte) 0}, 4);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 7:
            case (byte) 64:
            case (byte) 66:
            case (byte) 67:
            case (byte) 68:
                ly(bArr, i, i2);
                return;
            case (byte) 11:
                break;
            case (byte) 16:
                if (bArr[i + 2] == (byte) 10) {
                    bArr2 = new byte[12];
                    bArr2[0] = (byte) 16;
                    bArr2[1] = (byte) 10;
                    while (i3 < 10) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 17:
                if (bArr[i + 2] >= (byte) 3 && bArr[i + 2] <= (byte) 67) {
                    i4 = bArr[i + 2] & 127;
                    byte[] bArr3 = new byte[(i4 + 2)];
                    bArr3[0] = (byte) 17;
                    bArr3[1] = (byte) i4;
                    while (i3 < i4) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 7) {
                    bArr2 = new byte[7];
                    while (i3 < 7) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    if (lx(bArr2)) {
                        jg(bArr2);
                        this.ej.od(this.ei);
                    }
                    ly(bArr, i, i2);
                    return;
                }
                return;
            case (byte) 34:
                if (mc(bArr[i + 2], 4)) {
                    int[] iArr = new int[(this.er + 6)];
                    for (i4 = 0; i4 < this.er; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 6;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    if (this.er == 6) {
                        this.ek.front_cnt = 2;
                        this.ek.f1 = iArr[4];
                        this.ek.f2 = iArr[5];
                    }
                    jk();
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2 && (bArr[i + 3] & 1) != 0) {
                    jh(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] >= (byte) 2) {
                    float f = (float) (bArr[i + 3] & 255);
                    String str = "";
                    if (f >= 1.0f) {
                        float f2 = (f / 2.0f) - 39.5f;
                        if ((bArr[i + 4] & 255) == 0) {
                            str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f2)}) + this.el.getString(R.string.c_dan);
                        } else {
                            str = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf((float) ((((double) f2) * 1.8d) + 32.0d))}) + this.el.getString(R.string.f_dan);
                        }
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i4 = ((bArr[i + 4] & 255) << 8) + (bArr[i + 3] & 255);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    lz((i4 * 30) / 4600);
                    break;
                }
                break;
            case (byte) 51:
                if (bArr[i + 2] != (byte) 8) {
                    return;
                }
                return;
            default:
                return;
        }
        if (bArr[i + 2] != (byte) 2) {
        }
    }

    public void mo36d() {
        mo48p();
        this.ej.oa(1);
        this.ej.oh(1);
        if (System.getInt(this.ej.getContentResolver(), "mCar_type", -1) != -1) {
            this.ej.ob((byte) -54, new byte[]{(byte) r0}, 1);
        }
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public int[][] getAirBtnTable() {
        return new int[][]{new int[]{3841, 46, 33282, 1, 15}, new int[]{3842, 46, 33282, 1, 1}, new int[]{3844, 46, 33282, 1, 14}, new int[]{3845, 46, 33282, 1, 3}, new int[]{3846, 46, 33282, 1, 2}, new int[]{3847, 46, 33282, 1, 4}, new int[]{3848, 46, 33282, 1, 5}, new int[]{3849, 46, 33282, 1, 20}, new int[]{3850, 46, 33282, 1, 21}, new int[]{3851, 46, 33282, 1, 17}, new int[]{3852, 46, 33282, 1, 18}, new int[]{3853, 46, 33282, 1, 16}, new int[]{3855, 46, 33282, 1, 7}, new int[]{3856, 46, 33282, 1, 6}, new int[]{3857, 46, 33282, 1, 13}, new int[]{3858, 46, 33282, 1, 10}, new int[]{3860, 46, 33282, 1, 8}, new int[]{3861, 46, 33282, 1, 11}, new int[]{3863, 46, 33282, 1, 9}, new int[]{3872, 46, 33282, 1, 12}};
    }

    public void mo40h(String str, int i) {
        mo46n();
    }

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 16;
        bArr[1] = (byte) 19;
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[5] = (byte) ((i2 / 60) / 60);
        bArr[6] = (byte) ((i2 / 60) % 60);
        bArr[7] = (byte) (i2 % 60);
        this.ej.ob((byte) -64, bArr, 8);
    }

    void jh(byte[] bArr) {
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

    int jj(int i, int i2) {
        return i == 0 ? i2 == 0 ? 0 : i2 == 255 ? 65535 : (i2 < 1 || i2 > 254) ? -1 : this.ei.tempUnit ? i2 * 10 : i2 * 5 : -1;
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        r0 = new byte[8];
        int i5 = i3 / 1000;
        r0[5] = (byte) ((i5 / 60) / 60);
        r0[6] = (byte) ((i5 / 60) % 60);
        r0[7] = (byte) (i5 % 60);
        this.ej.ob((byte) -64, r0, 8);
    }

    public void mo45m(int i, int i2, int i3) {
        r0 = new byte[8];
        int i4 = i3 / 1000;
        r0[5] = (byte) ((i4 / 60) / 60);
        r0[6] = (byte) ((i4 / 60) % 60);
        r0[7] = (byte) (i4 % 60);
        this.ej.ob((byte) -64, r0, 8);
    }

    public void mo46n() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 1;
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[2] = (byte) (b + 1);
            bArr[3] = (byte) (i & 255);
            bArr[4] = (byte) ((i >> 8) & 255);
            bArr[5] = (byte) (b2 + 1);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[2] = (byte) ((b - 2) + 16);
            bArr[3] = (byte) (i & 255);
            bArr[4] = (byte) ((i >> 8) & 255);
            bArr[5] = (byte) (b2 + 1);
        }
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            ji((byte) 10);
        } else if (language.equals("en")) {
            ji((byte) 1);
        } else if (language.equals("de")) {
            ji((byte) 3);
        } else if (language.equals("it")) {
            ji((byte) 5);
        } else if (language.equals("fr")) {
            ji((byte) 2);
        } else if (!language.equals("sv")) {
            if (language.equals("es")) {
                ji((byte) 4);
            } else if (language.equals("nl")) {
                ji((byte) 6);
            } else if (language.equals("pt")) {
                ji((byte) 8);
            } else if (!language.equals("nb") && !language.equals("fi") && !language.equals("da")) {
                if (language.equals("pl")) {
                    ji((byte) 7);
                } else if (language.equals("tr")) {
                    ji((byte) 9);
                } else if (!language.equals("ar") && language.equals("ru")) {
                    ji((byte) 13);
                }
            }
        }
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[6];
        if (!DateFormat.is24HourFormat(this.el)) {
            if (this.en > 12) {
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
            this.en |= 128;
        }
        bArr[3] = (byte) (this.en & 255);
        bArr[4] = (byte) (this.eo & 255);
        bArr[0] = (byte) ((time.year - 2000) & 255);
        bArr[1] = (byte) ((time.month + 1) & 255);
        bArr[2] = (byte) (time.monthDay & 255);
        this.ej.ob((byte) -57, bArr, 6);
    }

    public void mo50r(int i) {
    }
}
