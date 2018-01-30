package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.p002b.C0035b;
import android.os.Handler;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.io.UnsupportedEncodingException;

public class ch extends C0001b {
    private boolean cf;
    byte[] cg;
    private Handler ch;

    public ch(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.cg = new byte[16];
        this.ch = null;
        this.cf = false;
        this.eh = 77;
    }

    private boolean is(long j) {
        return (j / 60) / 60 > 0;
    }

    private String it(long j) {
        String str = "";
        return "" + (j / 10) + (j % 10);
    }

    private String iu(long j, boolean z) {
        long j2 = (j / 60) % 60;
        long j3 = j % 60;
        return z ? it((j / 60) / 60) + ":" + it(j2) + ":" + it(j3) : it(j2) + ":" + it(j3);
    }

    private byte[] iv(String str) {
        try {
            byte[] bytes = (str + "                            ").getBytes("unicode");
            byte[] bArr = new byte[16];
            if (bytes != null) {
                for (int i = 1; i < bArr.length; i++) {
                    bArr[i] = bytes[i + 1];
                }
            }
            return bArr;
        } catch (UnsupportedEncodingException e) {
            return this.cg;
        }
    }

    private int iw(int i, int i2) {
        return i <= i2 ? (i2 - i) + 1 : 0;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        switch (bArr[i + 1]) {
            case (byte) 65:
                if (bArr[i + 2] == (byte) 12) {
                    int[] iArr = new int[12];
                    for (i3 = 0; i3 < 12; i3++) {
                        iArr[i3] = bArr[(i + 3) + i3] & 255;
                    }
                    this.ek.mode = 1;
                    this.ek.back_cnt = 4;
                    this.ek.bmax1 = 3;
                    this.ek.bmax2 = 4;
                    this.ek.bmax3 = 4;
                    this.ek.bmax4 = 3;
                    this.ek.b1 = iw(iArr[0], 3);
                    this.ek.b2 = iw(iArr[1], 4);
                    this.ek.b3 = iw(iArr[2], 4);
                    this.ek.b4 = iw(iArr[3], 3);
                    this.ek.bc1 = -65536;
                    this.ek.bc2 = -65536;
                    this.ek.bc3 = -65536;
                    this.ek.bc4 = -65536;
                    this.ek.front_cnt = 4;
                    this.ek.fmax1 = 3;
                    this.ek.fmax2 = 4;
                    this.ek.fmax3 = 4;
                    this.ek.fmax4 = 3;
                    this.ek.f1 = iw(iArr[4], 3);
                    this.ek.f2 = iw(iArr[5], 4);
                    this.ek.f3 = iw(iArr[6], 4);
                    this.ek.f4 = iw(iArr[7], 3);
                    this.ek.fc1 = -65536;
                    this.ek.fc2 = -65536;
                    this.ek.fc3 = -65536;
                    this.ek.fc4 = -65536;
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 114:
                if (bArr[i + 2] == (byte) 14) {
                    if (C0035b.lp() <= 19 && this.ej.jg == 2 && this.ch != null) {
                        if (this.cf) {
                            this.cf = false;
                            this.ch.removeMessages(0);
                            this.ch.sendEmptyMessageDelayed(0, 120);
                        }
                        if (bArr[i + 5] == (byte) 15) {
                            this.cf = true;
                        }
                    }
                    i3 = (bArr[i + 8] & 255) + ((bArr[i + 7] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / 500;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
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
        this.ej.ok((byte) -45, iv(""), 16);
        this.ch = new dr(this);
        mo48p();
        this.ej.oa(1);
        this.ej.oh(1);
    }

    public void mo37e() {
        this.ej.ok((byte) -46, new byte[]{(byte) 10, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
        this.ej.ok((byte) -45, iv(""), 16);
    }

    public void mo38f() {
        this.ej.ok((byte) -46, new byte[]{(byte) 8, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
        this.ej.ok((byte) -45, iv(""), 16);
    }

    public void mo39g() {
        this.ej.ok((byte) -46, new byte[]{(byte) 12, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
        this.ej.ok((byte) -45, iv(""), 16);
    }

    public void mo40h(String str, int i) {
        this.ej.ok((byte) -46, new byte[]{(byte) 10, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
        this.ej.ok((byte) -45, iv(""), 16);
    }

    public void mo41i() {
        this.ej.ok((byte) -46, new byte[]{(byte) 8, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
        this.ej.ok((byte) -45, iv(""), 16);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[13];
        bArr[0] = (byte) (i3 == 2 ? 7 : 6);
        bArr[1] = (byte) 32;
        bArr[2] = (byte) 32;
        bArr[3] = (byte) 32;
        bArr[4] = (byte) 32;
        bArr[5] = (byte) 32;
        bArr[6] = (byte) 32;
        bArr[7] = (byte) 32;
        bArr[8] = (byte) 32;
        bArr[9] = (byte) 32;
        bArr[10] = (byte) 32;
        bArr[11] = (byte) 32;
        bArr[12] = (byte) 32;
        this.ej.ok((byte) -46, bArr, 13);
        this.ej.ok((byte) -45, iv(iu((long) i2, is((long) i2))), 16);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 13, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        bArr[1] = (byte) (((i / 100) % 10) + 48);
        bArr[2] = (byte) (((i / 10) % 10) + 48);
        bArr[3] = (byte) ((i % 10) + 48);
        bArr[6] = (byte) (((i2 / 100) % 10) + 48);
        bArr[7] = (byte) (((i2 / 10) % 10) + 48);
        bArr[8] = (byte) ((i2 % 10) + 48);
        this.ej.ok((byte) -46, bArr, 13);
        this.ej.ok((byte) -45, iv(iu((long) (i3 / 1000), is((long) (i3 / 1000)))), 16);
    }

    public void mo46n() {
        this.ej.ok((byte) -46, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
        this.ej.ok((byte) -45, iv(""), 16);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 1, (byte) 45, (byte) 1, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) 48;
            bArr[2] = (byte) (b2 + 49);
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
            bArr[1] = (byte) 48;
            bArr[2] = (byte) (b2 + 49);
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
        this.ej.ok((byte) -46, bArr, 13);
        this.ej.ok((byte) -45, iv(""), 16);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            this.ej.ok((byte) -102, new byte[]{(byte) 1, (byte) 2}, 2);
        } else if (language.equals("en")) {
            this.ej.ok((byte) -102, new byte[]{(byte) 1, (byte) 1}, 2);
        }
    }

    public void mo49q() {
        int i = 1;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[10];
        bArr[3] = (byte) (this.en & 127);
        bArr[4] = (byte) (this.eo & 255);
        bArr[5] = (byte) (is24HourFormat ? 1 : 0);
        if (this.en <= 12) {
            i = 0;
        }
        bArr[6] = (byte) i;
        this.ej.ok((byte) -53, bArr, 10);
    }

    public void mo50r(int i) {
    }
}
