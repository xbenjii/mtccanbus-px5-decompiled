package android.microntek.canbus.p001a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.microntek.canbus.serializable.Radar;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import java.util.Arrays;
import java.util.Locale;

public class ai extends C0001b {
    private static long af = 0;

    public ai(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 17;
        this.ei = new AirCondition();
    }

    private void cw(byte[] bArr) {
        this.ei.viewShow = (bArr[1] & 16) != 0;
        if ((bArr[0] & 128) == 0 || (bArr[1] & 16) == 0) {
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
            this.ei.modeAuto = 1;
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
        this.ei.windLevelMax = 7;
        if ((bArr[4] & 1) != 0) {
            this.ei.tempUnit = true;
        } else {
            this.ei.tempUnit = false;
        }
        int i = bArr[2] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 31) {
            this.ei.tempLeft = 65535;
        } else if (i < 1 || i > 29) {
            this.ei.tempLeft = -1;
        } else if (this.ei.tempUnit) {
            this.ei.tempLeft = (i + 59) * 10;
        } else {
            this.ei.tempLeft = (i + 31) * 5;
        }
        i = bArr[3] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 31) {
            this.ei.tempRight = 65535;
        } else if (i < 1 || i > 29) {
            this.ei.tempRight = -1;
        } else if (this.ei.tempUnit) {
            this.ei.tempRight = (i + 59) * 10;
        } else {
            this.ei.tempRight = (i + 31) * 5;
        }
        if ((bArr[4] & 32) != 0) {
            this.ei.windLoop = 2;
        } else if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatHotLeft = (bArr[5] & 48) >> 4;
        this.ei.seatHotRight = bArr[5] & 3;
    }

    private void cy(int i, int i2) {
        this.ej.ob((byte) -58, new byte[]{(byte) (i & 255), (byte) (i2 & 255)}, 2);
    }

    private void da(int i, byte[] bArr) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.microntek.controlsettings", "com.microntek.controlsettings.canbus17drivingMode"));
        intent.addFlags(807600128);
        intent.putExtra("cftype", i);
        intent.putExtra("buf", bArr);
        try {
            this.ej.startActivityAsUser(intent, UserHandle.CURRENT_OR_SELF);
        } catch (ActivityNotFoundException e) {
            Log.e("Canbus17", "controlsettings activity not found! " + e.getMessage());
        }
    }

    private boolean db() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - af <= 1200) {
            return false;
        }
        af = uptimeMillis;
        return true;
    }

    private int dc(int i, int i2, int i3) {
        if (i > i3) {
            return 0;
        }
        int i4 = (i * i2) / i3;
        if (i4 == 0 && i != 0) {
            i4 = 1;
        }
        return i4;
    }

    private void dd(Radar radar) {
        if (this.ej.oc() == 0 && this.ek.view_show) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        this.ej.oe(this.ek);
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4 = System.getInt(this.ej.getContentResolver(), "PackingRadarEN", 1);
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        int i5;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) -107:
                if (bArr[i + 2] < (byte) 2) {
                    return;
                }
                if (bArr[i + 3] == (byte) 1) {
                    lv("av_mute=true");
                    return;
                } else if (bArr[i + 3] == (byte) 0) {
                    lv("av_mute=false");
                    return;
                } else {
                    return;
                }
            case (byte) 33:
                if (mc(bArr[i + 2], 7)) {
                    bArr2 = new byte[(this.er + 2)];
                    bArr2[0] = (byte) 33;
                    bArr2[1] = (byte) this.er;
                    for (i4 = 0; i4 < this.er; i4++) {
                        bArr2[i4 + 2] = bArr[(i + 3) + i4];
                    }
                    this.ej.oj(bArr2);
                    bArr3 = new byte[this.er];
                    while (i3 < this.er) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    if (lx(bArr3)) {
                        cw(bArr3);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 34:
                if (i4 != 0 && bArr[i + 2] == (byte) 6) {
                    bArr4 = new byte[6];
                    i4 = 0;
                    for (i5 = 0; i5 < 6; i5++) {
                        bArr4[i5] = bArr[(i + 3) + i5];
                        if ((bArr4[i5] & 255) < 166) {
                            i4 = true;
                        }
                    }
                    this.ek.mode = 1;
                    this.ek.back_cnt = 4;
                    this.ek.bmax1 = 4;
                    this.ek.bmax2 = 11;
                    this.ek.bmax3 = 11;
                    this.ek.bmax4 = 4;
                    this.ek.b1 = dc(bArr4[0] & 255, 4, 60);
                    this.ek.b2 = dc(bArr4[1] & 255, 11, 165);
                    this.ek.b3 = dc(bArr4[2] & 255, 11, 165);
                    this.ek.b4 = dc(bArr4[3] & 255, 4, 60);
                    i3 = ((bArr4[4] & 240) >> 4) & 3;
                    if (i3 == 1) {
                        this.ek.bc1 = -1;
                    } else if (i3 == 2) {
                        this.ek.bc1 = -256;
                    } else if (i3 == 3) {
                        this.ek.bc1 = -65536;
                    } else {
                        this.ek.bc1 = -65536;
                    }
                    i3 = (bArr4[4] & 15) & 3;
                    if (i3 == 1) {
                        this.ek.bc2 = -1;
                    } else if (i3 == 2) {
                        this.ek.bc2 = -256;
                    } else if (i3 == 3) {
                        this.ek.bc2 = -65536;
                    } else {
                        this.ek.bc2 = -65536;
                    }
                    if (i3 == 1) {
                        this.ek.bc3 = -1;
                    } else if (i3 == 2) {
                        this.ek.bc3 = -256;
                    } else if (i3 == 3) {
                        this.ek.bc3 = -65536;
                    } else {
                        this.ek.bc3 = -65536;
                    }
                    i3 = (bArr4[5] & 15) & 3;
                    if (i3 == 1) {
                        this.ek.bc4 = -1;
                    } else if (i3 == 2) {
                        this.ek.bc4 = -256;
                    } else if (i3 == 3) {
                        this.ek.bc4 = -65536;
                    } else {
                        this.ek.bc4 = -65536;
                    }
                    if (i4 != 0) {
                        dd(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 35:
                if (i4 != 0 && bArr[i + 2] == (byte) 6) {
                    bArr4 = new byte[6];
                    i4 = 0;
                    for (i5 = 0; i5 < 6; i5++) {
                        bArr4[i5] = bArr[(i + 3) + i5];
                        if ((bArr4[i5] & 255) < 166) {
                            i4 = true;
                        }
                    }
                    this.ek.mode = 1;
                    this.ek.front_cnt = 4;
                    this.ek.fmax1 = 4;
                    this.ek.fmax2 = 8;
                    this.ek.fmax3 = 8;
                    this.ek.fmax4 = 4;
                    this.ek.f1 = dc(bArr4[0] & 255, 4, 60);
                    this.ek.f2 = dc(bArr4[1] & 255, 8, 120);
                    this.ek.f3 = dc(bArr4[2] & 255, 8, 120);
                    this.ek.f4 = dc(bArr4[3] & 255, 4, 60);
                    i3 = ((bArr4[4] & 240) >> 4) & 3;
                    if (i3 == 1) {
                        this.ek.fc1 = -1;
                    } else if (i3 == 2) {
                        this.ek.fc1 = -256;
                    } else if (i3 == 3) {
                        this.ek.fc1 = -65536;
                    } else {
                        this.ek.fc1 = -65536;
                    }
                    i3 = (bArr4[4] & 15) & 3;
                    if (i3 == 1) {
                        this.ek.fc2 = -1;
                    } else if (i3 == 2) {
                        this.ek.fc2 = -256;
                    } else if (i3 == 3) {
                        this.ek.fc2 = -65536;
                    } else {
                        this.ek.fc2 = -65536;
                    }
                    if (i3 == 1) {
                        this.ek.fc3 = -1;
                    } else if (i3 == 2) {
                        this.ek.fc3 = -256;
                    } else if (i3 == 3) {
                        this.ek.fc3 = -65536;
                    } else {
                        this.ek.fc3 = -65536;
                    }
                    i3 = (bArr4[5] & 15) & 3;
                    if (i3 == 1) {
                        this.ek.fc4 = -1;
                    } else if (i3 == 2) {
                        this.ek.fc4 = -256;
                    } else if (i3 == 3) {
                        this.ek.fc4 = -65536;
                    } else {
                        this.ek.fc4 = -65536;
                    }
                    if (i4 != 0) {
                        dd(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[2];
                    bArr3[0] = bArr[i + 3];
                    if (((byte) (bArr3[0] & 1)) == (byte) 1) {
                        cx(bArr3);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 37:
                if (bArr[i + 2] == (byte) 1 || bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[]{(byte) 37, (byte) 1, bArr[i + 3]};
                    this.ej.oj(bArr3);
                    if ((bArr3[2] & 2) != 0) {
                        this.ek.view_show = false;
                        this.ek.mode = 1;
                        dd(this.ek);
                        return;
                    }
                    this.ek.view_show = true;
                    dd(this.ek);
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] != (byte) 7) {
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] == (byte) 3) {
                    String str;
                    byte b = bArr[i + 3];
                    i4 = ((bArr[i + 5] & 255) << 8) | (bArr[i + 4] & 255);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    String str2 = "";
                    if (((byte) (b & 1)) == (byte) 1) {
                        str = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf(((float) i4) * 0.1f)}) + this.el.getString(R.string.f_dan);
                    } else {
                        str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(((float) i4) * 0.1f)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] == (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 19918;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] != (byte) 2) {
                    return;
                }
                return;
            case (byte) 64:
                if (bArr[i + 2] == (byte) 5) {
                    bArr3 = new byte[7];
                    bArr3[0] = (byte) 64;
                    bArr3[1] = (byte) 5;
                    while (i3 < 5) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    if (bArr3[2] == (byte) -96 && (bArr3[3] & 16) != 0 && db()) {
                        da(1, bArr3);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 80:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[8];
                    bArr3[0] = (byte) 80;
                    bArr3[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 96:
                if (bArr[i + 2] == (byte) 8) {
                    bArr3 = new byte[10];
                    bArr3[0] = (byte) 96;
                    bArr3[1] = (byte) 8;
                    while (i3 < 8) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 97:
                bArr3 = new byte[(bArr[i + 2] + 2)];
                bArr3[0] = (byte) 97;
                bArr3[1] = bArr[i + 2];
                byte b2;
                while (b2 < bArr[i + 2]) {
                    bArr3[b2 + 2] = bArr[(i + 3) + b2];
                    b2++;
                }
                this.ej.oj(bArr3);
                return;
            case (byte) 98:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[6];
                    bArr3[0] = (byte) 98;
                    bArr3[1] = (byte) 4;
                    while (i3 < 4) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 99:
                i4 = bArr[i + 2] & 127;
                bArr2 = new byte[(i4 + 2)];
                bArr2[0] = (byte) 99;
                bArr2[1] = bArr[i + 2];
                while (i3 < i4) {
                    bArr2[i3 + 2] = bArr[(i + 3) + i3];
                    i3++;
                }
                this.ej.oj(bArr2);
                return;
            case (byte) 100:
                if (bArr[i + 2] == (byte) 5) {
                    bArr3 = new byte[7];
                    bArr3[0] = (byte) 100;
                    bArr3[1] = (byte) 5;
                    while (i3 < 5) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 101:
                if (bArr[i + 2] >= (byte) 4) {
                    this.ej.oj(Arrays.copyOfRange(bArr, i + 1, i2));
                    return;
                }
                return;
            default:
                return;
        }
    }

    void cx(byte[] bArr) {
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

    public void cz(int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            switch (i) {
                case 3841:
                    if (!this.ei.onOff) {
                        i3 = 1;
                    }
                    cy(178, i3);
                    return;
                case 3846:
                    if (this.ei.modeAuto != 1) {
                        i3 = 1;
                    }
                    cy(187, i3);
                    return;
                case 3857:
                    if (this.ei.modeDual != 1) {
                        i3 = 1;
                    }
                    cy(179, i3);
                    return;
                default:
                    return;
            }
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
    }

    public void mo37e() {
        mo40h(null, 1);
    }

    public void mo38f() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 3;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public int[][] getAirBtnTable() {
        int[][] iArr = new int[16][];
        iArr[0] = new int[]{3841};
        iArr[1] = new int[]{3842};
        iArr[2] = new int[]{3843};
        iArr[3] = new int[]{3844};
        iArr[4] = new int[]{3845};
        iArr[5] = new int[]{3846};
        iArr[6] = new int[]{3847};
        iArr[7] = new int[]{3848};
        iArr[8] = new int[]{3849};
        iArr[9] = new int[]{3850};
        iArr[10] = new int[]{3851};
        iArr[11] = new int[]{3852};
        iArr[12] = new int[]{3853};
        iArr[13] = new int[]{3855};
        iArr[14] = new int[]{3856};
        iArr[15] = new int[]{3857};
        return iArr;
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 5;
        bArr[1] = (byte) 64;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo41i() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 10;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 2;
        bArr[1] = (byte) 19;
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[5] = (byte) ((i2 / 60) / 60);
        bArr[6] = (byte) ((i2 / 60) % 60);
        bArr[7] = (byte) (i2 % 60);
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
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
        this.ej.ob((byte) -64, new byte[8], 8);
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

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[7];
        if (!DateFormat.is24HourFormat(this.el)) {
            this.en += 128;
        }
        bArr[0] = (byte) ((time.year - 2000) & 255);
        bArr[1] = (byte) ((time.month + 1) & 255);
        bArr[2] = (byte) (time.monthDay & 255);
        bArr[3] = (byte) (this.en & 255);
        bArr[4] = (byte) (this.eo & 255);
        bArr[6] = (byte) 1;
        this.ej.ob((byte) -90, bArr, 7);
    }

    public void mo50r(int i) {
    }
}
