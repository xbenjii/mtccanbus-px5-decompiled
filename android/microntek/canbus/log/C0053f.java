package android.microntek.canbus.log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class C0053f implements OnItemSelectedListener {
    final /* synthetic */ CanBusLogActivity hw;

    C0053f(CanBusLogActivity canBusLogActivity) {
        this.hw = canBusLogActivity;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.hw.hl = i;
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
