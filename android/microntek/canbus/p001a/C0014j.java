package android.microntek.canbus.p001a;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings.System;
import android.text.format.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class C0014j extends C0001b {
    public C0014j(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 24;
    }

    void av(byte[] bArr) {
        boolean z = true;
        Door door;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        if (this.ej.jg == 1) {
            door = this.em;
            z2 = (bArr[0] & 64) != 0;
            z3 = (bArr[0] & 128) != 0;
            z4 = (bArr[0] & 16) != 0;
            z5 = (bArr[0] & 32) != 0;
            z6 = (bArr[0] & 8) != 0;
            if ((bArr[0] & 0) == 0) {
                z = false;
            }
            if (door.la(z2, z3, z4, z5, z6, z)) {
                this.ej.of(this.em);
                return;
            }
            return;
        }
        door = this.em;
        z2 = (bArr[0] & 128) != 0;
        z3 = (bArr[0] & 64) != 0;
        z4 = (bArr[0] & 32) != 0;
        z5 = (bArr[0] & 16) != 0;
        z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
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
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] == (byte) 8) {
                    String str;
                    i4 = ((bArr[i + 3] & 255) << 8) + (bArr[i + 4] & 255);
                    String str2 = "";
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    if (this.eq) {
                        str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(((float) i4) * 0.5f)}) + this.el.getString(R.string.c_dan);
                    } else {
                        str = String.format(Locale.US, " %.0f", new Object[]{Float.valueOf(((float) i4) * 1.0f)}) + this.el.getString(R.string.f_dan);
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    bArr2 = new byte[9];
                    bArr2[0] = (byte) 3;
                    for (i4 = 0; i4 < 8; i4++) {
                        bArr2[i4 + 1] = bArr[(i + 3) + i4];
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] == (byte) 5) {
                    bArr2 = new byte[6];
                    bArr2[0] = (byte) 4;
                    for (i4 = 0; i4 < 5; i4++) {
                        bArr2[i4 + 1] = bArr[(i + 3) + i4];
                    }
                    this.ej.oj(bArr2);
                    if (bArr2[4] == (byte) 1) {
                        this.eq = false;
                        return;
                    } else {
                        this.eq = true;
                        return;
                    }
                }
                return;
            case (byte) 7:
                if (bArr[i + 2] == (byte) 2) {
                    byte[] bArr3 = new byte[3];
                    bArr3[0] = (byte) 7;
                    while (i3 < 2) {
                        bArr3[i3 + 1] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] == (byte) 1) {
                    av(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 28:
                if (bArr[i + 2] == (byte) 8) {
                    int[] iArr = new int[8];
                    for (i4 = 0; i4 < 8; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 15;
                        if (iArr[i4] != 0) {
                            iArr[i4] = 0 - (iArr[i4] - 11);
                        }
                    }
                    this.ek.max = 10;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    this.ek.front_cnt = 4;
                    this.ek.f1 = iArr[4];
                    this.ek.f2 = iArr[5];
                    this.ek.f3 = iArr[6];
                    this.ek.f4 = iArr[7];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 1) {
                    i4 = bArr[i + 3] & 255;
                    if (i4 >= 128) {
                        i4 = 128 - i4;
                    }
                    i4 = (i4 * 35) / 50;
                    Intent intent2 = new Intent("com.microntek.canbusbackview");
                    intent2.putExtra("canbustype", this.eh);
                    intent2.putExtra("lfribackview", 0 - i4);
                    this.el.sendBroadcast(intent2);
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
        String string = System.getString(this.ej.getContentResolver(), "TimeZone");
        if (!(string == null || string.equals(""))) {
            ((AlarmManager) this.ej.getSystemService("alarm")).setTimeZone(string);
        }
        Calendar instance = Calendar.getInstance();
        instance.getTime();
        instance.setTime(new Date(112, 2, 31, 0, 0));
        long j = System.getLong(this.ej.getContentResolver(), "getTimeInMillis", instance.getTimeInMillis());
        SystemClock.setCurrentTimeMillis(j);
        this.ej.sendBroadcastAsUser(new Intent("android.intent.action.TIME_SET"), UserHandle.CURRENT_OR_SELF);
        SystemClock.setCurrentTimeMillis(j);
        this.ej.sendBroadcastAsUser(new Intent("android.intent.action.TIME_SET"), UserHandle.CURRENT_OR_SELF);
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
        this.ej.ob((byte) -125, new byte[]{(byte) (this.en & 127), (byte) (this.eo & 255), (byte) time.monthDay, (byte) (time.month + 1), (byte) (time.year - 2000)}, 5);
        System.putLong(this.ej.getContentResolver(), "getTimeInMillis", Calendar.getInstance().getTimeInMillis());
        System.putString(this.ej.getContentResolver(), "TimeZone", TimeZone.getDefault().getID());
    }

    public void mo50r(int i) {
    }
}
