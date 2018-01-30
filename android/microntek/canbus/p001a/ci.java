package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;

public class ci extends bx {
    public ci(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        byte[] bArr2;
        switch (bArr[i + 1]) {
            case (byte) -108:
                if (mc(bArr[i + 2], 4)) {
                    bArr2 = new byte[(this.er + 2)];
                    bArr2[0] = (byte) -108;
                    bArr2[1] = (byte) this.er;
                    while (i3 < this.er) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            case (byte) -107:
                if (mc(bArr[i + 2], 1)) {
                    bArr2 = new byte[(this.er + 2)];
                    bArr2[0] = (byte) -107;
                    bArr2[1] = (byte) this.er;
                    while (i3 < this.er) {
                        bArr2[i3 + 2] = bArr[(i + 3) + i3];
                        i3++;
                    }
                    this.ej.oj(bArr2);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
