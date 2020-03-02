package android.support.p003v7.widget;

import android.support.p000v4.content.ContextCompat;
import android.support.p003v7.internal.widget.TintManager;
import android.support.p003v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: android.support.v7.widget.AppCompatImageHelper */
class AppCompatImageHelper {
    private static final int[] VIEW_ATTRS = {16843033};
    private final TintManager mTintManager;
    private final ImageView mView;

    AppCompatImageHelper(ImageView view, TintManager tintManager) {
        this.mView = view;
        this.mTintManager = tintManager;
    }

    /* access modifiers changed from: 0000 */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attrs, VIEW_ATTRS, defStyleAttr, 0);
        try {
            if (a.hasValue(0)) {
                this.mView.setImageDrawable(a.getDrawable(0));
            }
        } finally {
            a.recycle();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setImageResource(int resId) {
        this.mView.setImageDrawable(this.mTintManager != null ? this.mTintManager.getDrawable(resId) : ContextCompat.getDrawable(this.mView.getContext(), resId));
    }
}
