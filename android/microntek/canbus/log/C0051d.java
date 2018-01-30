package android.microntek.canbus.log;

import android.view.View;
import android.view.View.OnClickListener;

class C0051d implements OnClickListener {
    final /* synthetic */ CanBusLogActivity hu;

    C0051d(CanBusLogActivity canBusLogActivity) {
        this.hu = canBusLogActivity;
    }

    public void onClick(View view) {
        if (this.hu.hp != null && !this.hu.hp.equals("")) {
            new Thread(new C0052e(this)).run();
        }
    }
}
