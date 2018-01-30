package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.provider.Settings.System;
import java.util.Locale;

public class C0024v extends C0001b {
    public C0024v(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 82;
    }

    private void bn(byte[] bArr) {
        this.ei.seatShow = false;
        if ((bArr[2] & 15) > 0) {
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
        if ((bArr[1] & 1) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[1] & 2) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[1] & 4) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = (bArr[2] & 15) == 8 ? 7 : bArr[2] & 15;
        this.ei.windLevelMax = 7;
        this.ei.tempUnit = (bArr[4] & 1) != 0;
        this.ei.tempLeft = bp(bArr[3] & 255);
        this.ei.tempRight = bp(bArr[4] & 255);
        if ((bArr[0] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.acMax = -1;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        int i4;
        String str;
        byte[] bArr2;
        byte[] bArr3;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) -127:
                i4 = bArr[i + 2] & 15;
                if (i4 == 7 || i4 == 8) {
                    str = "129,8,";
                    bArr2 = new byte[(i4 + 2)];
                    bArr2[0] = (byte) -127;
                    bArr2[1] = (byte) i4;
                    while (i3 < i4) {
                        str = str + (bArr[(i + 3) + i3] & 255) + ",";
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    System.putString(this.ej.getContentResolver(), "Canbus82Data81", str);
                    return;
                }
                return;
            case (byte) 33:
                if ((bArr[i + 2] & 15) >= 5) {
                    bArr3 = new byte[5];
                    while (i3 < 5) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    bn(bArr3);
                    if (lx(bArr3)) {
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] == (byte) 4) {
                    bArr2 = new byte[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        bArr2[i4] = (byte) (bArr[(i + 3) + i4] == (byte) 0 ? 0 : 5 - bArr[(i + 3) + i4]);
                    }
                    this.ek.max = 4;
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
                        bArr2[i4] = (byte) (bArr[(i + 3) + i4] == (byte) 0 ? 0 : 5 - bArr[(i + 3) + i4]);
                    }
                    this.ek.max = 4;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[0];
                    this.ek.f2 = bArr2[1];
                    this.ek.f3 = bArr2[2];
                    this.ek.f4 = bArr2[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] == (byte) 2) {
                    bo(new byte[]{bArr[i + 3]});
                    i4 = bArr[i + 4] & 255;
                    str = "";
                    if (!(i4 == 255 || i4 == 254)) {
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i4 - 40)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] == (byte) 2) {
                    i3 = bArr[i + 3] & 255;
                    int i5 = (bArr[i + 4] & 255) | ((i3 & 127) << 8);
                    if ((i3 & 128) == 0) {
                        i5 = 0 - i5;
                    }
                    i5 = (i5 * 30) / 5400;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i5);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 56:
                if (bArr[i + 2] == (byte) 8) {
                    bArr3 = new byte[10];
                    bArr3[0] = (byte) 56;
                    bArr3[1] = (byte) 8;
                    while (i3 < 8) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 113:
                if (bArr[i + 2] == (byte) 7) {
                    bArr3 = new byte[9];
                    bArr3[0] = (byte) 113;
                    bArr3[1] = (byte) 7;
                    while (i3 < 7) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    void bo(byte[] bArr) {
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

    int bp(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : (i < 36 || i > 52) ? i : (i * 10) / 2;
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
        mo48p();
    }

    public void mo37e() {
        mo39g();
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        this.ej.ob((byte) -64, new byte[]{(byte) 7}, 1);
    }

    public void mo40h(String str, int i) {
        this.ej.ob((byte) -64, new byte[]{(byte) 5, (byte) 4, (byte) 1, (byte) 66, (byte) 84}, 5);
    }

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        mo39g();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -64, new byte[]{(byte) 8, (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255), (byte) (((i3 / 1000) / 60) / 60), (byte) (((i3 / 1000) / 60) % 60), (byte) ((i3 / 1000) % 60)}, 8);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[]{(byte) 0}, 1);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) 1;
        bArr[4] = (byte) (b2 + 1);
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[1] = (byte) (b + 1);
            bArr[2] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 3) {
            bArr[1] = (byte) 17;
            bArr[2] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        } else if (b == (byte) 4) {
            bArr[1] = (byte) 18;
            bArr[2] = (byte) (i & 255);
            bArr[3] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -64, bArr, 5);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            this.ej.ob((byte) -125, new byte[]{(byte) 13, (byte) 0}, 2);
        } else if (language.equals("en")) {
            this.ej.ob((byte) -125, new byte[]{(byte) 13, (byte) 1}, 2);
        }
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
