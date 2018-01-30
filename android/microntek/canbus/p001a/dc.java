package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.microntek.canbus.serializable.Radar;
import java.util.Locale;

public class dc extends C0001b {
    public dc(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 27;
    }

    private void km(byte[] bArr) {
        if (((byte) (bArr[0] & 64)) == (byte) 64 && ((byte) (bArr[0] & 128)) == Byte.MIN_VALUE) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 3) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[5] & 31) == 19) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[2] & 16) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[2] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[4] & 15) {
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windDown = true;
                this.ei.windMid = false;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windDown = true;
                this.ei.windMid = true;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = false;
                this.ei.windDown = false;
                this.ei.windMid = true;
                break;
            case 11:
                this.ei.windUp = true;
                this.ei.windDown = false;
                this.ei.windMid = false;
                break;
            case 12:
                this.ei.windUp = true;
                this.ei.windDown = true;
                this.ei.windMid = false;
                break;
            case 13:
                this.ei.windUp = true;
                this.ei.windDown = false;
                this.ei.windMid = true;
                break;
            case 14:
                this.ei.windUp = true;
                this.ei.windDown = true;
                this.ei.windMid = true;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windDown = false;
                this.ei.windMid = false;
                break;
        }
        this.ei.windLevel = (bArr[5] & 31) >= 8 ? 7 : bArr[5] & 15;
        this.ei.windLevelMax = 7;
        this.ei.tempLeft = kp(bArr[6] & 255);
        this.ei.tempRight = kp(bArr[7] & 255);
        if ((bArr[1] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatHotLeft = (bArr[2] >> 2) & 3;
        this.ei.seatHotRight = bArr[2] & 3;
    }

    private int ko(int i) {
        int i2 = 10;
        int i3 = i & 255;
        if (i3 > 150 || i3 == 0) {
            i2 = 0;
        } else if (i3 >= 10) {
            i2 = i3;
        }
        return i2 / 10;
    }

    private void kq(Radar radar) {
        this.ej.oe(radar);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        byte[] bArr2;
        byte[] bArr3;
        switch (bArr[i + 1]) {
            case (byte) 17:
                if (bArr[i + 2] == (byte) 10) {
                    i4 = ((bArr[i + 9] & 255) << 8) + (bArr[i + 10] & 255);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    i4 = (i4 * 30) / 450;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    bArr2 = new byte[11];
                    bArr2[0] = bArr[i + 1];
                    while (i3 < 10) {
                        bArr2[i3 + 1] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] == (byte) 10) {
                    kn(new byte[]{bArr[i + 5]});
                    bArr2 = new byte[11];
                    bArr2[0] = bArr[i + 1];
                    while (i3 < 10) {
                        bArr2[i3 + 1] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] == (byte) 12) {
                    bArr3 = new byte[12];
                    for (i4 = 0; i4 < 12; i4++) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                    }
                    String str = "";
                    float f = (((float) (bArr[i + 14] & 255)) * 0.5f) - 40.0f;
                    String str2 = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                    if (((byte) (bArr3[0] & 64)) != (byte) 64) {
                        str2 = "";
                    }
                    Intent intent2 = new Intent("com.canbus.temperature");
                    intent2.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent2);
                    km(bArr3);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] == (byte) 12) {
                    for (i4 = 0; i4 < 8; i4++) {
                        this.ep[i4] = bArr[(i + 3) + i4];
                    }
                    this.ek.max = 15;
                    this.ek.front_cnt = 4;
                    this.ek.f1 = ko(this.ep[4]);
                    this.ek.f2 = ko(this.ep[5]);
                    this.ek.f3 = ko(this.ep[6]);
                    this.ek.f4 = ko(this.ep[7]);
                    this.ek.back_cnt = 4;
                    this.ek.b1 = ko(this.ep[0]);
                    this.ek.b2 = ko(this.ep[1]);
                    this.ek.b3 = ko(this.ep[2]);
                    this.ek.b4 = ko(this.ep[3]);
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    kq(this.ek);
                    return;
                }
                return;
            default:
                if (i2 > 16) {
                    i2 = 16;
                }
                bArr3 = new byte[i2];
                bArr3[0] = bArr[i + 1];
                for (i4 = 3; i4 < i2; i4++) {
                    bArr3[i4 - 2] = bArr[i + i4];
                }
                this.ej.oj(bArr3);
                return;
        }
    }

    public void mo36d() {
        mo48p();
        this.ej.oh(1);
        this.ej.oa(1);
    }

    public void mo37e() {
        this.ej.ol((byte) -111, new byte[]{(byte) 10, (byte) 32, (byte) 65, (byte) 50, (byte) 68, (byte) 80, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 14);
    }

    public void mo38f() {
        this.ej.ol((byte) -111, new byte[]{(byte) 8, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 14);
    }

    public void mo39g() {
        this.ej.ol((byte) -111, new byte[]{(byte) 12, (byte) 32, (byte) 65, (byte) 86, (byte) 73, (byte) 78, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 14);
    }

    public void mo40h(String str, int i) {
        this.ej.ol((byte) -111, new byte[]{(byte) 10, (byte) 32, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32, (byte) 32}, 14);
    }

    public void mo41i() {
        this.ej.ol((byte) -111, new byte[]{(byte) 8, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 14);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 7, (byte) 0, (byte) 32, (byte) 68, (byte) 58, (byte) 32, (byte) 32, (byte) 58, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (i > 0) {
            bArr[1] = (byte) 1;
        }
        bArr[2] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        bArr[3] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        bArr[4] = (byte) 58;
        bArr[5] = (byte) ((((i2 / 60) % 60) / 10) + 48);
        bArr[6] = (byte) ((((i2 / 60) % 60) % 10) + 48);
        bArr[7] = (byte) 58;
        bArr[8] = (byte) (((i2 % 60) / 10) + 48);
        bArr[9] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.ol((byte) -111, bArr, 14);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[]{(byte) 11, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i5 = i3 / 1000;
        bArr[3] = (byte) ((((i5 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i5 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i5 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i5 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i5 % 60) / 10) + 48);
        bArr[10] = (byte) (((i5 % 60) % 10) + 48);
        this.ej.ol((byte) -111, bArr, 14);
    }

    void kn(byte[] bArr) {
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

    int kp(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : (i * 10) / 2;
    }

    public void mo44l() {
        this.ej.ol((byte) -111, new byte[]{(byte) 13, (byte) 32, (byte) 109, (byte) 111, (byte) 118, (byte) 105, (byte) 101, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 14);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 13, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i4 = i3 / 1000;
        bArr[3] = (byte) ((((i4 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i4 % 60) / 10) + 48);
        bArr[10] = (byte) (((i4 % 60) % 10) + 48);
        this.ej.ol((byte) -111, bArr, 14);
    }

    public void mo46n() {
        this.ej.ol((byte) -111, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 14);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 1, (byte) 45, (byte) 1, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            bArr[1] = (byte) 32;
            bArr[2] = (byte) 32;
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
            bArr[1] = (byte) 32;
            bArr[2] = (byte) 32;
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
        this.ej.ol((byte) -111, bArr, 14);
    }

    public void mo48p() {
        if (this.ej.getResources().getConfiguration().locale.getLanguage().endsWith("zh")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 2}, 2);
            return;
        }
        this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 1}, 2);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
