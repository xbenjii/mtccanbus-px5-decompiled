package android.microntek.canbus.p001a;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class C0009do implements OnClickListener {
    final /* synthetic */ bm dj;

    C0009do(bm bmVar) {
        this.dj = bmVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.dj.bc.stop();
        this.dj.fz();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.microntek.controlinfo", "com.microntek.controlinfo.canbus81tpmsinfo"));
        intent.setFlags(268435456);
        this.dj.ej.startActivity(intent);
    }
}
