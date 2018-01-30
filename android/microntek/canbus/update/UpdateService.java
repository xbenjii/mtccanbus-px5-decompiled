package android.microntek.canbus.update;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.microntek.canbus.BaseApplication;
import android.os.Environment;
import android.os.IBinder;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UpdateService extends Service {
    private static final String[] hy = new String[]{"/mnt/sdcard", "/mnt/external_sd", "/mnt/external_sd2", "/mnt/usb_storage", "/mnt/usb_storage2", "/mnt/usb_storage3", "/mnt/usb_storage4"};
    private BroadcastReceiver hz = new C0068e(this);
    private Context ia;
    private List<C0066b> ib;
    private C0064c ic;
    private Toast id = null;
    private String ie = "CANBUS.UPDE";

    private String no() {
        np();
        for (int i = 0; i < this.ib.size(); i++) {
            String str = ((C0066b) this.ib.get(i)).ig + "/" + this.ie;
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                return str;
            }
        }
        return null;
    }

    private void np() {
        StorageManager storageManager = (StorageManager) getSystemService(StorageManager.class);
        this.ib = new ArrayList();
        this.ib.add(new C0066b(this, Environment.getExternalStorageDirectory().getPath().toString(), "FLASH", "flash", null));
        for (VolumeInfo volumeInfo : storageManager.getVolumes()) {
            if (volumeInfo.getType() == 0 && volumeInfo.isMountedReadable()) {
                DiskInfo disk = volumeInfo.getDisk();
                if (disk.isSd()) {
                    this.ib.add(new C0066b(this, volumeInfo.getPath().getPath(), storageManager.getBestVolumeDescription(volumeInfo), "sd", volumeInfo.getId()));
                } else if (disk.isUsb()) {
                    this.ib.add(new C0066b(this, volumeInfo.getPath().getPath(), storageManager.getBestVolumeDescription(volumeInfo), "usb", volumeInfo.getId()));
                }
            }
        }
    }

    public void nn(String str) {
        if (this.id != null) {
            this.id.cancel();
        }
        this.id = Toast.makeText(this.ia, str, 0);
        this.id.show();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.ia = getApplicationContext();
        BaseApplication.py().qm(true);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.canbus.receiver");
        registerReceiver(this.hz, intentFilter);
    }

    public void onDestroy() {
        unregisterReceiver(this.hz);
        BaseApplication.py().qm(false);
        super.onDestroy();
    }

    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        String stringExtra = intent.getStringExtra("customer");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "s";
        }
        String no = no();
        if (this.ic == null) {
            if (stringExtra.equals("s")) {
                this.ic = new C0067d(this);
            } else if (stringExtra.equals("r")) {
                this.ic = new C0065a(this);
            }
        }
        if (no != null) {
            this.ic.ns(no);
        } else {
            nn("No File !!!");
        }
        Log.i("CanBusUpdateService", "canbusupdate>> customer>>" + stringExtra + ">>path>>" + no);
    }
}
