package android.microntek.canbus.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.microntek.canbus.R$styleable;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MyButton extends FrameLayout {
    ImageView dy;
    ImageView dz;

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attributeSet) {
        LayoutParams layoutParams;
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MyButton);
        int resourceId = obtainStyledAttributes.getResourceId(2, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 10);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, 10);
        int resourceId2 = obtainStyledAttributes.getResourceId(4, 0);
        if (resourceId2 != 0) {
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(5, 0);
            int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(6, 0);
            layoutParams = (dimensionPixelSize3 == 0 || dimensionPixelSize4 == 0) ? new FrameLayout.LayoutParams(-1, -1, 17) : new FrameLayout.LayoutParams(dimensionPixelSize3, dimensionPixelSize4, 17);
            this.dz = new ImageView(context);
            this.dz.setImageResource(resourceId2);
            this.dz.setScaleType(ScaleType.FIT_XY);
            this.dz.setDuplicateParentStateEnabled(true);
            this.dz.setLayoutParams(layoutParams);
            addView(this.dz);
        }
        layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize2, 17);
        this.dy = new ImageView(context);
        this.dy.setImageResource(resourceId);
        this.dy.setScaleType(ScaleType.FIT_XY);
        this.dy.setDuplicateParentStateEnabled(true);
        this.dy.setLayoutParams(layoutParams);
        addView(this.dy);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            this.dy.setAlpha(255);
        } else {
            this.dy.setAlpha(50);
        }
    }
}
