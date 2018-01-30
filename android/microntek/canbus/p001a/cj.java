package android.microntek.canbus.p001a;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.Handler;
import android.provider.Settings.System;
import java.util.Locale;

public class cj extends C0001b {
    private AlertDialog ci;
    private Handler cj;
    private Ringtone ck;
    private boolean cl;

    public cj(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.ci = null;
        this.cl = true;
        this.eh = 80;
        this.ck = RingtoneManager.getRingtone(this.el.getApplicationContext(), RingtoneManager.getDefaultUri(4));
        this.cj = new ds(this);
    }

    private void ix(boolean z) {
        if (this.ci == null) {
            this.ci = new Builder(this.el).setTitle(this.el.getString(R.string.titel)).setCancelable(false).setMessage(this.el.getString(R.string.messagess)).setPositiveButton(this.el.getString(R.string.confirm), new dt(this)).setNegativeButton(this.el.getString(R.string.cancel), new du(this)).create();
            this.ci.getWindow().setType(2003);
        }
        if (z || this.ej.pa().equals("com.microntek.controlinfo.canbus80tpmsinfo")) {
            this.ck.stop();
            this.ci.cancel();
        } else if (!this.ci.isShowing()) {
            this.ck.play();
            this.ci.show();
        }
    }

    private void iy(byte[] bArr) {
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
        if ((bArr[0] & 16) != 0) {
            this.ei.modeAuto = 2;
        } else if ((bArr[0] & 8) != 0) {
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
        int i = bArr[2] & 255;
        this.ei.tempLeft = ja(i);
        i = bArr[3] & 255;
        this.ei.tempRight = ja(i);
        if ((bArr[0] & 32) == 0) {
            this.ei.windLoop = 0;
        } else if ((bArr[4] & 32) != 0) {
            this.ei.windLoop = 2;
        } else {
            this.ei.windLoop = 1;
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatShow = false;
    }

    private int ja(int i) {
        int i2 = i & 127;
        return ((i & 128) == 0 ? 1 : null) != null ? (i2 < 1 || i2 > 15) ? -1 : (i2 * 10) + 150 : i2 == 0 ? 0 : i2 == 31 ? 65535 : (i2 < 1 || i2 > 28) ? -1 : (i2 * 5) + 175;
    }

    private void jb() {
        this.cj.removeMessages(0);
        this.cj.sendEmptyMessageDelayed(0, 20000);
        this.cl = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int[] iArr;
        int i4;
        String str;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 5) {
                    byte[] ma = ma(bArr, i + 3, i + 8);
                    if (lx(ma)) {
                        iy(ma);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 6) {
                    mb();
                    iArr = new int[6];
                    i4 = 0;
                    while (i4 < 6) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] != 0 && iArr[i4] <= 32) {
                            iArr[i4] = (iArr[i4] * 15) / 32;
                        }
                        i4++;
                    }
                    this.ek.max = 15;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 6) {
                    mb();
                    iArr = new int[6];
                    i4 = 0;
                    while (i4 < 6) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] != 0 && iArr[i4] <= 32) {
                            iArr[i4] = (iArr[i4] * 15) / 32;
                        }
                        i4++;
                    }
                    this.ek.max = 15;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = iArr[0];
                    this.ek.f2 = iArr[1];
                    this.ek.f3 = iArr[2];
                    this.ek.f4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2 && (bArr[i + 3] & 1) != 0) {
                    iz(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] >= (byte) 3) {
                    float f = (((float) (bArr[i + 4] & 255)) * 0.5f) - 35.0f;
                    str = "";
                    if (f >= -35.0f && f <= 50.0f) {
                        if ((bArr[i + 3] & 1) == 0) {
                            str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                        } else {
                            str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.f_dan);
                        }
                    }
                    md(str);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    lz(((32768 - (((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255))) * 30) / 8874);
                    return;
                }
                return;
            case (byte) 56:
                if (bArr[i + 2] == (byte) 8) {
                    str = "56,8,";
                    byte[] bArr2 = new byte[10];
                    bArr2[0] = (byte) 56;
                    bArr2[1] = (byte) 8;
                    while (i3 < 8) {
                        str = str + (bArr[(i + 3) + i3] & 255) + ",";
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    System.putString(this.ej.getContentResolver(), "Canbus80Data38", str);
                    return;
                }
                return;
            case (byte) 57:
                if (bArr[i + 2] == (byte) 2) {
                    byte[] bArr3 = new byte[10];
                    bArr3[0] = (byte) 57;
                    bArr3[1] = (byte) 2;
                    String str2 = "57,2,";
                    boolean z = true;
                    for (int i5 = 0; i5 < 2; i5++) {
                        str2 = str2 + (bArr[(i + 3) + i5] & 255) + ",";
                        bArr3[i5 + 2] = bArr[(i + 3) + i5];
                        if (bArr[(i + 3) + i5] != (byte) 0) {
                            z = false;
                        }
                    }
                    this.ej.oj(bArr3);
                    i3 = System.getInt(this.ej.getContentResolver(), "tpms_alarm_prompt", 1);
                    if (this.cl && i3 == 1) {
                        ix(z);
                    }
                    System.putString(this.ej.getContentResolver(), "Canbus80Data39", str2);
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

    public void mo37e() {
    }

    public void mo38f() {
    }

    public void mo39g() {
    }

    public void mo40h(String str, int i) {
    }

    public void mo41i() {
    }

    void iz(byte[] bArr) {
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

    public void mo42j(int i, int i2, int i3) {
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
    }

    public void mo44l() {
    }

    public void mo45m(int i, int i2, int i3) {
    }

    public void mo46n() {
    }

    public void mo47o(byte b, int i, byte b2) {
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
