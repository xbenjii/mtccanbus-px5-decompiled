package android.microntek.canbus.p001a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class dp implements OnClickListener {
    final /* synthetic */ bm dk;

    dp(bm bmVar) {
        this.dk = bmVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        this.dk.bc.stop();
        this.dk.fz();
    }
}
