package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class bw extends C0001b {
    byte[] br = new byte[37];

    public bw(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void ho(byte[] bArr) {
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
        if ((bArr[0] & 4) != 0) {
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
            case 12:
                this.ei.windUp = true;
                this.ei.windMid = false;
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
        this.ei.tempLeft = hq(i);
        i = bArr[7] & 255;
        this.ei.tempRight = hq(i);
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
        this.ei.seatShow = false;
    }

    private int hq(int i) {
        return i == 254 ? 0 : i == 255 ? 65535 : i < 100 ? i * 5 : -1;
    }

    private byte[] hr(byte b, String str) {
        try {
            byte[] bytes = (str + "                          ").getBytes("unicode");
            byte[] bArr = new byte[37];
            bArr[0] = b;
            if (bytes != null) {
                for (int i = 1; i < bArr.length - 1; i++) {
                    if (i != 1) {
                        bArr[i] = bytes[i];
                    }
                }
            }
            return bArr;
        } catch (UnsupportedEncodingException e) {
            return this.br;
        }
    }

    public void ak(int i, int i2, int i3) {
        this.ej.ol((byte) -107, hr((byte) 13, "USB " + i2), 37);
    }

    public void al() {
        this.ej.ol((byte) 106, new byte[]{(byte) 5, (byte) 1, (byte) 49}, 3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3;
        switch (bArr[i + 1]) {
            case (byte) -63:
            case (byte) -16:
            case (byte) 67:
            case (byte) 96:
            case (byte) 98:
                ly(bArr, i, i2);
                return;
            case (byte) 18:
                if (bArr[i + 2] >= (byte) 10) {
                    hp(new byte[]{bArr[i + 5]});
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] >= (byte) 12) {
                    byte[] ma = ma(bArr, i + 3, i2);
                    if (lx(ma)) {
                        ho(ma);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] >= (byte) 14) {
                    i3 = ((bArr[i + 7] & 255) << 8) | (bArr[i + 8] & 255);
                    if (i3 != 65535) {
                        mg(i3);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] >= (byte) 12) {
                    mb();
                    int[] iArr = new int[4];
                    i3 = 0;
                    while (i3 < 4) {
                        iArr[i3] = bArr[(i + 3) + i3] & 255;
                        if (iArr[i3] >= 7) {
                            iArr[i3] = 0;
                        } else if ((i3 == 0 || i3 == 3) && iArr[i3] == 1) {
                            iArr[i3] = 3;
                        } else if ((i3 == 0 || i3 == 3) && iArr[i3] == 2) {
                            iArr[i3] = 6;
                        }
                        i3++;
                    }
                    this.ek.max = 6;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void bg() {
        this.ej.ol((byte) -107, hr((byte) 0, ""), 37);
    }

    public void bh() {
        mo39g();
    }

    public void mo36d() {
        mo48p();
    }

    public void mo37e() {
        this.ej.ol((byte) -107, hr((byte) 10, "    A2DP      "), 37);
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        this.ej.ol((byte) -107, hr((byte) 12, "    AUX      "), 37);
    }

    public int[][] getAirBtnTable() {
        return new int[][]{new int[]{3841, 23205, 15618, 30, 256}, new int[]{3842, 23205, 15618, 2, 256}, new int[]{3844, 23205, 15618, 6, 256}, new int[]{3845, 23205, 15618, 7, 256}, new int[]{3846, 23205, 15618, 4, 256}, new int[]{3847, 23205, 15618, 13, 256}, new int[]{3848, 23205, 15618, 14, 256}, new int[]{3849, 23205, 15618, 15, 256}, new int[]{3850, 23205, 15618, 16, 256}, new int[]{3853, 23205, 15618, 1, 256}, new int[]{3855, 23205, 15618, 12, 256}, new int[]{3856, 23205, 15618, 11, 256}, new int[]{3857, 23205, 15618, 3, 256}, new int[]{3858, 23205, 15618, 29, 256}, new int[]{3860, 23205, 15618, 26, 256}, new int[]{3861, 23205, 15618, 28, 256}, new int[]{3863, 23205, 15618, 27, 256}, new int[]{3872, 23205, 15618, 5, 256}};
    }

    public void mo40h(String str, int i) {
        this.ej.ol((byte) -107, hr((byte) 10, "  BLUETOOTH "), 37);
    }

    void hp(byte[] bArr) {
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

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        this.ej.ol((byte) -107, hr((byte) 6, "CDTOTAL " + i), 37);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ol((byte) -107, hr((byte) 13, "USB " + i2), 37);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ol((byte) -107, hr((byte) 13, "USB " + i2), 37);
    }

    public void mo46n() {
        mo39g();
    }

    public void mo47o(byte b, int i, byte b2) {
        String str;
        byte b3 = (byte) 1;
        String str2 = "";
        if (b >= (byte) 0 && b <= (byte) 2) {
            str = "FM" + (b + 1) + " " + String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(((float) i) / 100.0f)}) + "MHz";
        } else if (b < (byte) 3 || b > (byte) 4) {
            str = str2;
        } else {
            str = "AM" + (b - 2) + " " + i + "KHz";
            b3 = (byte) 4;
        }
        this.ej.ol((byte) -107, hr(b3, str), 37);
    }

    public void mo48p() {
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 2}, 2);
        } else if (language.equals("en")) {
            this.ej.ol((byte) -102, new byte[]{(byte) 1, (byte) 1}, 2);
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
        bArr[6] = (byte) ((time.year - 2000) & 255);
        bArr[7] = (byte) ((time.month + 1) & 255);
        bArr[8] = (byte) (time.monthDay & 255);
        this.ej.ol((byte) -53, bArr, 10);
    }
}
