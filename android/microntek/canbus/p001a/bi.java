package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;

public class bi extends C0001b {
    byte[] az;

    public bi(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.az = new byte[12];
        this.eh = 47;
    }

    private void fp(byte[] bArr) {
        this.ei.seatShow = false;
        this.ei.windRearShow = false;
        this.ei.wind_FrameShow = false;
        if ((bArr[0] & 64) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        this.ei.modeAc = true;
        if ((bArr[0] & 2) != 0) {
            this.ei.modeAuto = 2;
        } else if ((bArr[0] & 1) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        this.ei.DIS_IMG = 1;
        if ((bArr[1] & 8) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if (bArr[4] == (byte) 3 || bArr[4] == (byte) 5 || bArr[4] == (byte) 6 || bArr[4] == (byte) 11 || bArr[4] == (byte) 12 || bArr[4] == (byte) 13 || bArr[4] == (byte) 14) {
            if (bArr[4] == (byte) 14) {
                this.ei.windDown = true;
                this.ei.windRear = false;
                this.ei.windUp = true;
                this.ei.windMid = true;
            }
            if (bArr[4] == (byte) 13) {
                this.ei.windDown = false;
                this.ei.windRear = false;
                this.ei.windUp = true;
                this.ei.windMid = true;
            }
            if (bArr[4] == (byte) 12) {
                this.ei.windDown = true;
                this.ei.windRear = false;
                this.ei.windUp = true;
                this.ei.windMid = false;
            }
            if (bArr[4] == (byte) 11) {
                this.ei.windDown = false;
                this.ei.windRear = false;
                this.ei.windUp = true;
                this.ei.windMid = false;
            }
            if (bArr[4] == (byte) 6) {
                this.ei.windDown = false;
                this.ei.windRear = false;
                this.ei.windUp = false;
                this.ei.windMid = true;
            }
            if (bArr[4] == (byte) 5) {
                this.ei.windDown = true;
                this.ei.windRear = false;
                this.ei.windUp = false;
                this.ei.windMid = true;
            }
            if (bArr[4] == (byte) 3) {
                this.ei.windDown = true;
                this.ei.windRear = false;
                this.ei.windUp = false;
                this.ei.windMid = false;
            }
        } else {
            this.ei.windDown = false;
            this.ei.windRear = false;
            this.ei.windUp = false;
            this.ei.windMid = false;
        }
        this.ei.windLevel = bArr[5] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[6] & 255;
        if (i == 254) {
            this.ei.tempLeft = 0;
        } else if (i == 255) {
            this.ei.tempLeft = 65535;
        } else {
            this.ei.tempLeft = i * 5;
        }
        i = bArr[7] & 255;
        if (i == 254) {
            this.ei.tempRight = 0;
        } else if (i == 255) {
            this.ei.tempRight = 65535;
        } else {
            this.ei.tempRight = i * 5;
        }
        if ((bArr[0] & 32) == 0) {
            this.ei.windLoop = 0;
        } else if ((bArr[4] & 128) != 0) {
            this.ei.windLoop = 2;
        } else {
            this.ei.windLoop = 1;
        }
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        int oc = this.ej.oc();
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgHeight2 /*6*/:
                if (bArr[i + 2] == (byte) 2) {
                    break;
                }
                break;
            case (byte) 17:
                if (bArr[i + 2] == (byte) 10) {
                    fq(new byte[]{bArr[i + 12]});
                    oc = (bArr[i + 10] & 255) + ((bArr[i + 9] & 255) << 8);
                    if (oc >= 32768) {
                        oc -= 65536;
                    }
                    oc = (oc * 35) / 480;
                    Intent intent = new Intent("com.microntek.canbusbackview");
                    intent.putExtra("canbustype", this.eh);
                    intent.putExtra("lfribackview", oc);
                    this.el.sendBroadcast(intent);
                    break;
                }
                break;
            case (byte) 49:
                if (bArr[i + 2] == (byte) 12) {
                    byte[] bArr3 = new byte[12];
                    oc = 0;
                    for (int i3 = 0; i3 < 12; i3++) {
                        bArr3[i3] = bArr[(i + 3) + i3];
                        if (bArr3[i3] != this.az[i3]) {
                            oc = 1;
                        }
                        this.az[i3] = bArr3[i3];
                    }
                    if (oc != 0) {
                        fp(bArr3);
                        this.ej.od(this.ei);
                        break;
                    }
                }
                break;
            case (byte) 65:
                if (oc != 0 && bArr[i + 2] == (byte) 16) {
                    bArr2 = new byte[8];
                    for (oc = 0; oc < 8; oc++) {
                        bArr2[oc] = (byte) (((bArr[(i + 3) + oc] & 255) * 10) / 255);
                    }
                    this.ek.max = 10;
                    this.ek.back_cnt = 4;
                    this.ek.b1 = bArr2[0];
                    this.ek.b2 = bArr2[1];
                    this.ek.b3 = bArr2[3];
                    this.ek.b4 = bArr2[2];
                    this.ek.front_cnt = 4;
                    this.ek.f1 = bArr2[4];
                    this.ek.f2 = bArr2[5];
                    this.ek.f3 = bArr2[7];
                    this.ek.f4 = bArr2[6];
                    this.ej.oe(this.ek);
                    break;
                }
        }
        int i4 = i2 - i;
        if (i4 > 3 && i4 <= 20) {
            bArr2 = new byte[i4];
            for (oc = 0; oc < i4; oc++) {
                bArr2[oc] = bArr[(i + 1) + oc];
            }
            this.ej.oj(bArr2);
        }
    }

    public void mo36d() {
        this.ej.oa(1);
        this.ej.oh(1);
    }

    public void mo37e() {
        this.ej.ol((byte) -31, new byte[]{(byte) 10, (byte) 32, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32}, 13);
    }

    public void mo38f() {
        this.ej.ol((byte) -31, new byte[]{(byte) 8, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    void fq(byte[] bArr) {
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

    public void mo39g() {
        this.ej.ol((byte) -31, new byte[]{(byte) 12, (byte) 32, (byte) 65, (byte) 86, (byte) 73, (byte) 78, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo40h(String str, int i) {
        this.ej.ol((byte) -31, new byte[]{(byte) 10, (byte) 32, (byte) 66, (byte) 108, (byte) 117, (byte) 101, (byte) 84, (byte) 111, (byte) 111, (byte) 116, (byte) 104, (byte) 32, (byte) 32}, 13);
    }

    public void mo41i() {
        this.ej.ol((byte) -31, new byte[]{(byte) 8, (byte) 32, (byte) 84, (byte) 86, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        bArr[0] = (byte) 7;
        bArr[5] = (byte) ((((i2 / 60) / 60) / 10) + 48);
        bArr[6] = (byte) ((((i2 / 60) / 60) % 10) + 48);
        bArr[7] = (byte) 58;
        bArr[8] = (byte) ((((i2 / 60) % 60) / 10) + 48);
        bArr[9] = (byte) ((((i2 / 60) % 60) % 10) + 48);
        bArr[10] = (byte) 58;
        bArr[11] = (byte) (((i2 % 60) / 10) + 48);
        bArr[12] = (byte) (((i2 % 60) % 10) + 48);
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[]{(byte) 11, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i5 = i3 / 1000;
        bArr[3] = (byte) ((((i5 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i5 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i5 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i5 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i5 % 60) / 10) + 48);
        bArr[10] = (byte) (((i5 % 60) % 10) + 48);
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo44l() {
        this.ej.ol((byte) -31, new byte[]{(byte) 13, (byte) 32, (byte) 109, (byte) 111, (byte) 118, (byte) 105, (byte) 101, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[]{(byte) 13, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
        int i4 = i3 / 1000;
        bArr[3] = (byte) ((((i4 / 60) / 60) / 10) + 48);
        bArr[4] = (byte) ((((i4 / 60) / 60) % 10) + 48);
        bArr[5] = (byte) 58;
        bArr[6] = (byte) ((((i4 / 60) % 60) / 10) + 48);
        bArr[7] = (byte) ((((i4 / 60) % 60) % 10) + 48);
        bArr[8] = (byte) 58;
        bArr[9] = (byte) (((i4 % 60) / 10) + 48);
        bArr[10] = (byte) (((i4 % 60) % 10) + 48);
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo46n() {
        this.ej.ol((byte) -31, new byte[]{(byte) 0, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32}, 13);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
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
        this.ej.ol((byte) -31, bArr, 13);
    }

    public void mo49q() {
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[10];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = (byte) (this.en & 255);
        bArr[2] = (byte) (this.eo & 255);
        if (is24HourFormat) {
            bArr[5] = (byte) 1;
        } else {
            bArr[5] = (byte) 0;
        }
        bArr[6] = (byte) (((time.year - 2000) + 208) & 255);
        bArr[7] = (byte) ((time.month + 1) & 255);
        bArr[8] = (byte) (time.monthDay & 255);
        bArr[9] = (byte) 2;
        this.ej.ol((byte) -53, bArr, 10);
    }

    public void mo50r(int i) {
    }
}
