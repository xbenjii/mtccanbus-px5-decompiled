package android.microntek.canbus.p001a;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class dt implements OnClickListener {
    final /* synthetic */ cj dp;

    dt(cj cjVar) {
        this.dp = cjVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.dp.ck.stop();
        this.dp.jb();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.microntek.controlinfo", "com.microntek.controlinfo.canbus80tpmsinfo"));
        intent.setFlags(268435456);
        this.dp.ej.startActivity(intent);
    }
}
