package android.microntek.canbus.p001a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.UserHandle;
import android.util.Log;
import java.util.Locale;

public class am extends C0001b {
    private boolean ai;

    public am(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 50;
    }

    private void di(byte[] bArr) {
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
        this.ei.windLevelMax = 8;
        int i = bArr[1] & 15;
        if (i == 0) {
            this.ei.windLevel = 0;
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = -1;
            this.ei.windLevel = i <= 1 ? 0 : i - 1;
        }
        this.ei.tempUnit = (bArr[4] & 1) != 0;
        i = bArr[2] & 255;
        if (i == 0) {
            this.ei.tempLeft = 0;
        } else if (i == 255) {
            this.ei.tempLeft = 65535;
        } else if (i < 30 || i > 64) {
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
        } else if (i == 255) {
            this.ei.tempRight = 65535;
        } else if (i < 30 || i > 64) {
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
        if ((bArr[4] & 8) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    private void dk(int i) {
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

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
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
        int i4;
        byte[] bArr3;
        Intent intent;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc /*2*/:
                if (bArr[i + 2] >= (byte) 1 && bArr[i + 3] == (byte) 32) {
                    this.ai = true;
                    return;
                }
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] < (byte) 6) {
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr2 = new byte[6];
                    for (i4 = 0; i4 < 6; i4++) {
                        if (bArr[(i + 3) + i4] != (byte) 0) {
                            bArr2[i4] = (byte) ((0 - (bArr[(i + 3) + i4] - 5)) & 15);
                        }
                    }
                    this.ek.max = 4;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = bArr2[3];
                    this.ek.b2 = bArr2[4];
                    this.ek.b3 = bArr2[5];
                    this.ek.front_cnt = 3;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 7) {
                    bArr3 = new byte[7];
                    while (i3 < 7) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    di(bArr3);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 35) / 5450;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr2 = new byte[6];
                    for (i4 = 0; i4 < 6; i4++) {
                        bArr2[i4] = (byte) ((0 - (bArr[(i + 3) + i4] - 5)) & 15);
                    }
                    this.ek.max = 5;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = bArr2[3];
                    this.ek.b2 = bArr2[4];
                    this.ek.b3 = bArr2[5];
                    this.ek.front_cnt = 3;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] >= (byte) 7 && bArr[i + 3] == (byte) 2) {
                    bArr2 = new byte[7];
                    for (i4 = 0; i4 < 7; i4++) {
                        bArr2[i4] = (byte) ((0 - (bArr[(i + 3) + i4] - 5)) & 15);
                    }
                    this.ek.max = 5;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = bArr2[1];
                    this.ek.b2 = bArr2[2];
                    this.ek.b3 = bArr2[3];
                    if (bArr2[4] == (byte) 0 && bArr2[5] == (byte) 0 && bArr2[6] == (byte) 0) {
                        this.ek.front_cnt = 0;
                    } else {
                        this.ek.front_cnt = 3;
                    }
                    this.ek.f1 = bArr2[4];
                    this.ek.f2 = bArr2[5];
                    this.ek.f3 = bArr2[6];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 51:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr3 = new byte[8];
                    bArr3[0] = (byte) 51;
                    bArr3[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 52:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr3 = new byte[8];
                    bArr3[0] = (byte) 52;
                    bArr3[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 53:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 53;
                    bArr2[1] = (byte) 6;
                    for (i4 = 0; i4 < 6; i4++) {
                        bArr2[i4 + 2] = bArr[(i + 3) + i4];
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 54:
                if (bArr[i + 2] >= (byte) 1) {
                    oc = bArr[i + 3] & 127;
                    String str = "";
                    if ((bArr[i + 3] & 128) != 0) {
                        oc = 0 - oc;
                    }
                    if (oc >= -40 && oc <= 85) {
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(oc)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 56:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr3 = new byte[2];
                    bArr3[0] = bArr[i + 3];
                    dj(bArr3);
                    byte[] bArr4 = new byte[8];
                    bArr4[0] = (byte) 56;
                    bArr4[1] = (byte) 6;
                    for (i4 = 0; i4 < 6; i4++) {
                        bArr4[i4 + 2] = bArr[(i + 3) + i4];
                    }
                    if (oc == 0 && this.ai) {
                        this.ai = false;
                        dk((bArr4[3] & 3) + 51);
                    }
                    this.ej.oj(bArr4);
                    return;
                }
                return;
            case (byte) 59:
                if (bArr[i + 2] >= (byte) 6) {
                    bArr3 = new byte[7];
                    bArr3[0] = (byte) 59;
                    while (i3 < 6) {
                        bArr3[i3 + 1] = bArr[(i + 3) + i3];
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
        this.ej.oa(1);
        this.ej.oh(1);
    }

    void dj(byte[] bArr) {
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

    public void mo37e() {
        this.ej.oi((byte) 9, new byte[]{(byte) 11}, 1);
    }

    public void mo38f() {
        this.ej.oi((byte) 9, new byte[]{(byte) 11}, 1);
    }

    public void mo39g() {
        mo46n();
    }

    public void mo40h(String str, int i) {
        this.ej.oi((byte) 9, new byte[]{(byte) 11}, 1);
    }

    public void mo41i() {
        mo38f();
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 1;
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) (((i2 / 60) % 60) * ((i2 / 60) / 60));
        bArr[4] = (byte) (i2 % 60);
        this.ej.oi((byte) 9, bArr, 5);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        int i4 = i3 / 1000;
        r1 = new byte[5];
        r1[3] = (byte) (((((i4 / 60) / 60) * 60) + ((i4 / 60) % 60)) & 255);
        r1[4] = (byte) (i4 % 60);
        this.ej.oi((byte) 9, r1, 5);
    }

    public void mo46n() {
        this.ej.oi((byte) 9, new byte[]{Byte.MIN_VALUE}, 1);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[4];
        bArr[0] = (byte) 2;
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[1] = (byte) (b + 0);
            bArr[2] = (byte) ((i / 100) & 255);
            bArr[3] = (byte) ((i % 100) & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[1] = (byte) (b + 0);
            bArr[2] = (byte) ((i / 100) & 255);
            bArr[3] = (byte) ((i % 100) & 255);
        }
        this.ej.oi((byte) 9, bArr, 4);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo49q() {
        /*
        r8 = this;
        r0 = 12;
        r7 = 5;
        r6 = 0;
        r2 = new android.text.format.Time;
        r2.<init>();
        r2.setToNow();
        r1 = r2.hour;
        r3 = r2.minute;
        r4 = r8.el;
        r4 = android.text.format.DateFormat.is24HourFormat(r4);
        r5 = new byte[r7];
        if (r4 != 0) goto L_0x004f;
    L_0x001a:
        if (r1 <= r0) goto L_0x001e;
    L_0x001c:
        r1 = r1 + -12;
    L_0x001e:
        if (r1 != 0) goto L_0x004f;
    L_0x0020:
        r1 = r2.year;
        r1 = r1 + -2000;
        r1 = r1 & 255;
        r1 = (byte) r1;
        r5[r6] = r1;
        r1 = r2.month;
        r1 = r1 + 1;
        r1 = r1 & 255;
        r1 = (byte) r1;
        r4 = 1;
        r5[r4] = r1;
        r1 = r2.monthDay;
        r1 = r1 & 255;
        r1 = (byte) r1;
        r2 = 2;
        r5[r2] = r1;
        r0 = r0 & 255;
        r0 = (byte) r0;
        r1 = 3;
        r5[r1] = r0;
        r0 = r3 & 255;
        r0 = (byte) r0;
        r1 = 4;
        r5[r1] = r0;
        r0 = r8.ej;
        r1 = -90;
        r0.oi(r1, r5, r7);
        return;
    L_0x004f:
        r0 = r1;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.microntek.canbus.a.am.q():void");
    }

    public void mo50r(int i) {
    }
}
