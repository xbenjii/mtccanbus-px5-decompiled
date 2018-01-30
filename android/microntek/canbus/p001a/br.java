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
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class br extends C0001b {
    byte[] bf;
    private boolean bg;

    public br(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.bf = new byte[31];
        this.bg = false;
        this.eh = 75;
    }

    private void gl(byte[] bArr) {
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
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[2] & 64) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[2] & 16) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[2] & 32) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[1] & 15;
        this.ei.windLevelMax = 7;
        this.ei.tempLeft = gn(bArr[3] & 255);
        this.ei.tempRight = gn(bArr[4] & 255);
        if ((bArr[0] & 8) == 8) {
            this.ei.windLoop = 2;
        } else if ((bArr[0] & 4) == 4) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatHotLeft = (bArr[5] >> 6) & 3;
        this.ei.seatHotRight = (bArr[5] >> 2) & 3;
    }

    private byte[] go(byte b, String str) {
        try {
            byte[] bytes = (str + "                          ").getBytes("unicode");
            byte[] bArr = new byte[31];
            bArr[0] = b;
            if (bytes != null) {
                for (int i = 1; i < bArr.length - 1; i++) {
                    if (i != 1) {
                        bArr[i] = bytes[i];
                    }
                }
            }
            return bArr;
        } catch (UnsupportedEncodingException e) {
            return this.bf;
        }
    }

    private void gp() {
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        this.ej.oe(this.ek);
    }

    public void ak(int i, int i2, int i3) {
        this.ej.ob((byte) -112, go((byte) 4, "USB " + i2), 31);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        int i3;
        int i4 = 0;
        int i5;
        String str;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgWidth /*0*/:
                if (bArr[i + 2] != (byte) 8) {
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] >= (byte) 8) {
                    bArr2 = new byte[7];
                    for (i3 = 0; i3 < 7; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    gl(bArr2);
                    if (lx(bArr2)) {
                        this.ej.od(this.ei);
                    }
                    i5 = bArr[(i + 3) + 7] & 255;
                    str = "";
                    if (i5 <= 125) {
                        i3 = i5 - 40;
                        if (this.ei.tempUnit) {
                            str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf((((float) i3) * 1.8f) + 32.0f)}) + this.el.getString(R.string.f_dan);
                        } else {
                            str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                        }
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    bArr3 = new byte[10];
                    bArr3[0] = (byte) 5;
                    bArr3[1] = (byte) 8;
                    while (i4 < 8) {
                        bArr3[i4 + 2] = bArr[(i + 3) + i4];
                        i4++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 7:
                break;
            case (byte) 9:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = ((bArr[i + 3] & 255) << 8) + (bArr[i + 4] & 255);
                    if (this.ej.jg != 2) {
                        if (i3 >= 32768) {
                            i3 -= 65536;
                        }
                        lz(0 - ((i3 * 30) / 540));
                        break;
                    }
                    lz(0 - ((((i3 - 616) - 1792) * 30) / 1199));
                    break;
                }
                break;
            case (byte) 10:
                if (bArr[i + 2] >= (byte) 2) {
                    gm(new byte[]{bArr[i + 4]});
                    return;
                }
                return;
            case (byte) 11:
                if (bArr[i + 2] != (byte) 2) {
                    return;
                }
                return;
            case (byte) 16:
                if (bArr[i + 2] >= (byte) 10) {
                    bArr3 = new byte[12];
                    bArr3[0] = (byte) 16;
                    bArr3[1] = (byte) 10;
                    while (i4 < 10) {
                        bArr3[i4 + 2] = bArr[(i + 3) + i4];
                        i4++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 17:
                if (bArr[i + 2] >= (byte) 3 && bArr[i + 2] <= (byte) 67) {
                    i3 = bArr[i + 2] & 127;
                    bArr2 = new byte[(i3 + 2)];
                    bArr2[0] = (byte) 17;
                    bArr2[1] = (byte) i3;
                    while (i4 < i3) {
                        bArr2[i4 + 2] = bArr[(i + 3) + i4];
                        i4++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 21:
                if (bArr[i + 2] >= (byte) 1) {
                    i5 = bArr[i + 3] & 255;
                    str = "";
                    if (i5 <= 125) {
                        i3 = i5 - 40;
                        if (this.bg) {
                            str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf((int) ((((double) i3) * 1.8d) + 32.0d))}) + this.el.getString(R.string.f_dan);
                        } else {
                            str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                        }
                    }
                    Intent intent2 = new Intent("com.canbus.temperature");
                    intent2.putExtra("temperature", str);
                    this.el.sendBroadcast(intent2);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 5) {
                    bArr2 = new byte[5];
                    for (i3 = 0; i3 < 5; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    this.ek.max = 5;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[1];
                    this.ek.b3 = bArr2[2];
                    this.ek.b4 = bArr2[3];
                    if ((bArr2[4] & 128) != 0) {
                        gp();
                        return;
                    }
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] >= (byte) 5) {
                    bArr2 = new byte[5];
                    for (i3 = 0; i3 < 5; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                    }
                    this.ek.max = 5;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ek.f4 = bArr2[3];
                    if ((bArr2[4] & 128) != 0) {
                        gp();
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
        if (mc(bArr[i + 2], 12)) {
            bArr2 = new byte[(this.er + 2)];
            bArr2[0] = (byte) 7;
            bArr2[1] = (byte) this.er;
            for (i3 = 0; i3 < this.er; i3++) {
                bArr2[i3 + 2] = bArr[(i + 3) + i3];
            }
            this.ej.oj(bArr2);
            if (this.er > 12) {
                boolean z;
                if ((bArr2[12] & 16) != 0) {
                    z = true;
                }
                this.bg = z;
            }
        }
    }

    public void bg() {
        this.ej.ob((byte) -112, go((byte) 0, ""), 31);
    }

    public void bh() {
        mo39g();
    }

    public void mo36d() {
        mo48p();
        this.ej.oa(1);
        this.ej.oh(1);
        mo39g();
    }

    public void mo37e() {
        this.ej.ob((byte) -112, go((byte) 5, "    A2DP      "), 31);
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        this.ej.ob((byte) -112, go((byte) 7, "    AUX      "), 31);
    }

    void gm(byte[] bArr) {
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

    int gn(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 127) {
            return 65535;
        }
        if (i <= 30) {
            this.ei.tempUnit = false;
            return i * 10;
        } else if (i > 84) {
            return -1;
        } else {
            this.ei.tempUnit = true;
            return i * 10;
        }
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -112, go((byte) 5, "  BLUETOOTH "), 31);
    }

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -112, go((byte) 3, "CDTOTAL " + i), 31);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ob((byte) -112, go((byte) 4, "IPOD " + i2), 31);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -112, go((byte) 4, "USB " + i2), 31);
    }

    public void mo46n() {
        mo39g();
    }

    public void mo47o(byte b, int i, byte b2) {
        String str;
        byte b3 = (byte) 1;
        String str2 = "";
        if (b >= (byte) 0 && b <= (byte) 2) {
            str = "FM" + (b + 1) + " " + String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(((float) i) / 100.0f)}) + "MHz";
        } else if (b < (byte) 3 || b > (byte) 4) {
            str = str2;
        } else {
            str = "AM" + (b - 2) + " " + i + "KHz";
            b3 = (byte) 2;
        }
        this.ej.ob((byte) -112, go(b3, str), 31);
    }

    public void mo48p() {
        if (this.ej.getResources().getConfiguration().locale.getLanguage().equals("zh")) {
            this.ej.ob((byte) -105, new byte[]{(byte) 83, (byte) 9}, 2);
        } else {
            this.ej.ob((byte) -105, new byte[]{(byte) 83, (byte) 1}, 2);
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
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
            this.en |= 128;
        }
        bArr[1] = (byte) (this.en & 255);
        bArr[2] = (byte) (this.eo & 255);
        this.ej.ob((byte) -58, bArr, 4);
        bArr = new byte[7];
        bArr[0] = (byte) 80;
        if (!is24HourFormat) {
            if (this.en > 12) {
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
            this.en |= 128;
        }
        bArr[1] = (byte) (this.en & 255);
        bArr[2] = (byte) (this.eo & 255);
        bArr[4] = (byte) ((time.year - 2000) & 255);
        bArr[5] = (byte) ((time.month + 1) & 255);
        bArr[6] = (byte) (time.monthDay & 255);
        this.ej.ob((byte) -58, bArr, 7);
    }

    public void mo50r(int i) {
    }
}
