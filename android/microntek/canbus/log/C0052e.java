package android.microntek.canbus.log;

import java.io.FileOutputStream;

class C0052e implements Runnable {
    final /* synthetic */ C0051d hv;

    C0052e(C0051d c0051d) {
        this.hv = c0051d;
    }

    public void run() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.hv.hu.nc());
            fileOutputStream.write(this.hv.hu.hp.toString().getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
        }
    }
}
