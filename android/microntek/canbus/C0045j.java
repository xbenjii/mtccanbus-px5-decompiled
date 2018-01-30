package android.microntek.canbus;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class C0045j implements OnClickListener {
    final /* synthetic */ C0044i oz;
    final /* synthetic */ Message pa;

    C0045j(C0044i c0044i, Message message) {
        this.oz = c0044i;
        this.pa = message;
    }

    public void onClick(View view) {
        this.oz.oy.qv();
        this.oz.oy.lw.sendMessageDelayed(this.pa, 100);
    }
}
