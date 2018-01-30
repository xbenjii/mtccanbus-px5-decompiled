package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class C0006c extends C0001b {
    public C0006c(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void m769z(byte[] bArr) {
        if ((bArr[0] & 128) != 0 || (bArr[0] & 7) > 0) {
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
        if ((bArr[0] & 16) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[1] & 127) {
            case R$styleable.MyButton_imgSrc /*2*/:
            case 7:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 8:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case 9:
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
        this.ei.windLevel = bArr[0] & 7;
        this.ei.windLevelMax = 7;
        this.ei.tempLeft = ab((bArr[1] & 128) != 0, bArr[2] & 255);
        this.ei.tempRight = ab((bArr[1] & 128) != 0, bArr[3] & 255);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatHotLeft = (bArr[4] & 48) >> 4;
        this.ei.seatHotRight = bArr[4] & 3;
    }

    void aa(byte[] bArr) {
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

    int ab(boolean z, int i) {
        int i2 = -1;
        if (i == 255) {
            return -1;
        }
        if (!z) {
            i2 = i * 10;
        } else if (i == 128) {
            i2 = 0;
        } else if (i == 157) {
            i2 = 65535;
        } else if (i > 128 && i < 157) {
            i2 = (((i - 128) * 10) / 2) + 180;
        }
        return i2;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        Intent intent;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] >= (byte) 6) {
                    String str;
                    byte[] bArr2 = new byte[6];
                    for (i3 = 0; i3 < 6; i3++) {
                        if (i3 != 5) {
                            bArr2[i3] = bArr[(i + 3) + i3];
                        }
                    }
                    if (lx(bArr2)) {
                        m769z(bArr2);
                        this.ej.od(this.ei);
                    }
                    i3 = bArr[i + 8] & 127;
                    String str2 = "";
                    if ((bArr[i + 8] & 255) != 255) {
                        if ((bArr[i + 8] & 128) != 0) {
                            i3 = 0 - i3;
                        }
                        str = String.format(Locale.US, " %d", new Object[]{Integer.valueOf(i3)}) + this.el.getString(R.string.c_dan);
                    } else {
                        str = str2;
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 7:
            case (byte) 9:
            case (byte) 13:
                ly(bArr, i, i2);
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 2) {
                    int[] iArr = new int[2];
                    i3 = 0;
                    while (i3 < 2) {
                        iArr[i3] = bArr[(i + 3) + i3] & 255;
                        if (iArr[i3] < 1 || iArr[i3] > 3) {
                            iArr[i3] = 0;
                        } else {
                            iArr[i3] = 0 - (iArr[i3] - 4);
                        }
                        i3++;
                    }
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    this.ek.max = 3;
                    this.ek.back_cnt = 2;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 40:
                if (bArr[i + 2] >= (byte) 2) {
                    aa(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 48:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = (bArr[i + 4] & 255) + ((bArr[i + 3] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / 5500;
                    intent = new Intent("com.microntek.canbusbackview");
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
    }

    public void mo50r(int i) {
    }
}
