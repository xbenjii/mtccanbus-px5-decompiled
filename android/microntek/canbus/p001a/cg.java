package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class cg extends C0001b {
    int ce;

    public cg(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.ce = 16777215;
        this.eh = 99;
    }

    private synchronized byte[] ip() {
        byte[] bArr;
        bArr = new byte[5];
        Object string = System.getString(this.ej.getContentResolver(), "Setting_0x82_CMD");
        try {
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                int i = 0;
                while (i < split.length && i < 5) {
                    bArr[i] = (byte) Integer.parseInt(split[i]);
                    i++;
                }
            }
        } catch (Exception e) {
        }
        return bArr;
    }

    private void iq(byte[] bArr) {
        if (bArr.length == 5) {
            System.putString(this.ej.getContentResolver(), "Setting_0x82_CMD", (bArr[0] & 255) + "," + (bArr[1] & 255) + "," + (bArr[2] & 255) + "," + (bArr[3] & 255) + "," + (bArr[4] & 255) + ",");
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        boolean z = true;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] >= (byte) 8) {
                    this.ce = ((bArr[i + 3] & 255) << 8) | (bArr[i + 4] & 255);
                    ir();
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] >= (byte) 5) {
                    System.putString(this.ej.getContentResolver(), "canbus99_cmd_0x04", (bArr[i + 1] & 255) + "," + (bArr[i + 2] & 255) + "," + (bArr[i + 3] & 255) + "," + (bArr[i + 4] & 255) + "," + (bArr[i + 5] & 255) + "," + (bArr[i + 6] & 255) + "," + (bArr[i + 7] & 255));
                    AirCondition airCondition = this.ei;
                    if (bArr[i + 6] != (byte) 1) {
                        z = false;
                    }
                    airCondition.tempUnit = z;
                    ly(bArr, i, i2);
                    ir();
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] >= (byte) 2) {
                    System.putString(this.ej.getContentResolver(), "canbus99_cmd_0x05", (bArr[i + 1] & 255) + "," + (bArr[i + 2] & 255) + "," + (bArr[i + 3] & 255) + "," + (bArr[i + 4] & 255));
                    ly(bArr, i, i2);
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] >= (byte) 1) {
                    io(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 15:
                if (bArr[i + 2] == (byte) 1) {
                    mg(bArr[i + 3] & 255);
                    return;
                }
                return;
            case (byte) 27:
                if (bArr[i + 2] >= (byte) 4) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ek.max = 10;
                    for (int i3 = 0; i3 < 4; i3++) {
                        this.ep[i3] = bArr[(i + 3) + i3] & 255;
                        if (this.ep[i3] < 253) {
                            this.ep[i3] = (this.ep[i3] * this.ek.max) / 253;
                        } else {
                            this.ep[i3] = 0;
                        }
                    }
                    this.ek.back_cnt = 4;
                    this.ek.b1 = this.ep[0];
                    this.ek.b2 = this.ep[1];
                    this.ek.b3 = this.ep[2];
                    this.ek.b4 = this.ep[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 1) {
                    int i4 = bArr[i + 3] & 255;
                    if (i4 >= 128) {
                        i4 = 128 - i4;
                    }
                    lz(0 - ((i4 * 30) / 40));
                    return;
                }
                return;
            case (byte) 60:
                if (bArr[i + 2] >= (byte) 3) {
                    System.putString(this.ej.getContentResolver(), "canbus99_cmd_0x3C", (bArr[i + 1] & 255) + "," + (bArr[i + 2] & 255) + "," + (bArr[i + 3] & 255) + "," + (bArr[i + 4] & 255) + "," + (bArr[i + 5] & 255));
                    ly(bArr, i, i2);
                    return;
                }
                return;
            default:
                ly(bArr, i, i2);
                return;
        }
    }

    public void mo36d() {
        this.ej.oh(1);
        this.ej.oa(1);
        mo48p();
    }

    void io(byte[] bArr) {
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

    void ir() {
        if (this.ce != 16777215) {
            String str = "";
            if (this.ce > 32768) {
                this.ce -= 65536;
            }
            if (this.ei.tempUnit) {
                str = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf((float) this.ce)}) + this.el.getString(R.string.f_dan);
            } else {
                str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(((float) this.ce) / 2.0f)}) + this.el.getString(R.string.c_dan);
            }
            md(str);
        }
    }

    public void mo48p() {
        Locale locale = this.ej.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        byte[] ip = ip();
        if (language.equals("zh") && country.equalsIgnoreCase("CN")) {
            ip[1] = (byte) 10;
        } else if (language.equals("zh")) {
            ip[1] = (byte) 9;
        } else if (language.equals("en")) {
            ip[1] = (byte) 1;
        } else {
            ip[1] = (byte) 0;
        }
        this.ej.ob((byte) -126, ip, 5);
        iq(ip);
    }

    public void mo49q() {
        int i = 1;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        this.ej.ob((byte) -122, new byte[]{(byte) (this.en & 127), (byte) (this.eo & 255), (byte) (time.monthDay & 255), (byte) ((time.month + 1) & 255), (byte) ((time.year - 2000) & 255)}, 5);
        byte[] ip = ip();
        if (!is24HourFormat) {
            i = 0;
        }
        ip[4] = (byte) i;
        this.ej.ob((byte) -126, ip, 5);
        iq(ip);
    }
}
