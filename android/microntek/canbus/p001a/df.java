package android.microntek.canbus.p001a;

import android.content.Context;
import android.content.Intent;
import android.microntek.canbus.CanBusServer;
import android.microntek.canbus.R;
import android.microntek.canbus.p000c.C0001b;
import android.microntek.canbus.serializable.Door;
import java.util.Locale;

public class df extends C0001b {
    public df(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 86;
    }

    public void mo35b(byte[] bArr, int i, int i2) {
        switch (bArr[i + 1]) {
            case (byte) -96:
                if (bArr[i + 2] == (byte) 2) {
                    String stringBuilder;
                    int i3 = bArr[i + 3] & 1;
                    int i4 = bArr[i + 4] & 255;
                    String str = "";
                    if (i4 <= 59) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        Locale locale = Locale.US;
                        String str2 = " %d";
                        Object[] objArr = new Object[1];
                        if (i3 == 1) {
                            i4 = 0 - i4;
                        }
                        objArr[0] = Integer.valueOf(i4);
                        stringBuilder = stringBuilder2.append(String.format(locale, str2, objArr)).append(this.el.getString(R.string.c_dan)).toString();
                    } else {
                        stringBuilder = str;
                    }
                    Intent intent = new Intent("com.canbus.temperature");
                    intent.putExtra("temperature", stringBuilder);
                    this.el.sendBroadcast(intent);
                    return;
                }
                return;
            case (byte) -48:
                if (bArr[i + 2] == (byte) 1) {
                    kx(new byte[]{bArr[i + 3]});
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void mo36d() {
    }

    void kx(byte[] bArr) {
        boolean z = true;
        Door door = this.em;
        boolean z2 = (bArr[0] & 1) != 0;
        boolean z3 = (bArr[0] & 2) != 0;
        boolean z4 = (bArr[0] & 4) != 0;
        boolean z5 = (bArr[0] & 8) != 0;
        boolean z6 = (bArr[0] & 16) != 0;
        if ((bArr[0] & 0) == 0) {
            z = false;
        }
        if (door.la(z2, z3, z4, z5, z6, z)) {
            this.ej.of(this.em);
        }
    }
}
