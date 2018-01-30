package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;

public class da extends ai {
    public da(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 35;
    }
}
