package android.microntek.canbus.p001a;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Log;
import java.util.Locale;

public class ah extends C0001b {
    private static long ae = 0;
    private boolean ad = false;

    public ah(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void cp(byte[] bArr) {
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
        if (((bArr[0] >> 4) & 3) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        this.ei.modeDual = -1;
        if ((bArr[0] & 8) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch ((bArr[1] >> 4) & 15) {
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case 7:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 8:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevelMax = 8;
        if ((bArr[2] & 15) == 0) {
            this.ei.windLevel = 0;
            this.ei.modeAuto = 1;
        } else {
            this.ei.windLevel = bArr[2] & 15;
        }
        if (this.ej.jg == 1) {
            this.ei.tempLeft = cv(bArr[3] & 127);
            this.ei.tempRight = cv(bArr[4] & 127);
        } else {
            this.ei.tempLeft = cu(bArr[3] & 127);
            this.ei.tempRight = cu(bArr[4] & 127);
        }
        if ((bArr[0] & 3) == 1) {
            this.ei.windLoop = 1;
        } else if ((bArr[0] & 3) == 2) {
            this.ei.windLoop = 0;
        } else {
            this.ei.windLoop = -1;
        }
        this.ei.rearLock = -1;
        if ((bArr[0] & 16) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    private void cr(byte b) {
        this.ej.ob(Byte.MIN_VALUE, new byte[]{(byte) 14, b}, 2);
    }

    private void cs(int i) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.microntek.controlinfo", "com.microntek.controlinfo.canbus83carinfo"));
        intent.addFlags(807600128);
        intent.putExtra("cftype", i);
        try {
            this.ej.startActivityAsUser(intent, UserHandle.CURRENT_OR_SELF);
        } catch (ActivityNotFoundException e) {
            Log.e("Canbus50", "controlinfo activity not found! " + e.getMessage());
        }
    }

    private boolean ct() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - ae <= 1000) {
            return false;
        }
        ae = uptimeMillis;
        return true;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        Intent intent;
        int i5;
        byte[] bArr2;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgHeight /*1*/:
                if (bArr[i + 2] != (byte) 3) {
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc /*2*/:
                if ((bArr[i + 2] == (byte) 1 || bArr[i + 2] == (byte) 2) && bArr[i + 3] == (byte) 32 && ct()) {
                    cs(1);
                    return;
                }
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] != (byte) 6) {
                    return;
                }
                return;
            case (byte) 31:
                if (bArr[i + 2] >= (byte) 1) {
                    i4 = (((bArr[i + 3] & 255) - 127) * 30) / 127;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 50:
                i5 = bArr[i + 2] & 15;
                if ((i5 == 7 || i5 == 4) && bArr[i + 3] == (byte) 2) {
                    bArr2 = new byte[7];
                    for (i4 = 0; i4 < i5; i4++) {
                        bArr2[i4] = (byte) ((0 - (bArr[(i + 3) + i4] - 5)) & 15);
                    }
                    mb();
                    this.ek.max = 5;
                    this.ek.back_cnt = 3;
                    this.ek.b1 = bArr2[1];
                    this.ek.b2 = bArr2[2];
                    this.ek.b3 = bArr2[3];
                    if (bArr2[4] == (byte) 0 && bArr2[5] == (byte) 0 && bArr2[6] == (byte) 0) {
                        this.ek.front_cnt = 0;
                    } else {
                        this.ek.front_cnt = 3;
                    }
                    this.ek.f1 = bArr2[4];
                    this.ek.f2 = bArr2[5];
                    this.ek.f3 = bArr2[6];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 51:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[8];
                    bArr3[0] = (byte) 51;
                    bArr3[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 52:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[8];
                    bArr3[0] = (byte) 52;
                    bArr3[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 53:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[8];
                    bArr3[0] = (byte) 53;
                    bArr3[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr3[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 54:
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
            case (byte) 56:
                i4 = bArr[i + 2] & 15;
                if (i4 >= 3) {
                    byte[] bArr4 = new byte[2];
                    bArr4[0] = bArr[i + 3];
                    cq(bArr4);
                    bArr4 = new byte[8];
                    bArr4[0] = (byte) 56;
                    bArr4[1] = (byte) i4;
                    while (i3 < i4) {
                        bArr4[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr4);
                    return;
                }
                return;
            case (byte) 59:
                if (bArr[i + 2] == (byte) 6) {
                    bArr3 = new byte[7];
                    bArr3[0] = (byte) 59;
                    while (i3 < 6) {
                        bArr3[i3 + 1] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr3);
                    return;
                }
                return;
            case (byte) 65:
                i5 = bArr[i + 2] & 15;
                if (i5 >= 5) {
                    bArr2 = new byte[6];
                    for (i4 = 0; i4 < i5; i4++) {
                        bArr2[i4] = bArr[(i + 3) + i4];
                    }
                    cp(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 66:
                if ((bArr[i + 2] & 15) >= 4 && bArr[i + 3] == (byte) 2) {
                    bArr3 = new byte[4];
                    while (i3 < 4) {
                        bArr3[i3] = (byte) ((0 - (bArr[(i + 3) + i3] - 5)) & 15);
                        i3++;
                    }
                    mb();
                    this.ek.max = 5;
                    this.ek.front_cnt = 3;
                    this.ek.f1 = bArr3[1];
                    this.ek.f2 = bArr3[2];
                    this.ek.f3 = bArr3[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 98:
                if (bArr[i + 2] >= (byte) 1) {
                    i4 = bArr[i + 3] & 255;
                    if (i4 >= 128) {
                        i4 = 128 - i4;
                    }
                    i4 = (i4 * 30) / 127;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", 0 - i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            default:
                return;
        }
    }

    void cq(byte[] bArr) {
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

    int cu(int i) {
        return i == 0 ? 0 : i == 63 ? 65535 : (i < 11 || i > 44) ? -1 : (((i - 11) * 10) / 2) + 150;
    }

    int cv(int i) {
        return i == 0 ? 0 : i == 30 ? 65535 : (i < 1 || i > 29) ? -1 : this.ad ? (i * 5) + 135 : (i * 10) + 150;
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
        this.ej.ob((byte) -127, new byte[]{(byte) 1}, 1);
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

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        this.ad = false;
        if (language.equals("zh")) {
            cr((byte) 2);
        } else if (language.equals("en")) {
            cr((byte) 0);
        } else if (language.equals("fr")) {
            cr((byte) 1);
        } else if (language.equals("ru") || language.equals("tr")) {
            this.ad = true;
        }
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
