package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;

public class dd extends C0001b {
    public dd(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void kr(byte[] bArr) {
        this.ei.viewShow = (bArr[1] & 16) != 0;
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
        if ((bArr[0] & 2) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        this.ei.windRearShow = false;
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
        this.ei.windLevelMax = 7;
        this.ei.tempLeft = ku(bArr[2] & 255);
        this.ei.tempRight = ku(bArr[3] & 255);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    private void kt(byte b) {
        this.ej.ob((byte) -58, new byte[]{(byte) -92, b, (byte) 0}, 3);
    }

    private int ku(int i) {
        return i == 0 ? 0 : i == 255 ? 65535 : i <= 112 ? i * 5 : -1;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void at() {
        this.ej.ob((byte) -112, new byte[]{(byte) 41, (byte) 0}, 2);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 6) {
                    byte[] bArr2 = new byte[6];
                    while (i3 < 6) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    kr(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    int[] iArr = new int[4];
                    for (int i4 = 0; i4 < 4; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] > 10) {
                            iArr[i4] = 0;
                        }
                    }
                    mb();
                    this.ek.max = 10;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = iArr[0];
                    this.ek.b2 = iArr[1];
                    this.ek.b3 = iArr[2];
                    this.ek.b4 = iArr[3];
                    this.ej.oe(this.ek);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2 && (bArr[i + 3] & 1) != 0) {
                    ks(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = ((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255);
                    if (i3 >= 32768) {
                        i3 -= 65536;
                    }
                    lz((i3 * 30) / 8960);
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
        mo48p();
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        mo41i();
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 5;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo41i() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 12;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 12;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    void ks(byte[] bArr) {
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

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 8;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo46n() {
        this.ej.ob((byte) -64, new byte[8], 8);
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
        String language = this.ej.getResources().getConfiguration().locale.getLanguage();
        if (language.equals("zh")) {
            kt((byte) 27);
        } else if (language.equals("en")) {
            kt((byte) 2);
        }
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        byte[] bArr = new byte[7];
        if (!DateFormat.is24HourFormat(this.el)) {
            if (this.en > 12) {
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
        }
        bArr[3] = (byte) (this.en & 127);
        bArr[4] = (byte) (this.eo & 255);
        this.ej.ob((byte) -90, bArr, 7);
    }

    public void mo50r(int i) {
        byte[] bArr = new byte[1];
        if (i == 0) {
            bArr[0] = Byte.MIN_VALUE;
        } else {
            bArr[0] = (byte) (i & 255);
        }
        this.ej.ob((byte) -60, bArr, 1);
    }
}
