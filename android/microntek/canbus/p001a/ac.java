package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.text.format.DateFormat;
import android.text.format.Time;
import java.util.Locale;

public class ac extends C0001b {
    private byte[] f11u;

    public ac(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.f11u = new byte[11];
        this.eh = 70;
    }

    private void ca(byte[] bArr) {
        this.ei.modeDual = -1;
        this.ei.rearLock = -1;
        this.ei.wind_FrameShow = false;
        this.ei.windRearShow = false;
        this.ei.seatShow = false;
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[0] & 3) != 0) {
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
        byte b = bArr[4];
        if (b == (byte) 2) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if (b == (byte) 3) {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if (b == (byte) 5) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else if (b == (byte) 6) {
            this.ei.windUp = false;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if (b == (byte) 11) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = false;
        } else if (b == (byte) 12) {
            this.ei.windUp = true;
            this.ei.windMid = false;
            this.ei.windDown = true;
        } else if (b == (byte) 13) {
            this.ei.windUp = true;
            this.ei.windMid = true;
            this.ei.windDown = false;
        } else if (b == (byte) 14) {
            this.ei.windUp = true;
            this.ei.windMid = true;
            this.ei.windDown = true;
        } else {
            this.ei.windUp = false;
            this.ei.windMid = false;
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[5] & 15;
        this.ei.windLevelMax = 8;
        int i = bArr[6] & 255;
        if (i == 254) {
            this.ei.tempLeft = 0;
        } else if (i == 255) {
            this.ei.tempLeft = 65535;
        } else if (i < 34 || i > 64) {
            this.ei.tempLeft = -1;
        } else {
            this.ei.tempLeft = (i * 10) / 2;
        }
        i = bArr[7] & 255;
        if (i == 254) {
            this.ei.tempRight = 0;
        } else if (i == 255) {
            this.ei.tempRight = 65535;
        } else if (i < 34 || i > 64) {
            this.ei.tempRight = -1;
        } else {
            this.ei.tempRight = (i * 10) / 2;
        }
        if ((bArr[2] & 16) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = -1;
        }
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 49:
                if (bArr[i + 2] >= (byte) 12) {
                    int i3;
                    byte[] bArr2 = new byte[11];
                    int i4 = 0;
                    for (i3 = 0; i3 < 11; i3++) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        if (bArr2[i3] != this.f11u[i3]) {
                            i4 = 1;
                        }
                        this.f11u[i3] = bArr2[i3];
                    }
                    if (i4 != 0) {
                        ca(bArr2);
                        this.ej.od(this.ei);
                    }
                    String str = "";
                    if ((bArr[(i + 3) + 11] & 255) <= 250) {
                        str = String.format(Locale.US, "  %.1f", new Object[]{Float.valueOf((((float) i3) / 2.0f) - 40.0f)}) + this.el.getString(R.string.c_dan);
                    }
                    md(str);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        if (this.ej.jg != 0) {
            this.ej.ol((byte) 36, new byte[]{(byte) (this.ej.jg + 31), (byte) 0}, 2);
        }
    }

    public void mo37e() {
        this.ej.ol((byte) -46, new byte[]{(byte) 21, (byte) 65, (byte) 50, (byte) 68, (byte) 80, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo38f() {
        this.ej.ol((byte) -46, new byte[]{(byte) 8, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo39g() {
        this.ej.ol((byte) -46, new byte[]{(byte) 12, (byte) 65, (byte) 86, (byte) 73, (byte) 78, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo40h(String str, int i) {
        this.ej.ol((byte) -46, new byte[]{(byte) 10, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo41i() {
        this.ej.ol((byte) -46, new byte[]{(byte) 8, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[13];
        bArr[0] = (byte) (i3 == 2 ? 7 : 6);
        bArr[1] = (byte) 32;
        bArr[2] = (byte) 32;
        bArr[3] = (byte) 32;
        bArr[4] = (byte) 32;
        bArr[5] = (byte) 32;
        bArr[6] = (byte) 32;
        bArr[7] = (byte) 32;
        bArr[8] = (byte) 32;
        bArr[9] = (byte) 32;
        bArr[10] = (byte) 32;
        bArr[11] = (byte) 32;
        bArr[12] = (byte) 32;
        bArr[2] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        bArr[3] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        bArr[4] = (byte) 58;
        bArr[5] = (byte) (((i2 / 60) / 10) + 48);
        bArr[6] = (byte) (((i2 % 60) % 10) + 48);
        bArr[7] = (byte) 58;
        bArr[8] = (byte) (((i2 / 60) / 10) + 48);
        bArr[9] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.ol((byte) -46, bArr, 13);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        this.ej.ol((byte) -46, new byte[]{(byte) 11, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 13, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i4 = i3 / 1000;
        bArr[1] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        bArr[2] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        bArr[3] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        bArr[4] = (byte) (((i4 % 60) / 10) + 48);
        bArr[5] = (byte) (((i4 % 60) % 10) + 48);
        bArr[6] = (byte) ((i2 / 1000) + 48);
        bArr[7] = (byte) (((i2 / 100) % 10) + 48);
        bArr[8] = (byte) (((i2 / 10) % 10) + 48);
        bArr[9] = (byte) ((i2 % 10) + 48);
        this.ej.ol((byte) -46, bArr, 13);
    }

    public void mo46n() {
        this.ej.ol((byte) -46, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        if (b >= (byte) 0 && b <= (byte) 2) {
            bArr[0] = (byte) (b + 1);
            if (i > 9999) {
                bArr[1] = (byte) ((i / 10000) + 48);
                bArr[2] = (byte) (((i / 1000) % 10) + 48);
                bArr[3] = (byte) (((i / 100) % 10) + 48);
                bArr[4] = (byte) 46;
                bArr[5] = (byte) (((i / 10) % 10) + 48);
                bArr[6] = (byte) ((i % 10) + 48);
            } else {
                bArr[2] = (byte) ((i / 1000) + 48);
                bArr[3] = (byte) (((i / 100) % 10) + 48);
                bArr[4] = (byte) 46;
                bArr[5] = (byte) (((i / 10) % 10) + 48);
                bArr[6] = (byte) ((i % 10) + 48);
            }
            bArr[7] = (byte) 77;
            bArr[8] = (byte) 72;
            bArr[9] = (byte) 90;
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[0] = (byte) (b + 1);
            if (i > 999) {
                bArr[2] = (byte) ((i / 1000) + 48);
                bArr[3] = (byte) (((i / 100) % 10) + 48);
                bArr[4] = (byte) (((i / 10) % 10) + 48);
                bArr[5] = (byte) ((i % 10) + 48);
            } else {
                bArr[3] = (byte) ((i / 100) + 48);
                bArr[4] = (byte) (((i / 10) % 10) + 48);
                bArr[5] = (byte) ((i % 10) + 48);
            }
            bArr[6] = (byte) 75;
            bArr[7] = (byte) 72;
            bArr[8] = (byte) 90;
        }
        this.ej.ol((byte) -46, bArr, 13);
    }

    public void mo48p() {
    }

    public void mo49q() {
        int i = 1;
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[10];
        bArr[1] = (byte) this.en;
        bArr[2] = (byte) this.eo;
        if (!is24HourFormat) {
            i = 0;
        }
        bArr[5] = (byte) i;
        this.ej.ol((byte) -53, bArr, 10);
    }

    public void mo50r(int i) {
    }
}
