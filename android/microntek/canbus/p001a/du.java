package android.microntek.canbus.p001a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class du implements OnClickListener {
    final /* synthetic */ cj dq;

    du(cj cjVar) {
        this.dq = cjVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        this.dq.ck.stop();
        this.dq.jb();
    }
}
