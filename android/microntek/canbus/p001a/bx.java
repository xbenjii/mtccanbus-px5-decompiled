package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class bx extends C0001b {
    private static long cb = 0;
    byte bs;
    private boolean bt;
    private boolean bu;
    private boolean bv;
    private boolean bw;
    private View bx;
    private Object by;
    private TextView bz;
    private WindowManager ca;
    private LayoutParams cc;

    public bx(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.bv = false;
        this.bt = false;
        this.bu = false;
        this.by = new Object();
        this.bs = (byte) 1;
        this.eh = 10;
    }

    private void hs(byte[] bArr) {
        new Thread(new dq(this, bArr)).start();
    }

    private void hv(byte b) {
        this.ej.ob((byte) -125, new byte[]{(byte) 49, b}, 2);
    }

    private void hx() {
        if (this.bx != null && !this.bv) {
            this.bv = true;
            this.bz.setText(R.string.emergency_call);
            this.ca.addView(this.bx, this.cc);
        }
    }

    private boolean hy() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - cb <= 800) {
            return false;
        }
        cb = uptimeMillis;
        return true;
    }

    private void hz() {
        this.cc = new LayoutParams();
        this.ca = (WindowManager) this.ej.getApplication().getSystemService("window");
        this.cc.type = 2002;
        this.cc.format = 1;
        this.cc.flags = 40;
        this.cc.gravity = 81;
        this.cc.x = 0;
        this.cc.y = 0;
        this.cc.width = -1;
        this.cc.height = -1;
        this.bx = LayoutInflater.from(this.ej.getApplication()).inflate(R.layout.canbus10and15layout, null);
        this.bz = (TextView) this.bx.findViewById(R.id.text_view);
    }

    private void ia() {
        if (this.bx != null && this.bv) {
            this.bv = false;
            this.ca.removeView(this.bx);
        }
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void at() {
        this.ej.ob((byte) -112, new byte[]{(byte) -108, (byte) 0}, 2);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int[] iArr;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) -108:
                if (mc(bArr[i + 2], 4)) {
                    byte[] bArr2 = new byte[(this.er + 2)];
                    bArr2[0] = (byte) -108;
                    bArr2[1] = (byte) this.er;
                    while (i3 < this.er) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) -107:
                if (bArr[i + 3] == (byte) 1) {
                    lv("av_mute=true");
                    return;
                } else {
                    lv("av_mute=false");
                    return;
                }
            case (byte) 32:
                if (bArr[i + 2] < (byte) 2 || !this.bu || bArr[i + 4] != (byte) 1) {
                    return;
                }
                if (bArr[i + 3] == (byte) 35) {
                    lv("av_mute=true");
                    hx();
                    return;
                } else if (bArr[i + 3] == (byte) 36) {
                    lv("av_mute=false");
                    ia();
                    return;
                } else {
                    return;
                }
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    mb();
                    iArr = new int[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
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
                    mb();
                    iArr = new int[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
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
                    hu(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = ((bArr[i + 3] & 127) << 8) | (bArr[i + 4] & 255);
                    if ((bArr[i + 3] & 128) == 0) {
                        i3 = 0 - i3;
                    }
                    lz((i3 * 30) / 5400);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        if (lu().contains("HZC") || mh().contains("HZC")) {
            this.bt = true;
        } else if (lu().contains("KLD") || mh().contains("KLD")) {
            this.bu = true;
        }
        hz();
        this.ej.oa(1);
        this.ej.oh(1);
        mo48p();
        if (!this.bu || me() != 2) {
            return;
        }
        if (System.getInt(this.ej.getContentResolver(), "degrees_360", 0) == 1) {
            hs(new byte[]{(byte) 97, (byte) 1});
        } else {
            hs(new byte[]{(byte) 96, (byte) 0});
        }
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        bArr[1] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        if (this.ej.jh == null || !this.ej.jh.startsWith("KY")) {
            this.ej.ob((byte) -64, bArr, 8);
        } else {
            mo39g();
        }
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo40h(String str, int i) {
        int i2 = 0;
        int i3 = 1;
        if ((i == 1 || i == 2) && lu().indexOf("KLD") != -1) {
            try {
                byte[] bytes = str.getBytes("gb2312");
                byte[] bArr = new byte[(bytes.length + 2)];
                if (i != 1) {
                    i3 = 2;
                }
                bArr[0] = (byte) i3;
                bArr[1] = (byte) 1;
                while (i2 < bytes.length) {
                    bArr[i2 + 2] = bytes[i2];
                    i2++;
                }
                this.ej.ob((byte) -59, bArr, bytes.length + 2);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        byte[] bArr2 = new byte[8];
        bArr2[0] = (byte) 11;
        bArr2[1] = (byte) 0;
        this.ej.ob((byte) -64, bArr2, 8);
    }

    public void ht(int i) {
        int i2 = 0;
        if (hy() && i == 513 && this.bu && me() == 2) {
            hs(new byte[]{(byte) 101, this.bs});
            if (this.bs != (byte) 1) {
                i2 = 1;
            }
            this.bs = (byte) i2;
        }
    }

    void hu(byte[] bArr) {
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

    public void hw(int i, int i2) {
        byte[] bArr = new byte[1];
        if (i == 0) {
            bArr[0] = (byte) (i2 | 128);
        } else {
            bArr[0] = (byte) (i2 & 255);
        }
        this.ej.ob((byte) -60, bArr, 1);
    }

    public void mo41i() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 10;
        bArr[1] = (byte) 48;
        if (this.ej.jh == null || !this.ej.jh.startsWith("KY")) {
            this.ej.ob((byte) -64, bArr, 8);
        } else {
            mo39g();
        }
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
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        r0 = new byte[8];
        int i5 = i3 / 1000;
        r0[5] = (byte) ((i5 / 60) / 60);
        r0[6] = (byte) ((i5 / 60) % 60);
        r0[7] = (byte) (i5 % 60);
        this.ej.ob((byte) -64, r0, 8);
    }

    public void mo45m(int i, int i2, int i3) {
        r0 = new byte[8];
        int i4 = i3 / 1000;
        r0[5] = (byte) ((i4 / 60) / 60);
        r0[6] = (byte) ((i4 / 60) % 60);
        r0[7] = (byte) (i4 % 60);
        this.ej.ob((byte) -64, r0, 8);
    }

    public void mo46n() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 1;
        bArr[1] = (byte) 1;
        if (this.bt) {
            if (b >= (byte) 0 && b <= (byte) 2) {
                bArr[2] = (byte) 0;
                bArr[3] = (byte) (i & 255);
                bArr[4] = (byte) ((i >> 8) & 255);
            } else if (b >= (byte) 3 && b <= (byte) 4) {
                bArr[2] = (byte) 16;
                bArr[3] = (byte) (i & 255);
                bArr[4] = (byte) ((i >> 8) & 255);
            }
        } else if (b >= (byte) 0 && b <= (byte) 2) {
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
        if (language.equals("zh")) {
            hv((byte) 0);
        } else if (language.equals("en")) {
            hv((byte) 1);
        } else if (language.equals("de")) {
            hv((byte) 2);
        } else if (language.equals("it")) {
            hv((byte) 5);
        } else if (language.equals("sv")) {
            hv((byte) 12);
        } else if (language.equals("es")) {
            hv((byte) 4);
        } else if (language.equals("nl")) {
            hv((byte) 6);
        } else if (language.equals("pt")) {
            hv((byte) 7);
        } else if (language.equals("nb")) {
            hv((byte) 14);
        } else if (language.equals("fi")) {
            hv((byte) 13);
        } else if (language.equals("da")) {
            hv((byte) 11);
        } else if (language.equals("pl")) {
            hv((byte) 15);
        } else if (language.equals("tr")) {
            hv((byte) 8);
        } else if (!language.equals("ar")) {
            if (language.equals("ru")) {
                hv((byte) 9);
            } else if (language.equals("uk")) {
                hv((byte) 10);
            } else if (language.equals("sk")) {
                hv((byte) 16);
            } else if (language.equals("cs")) {
                hv((byte) 17);
            } else if (language.equals("hu")) {
                hv((byte) 18);
            } else if (language.equals("el")) {
                hv((byte) 19);
            } else if (language.equals("ko")) {
                hv((byte) 20);
            }
        }
    }

    public void mo49q() {
        int i;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[4];
        bArr[0] = (byte) 1;
        if (this.en <= 12 || is24HourFormat) {
            i = 0;
        } else {
            this.en -= 12;
            i = 128;
        }
        if (!is24HourFormat) {
            i |= 64;
        }
        bArr[1] = (byte) (this.en & 127);
        bArr[2] = (byte) (this.eo & 255);
        bArr[3] = (byte) (i & 255);
        this.ej.ob((byte) -58, bArr, 4);
        byte[] bArr2 = new byte[3];
        if (is24HourFormat) {
            bArr2[2] = (byte) 1;
        } else {
            bArr2[2] = (byte) 0;
            if (this.en == 0) {
                this.en = 12;
            }
        }
        bArr2[1] = (byte) (this.en & 127);
        bArr2[0] = (byte) (this.eo & 255);
        this.ej.ob((byte) -56, bArr2, 3);
    }
}
