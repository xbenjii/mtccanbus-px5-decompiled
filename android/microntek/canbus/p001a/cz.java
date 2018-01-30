package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.p002b.C0034a;
import android.microntek.canbus.serializable.Door;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;

public class cz extends C0001b {
    private boolean cy;
    Handler cz;
    private int da;

    public cz(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.cy = true;
        this.da = 0;
        this.cz = new dv(this);
        this.eh = 64;
    }

    private void kh(boolean z) {
        C0034a.lm(new String[]{"com.microntek.controlinfo"}, z, this.ej.getPackageManager());
    }

    private int kj(int i) {
        return i == 1 ? 4 : i == 2 ? 3 : i == 3 ? 2 : i == 4 ? 1 : 0;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        boolean z = true;
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        int i4;
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
                    ki(new byte[]{bArr[i + 5]});
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] == (byte) 12) {
                    int[] iArr = new int[12];
                    for (int i5 = 0; i5 < 12; i5++) {
                        iArr[i5] = bArr[(i + 3) + i5] & 255;
                    }
                    if (iArr[10] == 1) {
                        this.ek.max = 4;
                        this.ek.back_cnt = 4;
                        this.ek.b1 = kj(iArr[0]);
                        this.ek.b2 = kj(iArr[1]);
                        this.ek.b3 = kj(iArr[2]);
                        this.ek.b4 = kj(iArr[3]);
                        this.ek.front_cnt = 2;
                        this.ek.f1 = kj(iArr[4]);
                        this.ek.f2 = kj(iArr[7]);
                        this.ej.oe(this.ek);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 97:
                if (bArr[i + 2] == (byte) 9) {
                    if ((bArr[i + 3] & 16) != 0) {
                        System.putInt(this.ej.getContentResolver(), "CanBusBackViewDownViewDis", 1);
                    } else {
                        System.putInt(this.ej.getContentResolver(), "CanBusBackViewDownViewDis", 0);
                    }
                    if ((bArr[i + 3] & 128) == 0) {
                        z = false;
                    }
                    kh(z);
                    return;
                }
                return;
            default:
                i4 = i2 - i;
                if (i4 > 3 && i4 <= 40) {
                    byte[] bArr2 = new byte[i4];
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
        this.cz.removeMessages(0);
        this.cz.sendEmptyMessageDelayed(0, 0);
    }

    public void mo37e() {
        this.ej.ol((byte) -31, new byte[]{(byte) 10, (byte) 32, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32}, 13);
    }

    public void mo38f() {
        this.ej.ol((byte) -31, new byte[]{(byte) 8, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
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

    void ki(byte[] bArr) {
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
        if (this.cy) {
            this.cy = false;
            bArr[0] = (byte) 5;
            bArr[1] = (byte) 1;
            bArr[2] = (byte) 97;
            this.ej.ol((byte) 106, bArr, 3);
        }
    }

    public void mo50r(int i) {
    }
}
