package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.Canbus20Activity;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class cd extends C0001b {
    public cd(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 20;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        Intent intent;
        switch (bArr[i + 1]) {
            case (byte) 18:
                if (bArr[i + 2] == (byte) 1) {
                    bArr2 = new byte[]{bArr[i + 1], bArr[i + 3], bArr[i + 4]};
                    if (((byte) (bArr2[1] & 1)) != (byte) 1 || Canbus20Activity.mk != 1) {
                        intent = new Intent(this.ej.getApplicationContext(), Canbus20Activity.class);
                        intent.setFlags(268435456);
                        intent.putExtra("syncdata", bArr2);
                        this.ej.startActivity(intent);
                        intent = new Intent("com.microntek.canbus20activity");
                        intent.putExtra("syncdata", bArr2);
                        this.el.sendBroadcast(intent);
                        break;
                    }
                    Canbus20Activity.mk = 0;
                    return;
                }
                break;
            case (byte) 20:
                if (bArr[i + 2] == (byte) 1) {
                    in(new byte[]{bArr[i + 3]});
                    break;
                }
                break;
            case (byte) 22:
                if (bArr[i + 2] == (byte) 2) {
                    String str;
                    byte b = bArr[i + 4];
                    float f = (float) bArr[i + 3];
                    float abs = f > 0.0f ? Math.abs(f / 2.0f) - 39.5f : (Math.abs(Math.abs(f / 2.0f) - 64.0f) + 24.0f) + 0.5f;
                    String str2 = "";
                    if (f == 0.0f) {
                        str = "";
                    } else if (((byte) (b & 1)) == (byte) 1) {
                        abs = ((abs * 9.0f) / 5.0f) + 32.0f;
                        str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(abs)}) + this.el.getString(R.string.f_dan);
                    } else {
                        str = String.format(Locale.US, " %.1f", new Object[]{Float.valueOf(abs)}) + this.el.getString(R.string.c_dan);
                    }
                    intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", str);
                    this.el.sendBroadcast(intent);
                    break;
                }
                break;
            case (byte) 23:
                if (bArr[i + 2] == (byte) 2) {
                    bArr2 = new byte[]{bArr[i + 1], bArr[i + 3], bArr[i + 4]};
                    intent = new Intent("com.microntek.canbus20activity");
                    intent.putExtra("syncdata", bArr2);
                    this.el.sendBroadcast(intent);
                    break;
                }
                break;
        }
    }

    public void bg() {
        this.ej.ob((byte) -110, new byte[]{Byte.MIN_VALUE}, 1);
    }

    public void mo36d() {
    }

    public void mo37e() {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo38f() {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo39g() {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 3;
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo40h(String str, int i) {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo41i() {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -109, bArr, 6);
    }

    void in(byte[] bArr) {
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

    public void mo42j(int i, int i2, int i3) {
        this.ej.ob((byte) -109, new byte[]{(byte) 2, (byte) 32, (byte) (i & 255), (byte) 2, (byte) 2, (byte) 2}, 6);
    }

    public void mo43k(int i, int i2, byte b, int i3, int i4) {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo44l() {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo45m(int i, int i2, int i3) {
        this.ej.ob((byte) -109, new byte[]{(byte) 4, (byte) 32, (byte) (i2 & 255), (byte) 2, (byte) 32, (byte) 32}, 6);
    }

    public void mo46n() {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 15;
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo47o(byte b, int i, byte b2) {
        byte[] bArr = new byte[6];
        bArr[0] = (byte) 1;
        bArr[1] = b;
        bArr[2] = (byte) 32;
        bArr[3] = (byte) 32;
        if (b >= (byte) 0 && b <= (byte) 2) {
            int i2 = i / 10;
            bArr[5] = (byte) (i2 & 255);
            bArr[4] = (byte) ((i2 >> 8) & 255);
        } else if (b >= (byte) 3 && b <= (byte) 4) {
            bArr[1] = (byte) 6;
            bArr[5] = (byte) (i & 255);
            bArr[4] = (byte) ((i >> 8) & 255);
        }
        this.ej.ob((byte) -109, bArr, 6);
    }

    public void mo49q() {
    }

    public void mo50r(int i) {
    }
}
