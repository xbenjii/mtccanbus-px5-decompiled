package android.microntek.canbus.update;

import android.microntek.canbus.BaseApplication;
import java.util.Locale;

public class C0064c {
    public Object ik = new Object();
    public UpdateService il;

    public C0064c(UpdateService updateService) {
        this.il = updateService;
    }

    public void nr(byte[] bArr) {
    }

    public void ns(String str) {
    }

    public void nt(byte b, byte[] bArr, int i) {
        int i2 = 0;
        byte[] bArr2 = new byte[(i + 4)];
        bArr2[0] = (byte) 46;
        bArr2[1] = b;
        bArr2[2] = (byte) (i & 255);
        int i3 = (short) (bArr2[1] + bArr2[2]);
        while (i2 < i) {
            bArr2[i2 + 3] = bArr[i2];
            i3 = (short) (i3 + bArr2[i2 + 3]);
            i2++;
        }
        bArr2[i + 3] = (byte) ((i3 & 255) ^ 255);
        nu(bArr2);
    }

    public void nu(byte[] bArr) {
        String str = "";
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            str = str + String.format(Locale.US, "%d", new Object[]{Integer.valueOf(bArr[i] & 255)});
            if (i < length - 1) {
                str = str + ",";
            }
        }
        BaseApplication.py().qh("canbus_rsp=" + str);
    }

    public void nv(long j) {
        try {
            Thread.sleep(j);
        } catch (Exception e) {
        }
    }
}
