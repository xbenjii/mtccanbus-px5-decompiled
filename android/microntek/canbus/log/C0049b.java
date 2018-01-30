package android.microntek.canbus.log;

import android.view.View;
import android.view.View.OnClickListener;

class C0049b implements OnClickListener {
    final /* synthetic */ CanBusLogActivity hs;

    C0049b(CanBusLogActivity canBusLogActivity) {
        this.hs = canBusLogActivity;
    }

    public void onClick(View view) {
        this.hs.hp = "";
        this.hs.hh.setText(this.hs.hp);
    }
}
