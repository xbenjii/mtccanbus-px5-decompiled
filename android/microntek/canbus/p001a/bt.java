package android.microntek.canbus.p001a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.os.SystemClock;
import android.os.UserHandle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;

public class bt extends C0001b {
    private static long bl = 0;
    byte[] bj;
    private boolean bk;

    public bt(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.bk = false;
        this.bj = new byte[9];
        this.eh = 21;
        this.ei = new AirCondition();
    }

    private void ha(byte[] bArr) {
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
        } else if (i == 255) {
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

    private void hc(int i) {
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

    private boolean hd() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - bl <= 1200) {
            return false;
        }
        bl = uptimeMillis;
        return true;
    }

    private int he() {
        return this.ej.jg == 2 ? 10 : 4;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        byte[] bArr3;
        int i4;
        Intent intent;
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
            case (byte) 33:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[6];
                    for (i4 = 0; i4 < 6; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    if ((bArr3[1] & 16) != 0) {
                        ha(bArr3);
                        this.ej.od(this.ei);
                    }
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 33;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                } else if (bArr[i + 2] >= (byte) 9) {
                    byte[] bArr4 = new byte[9];
                    i4 = 0;
                    for (int i5 = 0; i5 < 9; i5++) {
                        bArr4[i5] = bArr[(i + 3) + i5];
                        if (i5 < 6) {
                            if (bArr4[i5] != this.bj[i5]) {
                                i4 = true;
                            }
                            this.bj[i5] = bArr4[i5];
                        }
                    }
                    if ((bArr4[4] & 64) != 0 && hd()) {
                        hc(1);
                    }
                    if (!(i4 == 0 || (bArr4[1] & 16) == 0)) {
                        ha(bArr4);
                        this.ej.od(this.ei);
                    }
                    bArr2 = new byte[11];
                    bArr2[0] = (byte) 33;
                    bArr2[1] = (byte) 9;
                    while (i3 < 9) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                } else {
                    return;
                }
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    mb();
                    this.ek.max = he();
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr3[0];
                    this.ek.b2 = bArr3[1];
                    this.ek.b3 = bArr3[2];
                    this.ek.b4 = bArr3[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 4) {
                    bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    i4 = this.ej.oc();
                    if (i4 == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ek.max = he();
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr3[0];
                    this.ek.f2 = bArr3[1];
                    this.ek.f3 = bArr3[2];
                    this.ek.f4 = bArr3[3];
                    if (this.ek.f1 == 0 && this.ek.f2 == 0 && this.ek.f3 == 0 && this.ek.f4 == 0) {
                        if (i4 != 1) {
                            return;
                        }
                    }
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    hb(new byte[]{bArr[i + 3]});
                    if (((byte) (bArr[i + 4] & 1)) == (byte) 1) {
                        this.bk = true;
                        return;
                    } else {
                        this.bk = false;
                        return;
                    }
                }
                return;
            case (byte) 38:
                if (bArr[i + 2] == (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 450;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i4);
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
                    i4 = (i4 * 30) / 4500;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 50:
                i4 = bArr[i + 2] & 15;
                bArr3 = new byte[(i4 + 2)];
                bArr3[0] = (byte) 50;
                bArr3[1] = (byte) i4;
                while (i3 < i4) {
                    bArr3[i3 + 2] = bArr[(i + 3) + i3];
                    i3++;
                }
                this.ej.oj(bArr3);
                return;
            case (byte) 51:
                if (bArr[i + 2] == (byte) 15 || bArr[i + 2] == (byte) 18) {
                    byte[] bArr5 = new byte[(bArr[i + 2] + 1)];
                    for (i4 = 0; i4 < bArr[i + 2] + 1; i4++) {
                        bArr5[i4] = bArr[(i + 2) + i4];
                    }
                    this.ej.oj(bArr5);
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
        mo48p();
    }

    public void mo37e() {
        this.ej.ob((byte) -64, new byte[]{(byte) 11, (byte) 48}, 2);
    }

    public void mo38f() {
        this.ej.ob((byte) -64, new byte[]{(byte) 0, (byte) 32}, 2);
    }

    public void mo39g() {
        this.ej.ob((byte) -64, new byte[]{(byte) 7, (byte) 48}, 2);
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -64, new byte[]{(byte) 5, (byte) 64}, 2);
    }

    void hb(byte[] bArr) {
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

    public void mo41i() {
        this.ej.ob((byte) -64, new byte[]{(byte) 0, (byte) 32}, 2);
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 2, (byte) 16}, 2);
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) (i & 255);
        bArr[4] = (byte) ((i2 / 60) % 60);
        bArr[5] = (byte) (i2 % 60);
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ob((byte) -64, new byte[]{(byte) 6, (byte) 18}, 2);
        byte[] bArr = new byte[6];
        bArr[3] = (byte) ((i2 >> 8) & 255);
        bArr[2] = (byte) (i2 & 255);
        bArr[1] = (byte) ((i >> 8) & 255);
        bArr[0] = (byte) (i & 255);
        bArr[4] = (byte) -1;
        this.ej.ob((byte) -61, bArr, 6);
    }

    public void mo44l() {
        this.ej.ob((byte) -64, new byte[]{(byte) 8, (byte) 32}, 2);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 8, (byte) 17}, 2);
        this.ej.ob((byte) -61, new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) (((i3 / 1000) / 60) % 60), (byte) ((i3 / 1000) % 60)}, 6);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[]{(byte) 0, (byte) 32}, 2);
    }

    public void mo47o(byte b, int i, byte b2) {
        this.ej.ob((byte) -64, new byte[]{(byte) 1, (byte) 1}, 2);
        byte[] bArr = new byte[4];
        if (b >= (byte) 0 && b <= (byte) 1) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 2) {
            bArr[0] = (byte) 2;
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) 16;
            bArr[1] = (byte) (i & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -62, bArr, 4);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            this.ej.ob((byte) -58, new byte[]{(byte) 85, (byte) 0}, 2);
        } else if (language.equals("en")) {
            this.ej.ob((byte) -58, new byte[]{(byte) 85, (byte) 1}, 2);
        }
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[4];
        bArr[0] = (byte) 80;
        if (!is24HourFormat) {
            if (this.en > 12) {
                this.en = (this.en - 12) + 128;
            } else {
                this.en += 128;
            }
        }
        bArr[1] = (byte) (this.en & 255);
        bArr[2] = (byte) (this.eo & 255);
        bArr[3] = (byte) 0;
        this.ej.ob((byte) -58, bArr, 4);
    }

    public void mo50r(int i) {
        new byte[1][0] = (byte) (i & 63);
    }
}
