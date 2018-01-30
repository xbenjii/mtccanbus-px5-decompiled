package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;

public class af extends C0001b {
    private Handler aa;
    byte[] f15y;
    private boolean f16z;

    public af(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.f16z = false;
        this.f15y = new byte[9];
        this.eh = 76;
        this.ei = new AirCondition();
    }

    private void ci(byte[] bArr) {
        this.ei.seatShow = false;
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
        this.ei.tempUnit = (bArr[4] & 1) != 0;
        int i = bArr[2] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 255) {
            this.ei.tempLeft = 65535;
        } else if (i >= 1 && i <= 254) {
            if (this.ei.tempUnit) {
                this.ei.tempLeft = i * 10;
            } else {
                this.ei.tempLeft = i * 5;
            }
        }
        i = bArr[3] & 255;
        if (i == 0) {
            this.ei.tempRight = 0;
        } else if (i == 31) {
            this.ei.tempRight = 65535;
        } else if (i >= 1 && i <= 254) {
            if (this.ei.tempUnit) {
                this.ei.tempRight = i * 10;
            } else {
                this.ei.tempRight = i * 5;
            }
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.rearLock = 1;
        } else {
            this.ei.rearLock = 0;
        }
        this.ei.acMax = -1;
    }

    private int ck(String str) {
        return System.getInt(this.ej.getContentResolver(), str, 0);
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
        byte[] bArr4;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) -48:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[4];
                    bArr2[0] = (byte) -48;
                    bArr2[1] = (byte) 2;
                    while (i3 < 2) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) -45:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[6];
                    bArr2[0] = (byte) -45;
                    bArr2[1] = (byte) 4;
                    while (i3 < 4) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[6];
                    while (i3 < 6) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    ci(bArr3);
                    this.ej.od(this.ei);
                    return;
                } else if (bArr[i + 2] == (byte) 9) {
                    bArr4 = new byte[9];
                    for (i4 = 0; i4 < 9; i4++) {
                        bArr4[i4] = bArr[(i + 3) + i4];
                        if (i4 < 6) {
                            if (bArr4[i4] != this.f15y[i4]) {
                                i3 = 1;
                            }
                            this.f15y[i4] = bArr4[i4];
                        }
                    }
                    if (i3 != 0) {
                        ci(bArr4);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr4 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr4[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr4[0];
                    this.ek.b2 = bArr4[1];
                    this.ek.b3 = bArr4[2];
                    this.ek.b4 = bArr4[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 4) {
                    bArr4 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr4[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 4;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr4[0];
                    this.ek.f2 = bArr4[1];
                    this.ek.f3 = bArr4[2];
                    this.ek.f4 = bArr4[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    cj(new byte[]{bArr[i + 3]});
                    if (((byte) (bArr[i + 4] & 1)) == (byte) 1) {
                        this.f16z = true;
                        return;
                    } else {
                        this.f16z = false;
                        return;
                    }
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / 4500;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[6];
                    bArr2[0] = (byte) 50;
                    bArr2[1] = (byte) 4;
                    while (i3 < 4) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 51:
                if (bArr[i + 2] == (byte) 15 || bArr[i + 2] == (byte) 18) {
                    bArr3 = new byte[(bArr[i + 2] + 1)];
                    while (i3 < bArr[i + 2] + 1) {
                        bArr3[i3] = bArr[(i + 2) + i3];
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

    void cj(byte[] bArr) {
        boolean z = true;
        Door door;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        if (this.ej.jg == 1) {
            door = this.em;
            z2 = (bArr[0] & 128) != 0;
            z3 = (bArr[0] & 64) != 0;
            z4 = (bArr[0] & 32) != 0;
            z5 = (bArr[0] & 16) != 0;
            z6 = (bArr[0] & 8) != 0;
            if ((bArr[0] & 4) == 0) {
                z = false;
            }
            if (door.la(z2, z3, z4, z5, z6, z)) {
                this.ej.of(this.em);
                return;
            }
            return;
        }
        door = this.em;
        z2 = (bArr[0] & 64) != 0;
        z3 = (bArr[0] & 128) != 0;
        z4 = (bArr[0] & 16) != 0;
        z5 = (bArr[0] & 32) != 0;
        z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
        this.aa = new dl(this);
        byte[] bArr = new byte[]{(byte) 65, (byte) ck("mScreen1")};
        this.ej.ob((byte) -58, bArr, 2);
        bArr[0] = (byte) 66;
        bArr[1] = (byte) ck("mScreen2");
        this.ej.ob((byte) -58, bArr, 2);
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo41i() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) 0;
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
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 6;
        bArr[1] = (byte) 19;
        bArr[2] = (byte) (i2 & 255);
        bArr[5] = (byte) (((i3 / 1000) / 60) / 60);
        bArr[6] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[7] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo44l() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 12;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 19;
        bArr[1] = (byte) 19;
        bArr[2] = (byte) (i2 & 255);
        bArr[5] = (byte) (((i3 / 1000) / 60) / 60);
        bArr[6] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[7] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo46n() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) 0;
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

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[2];
        if (!DateFormat.is24HourFormat(this.el)) {
            this.en |= 128;
        }
        bArr[0] = (byte) (this.en & 255);
        bArr[1] = (byte) (this.eo & 255);
        this.ej.ob((byte) -119, bArr, 2);
        if (this.aa != null) {
            this.aa.removeMessages(0);
            this.aa.sendEmptyMessageDelayed(0, 20000);
        }
    }

    public void mo50r(int i) {
        byte[] bArr = new byte[1];
        bArr[0] = i == 0 ? Byte.MIN_VALUE : (byte) i;
        this.ej.ob((byte) -60, bArr, 1);
    }
}
