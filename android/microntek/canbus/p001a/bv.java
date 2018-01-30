package android.microntek.canbus.p001a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import java.util.Locale;

public class bv extends C0001b {
    private static final String[] bq = new String[]{"com.microntek.sync"};
    private byte[] bm;
    private boolean bn;
    private boolean bo;
    private boolean bp;

    public bv(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.bo = true;
        this.bn = true;
        this.bm = new byte[6];
        this.bp = true;
        this.eh = 62;
    }

    private void hi(byte[] bArr) {
        this.ei.seatShow = false;
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
        if ((bArr[0] & 2) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
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
        this.ei.tempUnit = (bArr[4] & 64) != 0;
        int i = bArr[2] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 127) {
            this.ei.tempLeft = 65535;
        } else if (i < 31 || i > 59) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = i * 5;
            if (this.ei.tempUnit) {
                this.ei.tempLeft = ((this.ei.tempLeft * 9) / 5) + 320;
            }
        }
        i = bArr[3] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 127) {
            this.ei.tempRight = 65535;
        } else if (i < 31 || i > 59) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = i * 5;
            if (this.ei.tempUnit) {
                this.ei.tempRight = ((this.ei.tempRight * 9) / 5) + 320;
            }
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        if ((bArr[4] & 4) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    private int hk(byte b, int i) {
        return ((b >> i) & 1) == 1 ? 1 : 0;
    }

    private int[][][] hl(byte[] bArr) {
        r0 = new int[16][][];
        r1 = new int[8][];
        r1[0] = new int[]{0};
        r1[1] = new int[]{hk(bArr[0], 1), R.string.warn0_1, R.drawable.warn_icon_06, R.drawable.warn_icon_state_2};
        r1[2] = new int[]{hk(bArr[0], 2), R.string.warn0_2, R.drawable.warn_icon_05, R.drawable.warn_icon_state_2};
        r1[3] = new int[]{hk(bArr[0], 3), R.string.warn0_3, R.drawable.warn_icon_05, R.drawable.warn_icon_state_1};
        r1[4] = new int[]{hk(bArr[0], 4), R.string.warn0_4, R.drawable.warn_icon_09, R.drawable.warn_icon_state_0};
        r1[5] = new int[]{hk(bArr[0], 5), R.string.warn0_5, R.drawable.warn_icon_22, R.drawable.warn_icon_state_0};
        r1[6] = new int[]{hk(bArr[0], 6), R.string.warn0_6, R.drawable.warn_icon_23, R.drawable.warn_icon_state_1};
        r1[7] = new int[]{hk(bArr[0], 7), R.string.warn0_7, R.drawable.warn_icon_17, R.drawable.warn_icon_state_1};
        r0[0] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[1], 0), R.string.warn1_0, R.drawable.warn_icon_04, R.drawable.warn_icon_state_1};
        r1[1] = new int[]{hk(bArr[1], 1), R.string.warn1_1, R.drawable.warn_icon_07, R.drawable.warn_icon_state_2};
        r1[2] = new int[]{hk(bArr[1], 2), R.string.warn1_2, R.drawable.warn_icon_08, R.drawable.warn_icon_state_2};
        r1[3] = new int[]{hk(bArr[1], 3), R.string.warn1_3, R.drawable.warn_icon_01, R.drawable.warn_icon_state_1};
        r1[4] = new int[]{hk(bArr[1], 4), R.string.warn1_4, R.drawable.warn_icon_02, R.drawable.warn_icon_state_0};
        r1[5] = new int[]{hk(bArr[1], 5), R.string.warn1_5, R.drawable.warn_icon_02, R.drawable.warn_icon_state_0};
        r1[6] = new int[]{hk(bArr[1], 6), R.string.warn1_6, R.drawable.warn_icon_24, R.drawable.warn_icon_state_1};
        r1[7] = new int[]{0};
        r0[1] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{0};
        r1[1] = new int[]{0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{hk(bArr[2], 7), R.string.warn2_7, R.drawable.warn_icon_27, R.drawable.warn_icon_state_0};
        r0[2] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[3], 0), R.string.warn3_0, R.drawable.warn_icon_26, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{hk(bArr[3], 1), R.string.warn3_1, R.drawable.warn_icon_26, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{0};
        r0[3] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[4], 0), R.string.warn4_0, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{hk(bArr[4], 1), R.string.warn4_1, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{hk(bArr[4], 2), R.string.warn4_2, R.drawable.warn_icon_19, R.drawable.warn_icon_state_1};
        r1[3] = new int[]{hk(bArr[4], 3), R.string.warn4_3, R.drawable.warn_icon_27, R.drawable.warn_icon_state_0};
        r1[4] = new int[]{hk(bArr[4], 4), R.string.warn4_4, R.drawable.warn_icon_11, R.drawable.warn_icon_state_0};
        r1[5] = new int[]{hk(bArr[4], 5), R.string.warn4_5, R.drawable.warn_icon_19, R.drawable.warn_icon_state_1};
        r1[6] = new int[]{hk(bArr[4], 6), R.string.warn4_6, R.drawable.warn_icon_default, R.drawable.warn_icon_state_1};
        r1[7] = new int[]{hk(bArr[4], 7), R.string.warn4_7, R.drawable.warn_icon_default, R.drawable.warn_icon_state_1};
        r0[4] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[5], 0), R.string.warn5_0, R.drawable.warn_icon_27, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{hk(bArr[5], 1), R.string.warn5_1, R.drawable.warn_icon_19, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{hk(bArr[5], 2), R.string.warn5_2, R.drawable.warn_icon_25, R.drawable.warn_icon_state_0};
        r1[3] = new int[]{hk(bArr[5], 3), R.string.warn5_3, R.drawable.warn_icon_24, R.drawable.warn_icon_state_2};
        r1[4] = new int[]{hk(bArr[5], 4), R.string.warn5_4, R.drawable.warn_icon_24, R.drawable.warn_icon_state_2};
        r1[5] = new int[]{hk(bArr[5], 5), R.string.warn5_5, R.drawable.warn_icon_19, R.drawable.warn_icon_state_1};
        r1[6] = new int[]{hk(bArr[5], 6), R.string.warn5_6, R.drawable.warn_icon_19, R.drawable.warn_icon_state_1};
        r1[7] = new int[]{hk(bArr[5], 7), R.string.warn5_7, R.drawable.warn_icon_12, R.drawable.warn_icon_state_1};
        r0[5] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[6], 0), R.string.warn6_0, R.drawable.warn_icon_default, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{hk(bArr[6], 1), R.string.warn6_1, R.drawable.warn_icon_15, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{hk(bArr[6], 2), R.string.warn6_2, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[3] = new int[]{hk(bArr[6], 3), R.string.warn6_3, R.drawable.warn_icon_27, R.drawable.warn_icon_state_0};
        r1[4] = new int[]{hk(bArr[6], 4), R.string.warn6_4, R.drawable.warn_icon_default, R.drawable.warn_icon_state_0};
        r1[5] = new int[]{hk(bArr[6], 5), R.string.warn6_5, R.drawable.warn_icon_03, R.drawable.warn_icon_state_0};
        r1[6] = new int[]{hk(bArr[6], 6), R.string.warn6_6, R.drawable.warn_icon_27, R.drawable.warn_icon_state_2};
        r1[7] = new int[]{hk(bArr[6], 7), R.string.warn6_7, R.drawable.warn_icon_20, R.drawable.warn_icon_state_2};
        r0[6] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[7], 0), R.string.warn7_0, R.drawable.warn_icon_11, R.drawable.warn_icon_state_1};
        r1[1] = new int[]{hk(bArr[7], 1), R.string.warn7_1, R.drawable.warn_icon_11, R.drawable.warn_icon_state_1};
        r1[2] = new int[]{hk(bArr[7], 2), R.string.warn7_2, R.drawable.warn_icon_11, R.drawable.warn_icon_state_1};
        r1[3] = new int[]{hk(bArr[7], 3), R.string.warn7_3, R.drawable.warn_icon_11, R.drawable.warn_icon_state_1};
        r1[4] = new int[]{hk(bArr[7], 4), R.string.warn7_4, R.drawable.warn_icon_06, R.drawable.warn_icon_state_0};
        r1[5] = new int[]{hk(bArr[7], 5), R.string.warn7_5, R.drawable.warn_icon_06, R.drawable.warn_icon_state_0};
        r1[6] = new int[]{hk(bArr[7], 6), R.string.warn7_6, R.drawable.warn_icon_06, R.drawable.warn_icon_state_0};
        r1[7] = new int[]{hk(bArr[7], 7), R.string.warn7_7, R.drawable.warn_icon_06, R.drawable.warn_icon_state_0};
        r0[7] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[8], 0), R.string.warn8_0, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{hk(bArr[8], 1), R.string.warn8_1, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{hk(bArr[8], 2), R.string.warn8_2, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[3] = new int[]{hk(bArr[8], 3), R.string.warn8_3, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[4] = new int[]{hk(bArr[8], 4), R.string.warn8_4, R.drawable.warn_icon_10, R.drawable.warn_icon_state_1};
        r1[5] = new int[]{hk(bArr[8], 5), R.string.warn8_5, R.drawable.warn_icon_10, R.drawable.warn_icon_state_1};
        r1[6] = new int[]{hk(bArr[8], 6), R.string.warn8_6, R.drawable.warn_icon_16, R.drawable.warn_icon_state_1};
        r1[7] = new int[]{hk(bArr[8], 7), R.string.warn8_7, R.drawable.warn_icon_16, R.drawable.warn_icon_state_1};
        r0[8] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{0};
        r1[1] = new int[]{hk(bArr[9], 1), R.string.warn9_1, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{hk(bArr[9], 2), R.string.warn9_2, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[3] = new int[]{hk(bArr[9], 3), R.string.warn9_3, R.drawable.warn_icon_10, R.drawable.warn_icon_state_1};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{0};
        r0[9] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[10], 0), R.string.warn10_0, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{0};
        r0[10] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[11], 0), R.string.warn11_0, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{hk(bArr[11], 1), R.string.warn11_1, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{0};
        r0[11] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[12], 0), R.string.warn12_0, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[1] = new int[]{hk(bArr[12], 1), R.string.warn12_1, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{0};
        r0[12] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{0};
        r1[1] = new int[]{0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{hk(bArr[13], 3), R.string.warn13_3, R.drawable.warn_icon_default, R.drawable.warn_icon_state_0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{hk(bArr[13], 6), R.string.warn13_6, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r1[7] = new int[]{hk(bArr[13], 7), R.string.warn13_7, R.drawable.warn_icon_10, R.drawable.warn_icon_state_0};
        r0[13] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{hk(bArr[14], 0), R.string.warn14_0, R.drawable.warn_icon_07, R.drawable.warn_icon_state_1};
        r1[1] = new int[]{0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{0};
        r0[14] = r1;
        r1 = new int[8][];
        r1[0] = new int[]{0};
        r1[1] = new int[]{0};
        r1[2] = new int[]{0};
        r1[3] = new int[]{0};
        r1[4] = new int[]{0};
        r1[5] = new int[]{hk(bArr[15], 5), R.string.warn15_5, R.drawable.warn_icon_07, R.drawable.warn_icon_state_0};
        r1[6] = new int[]{0};
        r1[7] = new int[]{hk(bArr[15], 7), R.string.warn15_7, R.drawable.warn_icon_10, R.drawable.warn_icon_state_2};
        r0[15] = r1;
        return r0;
    }

    private void hm(byte b) {
        this.ej.ob((byte) -58, new byte[]{(byte) -92, b}, 2);
    }

    private void hn() {
        int i = System.getInt(this.ej.getContentResolver(), "panel_LED", 7);
        this.ej.ob((byte) -58, new byte[]{(byte) -94, (byte) i}, 2);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int oc = this.ej.oc();
        if (oc == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case (byte) -126:
                if (bArr[i + 2] == (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) -126;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 32:
                if (bArr[i + 2] == (byte) 2) {
                    bArr3 = new byte[4];
                    bArr3[0] = (byte) 32;
                    bArr3[1] = (byte) 2;
                    for (oc = 0; oc < 2; oc++) {
                        bArr3[oc + 2] = bArr[(i + 3) + oc];
                    }
                    this.ej.og(bArr3);
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] == (byte) 6) {
                    int i4;
                    byte[] bArr4 = new byte[6];
                    oc = 0;
                    for (i4 = 0; i4 < 6; i4++) {
                        bArr4[i4] = bArr[(i + 3) + i4];
                        if (i4 != 5) {
                            if (bArr4[i4] != this.bm[i4]) {
                                oc = true;
                            }
                            this.bm[i4] = bArr4[i4];
                        }
                    }
                    if (!((bArr4[1] & 16) == 0 || r0 == 0)) {
                        hi(bArr4);
                        this.ej.od(this.ei);
                    }
                    i4 = bArr4[5] & 255;
                    String str = "";
                    if (i4 >= 128) {
                        i4 -= 256;
                    }
                    if (i4 >= -20 && i4 <= 86) {
                        if (((byte) (bArr4[4] & 64)) == (byte) 64) {
                            str = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf((float) (((i4 * 9) / 5) + 32))}) + this.el.getString(R.string.f_dan);
                        } else {
                            str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i4)}) + this.el.getString(R.string.c_dan);
                        }
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    bArr4[0] = (byte) 41;
                    bArr4[1] = bArr4[4];
                    this.ej.oj(bArr4);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[4];
                    for (oc = 0; oc < 4; oc++) {
                        if (bArr[(i + 3) + oc] == (byte) 0) {
                            bArr3[oc] = (byte) 0;
                        } else if (bArr[(i + 3) + oc] < (byte) 3) {
                            bArr3[oc] = (byte) 1;
                        } else {
                            bArr3[oc] = (byte) (bArr[(i + 3) + oc] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr3[0];
                    this.ek.b2 = bArr3[1];
                    this.ek.b3 = bArr3[2];
                    this.ek.b4 = bArr3[3];
                    if (!this.bo) {
                        this.ek.front_cnt = 0;
                        this.ek.f1 = 0;
                        this.ek.f2 = 0;
                        this.ek.f3 = 0;
                        this.ek.f4 = 0;
                    }
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[4];
                    for (oc = 0; oc < 4; oc++) {
                        if (bArr[(i + 3) + oc] == (byte) 0) {
                            bArr3[oc] = (byte) 0;
                        } else if (bArr[(i + 3) + oc] < (byte) 3) {
                            bArr3[oc] = (byte) 1;
                        } else {
                            bArr3[oc] = (byte) (bArr[(i + 3) + oc] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr3[0];
                    this.ek.f2 = bArr3[1];
                    this.ek.f3 = bArr3[2];
                    this.ek.f4 = bArr3[3];
                    if (!this.bn) {
                        this.ek.back_cnt = 0;
                        this.ek.b1 = 0;
                        this.ek.b2 = 0;
                        this.ek.b3 = 0;
                        this.ek.b4 = 0;
                    }
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 6) {
                    hj(new byte[]{bArr[i + 3]});
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 36;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 37:
                if (bArr[i + 2] == (byte) 2) {
                    if ((bArr[i + 3] & 4) == 4) {
                        this.bo = true;
                    } else {
                        this.bo = false;
                        this.ek.max = 15;
                        this.ek.front_cnt = 0;
                        this.ek.f1 = 0;
                        this.ek.f2 = 0;
                        this.ek.f3 = 0;
                        this.ek.f4 = 0;
                    }
                    if ((bArr[i + 3] & 8) == 8) {
                        this.bn = true;
                    } else {
                        this.bn = false;
                        this.ek.max = 15;
                        this.ek.back_cnt = 0;
                        this.ek.b1 = 0;
                        this.ek.b2 = 0;
                        this.ek.b3 = 0;
                        this.ek.b4 = 0;
                    }
                    if (!this.bn && !this.bo) {
                        this.ek.zero_show = false;
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] != (byte) 6) {
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] >= (byte) 2) {
                    this.ej.oj(new byte[]{(byte) 39, bArr[i + 3], (byte) 0, (byte) 0, (byte) 0});
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[]{(byte) 40, bArr[i + 3], bArr[i + 4]};
                    Intent intent2 = new Intent();
                    intent2.setComponent(new ComponentName("android.microntek.canbus", "android.microntek.canbus.ParkServer"));
                    intent2.putExtra("canbustype", this.eh);
                    intent2.putExtra("park", bArr2);
                    if ((bArr2[1] & 1) != 0) {
                        this.el.startService(intent2);
                        return;
                    } else {
                        this.el.stopService(intent2);
                        return;
                    }
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] != (byte) 8) {
                    return;
                }
                return;
            case (byte) 42:
                if (bArr[i + 2] >= (byte) 16 && System.getInt(this.ej.getContentResolver(), "mOff_Warning_infor", 1) == 1) {
                    bArr2 = new byte[16];
                    while (i3 < 16) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.es.le(hl(bArr2));
                    this.es.lf(16);
                    mf();
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] != (byte) 16) {
                    return;
                }
                return;
            case (byte) 64:
                if (bArr[i + 2] == (byte) 1) {
                    bArr2 = new byte[3];
                    bArr2[0] = (byte) 64;
                    bArr2[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 80:
                if (bArr[i + 2] == (byte) 8) {
                    bArr2 = new byte[10];
                    bArr2[0] = (byte) 80;
                    bArr2[1] = (byte) 8;
                    while (i3 < 8) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 81:
                oc = bArr[i + 2] & 255;
                bArr3 = new byte[(oc + 2)];
                bArr3[0] = (byte) 81;
                bArr3[1] = bArr[i + 2];
                while (i3 < oc) {
                    bArr3[i3 + 2] = bArr[(i + 3) + i3];
                    i3++;
                }
                this.ej.og(bArr3);
                return;
            case (byte) 82:
                if (bArr[i + 2] == (byte) 3) {
                    bArr2 = new byte[5];
                    bArr2[0] = (byte) 82;
                    bArr2[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 83:
                if (bArr[i + 2] == (byte) 3) {
                    bArr2 = new byte[5];
                    bArr2[0] = (byte) 83;
                    bArr2[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 112:
                if (bArr[i + 2] == (byte) 20) {
                    bArr2 = new byte[22];
                    bArr2[0] = (byte) 112;
                    bArr2[1] = (byte) 20;
                    while (i3 < 20) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 113:
                if (bArr[i + 2] == (byte) 20) {
                    bArr2 = new byte[22];
                    bArr2[0] = (byte) 113;
                    bArr2[1] = (byte) 20;
                    while (i3 < 20) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 114:
                if (bArr[i + 2] == (byte) 20) {
                    bArr2 = new byte[22];
                    bArr2[0] = (byte) 114;
                    bArr2[1] = (byte) 20;
                    while (i3 < 20) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            case (byte) 120:
                if (bArr[i + 2] == (byte) 5) {
                    bArr2 = new byte[7];
                    bArr2[0] = (byte) 120;
                    bArr2[1] = (byte) 5;
                    while (i3 < 5) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                } else if (bArr[i + 2] == (byte) 3) {
                    bArr2 = new byte[7];
                    bArr2[0] = (byte) 120;
                    bArr2[1] = (byte) 3;
                    while (i3 < 3) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                } else {
                    return;
                }
            case (byte) 121:
                if (bArr[i + 2] == (byte) 1 && oc == 0) {
                    bArr2 = new byte[3];
                    bArr2[0] = (byte) 121;
                    bArr2[1] = (byte) 1;
                    while (i3 < 1) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.og(bArr2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void bg() {
        System.putInt(this.ej.getContentResolver(), "PowerState", 1);
    }

    public void bh() {
        System.putInt(this.ej.getContentResolver(), "PowerState", 0);
    }

    public void mo36d() {
        this.ej.oa(1);
        System.putInt(this.ej.getContentResolver(), "PowerState", 0);
        hn();
        mo48p();
        this.ej.ob((byte) -112, new byte[]{(byte) 120, (byte) 0}, 2);
    }

    void hj(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 16) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            hm((byte) 27);
        } else if (language.equals("en")) {
            hm((byte) 2);
        } else if (language.equals("de")) {
            hm((byte) 4);
        } else if (language.equals("it")) {
            hm((byte) 5);
        } else if (language.equals("fr")) {
            hm((byte) 6);
        } else if (language.equals("es")) {
            hm((byte) 8);
        } else if (language.equals("tr")) {
            hm((byte) 10);
        } else if (language.equals("ru")) {
            hm((byte) 11);
        } else if (language.equals("nl")) {
            hm((byte) 12);
        } else if (language.equals("pl")) {
            hm((byte) 14);
        } else if (language.equals("sv")) {
            hm((byte) 18);
        } else if (language.equals("pt")) {
            hm((byte) 22);
        }
    }
}
