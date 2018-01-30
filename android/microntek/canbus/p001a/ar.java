package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class ar extends C0001b {
    private byte[] al = new byte[13];

    public ar(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void dv(byte[] bArr) {
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 2;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[1] & 4) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
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
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgHeight2 /*6*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 11:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            case 12:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case 13:
                this.ei.windUp = true;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case 14:
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
        this.ei.windLevel = bArr[5] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[6] & 255;
        this.ei.tempLeft = dx(i);
        i = bArr[7] & 255;
        this.ei.tempRight = dx(i);
        if ((bArr[1] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        if ((bArr[0] & 32) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatHotLeft = bArr[2] & 3;
        this.ei.seatHotRight = (bArr[2] & 12) >> 2;
    }

    private int dx(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : i < 100 ? i * 5 : -1;
    }

    private void dy() {
        for (int i = 0; i < 13; i++) {
            this.al[i] = (byte) 32;
        }
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void al() {
        this.ej.ol((byte) 106, new byte[]{(byte) 5, (byte) 1, (byte) 49}, 3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        switch (bArr[i + 1]) {
            case (byte) -121:
            case (byte) -24:
            case (byte) -16:
            case (byte) 38:
                ly(bArr, i, i2);
                return;
            case (byte) 17:
                if (bArr[i + 2] >= (byte) 10) {
                    i3 = ((bArr[i + 9] & 255) << 8) | (bArr[i + 10] & 255);
                    if (i3 != 0) {
                        if (i3 >= 32768) {
                            i3 -= 65536;
                        }
                        lz((i3 * 30) / 10000);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] >= (byte) 10) {
                    dw(new byte[]{bArr[i + 5]});
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] >= (byte) 12) {
                    byte[] ma = ma(bArr, i + 3, i2 - 1);
                    if (lx(ma)) {
                        dv(ma);
                        this.ej.od(this.ei);
                    }
                    String str = "";
                    if ((bArr[i + 14] & 255) <= 214) {
                        str = String.format(Locale.US, "  %.1f", new Object[]{Float.valueOf((((float) (bArr[i + 14] & 255)) / 2.0f) - 40.0f)}) + this.el.getString(R.string.c_dan);
                    }
                    md(str);
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] >= (byte) 12) {
                    byte b = bArr[i + 14];
                    int[] iArr = new int[12];
                    for (i3 = 0; i3 < 11; i3++) {
                        iArr[i3] = bArr[(i + 3) + i3] & 255;
                        if (b == (byte) 0) {
                            if (iArr[i3] >= 4) {
                                iArr[i3] = 0;
                            }
                        } else if (iArr[i3] <= 150) {
                            iArr[i3] = (iArr[i3] * 20) / 150;
                        } else {
                            iArr[i3] = 0;
                        }
                    }
                    if (iArr[10] == 1) {
                        mb();
                        this.ek.max = b == (byte) 0 ? 3 : 20;
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
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        byte b;
        this.ej.oa(1);
        this.ej.oh(1);
        mo48p();
        switch (this.ej.jg) {
            case R$styleable.MyButton_imgHeight /*1*/:
                b = (byte) 11;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                b = (byte) 14;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                b = (byte) 19;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                b = (byte) 20;
                break;
            default:
                b = (byte) 18;
                break;
        }
        this.ej.ol((byte) 36, new byte[]{b, (byte) 53}, 2);
    }

    void dw(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 1) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo37e() {
        dy();
        this.al[0] = (byte) -123;
        this.ej.ol((byte) -111, this.al, 13);
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        dy();
        this.al[0] = (byte) 12;
        this.ej.ol((byte) -111, this.al, 13);
    }

    public int[][] getAirBtnTable() {
        return new int[][]{new int[]{3842, 23205, 15618, 2, 256}, new int[]{3845, 23205, 15618, 7, 256}, new int[]{3846, 23205, 15618, 4, 256}, new int[]{3847, 23205, 15618, 13, 256}, new int[]{3848, 23205, 15618, 14, 256}, new int[]{3849, 23205, 15618, 15, 256}, new int[]{3850, 23205, 15618, 16, 256}, new int[]{3853, 23205, 15618, 1, 256}, new int[]{3855, 23205, 15618, 12, 256}, new int[]{3856, 23205, 15618, 11, 256}, new int[]{3857, 23205, 15618, 3, 256}, new int[]{3858, 23205, 15618, 28, 256}, new int[]{3860, 23205, 15618, 25, 256}, new int[]{3861, 23205, 15618, 27, 256}, new int[]{3863, 23205, 15618, 26, 256}, new int[]{3872, 23205, 15618, 5, 256}};
    }

    public void mo40h(String str, int i) {
        dy();
        mo37e();
    }

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        mo39g();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo39g();
    }

    public void mo45m(int i, int i2, int i3) {
        dy();
        this.al[0] = (byte) 25;
        this.ej.ol((byte) -111, this.al, 13);
    }

    public void mo46n() {
        dy();
        this.al[0] = (byte) 0;
        this.ej.ol((byte) -111, this.al, 13);
    }

    public void mo47o(byte b, int i, byte b2) {
        dy();
        this.al[0] = (byte) (b + 1);
        this.al[1] = (byte) (((b2 / 10) % 10) + 48);
        this.al[2] = (byte) ((b2 % 10) + 48);
        if (b >= (byte) 0 && b <= (byte) 2) {
            if (i > 9999) {
                this.al[4] = (byte) ((i / 10000) + 48);
                this.al[5] = (byte) (((i / 1000) % 10) + 48);
                this.al[6] = (byte) (((i / 100) % 10) + 48);
                this.al[7] = (byte) 46;
                this.al[8] = (byte) (((i / 10) % 10) + 48);
            } else {
                this.al[5] = (byte) ((i / 1000) + 48);
                this.al[6] = (byte) (((i / 100) % 10) + 48);
                this.al[7] = (byte) 46;
                this.al[8] = (byte) (((i / 10) % 10) + 48);
            }
            this.al[9] = (byte) 77;
            this.al[10] = (byte) 72;
            this.al[11] = (byte) 90;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            if (i > 999) {
                this.al[4] = (byte) ((i / 1000) + 48);
                this.al[5] = (byte) (((i / 100) % 10) + 48);
                this.al[6] = (byte) (((i / 10) % 10) + 48);
                this.al[7] = (byte) ((i % 10) + 48);
            } else {
                this.al[4] = (byte) 48;
                this.al[5] = (byte) ((i / 100) + 48);
                this.al[6] = (byte) (((i / 10) % 10) + 48);
                this.al[7] = (byte) ((i % 10) + 48);
            }
            this.al[9] = (byte) 75;
            this.al[10] = (byte) 72;
            this.al[11] = (byte) 90;
        }
        this.ej.ol((byte) -111, this.al, 13);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 2}, 2);
        } else if (language.equals("en")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 1}, 2);
        } else {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 0}, 2);
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
        bArr[1] = (byte) (this.en & 255);
        bArr[2] = (byte) (this.eo & 255);
        if (!is24HourFormat) {
            i = 0;
        }
        bArr[5] = (byte) i;
        this.ej.ol((byte) -53, bArr, 10);
    }
}
