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

public class bc extends C0001b {
    private boolean at;

    public bc(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 88;
    }

    private void eu(byte[] bArr) {
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
        if ((bArr[6] & 1) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[4] & 64) != 0) {
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
        this.ei.tempUnit = (bArr[4] & 1) != 0;
        this.at = (bArr[4] & 2) != 0;
        this.ei.tempLeft = ez(bArr[2] & 255);
        this.ei.tempRight = ez(bArr[3] & 255);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[4] & 8) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatShow = false;
    }

    private void ew(byte b) {
        this.ej.ob((byte) -58, new byte[]{(byte) 0, b}, 2);
    }

    private synchronized int ex(int i) {
        int i2;
        if (i < 0) {
            i = 0 - i;
        }
        i2 = System.getInt(this.ej.getContentResolver(), "canbus1_angle", 450);
        if (i > i2) {
            System.putInt(this.ej.getContentResolver(), "canbus1_angle", i);
        }
        return i2;
    }

    private int ey(int i) {
        return this.ei.tempUnit ? (int) ((((double) (i * 10)) * 1.8d) + 32.0d) : i * 10;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 7) {
                    byte[] bArr2 = new byte[7];
                    while (i3 < 7) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    if ((bArr2[1] & 16) != 0) {
                        eu(bArr2);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2 && (bArr[i + 3] & 1) != 0) {
                    ev(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] == (byte) 2) {
                    int i5 = bArr[i + 3] & 255;
                    String str = "";
                    if (i5 > 0) {
                        i4 = (i5 / 2) - 39;
                        if (bArr[i + 4] == (byte) 0) {
                            str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i4)}) + this.el.getString(R.string.c_dan);
                        } else {
                            str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf((int) ((((double) i4) * 1.8d) + 32.0d))}) + this.el.getString(R.string.f_dan);
                        }
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
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
                    i4 = (i4 * 30) / ex(i4);
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 64:
                if (bArr[i + 2] >= (byte) 4) {
                    byte[] bArr3 = new byte[6];
                    bArr3[0] = (byte) 64;
                    bArr3[1] = (byte) 4;
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr3[i4 + 2] = bArr[(i + 3) + i4];
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
        this.ej.oh(1);
    }

    public void mo37e() {
        mo39g();
    }

    void ev(byte[] bArr) {
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

    int ez(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 255) {
            return 65535;
        }
        if (i < 1 || i > 254) {
            return -1;
        }
        if (!this.at) {
            return this.ei.tempUnit ? i * 10 : (i * 10) / 2;
        } else {
            switch (i >> 6) {
                case R$styleable.MyButton_imgWidth /*0*/:
                case R$styleable.MyButton_imgSrc1 /*3*/:
                    return ey(26);
                case R$styleable.MyButton_imgHeight /*1*/:
                    return ey(18);
                case R$styleable.MyButton_imgSrc /*2*/:
                    return ey(28);
                default:
                    return i;
            }
        }
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo40h(String str, int i) {
        mo46n();
    }

    public void mo41i() {
        mo39g();
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
        mo39g();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        r0 = new byte[8];
        int i4 = i3 / 1000;
        r0[6] = (byte) ((i4 / 60) % 60);
        r0[7] = (byte) (i4 % 60);
        this.ej.ob((byte) -64, r0, 8);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[8], 8);
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

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("en")) {
            ew((byte) 1);
        } else if (language.equals("de")) {
            ew((byte) 3);
        } else if (language.equals("it")) {
            ew((byte) 5);
        } else if (language.equals("fr")) {
            ew((byte) 2);
        } else if (language.equals("es")) {
            ew((byte) 4);
        } else if (language.equals("nl")) {
            ew((byte) 6);
        } else if (language.equals("pt")) {
            ew((byte) 8);
        } else if (language.equals("pl")) {
            ew((byte) 7);
        } else if (language.equals("tr")) {
            ew((byte) 9);
        }
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[6];
        if (!DateFormat.is24HourFormat(this.el)) {
            if (this.en > 12) {
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
            this.en |= 128;
        }
        bArr[0] = (byte) ((time.year - 2000) & 255);
        bArr[1] = (byte) ((time.month + 1) & 255);
        bArr[2] = (byte) (time.monthDay & 255);
        bArr[3] = (byte) (this.en & 255);
        bArr[4] = (byte) (this.eo & 255);
        this.ej.ob((byte) -57, bArr, 6);
    }

    public void mo50r(int i) {
    }
}
