package android.microntek.canbus.log;

import android.view.View;
import android.view.View.OnClickListener;

class C0050c implements OnClickListener {
    final /* synthetic */ CanBusLogActivity ht;

    C0050c(CanBusLogActivity canBusLogActivity) {
        this.ht = canBusLogActivity;
    }

    public void onClick(View view) {
        if (this.ht.ho.getText().equals("Start")) {
            this.ht.hj = false;
            this.ht.ho.setText("Stop");
        } else if (this.ht.ho.getText().equals("Stop")) {
            this.ht.hj = true;
            this.ht.ho.setText("Start");
        }
    }
}
