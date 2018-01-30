package android.microntek.canbus.log;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.microntek.canbus.BaseApplication;
import android.microntek.canbus.R;
import android.microntek.canbus.p002b.C0035b;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CanBusLogActivity extends Activity {
    private BroadcastReceiver hg = new C0048a(this);
    private TextView hh;
    private Button hi;
    private boolean hj = true;
    private boolean hk = false;
    private int hl = 0;
    private Button hm;
    private Spinner hn;
    private Button ho;
    private String hp = "";
    private Button hq;

    private void na(byte[] bArr) {
        if (this.hl == 0 || this.hl == (bArr[1] & 255)) {
            String str = "";
            for (int i = 0; i < bArr.length; i++) {
                str = str + String.format(Locale.US, "%02x ", new Object[]{Byte.valueOf(bArr[i])}) + "\t\t";
            }
            if (this.hj) {
                this.hp += nd() + str + "\n";
                this.hh.setText(this.hp);
            }
            if ((bArr[1] & 255) == 255 && this.hk) {
                this.hk = false;
                Toast.makeText(this, "TX RX Test OK !", 1000).show();
            }
        }
    }

    private List<String> nb() {
        List<String> arrayList = new ArrayList();
        arrayList.add("NO");
        for (int i = 1; i < 256; i++) {
            arrayList.add(String.format(Locale.US, "%02x ", new Object[]{Integer.valueOf(i)}));
        }
        return arrayList;
    }

    private String nc() {
        return "/mnt/sdcard/canbus " + BaseApplication.py().qg("cfg_canbus=") + " " + new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date()) + ".log";
    }

    private String nd() {
        return new SimpleDateFormat("hh:mm:ss:SSS").format(new Date()) + ": \t";
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.canbus_log);
        this.hh = (TextView) findViewById(R.id.canbuslog);
        this.ho = (Button) findViewById(R.id.canbusstop);
        this.hi = (Button) findViewById(R.id.canbusclear);
        this.hm = (Button) findViewById(R.id.savelog);
        this.hn = (Spinner) findViewById(R.id.select);
        this.hq = (Button) findViewById(R.id.txrx);
        this.ho.setText("Start");
        this.hi.setText("Clear");
        this.hm.setText("Save");
        this.hq.setText("TX RX");
        this.hi.setOnClickListener(new C0049b(this));
        this.ho.setOnClickListener(new C0050c(this));
        this.hm.setOnClickListener(new C0051d(this));
        SpinnerAdapter arrayAdapter = new ArrayAdapter(this, 17367048, nb());
        arrayAdapter.setDropDownViewResource(17367049);
        this.hn.setAdapter(arrayAdapter);
        this.hn.setSelection(0);
        this.hn.setOnItemSelectedListener(new C0053f(this));
        this.hq.setOnClickListener(new C0054g(this));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.canbus.receiver");
        registerReceiver(this.hg, intentFilter);
        C0035b.ea = true;
    }

    protected void onDestroy() {
        unregisterReceiver(this.hg);
        C0035b.ea = false;
        super.onDestroy();
    }
}
