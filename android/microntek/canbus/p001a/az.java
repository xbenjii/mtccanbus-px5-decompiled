package android.microntek.canbus.p001a;

import android.content.Context;
import android.microntek.canbus.CanBusServer;

public class az extends C0003u {
    public az(CanBusServer canBusServer, Context context) {
        super(canBusServer, context);
        this.eh = 40;
    }
}
