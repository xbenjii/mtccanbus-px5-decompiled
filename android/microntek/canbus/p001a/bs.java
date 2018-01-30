package android.microntek.canbus.p001a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.BaseApplication;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.UserHandle;
import android.provider.Settings.System;
import android.util.Log;
import java.util.Locale;

public class bs extends C0001b {
    private int bh;
    private byte[] bi;

    public bs(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.bh = 51;
        this.bi = new byte[128];
        this.eh = 61;
    }

    private void gq(byte[] bArr) {
        this.ei.seatShow = false;
        this.ei.modeAuto = 0;
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
        if ((bArr[0] & 1) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        byte b = bArr[1];
        if ((b & 128) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((b & 64) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((b & 32) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        if ((b & 224) == 0 || (bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        }
        this.ei.windLevel = bArr[1] & 15;
        if (this.ei.windLevel == 0) {
            this.ei.modeAuto = 1;
        }
        if (this.ei.windLevel >= 1) {
            this.ei.windLevel--;
        }
        this.ei.windLevelMax = 8;
        this.ei.tempUnit = (bArr[4] & 1) == 1;
        if ((bArr[4] & 2) == 0) {
            this.ei.tempLeft = gy(bArr[2] & 255);
            this.ei.tempRight = gy(bArr[3] & 255);
        } else {
            this.ei.tempLeft = -1;
            this.ei.tempRight = -1;
        }
        if ((bArr[0] & 32) == 0) {
            this.ei.windLoop = 0;
        } else if ((bArr[4] & 32) != 0) {
            this.ei.windLoop = 2;
        } else {
            this.ei.windLoop = 1;
        }
        if ((bArr[4] & 4) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    private int gs(byte b, int i) {
        return ((b >> i) & 1) == 1 ? 1 : 0;
    }

    private int gt(byte b, int i) {
        return ((b >> i) & 1) == 1 ? 61441 : 0;
    }

    private int gu(byte b, int i) {
        return ((b >> 4) & 7) == i ? 61441 : 0;
    }

    private int[][][] gv(byte[] bArr) {
        int[][][] iArr = new int[17][][];
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[0], 0), R.string.warn61_0_0, R.drawable.warn_icon_28};
        r1[1] = new int[]{gs(bArr[0], 1), R.string.warn61_0_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[0], 2), R.string.warn61_0_2, R.drawable.warn_icon_23};
        r1[3] = new int[]{gs(bArr[0], 3), R.string.warn61_0_3, R.drawable.warn_icon_23};
        r1[4] = new int[]{gs(bArr[0], 4), R.string.warn61_0_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[0], 5), R.string.warn61_0_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[0], 6), R.string.warn61_0_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[0], 7), R.string.warn61_0_7, R.drawable.warn_icon_24};
        iArr[0] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[1], 0), R.string.warn61_1_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[1], 1), R.string.warn61_1_1, R.drawable.warn_icon_07};
        r1[2] = new int[]{gs(bArr[1], 2), R.string.warn61_1_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[1], 3), R.string.warn61_1_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[1], 4), R.string.warn61_1_4, R.drawable.warn_icon_01};
        r1[5] = new int[]{gs(bArr[1], 5), R.string.warn61_1_5, R.drawable.warn_icon_02};
        r1[6] = new int[]{gs(bArr[1], 6), R.string.warn61_1_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[1], 7), R.string.warn61_1_7, R.drawable.warn_icon_24};
        iArr[1] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[2], 0), R.string.warn61_2_0, R.drawable.warn_icon_29};
        r1[1] = new int[]{gs(bArr[2], 1), R.string.warn61_2_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[2], 2), R.string.warn61_2_2, R.drawable.warn_icon_04};
        r1[3] = new int[]{gs(bArr[2], 3), R.string.warn61_2_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[2], 4), R.string.warn61_2_4, R.drawable.warn_icon_06};
        r1[5] = new int[]{gs(bArr[2], 5), R.string.warn61_2_5, R.drawable.warn_icon_15};
        r1[6] = new int[]{gs(bArr[2], 6), R.string.warn61_2_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[2], 7), R.string.warn61_2_7, R.drawable.warn_icon_30};
        iArr[2] = r1;
        r1 = new int[8][];
        r1[1] = new int[]{gs(bArr[3], 1), R.string.warn61_3_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[3], 2), R.string.warn61_3_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[3], 3), R.string.warn61_3_3, R.drawable.warn_icon_31};
        r1[4] = new int[]{gs(bArr[3], 4), R.string.warn61_3_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[3], 5), R.string.warn61_3_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[3], 6), R.string.warn61_3_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[3], 7), R.string.warn61_3_7, R.drawable.warn_icon_10};
        iArr[3] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[4], 0), R.string.warn61_4_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[4], 1), R.string.warn61_4_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[4], 2), R.string.warn61_4_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[4], 3), R.string.warn61_4_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[4], 4), R.string.warn61_4_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[4], 5), R.string.warn61_4_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[4], 6), R.string.warn61_4_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{0, R.string.warn61_4_7, R.drawable.warn_icon_10};
        iArr[4] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[5], 0), R.string.warn61_5_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[5], 1), R.string.warn61_5_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[5], 2), R.string.warn61_5_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[5], 3), R.string.warn61_5_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[5], 4), R.string.warn61_5_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[5], 5), R.string.warn61_5_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[5], 6), R.string.warn61_5_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[5], 7), R.string.warn61_5_7, R.drawable.warn_icon_10};
        iArr[5] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[6], 0), R.string.warn61_6_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[6], 1), R.string.warn61_6_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[6], 2), R.string.warn61_6_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[6], 3), R.string.warn61_6_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[6], 4), R.string.warn61_6_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[6], 5), R.string.warn61_6_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[6], 6), R.string.warn61_6_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[6], 7), R.string.warn61_6_7, R.drawable.warn_icon_10};
        iArr[6] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[7], 0), R.string.warn61_7_0, R.drawable.warn_icon_32};
        r1[1] = new int[]{gs(bArr[7], 1), R.string.warn61_7_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[7], 2), R.string.warn61_7_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[7], 3), R.string.warn61_7_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[7], 4), R.string.warn61_7_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[7], 5), R.string.warn61_7_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[7], 6), R.string.warn61_7_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[7], 7), R.string.warn61_7_7, R.drawable.warn_icon_10};
        iArr[7] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[8], 0), R.string.warn61_8_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[8], 1), R.string.warn61_8_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[8], 2), R.string.warn61_8_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[8], 3), R.string.warn61_8_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[8], 4), R.string.warn61_8_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[8], 5), R.string.warn61_8_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[8], 6), R.string.warn61_8_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[8], 7), R.string.warn61_8_7, R.drawable.warn_icon_10};
        iArr[8] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[9], 0), R.string.warn61_9_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[9], 1), R.string.warn61_9_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[9], 2), R.string.warn61_9_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[9], 3), R.string.warn61_9_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[9], 4), R.string.warn61_9_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[9], 5), R.string.warn61_9_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[9], 6), R.string.warn61_9_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[9], 7), R.string.warn61_9_7, R.drawable.warn_icon_10};
        iArr[9] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[10], 0), R.string.warn61_10_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[10], 1), R.string.warn61_10_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[10], 2), R.string.warn61_10_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[10], 3), R.string.warn61_10_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[10], 4), R.string.warn61_10_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[10], 5), R.string.warn61_10_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[10], 6), R.string.warn61_10_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[10], 7), R.string.warn61_10_7, R.drawable.warn_icon_10};
        iArr[10] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[11], 0), R.string.warn61_11_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[11], 1), R.string.warn61_11_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[11], 2), R.string.warn61_11_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[11], 3), R.string.warn61_11_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[11], 4), R.string.warn61_11_4, R.drawable.warn_icon_31};
        r1[5] = new int[]{gs(bArr[11], 5), R.string.warn61_11_5, R.drawable.warn_icon_07};
        r1[6] = new int[]{gs(bArr[11], 6), R.string.warn61_11_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[11], 7), R.string.warn61_11_7, R.drawable.warn_icon_30};
        iArr[11] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[12], 0), R.string.warn61_12_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[12], 1), R.string.warn61_12_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[12], 2), R.string.warn61_12_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gs(bArr[12], 3), R.string.warn61_12_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gs(bArr[12], 4), R.string.warn61_12_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gs(bArr[12], 5), R.string.warn61_12_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gs(bArr[12], 6), R.string.warn61_12_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gs(bArr[12], 7), R.string.warn61_12_7, R.drawable.warn_icon_10};
        iArr[12] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gs(bArr[13], 0), R.string.warn61_13_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gs(bArr[13], 1), R.string.warn61_13_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gs(bArr[13], 2), R.string.warn61_13_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{0, R.string.warn61_13_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{0, R.string.warn61_13_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{0, R.string.warn61_13_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{0, R.string.warn61_13_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{0, R.string.warn61_13_7, R.drawable.warn_icon_10};
        iArr[13] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gt(bArr[21], 0), R.string.warn61_21_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gt(bArr[21], 1), R.string.warn61_21_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gt(bArr[21], 2), R.string.warn61_21_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gt(bArr[21], 3), R.string.warn61_21_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gt(bArr[21], 4), R.string.warn61_21_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gt(bArr[21], 5), R.string.warn61_21_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gt(bArr[21], 6), R.string.warn61_21_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gt(bArr[21], 7), R.string.warn61_21_7, R.drawable.warn_icon_10};
        iArr[14] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gt(bArr[22], 0), R.string.warn61_22_0, R.drawable.warn_icon_10};
        r1[1] = new int[]{gt(bArr[22], 1), R.string.warn61_22_1, R.drawable.warn_icon_10};
        r1[2] = new int[]{gt(bArr[22], 2), R.string.warn61_22_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gt(bArr[22], 3), R.string.warn61_22_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{gu(bArr[22], 1), R.string.warn61_22_4, R.drawable.warn_icon_10};
        r1[5] = new int[]{gu(bArr[22], 2), R.string.warn61_22_5, R.drawable.warn_icon_10};
        r1[6] = new int[]{gu(bArr[22], 3), R.string.warn61_22_6, R.drawable.warn_icon_10};
        r1[7] = new int[]{gu(bArr[22], 4), R.string.warn61_22_6_1, R.drawable.warn_icon_10};
        iArr[15] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{gt(bArr[23], 0), R.string.warn61_23_0, R.drawable.warn_icon_27};
        r1[1] = new int[]{gt(bArr[23], 1), R.string.warn61_23_1, R.drawable.warn_icon_27};
        r1[2] = new int[]{gt(bArr[23], 2), R.string.warn61_23_2, R.drawable.warn_icon_10};
        r1[3] = new int[]{gt(bArr[23], 3), R.string.warn61_23_3, R.drawable.warn_icon_10};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{0};
        iArr[16] = r1;
        return iArr;
    }

    private void gw(byte b) {
        this.ej.ob(Byte.MIN_VALUE, new byte[]{(byte) 11, b}, 2);
    }

    private void gx(int i) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.microntek.controlinfo", "com.microntek.controlinfo.canbus61carinfo"));
        intent.addFlags(807600128);
        intent.putExtra("cftype", i);
        try {
            this.ej.startActivityAsUser(intent, UserHandle.CURRENT_OR_SELF);
        } catch (ActivityNotFoundException e) {
            Log.e("Canbus50", "controlinfo activity not found! " + e.getMessage());
        }
    }

    private int gy(int i) {
        return BaseApplication.py().qj() == 3 ? i == 0 ? 0 : i == 255 ? 65535 : (this.ei.tempUnit || i < 32 || i > 60) ? (!this.ei.tempUnit || i > 99) ? -1 : i * 10 : ((i * 10) / 2) - 20 : i != 0 ? i == 255 ? 65535 : (this.ei.tempUnit || i < 1 || i > 254) ? (!this.ei.tempUnit || i > 99) ? -1 : i * 10 : (i * 10) / 2 : 0;
    }

    private int gz(int i) {
        return ((((i / 1000) / 60) / 60) * 60) + (((i / 1000) / 60) % 60);
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void al() {
        this.ej.ob((byte) -112, new byte[]{(byte) 33, (byte) 0, (byte) 0, (byte) 0}, 4);
    }

    public void at() {
        this.ej.ob((byte) -112, new byte[]{(byte) 41, (byte) 0, (byte) 0, (byte) 0}, 4);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        Intent intent;
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case (byte) -22:
                if (bArr[i + 2] < (byte) 2) {
                    return;
                }
                return;
            case (byte) 32:
                if (bArr[i + 2] >= (byte) 2 && bArr[i + 3] == (byte) 32 && bArr[i + 4] == (byte) 1) {
                    if (this.bh == 54) {
                        this.bh = 51;
                    }
                    gx(this.bh);
                    this.bh++;
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 9) {
                    byte[] bArr3 = new byte[9];
                    for (i4 = 0; i4 < 9; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    gq(bArr3);
                    this.ej.od(this.ei);
                    ly(bArr, i, i2);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 5450;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] >= (byte) 7) {
                    int[] iArr = new int[7];
                    for (i4 = 1; i4 < 7; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] >= 5) {
                            iArr[i4] = 0;
                        } else {
                            iArr[i4] = 5 - iArr[i4];
                        }
                    }
                    mb();
                    this.ek.max = 5;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = iArr[1];
                    this.ek.b2 = iArr[2];
                    this.ek.b3 = iArr[3];
                    this.ek.front_cnt = 3;
                    this.ek.f1 = iArr[4];
                    this.ek.f2 = iArr[5];
                    this.ek.f3 = iArr[6];
                    if (bArr[i + 3] == (byte) 2) {
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 51:
                if (bArr[i + 2] >= (byte) 9) {
                    bArr2 = new byte[11];
                    bArr2[0] = (byte) 51;
                    bArr2[1] = (byte) 9;
                    while (i3 < 9) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 52:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 52;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 53:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 53;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 54:
                if (bArr[i + 2] >= (byte) 1) {
                    String str;
                    i4 = bArr[i + 3] & 255;
                    String str2 = "";
                    if ((i4 & 128) != 0) {
                        i4 = 0 - (i4 & 127);
                    }
                    if (this.ei.tempUnit) {
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf((int) ((((double) i4) * 1.8d) + 32.0d))}) + this.el.getString(R.string.f_dan);
                    } else {
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i4)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 55:
                if (bArr[i + 2] >= (byte) 21 && System.getInt(this.ej.getContentResolver(), "mOff_Warning_infor", 1) == 1) {
                    for (i4 = 0; i4 < 21; i4++) {
                        this.bi[i4] = bArr[(i + 3) + i4];
                    }
                    this.es.le(gv(this.bi));
                    this.es.lf(gv(this.bi).length);
                    mf();
                    for (i4 = 0; i4 < 21; i4++) {
                        this.bi[i4] = (byte) 0;
                    }
                    return;
                }
                return;
            case (byte) 56:
                if (bArr[i + 2] >= (byte) 6) {
                    gr(new byte[]{bArr[i + 3]});
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 56;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 57:
                if (bArr[i + 2] >= (byte) 8) {
                    for (i4 = 0; i4 < 8; i4++) {
                        this.bi[i4 + 21] = bArr[(i + 3) + i4];
                    }
                    this.es.le(gv(this.bi));
                    this.es.lf(gv(this.bi).length);
                    mf();
                    for (i4 = 0; i4 < 8; i4++) {
                        this.bi[i4 + 21] = (byte) 0;
                    }
                    return;
                }
                return;
            case (byte) 58:
                i4 = bArr[i + 2] & 255;
                if (i4 >= 2) {
                    byte[] bArr4 = new byte[(i4 + 2)];
                    bArr4[0] = (byte) 58;
                    bArr4[1] = (byte) i4;
                    while (i3 < i4) {
                        bArr4[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr4);
                    return;
                }
                return;
            case (byte) 59:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 59;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 60:
                if (bArr[i + 2] >= (byte) 12) {
                    bArr2 = new byte[4];
                    bArr2[0] = (byte) 60;
                    bArr2[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 61:
                if (bArr[i + 2] >= (byte) 12) {
                    bArr2 = new byte[14];
                    bArr2[0] = (byte) 59;
                    bArr2[1] = (byte) 12;
                    while (i3 < 12) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 62:
                if (bArr[i + 2] < (byte) 1) {
                    return;
                }
                return;
            case (byte) 68:
                if (bArr[i + 2] < (byte) 1) {
                    return;
                }
                return;
            case (byte) 74:
                if (bArr[i + 2] >= (byte) 21) {
                    bArr2 = new byte[23];
                    bArr2[0] = (byte) 74;
                    bArr2[1] = (byte) 21;
                    while (i3 < 21) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
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
        byte b = me() == 7 ? (byte) 1 : me() == 10 ? (byte) 2 : (byte) 0;
        this.ej.ob((byte) -54, new byte[]{b}, 1);
        mo48p();
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        mo41i();
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public int[][] getAirBtnTable() {
        int i = 0;
        int[][] iArr = new int[13][];
        int[] iArr2 = new int[5];
        iArr2[0] = 3841;
        iArr2[1] = 46;
        iArr2[2] = 35330;
        iArr2[3] = 3;
        iArr2[4] = this.ei.acMax == 1 ? 0 : 1;
        iArr[0] = iArr2;
        iArr2 = new int[5];
        iArr2[0] = 3842;
        iArr2[1] = 46;
        iArr2[2] = 35330;
        iArr2[3] = 2;
        iArr2[4] = this.ei.modeAc ? 0 : 1;
        iArr[1] = iArr2;
        iArr2 = new int[5];
        iArr2[0] = 3846;
        iArr2[1] = 46;
        iArr2[2] = 35330;
        iArr2[3] = 1;
        iArr2[4] = this.ei.modeAuto == 1 ? 0 : 1;
        iArr[2] = iArr2;
        iArr[3] = new int[]{3847, 46, 35330, 4, 1};
        iArr[4] = new int[]{3848, 46, 35330, 4, 2};
        iArr[5] = new int[]{3849, 46, 35330, 5, 1};
        iArr[6] = new int[]{3850, 46, 35330, 5, 2};
        iArr[7] = new int[]{3855, 46, 35330, 10, 2};
        iArr[8] = new int[]{3856, 46, 35330, 10, 1};
        iArr2 = new int[5];
        iArr2[0] = 3857;
        iArr2[1] = 46;
        iArr2[2] = 35330;
        iArr2[3] = 11;
        iArr2[4] = this.ei.modeDual == 1 ? 0 : 1;
        iArr[9] = iArr2;
        iArr2 = new int[5];
        iArr2[0] = 3858;
        iArr2[1] = 46;
        iArr2[2] = 35330;
        iArr2[3] = 8;
        iArr2[4] = this.ei.windDown ? 0 : 1;
        iArr[10] = iArr2;
        iArr2 = new int[5];
        iArr2[0] = 3859;
        iArr2[1] = 46;
        iArr2[2] = 35330;
        iArr2[3] = 7;
        iArr2[4] = this.ei.windUp ? 0 : 1;
        iArr[11] = iArr2;
        int[] iArr3 = new int[5];
        iArr3[0] = 3860;
        iArr3[1] = 46;
        iArr3[2] = 35330;
        iArr3[3] = 6;
        if (!this.ei.windMid) {
            i = 1;
        }
        iArr3[4] = i;
        iArr[12] = iArr3;
        return iArr;
    }

    void gr(byte[] bArr) {
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

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 5;
        bArr[1] = (byte) 64;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo41i() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 12;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo42j(int i, int i2, int i3) {
        mo46n();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 8, (byte) 19, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (gz(i3) & 255), (byte) ((i3 / 1000) % 60)}, 8);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[8], 8);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 1;
        this.ej.ob((byte) -64, bArr, 8);
        bArr = new byte[4];
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) 1;
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
            bArr[3] = (byte) (b2 + 1);
        } else if (b == (byte) 3 || b == (byte) 4) {
            bArr[0] = (byte) 16;
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
            bArr[3] = (byte) (b2 + 1);
        }
        this.ej.ob((byte) -62, bArr, 4);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            gw((byte) 1);
        } else if (language.equals("en")) {
            gw((byte) 0);
        } else if (language.equals("de")) {
            gw((byte) 4);
        } else if (language.equals("it")) {
            gw((byte) 6);
        } else if (!language.equals("sv")) {
            if (language.equals("es")) {
                gw((byte) 5);
            } else if (language.equals("nl")) {
                gw((byte) 7);
            } else if (language.equals("pt")) {
                gw((byte) 16);
            } else if (!language.equals("nb")) {
                if (language.equals("fi")) {
                    gw((byte) 13);
                } else if (!language.equals("da")) {
                    if (language.equals("pl")) {
                        gw((byte) 8);
                    } else if (language.equals("tr")) {
                        gw((byte) 9);
                    } else if (!language.equals("ar")) {
                        if (language.equals("ru")) {
                            gw((byte) 2);
                        } else if (!language.equals("uk") && !language.equals("sk") && !language.equals("cs") && !language.equals("hu") && !language.equals("el") && !language.equals("ko") && language.equals("fr")) {
                            gw((byte) 3);
                        }
                    }
                }
            }
        }
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
        byte[] bArr = new byte[]{(byte) i};
        if (i == 0) {
            bArr[0] = (byte) (bArr[0] | 128);
        }
        this.ej.ob((byte) -60, bArr, 1);
    }
}
