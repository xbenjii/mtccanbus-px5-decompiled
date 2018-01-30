package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;

public class ak extends C0001b {
    public ak(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 65;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) 36:
                if (bArr[i + 2] == (byte) 2) {
                    byte[] bArr2 = new byte[]{bArr[i + 3]};
                    if ((bArr2[0] & 1) != 0) {
                        dg(bArr2);
                        return;
                    }
                    return;
                }
                return;
            case (byte) 41:
                int i3 = ((bArr[i + 4] & 255) << 8) | (bArr[i + 3] & 255);
                if (i3 >= 32768) {
                    i3 -= 65536;
                }
                lz(0 - ((i3 * 30) / 1122));
                return;
            default:
                return;
        }
    }

    public void mo36d() {
        this.ej.oh(1);
    }

    void dg(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 64) != 0;
        boolean z3 = (bArr[0] & 128) != 0;
        boolean z4 = (bArr[0] & 0) != 0;
        boolean z5 = (bArr[0] & 32) != 0;
        boolean z6 = (bArr[0] & 0) != 0;
        if ((bArr[0] & 4) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }
}
