package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;

public class bk extends C0001b {
    public bk(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 63;
    }

    private void fs(byte[] bArr) {
        this.ei.seatShow = false;
        if ((bArr[2] & 15) != 0) {
            this.ei.onOff = true;
        } else {
            this.ei.onOff = false;
        }
        if ((bArr[1] & 1) != 0) {
            this.ei.modeAc = true;
        } else {
            this.ei.modeAc = false;
        }
        if ((bArr[1] & 4) != 0) {
            this.ei.modeAuto = 1;
        } else {
            this.ei.modeAuto = 0;
        }
        if ((bArr[2] & 64) != 0) {
            this.ei.modeDual = 1;
        } else {
            this.ei.modeDual = 0;
        }
        if ((bArr[1] & 32) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        if ((bArr[1] & 64) != 0) {
            this.ei.windUp = true;
        } else {
            this.ei.windUp = false;
        }
        if ((bArr[1] & 8) != 0) {
            this.ei.windMid = true;
        } else {
            this.ei.windMid = false;
        }
        if ((bArr[1] & 16) != 0) {
            this.ei.windDown = true;
        } else {
            this.ei.windDown = false;
        }
        this.ei.windLevel = bArr[2] & 15;
        this.ei.windLevelMax = 7;
        int i = bArr[3] & 255;
        if (i == 1) {
            this.ei.tempLeft = 0;
        } else if (i == 57) {
            this.ei.tempLeft = 65535;
        } else if (i < 3 || i > 55) {
            this.ei.tempLeft = -1;
        } else if (i % 2 != 0) {
            this.ei.tempLeft = (int) (((((float) (i / 2)) / 2.0f) * 10.0f) + 180.0f);
        }
        i = bArr[4] & 255;
        if (i == 1) {
            this.ei.tempRight = 0;
        } else if (i == 57) {
            this.ei.tempRight = 65535;
        } else if (i < 3 || i > 55) {
            this.ei.tempRight = -1;
        } else if (i % 2 != 0) {
            this.ei.tempRight = (int) (((((float) (i / 2)) / 2.0f) * 10.0f) + 180.0f);
        }
        if ((bArr[1] & 128) != 0) {
            this.ei.windLoop = 0;
        } else {
            this.ei.windLoop = 1;
        }
        this.ei.rearLock = -1;
        if ((bArr[2] & 128) != 0) {
            this.ei.acMax = 1;
        } else {
            this.ei.acMax = 0;
        }
    }

    private int fu(int i) {
        return (i < 25 || i > 127) ? 255 : ((i - 25) * 25) / 102;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        this.ej.oc();
        switch (bArr[i + 1]) {
            case R$styleable.MyButton_imgWidth /*0*/:
                if (bArr[i + 2] != (byte) 6) {
                    return;
                }
                return;
            case (byte) 16:
                if (bArr[i + 2] >= (byte) 5) {
                    byte[] bArr2 = new byte[5];
                    while (i3 < 5) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    fs(bArr2);
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2) {
                    ft(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 49:
                if (bArr[i + 2] >= (byte) 2) {
                    i3 = (bArr[i + 4] & 255) + ((bArr[i + 3] & 255) << 8);
                    if (i3 >= 2816 && i3 <= 12544) {
                        i3 = ((i3 - 7680) * 35) / 4864;
                        Intent intent = new Intent("com.microntek.canbusbackview");
                        intent.putExtra("canbustype", this.eh);
                        intent.putExtra("lfribackview", i3);
                        this.el.sendBroadcast(intent);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 50:
                if (bArr[i + 2] >= (byte) 6) {
                    int[] iArr = new int[6];
                    for (int i4 = 0; i4 < 6; i4++) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                    }
                    if (iArr[0] == 0) {
                        this.ek.max = iArr[1] > 30 ? 30 : iArr[1];
                        this.ek.back_cnt = 4;
                        this.ek.b1 = iArr[2] + 1;
                        this.ek.b2 = iArr[3] + 1;
                        this.ek.b3 = iArr[4] + 1;
                        this.ek.b4 = iArr[5] + 1;
                        this.ej.oe(this.ek);
                        return;
                    } else if (iArr[0] == 1) {
                        this.ek.max = 25;
                        this.ek.back_cnt = 4;
                        this.ek.b1 = fu(iArr[2]);
                        this.ek.b2 = fu(iArr[3]);
                        this.ek.b3 = fu(iArr[4]);
                        this.ek.b4 = fu(iArr[5]);
                        if (this.ek.b1 == 255 && this.ek.b2 == 255 && this.ek.b3 == 255) {
                            if (this.ek.b4 == 255) {
                                return;
                            }
                        }
                        this.ej.oe(this.ek);
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case (byte) 64:
                if (bArr[i + 2] >= (byte) 1) {
                    this.ej.oj(new byte[]{(byte) 64, (byte) 1, bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 82:
                if (bArr[i + 2] >= (byte) 2) {
                    this.ej.oj(new byte[]{(byte) 82, (byte) 2, bArr[i + 3], bArr[i + 4]});
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

    void ft(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 128) != 0;
        boolean z3 = (bArr[0] & 64) != 0;
        boolean z4 = (bArr[0] & 32) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 8) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }

    public void mo39g() {
    }

    public int[][] getAirBtnTable() {
        return new int[][]{new int[]{3842, 46, 43010, 1, 256}, new int[]{3844, 46, 43010, 27, 256}, new int[]{3845, 46, 43010, 21, 256}, new int[]{3846, 46, 43010, 2, 256}, new int[]{3847, 46, 43010, 13, 256}, new int[]{3848, 46, 43010, 14, 256}, new int[]{3849, 46, 43010, 15, 256}, new int[]{3850, 46, 43010, 16, 256}, new int[]{3853, 46, 43010, 0, 256}, new int[]{3855, 46, 43010, 12, 256}, new int[]{3856, 46, 43010, 11, 256}, new int[]{3857, 46, 43010, 3, 256}, new int[]{3859, 46, 43010, 6, 256}, new int[]{3865, 46, 43010, 17, 256}};
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
        Time time = new Time();
        time.setToNow();
        this.en = time.hour;
        this.eo = time.minute;
        boolean is24HourFormat = DateFormat.is24HourFormat(this.el);
        byte[] bArr = new byte[4];
        if (this.en > 12) {
            bArr[3] = (byte) 1;
        }
        if (is24HourFormat) {
            bArr[2] = (byte) 1;
        } else {
            if (this.en > 12) {
                this.en -= 12;
            }
            if (this.en == 0) {
                this.en = 12;
            }
        }
        bArr[0] = (byte) (this.eo & 255);
        bArr[1] = (byte) (this.en & 255);
        this.ej.ob((byte) -56, bArr, 4);
    }

    public void mo50r(int i) {
    }
}
