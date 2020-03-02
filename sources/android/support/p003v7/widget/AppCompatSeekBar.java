package android.support.p003v7.widget;

import android.content.Context;
import android.support.p003v7.appcompat.C0225R;
import android.support.p003v7.internal.widget.TintManager;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* renamed from: android.support.v7.widget.AppCompatSeekBar */
public class AppCompatSeekBar extends SeekBar {
    private AppCompatSeekBarHelper mAppCompatSeekBarHelper;
    private TintManager mTintManager;

    public AppCompatSeekBar(Context context) {
        this(context, null);
    }

    public AppCompatSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, C0225R.attr.seekBarStyle);
    }

    public AppCompatSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTintManager = TintManager.get(context);
        this.mAppCompatSeekBarHelper = new AppCompatSeekBarHelper(this, this.mTintManager);
        this.mAppCompatSeekBarHelper.loadFromAttributes(attrs, defStyleAttr);
    }
}
