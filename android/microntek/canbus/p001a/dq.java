package android.microntek.canbus.p001a;

class dq implements Runnable {
    final /* synthetic */ bx dl;
    final /* synthetic */ byte[] dm;

    dq(bx bxVar, byte[] bArr) {
        this.dl = bxVar;
        this.dm = bArr;
    }

    public void run() {
        if (this.dl.bw) {
            while (this.dl.bw) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                this.dl.ej.ob((byte) 32, this.dm, 2);
            }
            return;
        }
        synchronized (this.dl.by) {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(100);
                } catch (Exception e2) {
                }
                this.dl.ej.ob((byte) 32, this.dm, 2);
            }
        }
    }
}
