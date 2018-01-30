package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R$styleable;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import android.text.format.DateFormat;
import android.text.format.Time;

public class C0010e extends C0001b {
    public C0010e(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    private void ah(byte[] bArr) {
        if ((bArr[2] & 7) != 0) {
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
        if ((bArr[0] & 1) != 0) {
            this.ei.windFrontMax = true;
        } else {
            this.ei.windFrontMax = false;
        }
        if ((bArr[0] & 2) != 0) {
            this.ei.windRear = true;
        } else {
            this.ei.windRear = false;
        }
        switch (bArr[1] & 7) {
            case R$styleable.MyButton_imgHeight /*1*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = false;
                break;
            case R$styleable.MyButton_imgSrc /*2*/:
                this.ei.windUp = false;
                this.ei.windMid = true;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc1 /*3*/:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgSrc2 /*4*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = true;
                break;
            case R$styleable.MyButton_imgWidth2 /*5*/:
                this.ei.windUp = true;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
            default:
                this.ei.windUp = false;
                this.ei.windMid = false;
                this.ei.windDown = false;
                break;
        }
        this.ei.windLevel = bArr[2] & 7;
        this.ei.windLevelMax = 7;
        int i = bArr[3] & 255;
        this.ei.tempLeft = am(i);
        i = bArr[4] & 255;
        this.ei.tempRight = am(i);
        if ((bArr[0] & 16) != 0) {
            this.ei.windLoop = 1;
        } else {
            this.ei.windLoop = 0;
        }
        this.ei.seatShow = false;
    }

    private void aj(byte b) {
        this.ej.ob((byte) -58, new byte[]{(byte) 0, b}, 2);
    }

    void ai(byte[] bArr) {
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

    public void ak(int i, int i2, int i3) {
        mo45m(i, i2, i3);
    }

    public void al() {
        this.ej.ob((byte) -112, new byte[]{(byte) -88, (byte) 0}, 2);
    }

    int am(int i) {
        return i == 0 ? 0 : i == 30 ? 65535 : (i < 0 || i > 30) ? -1 : (i * 5) + 175;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 33:
                if (bArr[i + 2] >= (byte) 5) {
                    ah(ma(bArr, i + 3, i2));
                    this.ej.od(this.ei);
                    return;
                }
                return;
            case (byte) 36:
                if (bArr[i + 2] >= (byte) 2) {
                    ai(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            case (byte) 41:
                if (bArr[i + 2] >= (byte) 2) {
                    lz(0 - (((((bArr[i + 3] & 255) + ((bArr[i + 4] & 255) << 8)) - 32768) * 30) / 8076));
                    return;
                }
                return;
            case (byte) 64:
                if (bArr[i + 2] >= (byte) 5) {
                    this.ej.oj(ma(bArr, i + 1, i2));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oa(1);
    }

    public void mo37e() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 11;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo38f() {
        mo46n();
    }

    public void mo39g() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 7;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public int[][] getAirBtnTable() {
        int[][] iArr = new int[16][];
        iArr[0] = new int[]{3842, 46, 43010, 1, 256};
        int[] iArr2 = new int[5];
        iArr2[0] = 3845;
        iArr2[1] = 46;
        iArr2[2] = 43010;
        iArr2[3] = this.ei.windLoop == 1 ? 5 : 4;
        iArr2[4] = 256;
        iArr[1] = iArr2;
        iArr[2] = new int[]{3846, 46, 43010, 2, 256};
        iArr[3] = new int[]{3847, 46, 43010, 13, 256};
        iArr[4] = new int[]{3848, 46, 43010, 14, 256};
        iArr[5] = new int[]{3849, 46, 43010, 15, 256};
        iArr[6] = new int[]{3850, 46, 43010, 16, 256};
        iArr[7] = new int[]{3853, 46, 43010, 0, 256};
        iArr[8] = new int[]{3855, 46, 43010, 12, 256};
        iArr[9] = new int[]{3856, 46, 43010, 11, 256};
        iArr[10] = new int[]{3857, 46, 43010, 3, 256};
        iArr[11] = new int[]{3858, 46, 43010, 9, 256};
        iArr[12] = new int[]{3859, 46, 43010, 6, 256};
        iArr[13] = new int[]{3860, 46, 43010, 7, 256};
        iArr[14] = new int[]{3861, 46, 43010, 10, 256};
        iArr[15] = new int[]{3863, 46, 43010, 8, 256};
        return iArr;
    }

    public void mo40h(String str, int i) {
        int i2 = 1;
        if (i == 1 || i == 2) {
            try {
                byte[] bytes = str.getBytes("gb2312");
                byte[] bArr = new byte[(bytes.length + 2)];
                if (i != 1) {
                    i2 = 2;
                }
                bArr[0] = (byte) i2;
                bArr[1] = (byte) 1;
                for (int i3 = 0; i3 < bytes.length; i3++) {
                    bArr[i3 + 2] = bytes[i3];
                }
                this.ej.ob((byte) -59, bArr, bytes.length + 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void mo41i() {
    }

    public void mo42j(int i, int i2, int i3) {
        mo46n();
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 8;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo45m(int i, int i2, int i3) {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 9;
        this.ej.ob((byte) -64, bArr, 8);
    }

    public void mo46n() {
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 0;
        bArr[1] = (byte) 0;
        this.ej.ob((byte) -64, bArr, 8);
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
            aj((byte) 0);
        } else if (language.equals("en")) {
            aj((byte) 1);
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
        bArr[3] = (byte) ((this.en & 63) << 1);
        bArr[4] = (byte) (this.eo & 255);
        this.ej.ob((byte) -90, bArr, 7);
    }

    public void mo50r(int i) {
        this.ej.ob((byte) -60, new byte[]{(byte) i}, 1);
    }
}
