package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.p002b.C0035b;
import android.microntek.canbus.serializable.AirCondition;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class C0002a extends C0001b {
    private int f2a;
    private boolean f3b;
    private boolean f4c;
    private View f5d;
    private View f6e;
    private View f7f;
    private WindowManager f8g;
    private LayoutParams f9h;

    public C0002a(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.f2a = 0;
        this.f4c = false;
        this.f3b = false;
        this.eh = 15;
        this.ei = new AirCondition();
    }

    private void m16a(byte[] bArr) {
        this.ei.seatShow = false;
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
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
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
        this.ei.windLevelMax = 8;
        int i = bArr[2] & 255;
        this.ei.tempLeft = m37u(i);
        i = bArr[3] & 255;
        this.ei.tempRight = m37u(i);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        if ((bArr[0] & 2) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    private void m17s() {
        if (this.f5d != null && !this.f4c) {
            this.f4c = true;
            this.f8g.addView(this.f5d, this.f9h);
        }
    }

    private void m18t() {
        this.f9h = new LayoutParams();
        this.f8g = (WindowManager) this.ej.getApplication().getSystemService("window");
        this.f9h.type = 2002;
        this.f9h.format = 1;
        this.f9h.flags = 40;
        this.f9h.gravity = 81;
        this.f9h.x = 0;
        this.f9h.y = 0;
        this.f9h.width = -1;
        this.f9h.height = -1;
        this.f5d = LayoutInflater.from(this.ej.getApplication()).inflate(R.layout.canbus10and15layout, null);
        this.f6e = this.f5d.findViewById(R.id.img_view);
        this.f7f = this.f5d.findViewById(R.id.text_view);
    }

    private void m19v() {
        if (this.f5d != null && this.f4c) {
            this.f4c = false;
            this.f8g.removeView(this.f5d);
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        String language;
        byte[] bArr2;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 32:
                if (bArr[i + 2] >= (byte) 2 && this.f3b && bArr[i + 4] == (byte) 1) {
                    switch (bArr[i + 3] & 255) {
                        case 35:
                            lv("av_mute=true");
                            m17s();
                            if (this.f4c) {
                                this.f7f.setVisibility(0);
                                this.f6e.setBackgroundResource(R.drawable.img_canbus_sos);
                                return;
                            }
                            return;
                        case 36:
                            lv("av_mute=false");
                            m19v();
                            return;
                        case 37:
                            lv("av_mute=true");
                            m17s();
                            if (this.f4c) {
                                language = this.ej.getResources().getConfiguration().locale.getLanguage();
                                this.f7f.setVisibility(8);
                                if (language.equals("ru")) {
                                    this.f6e.setBackgroundResource(R.drawable.img_canbus_test_ru);
                                    return;
                                } else {
                                    this.f6e.setBackgroundResource(R.drawable.img_canbus_test_en);
                                    return;
                                }
                            }
                            return;
                        case 38:
                            lv("av_mute=false");
                            m19v();
                            return;
                        default:
                            return;
                    }
                }
                return;
            case (byte) 33:
                if (bArr[i + 2] == (byte) 5) {
                    byte[] bArr3 = new byte[5];
                    while (i3 < 5) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    m16a(bArr3);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 3;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[1];
                    this.ek.b3 = bArr2[2];
                    this.ek.b4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 35:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 3;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ek.f4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    m21c(new byte[]{bArr[i + 4]});
                    return;
                }
                return;
            case (byte) 39:
                if (bArr[i + 2] == (byte) 1) {
                    byte b = bArr[i + 3];
                    language = "";
                    if (b >= (byte) -40 && b <= (byte) 80) {
                        language = " " + b + this.el.getString(R.string.c_dan);
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", language);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    void m21c(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 1) != 0;
        boolean z3 = (bArr[0] & 2) != 0;
        boolean z4 = (bArr[0] & 4) != 0;
        boolean z5 = (bArr[0] & 8) != 0;
        boolean z6 = (bArr[0] & 16) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        if (C0035b.lp() > 19) {
            this.ej.ob((byte) -54, new byte[]{(byte) this.ej.jg}, 1);
            this.f2a = this.ej.jg;
        } else if (this.ej.jg == 4) {
            this.f2a = 2;
            this.ej.ob((byte) -54, new byte[]{(byte) 2}, 1);
        }
        if (lu().contains("KLD")) {
            this.f3b = true;
        }
        m18t();
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo40h(String str, int i) {
        int i2 = 3;
        int i3 = 0;
        if ((i == 1 || i == 2) && lw() == 3) {
            try {
                byte[] bytes = str.getBytes("gb2312");
                byte[] bArr = new byte[18];
                bArr[0] = (byte) 5;
                bArr[1] = (byte) 64;
                if (i != 1) {
                    i2 = 0;
                }
                bArr[2] = (byte) i2;
                while (i3 < bytes.length) {
                    if (i3 + 3 < 18) {
                        bArr[i3 + 3] = bytes[i3];
                    }
                    i3++;
                }
                this.ej.ob((byte) -64, bArr, 18);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        byte[] bArr2 = new byte[8];
        bArr2[0] = (byte) 11;
        bArr2[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr2, 8);
    }

    public void mo41i() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
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
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 6;
        bArr[1] = (byte) 19;
        bArr[2] = (byte) (i2 & 255);
        bArr[5] = (byte) (((i3 / 1000) / 60) / 60);
        bArr[6] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[7] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo44l() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 12;
        bArr[1] = (byte) 48;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 19;
        bArr[1] = (byte) 19;
        bArr[2] = (byte) (i2 & 255);
        bArr[5] = (byte) (((i3 / 1000) / 60) / 60);
        bArr[6] = (byte) (((i3 / 1000) / 60) % 60);
        bArr[7] = (byte) ((i3 / 1000) % 60);
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo46n() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
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
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[8];
        if (!DateFormat.is24HourFormat(this.el)) {
            if (this.en > 12) {
                this.en -= 12;
            } else if (this.en == 0) {
                this.en = 12;
            }
            this.en |= 128;
        }
        bArr[0] = (byte) 1;
        bArr[4] = (byte) (this.en & 255);
        bArr[5] = (byte) (this.eo & 255);
        this.ej.ob((byte) -58, bArr, 8);
    }

    public void mo50r(int i) {
    }

    int m37u(int i) {
        return this.f2a == 2 ? i == 0 ? 0 : i == 34 ? 65535 : (i < 1 || i > 33) ? -1 : (i + 30) * 5 : i != 0 ? i == 30 ? 65535 : (i < 1 || i > 29) ? -1 : (i + 34) * 5 : 0;
    }
}
