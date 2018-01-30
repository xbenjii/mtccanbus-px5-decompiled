package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class cs extends C0001b {
    private byte[] cr;
    private boolean cs;
    private boolean ct;
    private boolean cu;

    public cs(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.cr = new byte[8];
        this.ct = true;
        this.cs = true;
        this.cu = true;
        this.eh = 49;
    }

    private void jv(byte[] bArr) {
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

    private void jw(byte[] bArr) {
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
        this.ei.windLevelMax = 7;
        int i = bArr[6] & 255;
        if (i >= 30 && i <= 60) {
            this.ei.tempUnit = false;
            if (i == 30) {
                this.ei.tempRight = 0;
            } else if (i == 60) {
                this.ei.tempRight = 65535;
            } else if (i >= 31 && i <= 59) {
                this.ei.tempRight = i * 5;
            }
        } else if (i < 119 || i > 171) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempUnit = true;
            if (i == 119) {
                this.ei.tempRight = 0;
            } else if (i == 171) {
                this.ei.tempRight = 65535;
            } else if (i >= 120 && i <= 170 && i % 2 == 0) {
                this.ei.tempRight = i * 5;
            }
        }
        i = bArr[2] & 255;
        if ((bArr[4] & 8) == 0) {
            if (i == 0) {
                this.ei.tempLeft = 0;
            } else if (i == 15) {
                this.ei.tempLeft = 220;
                if (this.ei.tempUnit) {
                    this.ei.tempLeft = ((this.ei.tempLeft * 9) / 5) + 320;
                }
            } else if (i == 30) {
                this.ei.tempLeft = 65535;
            } else {
                this.ei.tempLeft = -1;
            }
            this.ei.seatShow = false;
            this.ei.modeAuto = -1;
            this.ei.modeDual = -1;
        } else {
            this.ei.seatShow = true;
            if (i < 30 || i > 60) {
                if (i < 119 || i > 171) {
                    this.ei.tempLeft = -1;
                } else if (i == 119) {
                    this.ei.tempLeft = 0;
                } else if (i == 171) {
                    this.ei.tempLeft = 65535;
                } else if (i >= 120 && i <= 170 && i % 2 == 0) {
                    this.ei.tempLeft = i * 5;
                }
            } else if (i == 30) {
                this.ei.tempLeft = 0;
            } else if (i == 60) {
                this.ei.tempLeft = 65535;
            } else if (i >= 31 && i <= 59) {
                this.ei.tempLeft = i * 5;
            }
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[4] & 1) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        if ((bArr[4] & 4) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatHotLeft = bArr[7] & 3;
        this.ei.seatHotRight = (bArr[7] >> 2) & 3;
        this.ei.b_seatShow = false;
        this.ei.b_ariStateShow = false;
        this.ei.b_wind_FrameShow = false;
        this.ei.b_rightShow = false;
        this.ei.b_windLevel = (bArr[3] >> 4) & 7;
        this.ei.b_windLevelMax = 7;
        i = bArr[3] & 15;
        if (i == 0) {
            this.ei.b_tempLeft = 0;
        } else if (i == 8) {
            this.ei.b_tempLeft = 65535;
        } else if (i == 4) {
            this.ei.b_tempLeft = 220;
            if (this.ei.tempUnit) {
                this.ei.b_tempLeft = ((this.ei.b_tempLeft * 9) / 5) + 320;
            }
        } else {
            this.ei.b_tempLeft = -1;
        }
        if ((bArr[3] & 128) != 0) {
            this.ei.b_modeAc = true;
        } else {
            this.ei.b_modeAc = false;
        }
        if ((bArr[4] & 1) != 0) {
            this.ei.b_onOff = true;
        } else {
            this.ei.b_onOff = false;
        }
    }

    private void jy(byte b) {
        this.ej.ob((byte) -58, new byte[]{(byte) -92, b}, 2);
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        byte[] bArr3;
        int i4;
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
            case R$styleable.MyButton_imgWidth /*0*/:
                if (bArr[i + 2] == (byte) 6) {
                    bArr2 = new byte[6];
                    while (i3 < 6) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    jv(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 20:
                if (bArr[i + 2] == (byte) 1) {
                    new Intent("com.microntek.light").putExtra("keyCode", bArr[i + 3] & 255);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        if (bArr[(i + 3) + i4] == (byte) 0) {
                            bArr3[i4] = (byte) 0;
                        } else if (bArr[(i + 3) + i4] < (byte) 3) {
                            bArr3[i4] = (byte) 1;
                        } else {
                            bArr3[i4] = (byte) (bArr[(i + 3) + i4] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr3[0];
                    this.ek.b2 = bArr3[1];
                    this.ek.b3 = bArr3[2];
                    this.ek.b4 = bArr3[3];
                    if (!this.ct) {
                        this.ek.front_cnt = 0;
                        this.ek.f1 = 0;
                        this.ek.f2 = 0;
                        this.ek.f3 = 0;
                        this.ek.f4 = 0;
                        this.ek.f5 = 0;
                        this.ek.f6 = 0;
                    }
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[6];
                    for (i4 = 0; i4 < 6; i4++) {
                        if (bArr[(i + 3) + i4] == (byte) 0) {
                            bArr3[i4] = (byte) 0;
                        } else if (bArr[(i + 3) + i4] < (byte) 3) {
                            bArr3[i4] = (byte) 1;
                        } else {
                            bArr3[i4] = (byte) (bArr[(i + 3) + i4] / 2);
                        }
                    }
                    this.ek.max = 15;
                    this.ek.front_cnt = 6;
                    this.ek.f1 = bArr3[5];
                    this.ek.f2 = bArr3[0];
                    this.ek.f3 = bArr3[1];
                    this.ek.f4 = bArr3[2];
                    this.ek.f5 = bArr3[3];
                    this.ek.f6 = bArr3[4];
                    if (!this.cs) {
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
                if (bArr[i + 2] == (byte) 5) {
                    bArr2 = new byte[2];
                    while (i3 < 2) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    jx(bArr2);
                    return;
                }
                return;
            case (byte) 37:
                if (bArr[i + 2] == (byte) 2) {
                    if ((bArr[i + 3] & 4) == 4) {
                        this.ct = true;
                    } else {
                        this.ct = false;
                        this.ek.max = 15;
                        this.ek.front_cnt = 0;
                        this.ek.f1 = 0;
                        this.ek.f2 = 0;
                        this.ek.f3 = 0;
                        this.ek.f4 = 0;
                        this.ek.f5 = 0;
                        this.ek.f6 = 0;
                    }
                    if ((bArr[i + 3] & 8) == 8) {
                        this.cs = true;
                    } else {
                        this.cs = false;
                        this.ek.max = 15;
                        this.ek.back_cnt = 0;
                        this.ek.b1 = 0;
                        this.ek.b2 = 0;
                        this.ek.b3 = 0;
                        this.ek.b4 = 0;
                    }
                    if (!this.cs && !this.ct) {
                        this.ek.zero_show = false;
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] == (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 450;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] >= (byte) 2) {
                    this.ej.oj(new byte[]{(byte) 39, bArr[i + 3], (byte) 0, (byte) 0, (byte) 0});
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] == (byte) 8) {
                    int i5;
                    byte[] bArr4 = new byte[8];
                    i4 = 0;
                    for (i5 = 0; i5 < 8; i5++) {
                        if (i5 != 5) {
                            bArr4[i5] = bArr[(i + 3) + i5];
                            if (bArr4[i5] != this.cr[i5]) {
                                i4 = true;
                            }
                            this.cr[i5] = bArr4[i5];
                        }
                    }
                    jw(bArr4);
                    if (i4 != 0) {
                        this.ej.od(this.ei);
                    }
                    i5 = bArr[i + 8] & 255;
                    String str = "";
                    if (i5 >= 128) {
                        i5 -= 256;
                    }
                    if (i5 >= -40 && i5 <= 86) {
                        if (((byte) (bArr[i + 7] & 64)) == (byte) 64) {
                            str = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf((float) (((i5 * 9) / 5) + 32))}) + this.el.getString(R.string.f_dan);
                        } else {
                            str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i5)}) + this.el.getString(R.string.c_dan);
                        }
                    }
                    md(str);
                    bArr4[0] = (byte) 41;
                    bArr4[1] = bArr[i + 7];
                    this.ej.oj(bArr4);
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
                i4 = bArr[i + 2] & 255;
                bArr3 = new byte[(i4 + 2)];
                bArr3[0] = (byte) 81;
                bArr3[1] = bArr[i + 2];
                while (i3 < i4) {
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
                if (bArr[i + 2] == (byte) 1 && this.cu) {
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
        this.cu = false;
        System.putInt(this.ej.getContentResolver(), "PowerState", 1);
    }

    public void bh() {
        this.cu = true;
        this.ej.og(new byte[]{(byte) -1, (byte) -1, (byte) 0});
        System.putInt(this.ej.getContentResolver(), "PowerState", 0);
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
        System.putInt(this.ej.getContentResolver(), "PowerState", 0);
        mo48p();
    }

    public void mo37e() {
        mo45m(0, 0, 0);
    }

    public void mo38f() {
        mo45m(0, 0, 0);
    }

    public void mo39g() {
        mo45m(0, 0, 0);
    }

    public void mo40h(String str, int i) {
        mo45m(0, 0, 0);
    }

    public void hw(int i, int i2) {
    }

    public void mo41i() {
        mo45m(0, 0, 0);
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 1, (byte) 16}, 2);
        byte[] bArr = new byte[6];
        bArr[1] = (byte) (i & 255);
        this.ej.ob((byte) -61, bArr, 6);
    }

    void jx(byte[] bArr) {
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

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 14, (byte) 48}, 2);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[]{(byte) 0, (byte) 34}, 2);
    }

    public void mo47o(byte b, int i, byte b2) {
        this.ej.ob((byte) -64, new byte[]{(byte) 1, (byte) 1}, 2);
        byte[] bArr = new byte[4];
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1 >= 2 ? 2 : 1);
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
            jy((byte) 27);
        } else if (language.equals("en")) {
            jy((byte) 2);
        } else if (language.equals("de")) {
            jy((byte) 4);
        } else if (language.equals("it")) {
            jy((byte) 5);
        } else if (language.equals("fr")) {
            jy((byte) 6);
        } else if (language.equals("es")) {
            jy((byte) 8);
        } else if (language.equals("tr")) {
            jy((byte) 10);
        } else if (language.equals("ru")) {
            jy((byte) 11);
        } else if (language.equals("nl")) {
            jy((byte) 12);
        } else if (language.equals("pl")) {
            jy((byte) 14);
        } else if (language.equals("sv")) {
            jy((byte) 18);
        } else if (language.equals("pt")) {
            jy((byte) 22);
        }
    }

    public void mo49q() {
        int i;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[4];
        if (DateFormat.is24HourFormat(this.el)) {
            i = 0;
        } else {
            if (this.en > 12) {
                this.en -= 12;
                i = 128;
            } else {
                i = 0;
            }
            i |= 64;
            if (this.en == 0) {
                this.en = 12;
            }
        }
        bArr[0] = (byte) (this.en & 127);
        bArr[1] = (byte) (this.eo & 63);
        bArr[2] = (byte) (i & 255);
        this.ej.ob((byte) -56, bArr, 3);
    }
}
