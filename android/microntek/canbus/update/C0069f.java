package android.microntek.canbus.update;

class C0069f implements Runnable {
    final /* synthetic */ C0067d it;
    final /* synthetic */ byte iu;
    final /* synthetic */ byte[] iv;
    final /* synthetic */ int iw;

    C0069f(C0067d c0067d, byte b, byte[] bArr, int i) {
        this.it = c0067d;
        this.iu = b;
        this.iv = bArr;
        this.iw = i;
    }

    public void run() {
        synchronized (this.it.ik) {
            this.it.nt(this.iu, this.iv, this.iw);
            this.it.nv(10);
        }
    }
}
