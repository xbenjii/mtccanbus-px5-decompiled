package android.microntek.canbus.p001a;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.text.format.Time;
import java.util.Locale;

public class dj extends C0001b {
    private Notification dc;
    private NotificationManager dd;
    private int de;

    public dj(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.de = 0;
        this.eh = 29;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        Intent intent;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc /*2*/:
                if (bArr[i + 2] == (byte) 4) {
                    String str;
                    i3 = (bArr[i + 3] & 255) << 8;
                    int i4 = bArr[i + 4] & 255;
                    if (this.de != i3 + i4) {
                        this.de = i3 + i4;
                        this.dc.setLatestEventInfo(this.ej, this.ej.getString(R.string.feasible), this.de + " Km", null);
                        this.dd.notify(1, this.dc);
                    }
                    float f = (float) (((bArr[i + 5] & 255) << 8) + (bArr[i + 6] & 255));
                    if (f < 33269.0f && f >= 32769.0f) {
                        f = (32769.0f - f) - 1.0f;
                    }
                    String str2 = "";
                    if (f <= -500.0f) {
                        f = -499.0f;
                    }
                    if (f < -500.0f || f > 770.0f) {
                        str = str2;
                    } else {
                        str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f * 0.1f)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] != (byte) 3) {
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] == (byte) 1) {
                    i3 = bArr[i + 3] & 255;
                    if (i3 == 0 || i3 == 128) {
                        i3 = 0;
                    }
                    if (i3 > 128) {
                        i3 = 128 - i3;
                    }
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] == (byte) 9) {
                    byte[] bArr2 = new byte[11];
                    bArr2[0] = (byte) 49;
                    bArr2[1] = (byte) 9;
                    for (i3 = 0; i3 < 9; i3++) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] == (byte) 1) {
                    this.ej.oj(new byte[]{(byte) 50, (byte) 1, bArr[i + 3]});
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void bg() {
        this.ej.ob((byte) -126, new byte[4], 4);
    }

    public void mo36d() {
        this.dd = (NotificationManager) this.ej.getSystemService("notification");
        this.dc = new Notification(R.drawable.oil, null, 0);
        Notification notification = this.dc;
        notification.flags |= 32;
        notification = this.dc;
        notification.flags |= 1;
        this.ej.oh(1);
    }

    public void mo37e() {
        this.ej.ob((byte) -126, new byte[]{(byte) 10, (byte) 32, (byte) 32, (byte) 32}, 4);
    }

    public void mo38f() {
        this.ej.ob((byte) -126, new byte[]{(byte) 11, (byte) 32, (byte) 32, (byte) 32}, 4);
    }

    public void mo39g() {
        this.ej.ob((byte) -126, new byte[]{(byte) 9, (byte) 32, (byte) 32, (byte) 32}, 4);
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -126, new byte[]{(byte) 10, (byte) 32, (byte) 32, (byte) 32}, 4);
    }

    public void mo41i() {
        this.ej.ob((byte) -126, new byte[]{(byte) 11, (byte) 32, (byte) 32, (byte) 32}, 4);
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -126, new byte[]{(byte) 6, (byte) -1, (byte) -1, (byte) -1}, 4);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ob((byte) -126, new byte[]{(byte) 5, (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) 32}, 4);
    }

    public void mo44l() {
        this.ej.ob((byte) -126, new byte[]{(byte) 3, (byte) -1, (byte) -1, (byte) -1}, 4);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -126, new byte[]{(byte) 3, (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) 32}, 4);
    }

    public void mo46n() {
        byte[] bArr = new byte[4];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -126, bArr, 4);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[4];
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) 1;
            int i2 = i / 10;
            bArr[1] = (byte) ((i2 >> 8) & 255);
            bArr[2] = (byte) (i2 & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) 2;
            bArr[1] = (byte) ((i >> 8) & 255);
            bArr[2] = (byte) (i & 255);
        }
        this.ej.ob((byte) -126, bArr, 4);
    }

    public void mo48p() {
        this.dc.setLatestEventInfo(this.ej, this.ej.getString(R.string.feasible), this.de + " Km", null);
        this.dd.notify(1, this.dc);
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        this.ej.ob((byte) -125, new byte[]{(byte) (this.en & 255), (byte) (this.eo & 255)}, 2);
    }

    public void mo50r(int i) {
    }
}
