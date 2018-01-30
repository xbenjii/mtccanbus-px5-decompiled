package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class cq extends C0001b {
    public cq(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void js(byte[] bArr) {
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
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[3] & 128) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[3] & 64) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[1] >> 4) {
            case R$styleable.MyButton_imgWidth /*0*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[1] & 7;
        this.ei.windLevelMax = 7;
        int i = bArr[2] & 255;
        this.ei.tempLeft = ju(i);
        this.ei.tempRight = this.ei.tempLeft;
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        byte[] bArr2;
        int i4;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    while (i3 < 4) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    js(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    byte[] bArr3 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 7;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr3[0];
                    this.ek.b2 = bArr3[1];
                    this.ek.b3 = bArr3[2];
                    this.ek.b4 = bArr3[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    jt(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] == (byte) 1) {
                    i4 = bArr[i + 3] & 127;
                    String str = "";
                    if ((bArr[i + 3] & 128) != 0) {
                        i4 = 0 - i4;
                    }
                    String str2 = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i4)}) + this.el.getString(R.string.c_dan);
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] == (byte) 2) {
                    i4 = bArr[i + 4] & 255;
                    i3 = bArr[i + 3] & 127;
                    i4 = (((bArr[i + 3] & 255) >= 128 ? 0 - ((i3 << 8) + i4) : i4 + ((128 - i3) << 8)) * 30) / 7700;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 64:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[6];
                    bArr2[0] = (byte) 64;
                    bArr2[1] = (byte) 4;
                    while (i3 < 4) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 80:
                if (bArr[i + 2] == (byte) 14) {
                    bArr2 = new byte[16];
                    bArr2[0] = (byte) 80;
                    bArr2[1] = (byte) 14;
                    while (i3 < 14) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 81:
                int i5 = bArr[i + 2] & 255;
                byte[] bArr4 = new byte[(i5 + 2)];
                bArr4[0] = (byte) 81;
                bArr4[1] = (byte) i5;
                for (i4 = 0; i4 < i5; i4++) {
                    bArr4[i4 + 2] = bArr[(i + 3) + i4];
                }
                this.ej.oj(bArr4);
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

    public void mo42j(int i, int i2, int i3) {
    }

    void jt(byte[] bArr) {
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

    int ju(int i) {
        return i == 0 ? 0 : i == 15 ? 65535 : i <= 10 ? (i + 16) * 10 : -1;
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
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[5];
        bArr[0] = (byte) ((time.year - 2000) & 255);
        bArr[1] = (byte) ((time.month + 1) & 255);
        bArr[2] = (byte) (time.monthDay & 255);
        bArr[3] = (byte) ((is24HourFormat ? this.en : this.en | 128) & 255);
        bArr[4] = (byte) (this.eo & 255);
        this.ej.ob((byte) -90, bArr, 5);
    }

    public void mo50r(int i) {
    }
}
