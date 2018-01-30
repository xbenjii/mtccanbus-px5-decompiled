package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class av extends C0001b {
    private byte am;

    public av(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.am = (byte) 0;
        this.eh = 87;
    }

    private void ec(byte[] bArr) {
        if ((bArr[0] & 128) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 64) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[0] & 16) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[1] >> 4) {
            case R$styleable.MyButton_imgWidth /*0*/:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
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
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = (bArr[1] & 15) <= 7 ? bArr[1] & 15 : -1;
        this.ei.windLevelMax = 7;
        this.ei.tempLeft = ee(bArr[2] & 255);
        this.ei.tempRight = ee(bArr[3] & 255);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 4) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        Intent intent;
        int i4;
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgSrc /*2*/:
            case (byte) 10:
                if (bArr[i + 2] >= (byte) 1) {
                    ed(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                if (bArr[i + 2] >= (byte) 4) {
                    byte[] bArr2 = new byte[4];
                    while (i3 < 4) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    if (lx(bArr2)) {
                        ec(bArr2);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                if (bArr[i + 2] >= (byte) 1) {
                    float f = (((float) (bArr[i + 3] & 255)) / 2.0f) - 40.0f;
                    String str = "";
                    if (f >= -40.0f && ((double) f) <= 87.5d) {
                        str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(f)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 7:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 8:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 9:
                if (bArr[i + 2] >= (byte) 1 && bArr[i + 3] != this.am) {
                    this.am = bArr[i + 3];
                    this.ei.seatHotLeft = (bArr[i + 3] & 48) >> 4;
                    this.ei.seatHotRight = bArr[i + 3] & 3;
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 11:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 12:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 13:
                if (bArr[i + 2] >= (byte) 1) {
                    i4 = (((bArr[i + 3] & 255) - 128) * 30) / 128;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i4);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 14:
                if (bArr[i + 2] >= (byte) 4) {
                    if (this.ej.oc() == 0) {
                        this.ek.zero_show = false;
                    } else {
                        this.ek.zero_show = true;
                    }
                    int[] iArr = new int[4];
                    for (i4 = 0; i4 < 4; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] > 7) {
                            iArr[i4] = 0;
                        }
                        iArr[i4] = 0 - (iArr[i4] - 7);
                    }
                    this.ek.max = 7;
                    this.ek.back_cnt = 2;
                    this.ek.front_cnt = 2;
                    this.ek.f1 = iArr[0];
                    this.ek.f2 = iArr[1];
                    this.ek.b1 = iArr[2];
                    this.ek.b2 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 15:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 113:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case Byte.MAX_VALUE:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oh(1);
        this.ej.oa(1);
        this.ej.ob((byte) -126, new byte[]{(byte) 1}, 1);
    }

    public void mo37e() {
    }

    void ed(byte[] bArr) {
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

    int ee(int i) {
        return i == 0 ? 0 : i == 14 ? 65535 : (i < 1 || i > 45) ? -1 : (i * 10) + 150;
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
