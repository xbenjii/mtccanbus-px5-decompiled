package android.microntek.canbus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {
    private StaticLayout ds;
    private TextPaint dt;

    public MyTextView(Context context) {
        super(context);
        lg();
    }

    public MyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        lg();
    }

    public MyTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        lg();
    }

    private void lg() {
        this.dt = new TextPaint(1);
        this.dt.setTextSize(getTextSize());
        this.dt.setColor(getCurrentTextColor());
        this.ds = new StaticLayout(getText(), this.dt, getWidth(), Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }

    public void lh(String str) {
        this.ds = new StaticLayout(str, this.dt, getWidth(), Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        this.ds.draw(canvas);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        lg();
    }
}
