package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.p002b.C0035b;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class aj extends C0001b {
    private byte[] ag;
    private byte[] ah;

    public aj(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.ah = new byte[12];
        this.ag = new byte[14];
        this.eh = 59;
    }

    private void de(byte[] bArr) {
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[0] & 8) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        this.ei.modeDual = -1;
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
        if ((bArr[4] & 15) == 14) {
            this.ei.windUp = true;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else if ((bArr[4] & 15) == 13) {
            this.ei.windUp = true;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if ((bArr[4] & 15) == 12) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if ((bArr[4] & 15) == 11) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = false;
        } else if ((bArr[4] & 15) == 6) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if ((bArr[4] & 15) == 5) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else if ((bArr[4] & 15) == 3) {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = false;
        }
        this.ei.windLevel = (bArr[5] & 15) > 7 ? 7 : bArr[5] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[6] & 255;
        if (i == 254) {
            this.ei.tempLeft = 0;
        } else if (i == 255) {
            this.ei.tempLeft = 65535;
        } else if (i < 0 || i > 99) {
            this.ei.tempLeft = -1;
        } else if (i < 40 || i > 52) {
            this.ei.tempLeft = (i / 2) * 10;
        } else {
            this.ei.tempLeft = (int) ((((float) i) * 0.5f) * 10.0f);
        }
        i = bArr[7] & 255;
        if (i == 254) {
            this.ei.tempRight = 0;
        } else if (i == 255) {
            this.ei.tempRight = 65535;
        } else if (i < 0 || i > 99) {
            this.ei.tempRight = -1;
        } else if (i < 40 || i > 52) {
            this.ei.tempRight = (i / 2) * 10;
        } else {
            this.ei.tempRight = (int) ((((float) i) * 0.5f) * 10.0f);
        }
        if ((bArr[1] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.rearLock = -1;
        if ((bArr[0] & 32) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
        this.ei.seatShow = false;
    }

    private int df(int i) {
        return (i < 0 || i > 6) ? 0 : i + 1;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.ej.oc() == 0) {
            this.ek.zero_show = false;
        } else {
            this.ek.zero_show = true;
        }
        Intent intent;
        byte[] bArr2;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 17:
                if (bArr[i + 2] == (byte) 10) {
                    i3 = (bArr[i + 10] & 255) + ((bArr[i + 9] & 255) << 8);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    i3 = (i3 * 30) / 540;
                    intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", i3);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 18:
                if (bArr[i + 2] == (byte) 10) {
                    new byte[1][0] = bArr[i + 5];
                    bArr2 = new byte[12];
                    bArr2[0] = (byte) 18;
                    bArr2[1] = (byte) 10;
                    while (i3 < 10) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] == (byte) 12) {
                    byte[] bArr3 = new byte[12];
                    i4 = 0;
                    while (i4 < 12) {
                        bArr3[i4] = bArr[(i + 3) + i4];
                        if (bArr3[i4] != this.ah[i4]) {
                            this.ah[i4] = bArr3[i4];
                            i4++;
                        } else {
                            this.ah[i4] = bArr3[i4];
                            i4++;
                        }
                    }
                    de(bArr3);
                    i4 = bArr3[11] & 255;
                    String str = "";
                    String str2 = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf((((float) i4) * 0.5f) - 40.0f)}) + this.el.getString(R.string.c_dan);
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str2);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) 52:
                if (bArr[i + 2] == (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 52;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 53:
                if (bArr[i + 2] == (byte) 6) {
                    bArr2 = new byte[8];
                    bArr2[0] = (byte) 53;
                    bArr2[1] = (byte) 6;
                    while (i3 < 6) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) 54:
                if (bArr[i + 2] != (byte) 1) {
                    return;
                }
                return;
            case (byte) 65:
                if (bArr[i + 2] == (byte) 12) {
                    int[] iArr = new int[12];
                    for (i4 = 0; i4 < 12; i4++) {
                        iArr[i4] = df(bArr[(i + 3) + i4] & 255);
                    }
                    this.ek.max = 7;
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
            default:
                return;
        }
    }

    public void bg() {
        this.ej.ol((byte) -95, new byte[3], 3);
    }

    public void mo36d() {
        byte[] bArr = new byte[2];
        if (C0035b.lp() > 19) {
            bArr[0] = (byte) this.ej.jg;
        } else {
            bArr[0] = (byte) 16;
        }
        bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        this.ej.ol((byte) -95, bArr, 3);
        this.ej.oa(1);
        this.ej.oh(1);
        this.ej.pf();
    }

    public void mo37e() {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 7;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo38f() {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 8;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo39g() {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 3;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 7;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo41i() {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 8;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 5;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 9;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo44l() {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 6;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 6;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo46n() {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 4;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[3];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) 1;
        this.ej.ol((byte) -95, bArr, 3);
    }

    public void mo49q() {
        int i = 1;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[10];
        bArr[0] = (byte) ((time.year - 2000) & 255);
        bArr[1] = (byte) ((time.month + 1) & 255);
        bArr[2] = (byte) (time.monthDay & 255);
        bArr[3] = (byte) (this.en & 255);
        bArr[4] = (byte) (this.eo & 255);
        if (!is24HourFormat) {
            i = 0;
        }
        bArr[5] = (byte) i;
        this.ej.ol((byte) -53, bArr, 10);
    }

    public void mo50r(int i) {
    }
}
