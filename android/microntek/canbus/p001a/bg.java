package android.microntek.canbus.p001a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.Handler;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;

public class bg extends C0001b {
    private static long aw = 0;
    private boolean au;
    Handler av;
    private int ax;

    public bg(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.au = true;
        this.ax = 0;
        this.av = new dm(this);
        this.eh = 85;
    }

    private void fc(byte[] bArr) {
        this.ei.seatShow = false;
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[4] & 255) == 10) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[2] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[4] & 255) {
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 7:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[5] & 7;
        this.ei.windLevelMax = 7;
        int i = bArr[6] & 255;
        this.ei.tempLeft = fg(i);
        i = bArr[7] & 255;
        this.ei.tempRight = fg(i);
        if ((bArr[1] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
    }

    private void fe(int i) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.microntek.controlsettings", "com.microntek.controlsettings.canbus21air"));
        intent.addFlags(807600128);
        intent.putExtra("cftype", i);
        try {
            this.ej.startActivityAsUser(intent, UserHandle.CURRENT_OR_SELF);
        } catch (ActivityNotFoundException e) {
            Log.e("Canbus21", "controlinfo activity not found! " + e.getMessage());
        }
    }

    private boolean ff() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - aw <= 1200) {
            return false;
        }
        aw = uptimeMillis;
        return true;
    }

    private int fh(int i) {
        return i == 1 ? 4 : i == 2 ? 3 : i == 3 ? 2 : i == 4 ? 1 : 0;
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
            case (byte) 17:
                if (bArr[i + 2] == (byte) 10) {
                    i4 = (bArr[i + 10] & 255) + ((bArr[i + 9] & 127) << 8);
                    if (bArr[i + 9] < (byte) 0) {
                        i4 = 0 - i4;
                    }
                    i4 = ((i4 / 10) * 30) / 520;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] == (byte) 10) {
                    fd(new byte[]{bArr[i + 5]});
                    return;
                }
                return;
            case (byte) 49:
                if (mc(bArr[i + 2], 12)) {
                    bArr2 = new byte[this.er];
                    for (i4 = 0; i4 < this.er; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    if ((bArr2[3] & 1) != 0 && ff()) {
                        fe(1);
                    }
                    if (lx(bArr2)) {
                        fc(bArr2);
                        this.ej.od(this.ei);
                    }
                    byte[] bArr3 = new byte[(this.er + 2)];
                    bArr3[0] = (byte) 49;
                    bArr3[1] = (byte) this.er;
                    while (i3 < this.er) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] == (byte) 12) {
                    int[] iArr = new int[12];
                    for (i4 = 0; i4 < 12; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                    }
                    if (iArr[10] == 1) {
                        this.ek.max = 4;
                        this.ek.back_cnt = 4;
                        this.ek.b1 = fh(iArr[0]);
                        this.ek.b2 = fh(iArr[1]);
                        this.ek.b3 = fh(iArr[2]);
                        this.ek.b4 = fh(iArr[3]);
                        this.ek.front_cnt = 2;
                        this.ek.f1 = fh(iArr[4]);
                        this.ek.f2 = fh(iArr[7]);
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            default:
                i4 = i2 - i;
                if (i4 > 3 && i4 <= 40) {
                    bArr2 = new byte[i4];
                    while (i3 < i4) {
                        bArr2[i3] = bArr[(i + 1) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
        int i = System.getInt(this.ej.getContentResolver(), "com.microntek.control64settings", 1);
        this.ej.ol((byte) 45, new byte[]{(byte) 2, (byte) i}, 2);
        this.av.removeMessages(0);
        this.av.sendEmptyMessageDelayed(0, 0);
    }

    public void mo37e() {
        this.ej.ol((byte) -31, new byte[]{(byte) 10, (byte) 32, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32}, 13);
    }

    public void mo38f() {
        this.ej.ol((byte) -31, new byte[]{(byte) 8, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    void fd(byte[] bArr) {
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

    int fg(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : (i < 38 || i > 62) ? -1 : (i * 10) / 2;
    }

    public void mo39g() {
        this.ej.ol((byte) -31, new byte[]{(byte) 12, (byte) 32, (byte) 65, (byte) 86, (byte) 73, (byte) 78, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo40h(String str, int i) {
        this.ej.ol((byte) -31, new byte[]{(byte) 10, (byte) 32, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32}, 13);
    }

    public void mo41i() {
        this.ej.ol((byte) -31, new byte[]{(byte) 8, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        bArr[0] = (byte) (i3 == 2 ? 7 : 6);
        bArr[5] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        bArr[6] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        bArr[7] = (byte) 58;
        bArr[8] = (byte) ((((i2 / 60) % 60) / 10) + 48);
        bArr[9] = (byte) ((((i2 / 60) % 60) % 10) + 48);
        bArr[10] = (byte) 58;
        bArr[11] = (byte) (((i2 % 60) / 10) + 48);
        bArr[12] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[]{(byte) 11, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i5 = i3 / 1000;
        bArr[3] = (byte) ((((i5 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i5 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i5 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i5 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i5 % 60) / 10) + 48);
        bArr[10] = (byte) (((i5 % 60) % 10) + 48);
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo44l() {
        this.ej.ol((byte) -31, new byte[]{(byte) 12, (byte) 32, (byte) 109, (byte) 111, (byte) 118, (byte) 105, (byte) 101, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 12, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i4 = i3 / 1000;
        bArr[3] = (byte) ((((i4 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i4 % 60) / 10) + 48);
        bArr[10] = (byte) (((i4 % 60) % 10) + 48);
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo46n() {
        this.ej.ol((byte) -31, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) 32;
            bArr[2] = (byte) 32;
            if (i > 9999) {
                bArr[4] = (byte) ((i / 10000) + 48);
                bArr[5] = (byte) (((i / 1000) % 10) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
                bArr[9] = (byte) ((i % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 1000) + 48);
                bArr[6] = (byte) (((i / 100) % 10) + 48);
                bArr[7] = (byte) 46;
                bArr[8] = (byte) (((i / 10) % 10) + 48);
                bArr[9] = (byte) ((i % 10) + 48);
            }
            bArr[10] = (byte) 77;
            bArr[11] = (byte) 72;
            bArr[12] = (byte) 90;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) 32;
            bArr[2] = (byte) 32;
            if (i > 999) {
                bArr[4] = (byte) ((i / 1000) + 48);
                bArr[5] = (byte) (((i / 100) % 10) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            } else {
                bArr[5] = (byte) ((i / 100) + 48);
                bArr[6] = (byte) (((i / 10) % 10) + 48);
                bArr[7] = (byte) ((i % 10) + 48);
            }
            bArr[9] = (byte) 75;
            bArr[10] = (byte) 72;
            bArr[11] = (byte) 90;
        }
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[3];
        bArr[0] = (byte) (this.en & 255);
        bArr[1] = (byte) (this.eo & 255);
        this.ej.ol((byte) -75, bArr, 3);
        if (this.au) {
            this.au = false;
            bArr[0] = (byte) 5;
            bArr[1] = (byte) 1;
            bArr[2] = (byte) 97;
            this.ej.ol((byte) 106, bArr, 3);
        }
    }

    public void mo50r(int i) {
    }
}
