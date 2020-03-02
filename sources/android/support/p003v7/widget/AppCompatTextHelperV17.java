package android.support.p003v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.p003v7.internal.widget.TintInfo;
import android.support.p003v7.internal.widget.TintManager;
import android.util.AttributeSet;
import android.widget.TextView;

/* renamed from: android.support.v7.widget.AppCompatTextHelperV17 */
class AppCompatTextHelperV17 extends AppCompatTextHelper {
    private static final int[] VIEW_ATTRS_v17 = {16843666, 16843667};
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableStartTint;

    AppCompatTextHelperV17(TextView view) {
        super(view);
    }

    /* access modifiers changed from: 0000 */
    public void loadFromAttributes(AttributeSet attrs, int defStyleAttr) {
        super.loadFromAttributes(attrs, defStyleAttr);
        Context context = this.mView.getContext();
        TintManager tintManager = TintManager.get(context);
        TypedArray a = context.obtainStyledAttributes(attrs, VIEW_ATTRS_v17, defStyleAttr, 0);
        if (a.hasValue(0)) {
            this.mDrawableStartTint = new TintInfo();
            this.mDrawableStartTint.mHasTintList = true;
            this.mDrawableStartTint.mTintList = tintManager.getTintList(a.getResourceId(0, 0));
        }
        if (a.hasValue(1)) {
            this.mDrawableEndTint = new TintInfo();
            this.mDrawableEndTint.mHasTintList = true;
            this.mDrawableEndTint.mTintList = tintManager.getTintList(a.getResourceId(1, 0));
        }
        a.recycle();
    }

    /* access modifiers changed from: 0000 */
    public void applyCompoundDrawablesTints() {
        super.applyCompoundDrawablesTints();
        if (this.mDrawableStartTint != null || this.mDrawableEndTint != null) {
            Drawable[] compoundDrawables = this.mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableStartTint);
            applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableEndTint);
        }
    }
}
