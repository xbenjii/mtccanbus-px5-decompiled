package android.microntek.canbus.p001a;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class bm extends C0001b {
    private AlertDialog ba;
    private Handler bb;
    private Ringtone bc;
    private boolean bd;

    public bm(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.ba = null;
        this.bd = true;
        this.eh = 81;
        this.bc = RingtoneManager.getRingtone(this.el.getApplicationContext(), RingtoneManager.getDefaultUri(4));
        this.bb = new dn(this);
    }

    private void fv(boolean z) {
        if (this.ba == null) {
            this.ba = new Builder(this.el).setTitle(this.el.getString(R.string.titel)).setCancelable(false).setMessage(this.el.getString(R.string.messagess)).setPositiveButton(this.el.getString(R.string.confirm), new C0009do(this)).setNegativeButton(this.el.getString(R.string.cancel), new dp(this)).create();
            this.ba.getWindow().setType(2003);
        }
        if (z || this.ej.pa().equals("com.microntek.controlinfo.canbus81tpmsinfo")) {
            this.bc.stop();
            this.ba.cancel();
        } else if (!this.ba.isShowing()) {
            this.bc.play();
            this.ba.show();
        }
    }

    private void fw(byte[] bArr) {
        if ((bArr[2] & 15) != 0) {
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
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[1] & 7) {
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[2] & 15;
        this.ei.windLevelMax = 8;
        int i = bArr[3] & 255;
        this.ei.tempLeft = fy(i);
        i = bArr[4] & 255;
        this.ei.tempRight = fy(i);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    private void fz() {
        this.bb.removeMessages(0);
        this.bb.sendEmptyMessageDelayed(0, 20000);
        this.bd = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] ma;
        int i4;
        int i5;
        int[] iArr;
        switch (bArr[i + 1]) {
            case (byte) 17:
                if (bArr[i + 2] >= (byte) 6) {
                    ma = ma(bArr, i + 3, i + 8);
                    if (lx(ma)) {
                        fw(ma);
                        this.ej.od(this.ei);
                    }
                    i4 = bArr[i + 8] & 255;
                    String str = "";
                    if (i4 < 254) {
                        i5 = i4 - 40;
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i5)}) + this.el.getString(R.string.c_dan);
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    iArr = new int[4];
                    for (i5 = 0; i5 < 4; i5++) {
                        iArr[i5] = bArr[(i + 3) + i5] & 15;
                        if (iArr[i5] != 0) {
                            iArr[i5] = 0 - (iArr[i5] - 5);
                        }
                    }
                    this.ek.max = 4;
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
                if (bArr[i + 2] >= (byte) 4) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    iArr = new int[4];
                    for (i5 = 0; i5 < 4; i5++) {
                        iArr[i5] = bArr[(i + 3) + i5] & 15;
                        if (iArr[i5] != 0) {
                            iArr[i5] = 0 - (iArr[i5] - 5);
                        }
                    }
                    this.ek.max = 4;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = iArr[0];
                    this.ek.f2 = iArr[1];
                    this.ek.f3 = iArr[2];
                    this.ek.f4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] >= (byte) 2) {
                    fx(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] >= (byte) 2) {
                    i5 = ((bArr[i + 3] & 127) << 8) | (bArr[i + 4] & 255);
                    if ((bArr[i + 3] & 128) == 0) {
                        i5 = 0 - i5;
                    }
                    lz((i5 * 30) / 12000);
                    return;
                }
                return;
            case (byte) 56:
                if (bArr[i + 2] >= (byte) 8) {
                    ma = new byte[10];
                    ma[0] = (byte) 56;
                    ma[1] = (byte) 8;
                    while (i3 < 8) {
                        ma[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(ma);
                    return;
                }
                return;
            case (byte) 57:
                if (bArr[i + 2] >= (byte) 4) {
                    byte[] bArr2 = new byte[6];
                    bArr2[0] = (byte) 57;
                    bArr2[1] = (byte) 4;
                    boolean z = true;
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4 + 2] = bArr[(i + 3) + i4];
                        if (bArr[(i + 3) + i4] != (byte) 0) {
                            z = false;
                        }
                    }
                    this.ej.oj(bArr2);
                    i3 = System.getInt(this.ej.getContentResolver(), "tpms_alarm_prompt", 1);
                    if (this.bd && i3 == 1) {
                        fv(z);
                        return;
                    }
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

    void fx(byte[] bArr) {
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

    int fy(int i) {
        return i == 0 ? 0 : i == 30 ? 65535 : (i <= 0 || i >= 30) ? -1 : (i * 5) + 170;
    }

    public void mo39g() {
    }

    public void mo40h(String str, int i) {
    }

    public void mo41i() {
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
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        if (!DateFormat.is24HourFormat(this.el)) {
            if (this.en > 12) {
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
        }
        byte[] bArr = new byte[6];
        bArr[0] = (byte) (time.year & 255);
        bArr[1] = (byte) (((time.year >> 8) & 15) | ((time.month + 1) << 4));
        bArr[2] = (byte) (time.monthDay & 255);
        bArr[3] = (byte) (this.en & 255);
        bArr[4] = (byte) (this.eo & 255);
        this.ej.ob((byte) -126, bArr, 6);
    }

    public void mo50r(int i) {
    }
}
