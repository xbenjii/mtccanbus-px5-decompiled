package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class ag extends C0001b {
    private final int[][] ab = new int[][]{new int[]{3842, 46, 33282, 17, 256}, new int[]{3844, 46, 33282, 22, 256}, new int[]{3845, 46, 33282, 19, 256}, new int[]{3846, 46, 33282, 20, 256}, new int[]{3847, 46, 33282, 31, 256}, new int[]{3848, 46, 33282, 30, 256}, new int[]{3849, 46, 33282, 33, 256}, new int[]{3850, 46, 33282, 32, 256}, new int[]{3855, 46, 33282, 28, 256}, new int[]{3856, 46, 33282, 29, 256}, new int[]{3857, 46, 33282, 23, 256}, new int[]{3858, 46, 33282, 26, 256}, new int[]{3859, 46, 33282, 27, 256}, new int[]{3860, 46, 33282, 24, 256}, new int[]{3872, 46, 33282, 21, 256}, new int[]{3876, 46, 33282, 66, 256}, new int[]{3875, 46, 33282, 64, 256}, new int[]{3874, 46, 33282, 65, 256}, new int[]{3873, 46, 33282, 34, 256}};
    private final int[][] ac = new int[][]{new int[]{3842, 46, 33282, 17, 256}, new int[]{3844, 46, 33282, 22, 256}, new int[]{3845, 46, 33282, 19, 256}, new int[]{3847, 46, 33282, 31, 256}, new int[]{3848, 46, 33282, 30, 256}, new int[]{3849, 46, 33282, 31, 256}, new int[]{3850, 46, 33282, 30, 256}, new int[]{3855, 46, 33282, 28, 256}, new int[]{3856, 46, 33282, 29, 256}, new int[]{3872, 46, 33282, 21, 256}};

    public ag(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void cm(byte[] bArr) {
        if ((bArr[0] & 128) == 0 || (bArr[1] & 16) == 0) {
            this.ei.onOff = false;
        } else {
            this.ei.onOff = true;
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
        if ((bArr[4] & 64) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
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
        this.ei.windLevelMax = 8;
        int i = bArr[2] & 255;
        this.ei.tempLeft = co(i);
        i = bArr[3] & 255;
        this.ei.tempRight = co(i);
        if ((bArr[0] & 32) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
        switch ((bArr[4] >> 2) & 3) {
            case R$styleable.MyButton_imgWidth /*0*/:
                this.ei.fast = 0;
                this.ei.normal = 0;
                this.ei.soft = 1;
                break;
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.fast = 0;
                this.ei.normal = 1;
                this.ei.soft = 0;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.fast = 1;
                this.ei.normal = 0;
                this.ei.soft = 0;
                break;
            default:
                this.ei.fast = 0;
                this.ei.normal = 0;
                this.ei.soft = 0;
                break;
        }
        if ((bArr[4] & 2) != 0) {
            this.ei.loopa = 1;
        } else {
            this.ei.loopa = 0;
        }
    }

    private int co(int i) {
        return i == 0 ? 0 : i == 255 ? 65535 : i <= 112 ? i * 5 : -1;
    }

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void al() {
        this.ej.ob((byte) -112, new byte[]{(byte) 33, (byte) 0}, 2);
    }

    public void at() {
        this.ej.ob((byte) -112, new byte[]{(byte) 41, (byte) 0}, 2);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4;
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 6) {
                    byte[] bArr2 = new byte[6];
                    while (i3 < 6) {
                        bArr2[i3] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    if (lx(bArr2)) {
                        cm(bArr2);
                        this.ej.od(this.ei);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 34:
                if (bArr[i + 2] >= (byte) 4) {
                    int[] iArr = new int[4];
                    i4 = 0;
                    while (i4 < 4) {
                        iArr[i4] = bArr[(i + 3) + i4] & 255;
                        if (iArr[i4] < 1 || iArr[i4] > 4) {
                            iArr[i4] = 0;
                        } else {
                            iArr[i4] = 5 - iArr[i4];
                        }
                        i4++;
                    }
                    mb();
                    this.ek.max = 4;
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
                if (bArr[i + 2] >= (byte) 2) {
                    cn(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    i4 = (bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8);
                    if (i4 >= 32768) {
                        i4 -= 65536;
                    }
                    lz((i4 * 30) / 5376);
                    return;
                }
                return;
            default:
                return;
        }
    }

    void cn(byte[] bArr) {
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

    public void mo36d() {
        this.ej.oh(1);
        this.ej.oa(1);
        this.ej.ob((byte) -125, new byte[]{(byte) me()}, 1);
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        mo39g();
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public int[][] getAirBtnTable() {
        return me() == 1 ? this.ac : this.ab;
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 5;
        bArr[1] = (byte) 64;
        bArr[2] = (byte) (i == 2 ? 4 : 3);
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo41i() {
        mo39g();
    }

    public void mo42j(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 16;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        mo45m(i, i2, i3);
    }

    public void mo45m(int i, int i2, int i3) {
        r0 = new byte[8];
        int i4 = i3 / 1000;
        r0[5] = (byte) ((i4 / 60) / 60);
        r0[6] = (byte) ((i4 / 60) % 60);
        r0[7] = (byte) (i4 % 60);
        this.ej.ob((byte) -64, r0, 8);
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

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
