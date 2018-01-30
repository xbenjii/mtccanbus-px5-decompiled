package android.microntek.canbus.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.microntek.canbus.serializable.Radar;
import android.util.AttributeSet;
import android.view.View;

public class Cicleview extends View {
    Context du;
    private Paint dv;
    private Radar dw = null;
    private float dx = 1.0f;

    public Cicleview(Context context) {
        super(context);
        this.du = context;
        ll();
    }

    public Cicleview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.du = context;
        ll();
    }

    public Cicleview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.du = context;
        ll();
    }

    private void lj(Canvas canvas, float f, float f2, int i, int i2) {
        float f3 = 66.0f * this.dx;
        float f4 = 66.0f * this.dx;
        float f5 = 60.0f * this.dx;
        float f6 = (f5 - ((float) ((i2 - 1) * 2))) / ((float) i2);
        this.dv.setStrokeWidth(f5);
        this.dv.setColor(-1606665156);
        canvas.drawArc(new RectF(-f3, -f4, f3, f4), f, f2, false, this.dv);
        if (i > 0) {
            if (i > i2) {
                i = i2;
            }
            this.dv.setStrokeWidth(f6);
            float f7 = (f3 - (f5 / 2.0f)) + (f6 / 2.0f);
            int i3 = 0;
            f4 = (f6 / 2.0f) + (f4 - (f5 / 2.0f));
            f5 = f7;
            while (i3 < i) {
                if (i3 < i2 / 3) {
                    this.dv.setColor(-65536);
                } else if (i3 < (i2 * 2) / 3) {
                    this.dv.setColor(-256);
                } else {
                    this.dv.setColor(-16711936);
                }
                canvas.drawArc(new RectF(-f5, -f4, f5, f4), f, f2, false, this.dv);
                i3++;
                f4 += 2.0f + f6;
                f5 += 2.0f + f6;
            }
        }
    }

    private void lk(Canvas canvas, float f, float f2, int i, int i2, int i3) {
        float f3 = 66.0f * this.dx;
        float f4 = 66.0f * this.dx;
        float f5 = 60.0f * this.dx;
        float f6 = (f5 - ((float) 18)) / 10.0f;
        int i4 = i <= 0 ? 0 : i;
        int i5 = i4 > i2 ? 0 : i4;
        this.dv.setStrokeWidth(f6);
        int i6 = 0;
        float f7 = (f6 / 2.0f) + (f4 - (f5 / 2.0f));
        float f8 = (f3 - (f5 / 2.0f)) + (f6 / 2.0f);
        while (i6 < i2) {
            if (i6 < i5) {
                this.dv.setColor(i3);
            } else {
                this.dv.setColor(-1606665156);
            }
            canvas.drawArc(new RectF(-f8, -f7, f8, f7), f, f2, false, this.dv);
            i6++;
            f7 += 2.0f + f6;
            f8 += 2.0f + f6;
        }
    }

    private void ll() {
        this.dv = new Paint();
        this.dv.setAntiAlias(true);
        this.dv.setStyle(Style.STROKE);
        if (this.du.getResources().getDisplayMetrics().widthPixels == 1024) {
            this.dx = 1.25f;
        }
    }

    public void li(Radar radar) {
        this.dw = radar;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.dw != null) {
            if (this.dw.mode == 0) {
                if (this.dw.front_cnt != 0) {
                    canvas.save();
                    canvas.translate(this.dx * 80.0f, this.dx * 106.0f);
                    if (this.dw.front_cnt == 4) {
                        lj(canvas, 227.0f, 20.0f, this.dw.f1, this.dw.max);
                        lj(canvas, 249.0f, 20.0f, this.dw.f2, this.dw.max);
                        lj(canvas, 271.0f, 20.0f, this.dw.f3, this.dw.max);
                        lj(canvas, 293.0f, 20.0f, this.dw.f4, this.dw.max);
                    } else if (this.dw.front_cnt == 3) {
                        lj(canvas, 227.0f, 26.0f, this.dw.f1, this.dw.max);
                        lj(canvas, 257.0f, 26.0f, this.dw.f2, this.dw.max);
                        lj(canvas, 287.0f, 26.0f, this.dw.f3, this.dw.max);
                    } else if (this.dw.front_cnt == 2) {
                        lj(canvas, 227.0f, 20.0f, this.dw.f1, this.dw.max);
                        lj(canvas, 293.0f, 20.0f, this.dw.f2, this.dw.max);
                    } else if (this.dw.front_cnt == 6) {
                        lj(canvas, 221.0f, 15.0f, this.dw.f1, this.dw.max);
                        lj(canvas, 238.0f, 15.0f, this.dw.f2, this.dw.max);
                        lj(canvas, 255.0f, 15.0f, this.dw.f3, this.dw.max);
                        lj(canvas, 272.0f, 15.0f, this.dw.f4, this.dw.max);
                        lj(canvas, 289.0f, 15.0f, this.dw.f5, this.dw.max);
                        lj(canvas, 306.0f, 15.0f, this.dw.f6, this.dw.max);
                    }
                    canvas.restore();
                }
                if (this.dw.back_cnt != 0) {
                    canvas.save();
                    canvas.translate(this.dx * 80.0f, this.dx * 256.0f);
                    if (this.dw.back_cnt == 4) {
                        lj(canvas, 113.0f, 20.0f, this.dw.b1, this.dw.max);
                        lj(canvas, 91.0f, 20.0f, this.dw.b2, this.dw.max);
                        lj(canvas, 69.0f, 20.0f, this.dw.b3, this.dw.max);
                        lj(canvas, 47.0f, 20.0f, this.dw.b4, this.dw.max);
                    } else if (this.dw.back_cnt == 3) {
                        lj(canvas, 107.0f, 26.0f, this.dw.b1, this.dw.max);
                        lj(canvas, 77.0f, 26.0f, this.dw.b2, this.dw.max);
                        lj(canvas, 47.0f, 26.0f, this.dw.b3, this.dw.max);
                    } else if (this.dw.back_cnt == 2) {
                        lj(canvas, 107.0f, 26.0f, this.dw.b1, this.dw.max);
                        lj(canvas, 47.0f, 26.0f, this.dw.b2, this.dw.max);
                    }
                    canvas.restore();
                }
            } else if (this.dw.mode == 1) {
                if (this.dw.front_cnt != 0) {
                    canvas.save();
                    canvas.translate(this.dx * 80.0f, this.dx * 106.0f);
                    if (this.dw.front_cnt == 4) {
                        if (!(this.dw.fc1 == 0 || this.dw.fc2 == 0 || this.dw.fc3 == 0 || this.dw.fc4 == 0)) {
                            lk(canvas, 227.0f, 20.0f, this.dw.f1, this.dw.fmax1, this.dw.fc1);
                            lk(canvas, 249.0f, 20.0f, this.dw.f2, this.dw.fmax2, this.dw.fc2);
                            lk(canvas, 271.0f, 20.0f, this.dw.f3, this.dw.fmax3, this.dw.fc3);
                            lk(canvas, 293.0f, 20.0f, this.dw.f4, this.dw.fmax4, this.dw.fc4);
                        }
                    } else if (!(this.dw.front_cnt != 3 || this.dw.fc1 == 0 || this.dw.fc2 == 0 || this.dw.fc3 == 0)) {
                        lk(canvas, 227.0f, 26.0f, this.dw.f1, this.dw.fmax1, this.dw.fc1);
                        lk(canvas, 257.0f, 26.0f, this.dw.f2, this.dw.fmax2, this.dw.fc2);
                        lk(canvas, 287.0f, 26.0f, this.dw.f3, this.dw.fmax3, this.dw.fc3);
                    }
                    canvas.restore();
                }
                if (this.dw.back_cnt != 0) {
                    canvas.save();
                    canvas.translate(this.dx * 80.0f, this.dx * 256.0f);
                    if (this.dw.back_cnt == 4) {
                        if (!(this.dw.bc1 == 0 || this.dw.bc2 == 0 || this.dw.bc3 == 0 || this.dw.bc4 == 0)) {
                            lk(canvas, 113.0f, 20.0f, this.dw.b1, this.dw.bmax1, this.dw.bc1);
                            lk(canvas, 91.0f, 20.0f, this.dw.b2, this.dw.bmax2, this.dw.bc2);
                            lk(canvas, 69.0f, 20.0f, this.dw.b3, this.dw.bmax3, this.dw.bc3);
                            lk(canvas, 47.0f, 20.0f, this.dw.b4, this.dw.bmax4, this.dw.bc4);
                        }
                    } else if (!(this.dw.back_cnt != 3 || this.dw.bc1 == 0 || this.dw.bc2 == 0 || this.dw.bc3 == 0)) {
                        lk(canvas, 107.0f, 26.0f, this.dw.b1, this.dw.bmax1, this.dw.bc1);
                        lk(canvas, 77.0f, 26.0f, this.dw.b2, this.dw.bmax2, this.dw.bc2);
                        lk(canvas, 47.0f, 26.0f, this.dw.b3, this.dw.bmax3, this.dw.bc3);
                    }
                    canvas.restore();
                }
            }
        }
    }
}
