package android.support.p003v7.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p003v7.app.ActionBarDrawerToggle.Delegate;
import android.support.p003v7.appcompat.C0225R;
import android.support.p003v7.internal.view.SupportMenuInflater;
import android.support.p003v7.internal.view.WindowCallbackWrapper;
import android.support.p003v7.internal.view.menu.MenuBuilder;
import android.support.p003v7.internal.widget.TintTypedArray;
import android.support.p003v7.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.Window.Callback;

/* renamed from: android.support.v7.app.AppCompatDelegateImplBase */
abstract class AppCompatDelegateImplBase extends AppCompatDelegate {
    ActionBar mActionBar;
    final AppCompatCallback mAppCompatCallback;
    final Callback mAppCompatWindowCallback;
    final Context mContext;
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    MenuInflater mMenuInflater;
    final Callback mOriginalWindowCallback = this.mWindow.getCallback();
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    boolean mThemeRead;
    private CharSequence mTitle;
    final Window mWindow;
    boolean mWindowNoTitle;

    /* renamed from: android.support.v7.app.AppCompatDelegateImplBase$ActionBarDrawableToggleImpl */
    private class ActionBarDrawableToggleImpl implements Delegate {
        private ActionBarDrawableToggleImpl() {
        }

        public Drawable getThemeUpIndicator() {
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[]{C0225R.attr.homeAsUpIndicator});
            Drawable result = a.getDrawable(0);
            a.recycle();
            return result;
        }

        public Context getActionBarThemedContext() {
            return AppCompatDelegateImplBase.this.getActionBarThemedContext();
        }

        public boolean isNavigationVisible() {
            ActionBar ab = AppCompatDelegateImplBase.this.getSupportActionBar();
            return (ab == null || (ab.getDisplayOptions() & 4) == 0) ? false : true;
        }

        public void setActionBarUpIndicator(Drawable upDrawable, int contentDescRes) {
            ActionBar ab = AppCompatDelegateImplBase.this.getSupportActionBar();
            if (ab != null) {
                ab.setHomeAsUpIndicator(upDrawable);
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }

        public void setActionBarDescription(int contentDescRes) {
            ActionBar ab = AppCompatDelegateImplBase.this.getSupportActionBar();
            if (ab != null) {
                ab.setHomeActionContentDescription(contentDescRes);
            }
        }
    }

    /* renamed from: android.support.v7.app.AppCompatDelegateImplBase$AppCompatWindowCallbackBase */
    class AppCompatWindowCallbackBase extends WindowCallbackWrapper {
        AppCompatWindowCallbackBase(Callback callback) {
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent event) {
            return AppCompatDelegateImplBase.this.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent event) {
            return super.dispatchKeyShortcutEvent(event) || AppCompatDelegateImplBase.this.onKeyShortcut(event.getKeyCode(), event);
        }

        public boolean onCreatePanelMenu(int featureId, Menu menu) {
            if (featureId != 0 || (menu instanceof MenuBuilder)) {
                return super.onCreatePanelMenu(featureId, menu);
            }
            return false;
        }

        public void onContentChanged() {
        }

        public boolean onPreparePanel(int featureId, View view, Menu menu) {
            MenuBuilder mb = menu instanceof MenuBuilder ? (MenuBuilder) menu : null;
            if (featureId == 0 && mb == null) {
                return false;
            }
            if (mb != null) {
                mb.setOverrideVisibleItems(true);
            }
            boolean onPreparePanel = super.onPreparePanel(featureId, view, menu);
            if (mb == null) {
                return onPreparePanel;
            }
            mb.setOverrideVisibleItems(false);
            return onPreparePanel;
        }

        public boolean onMenuOpened(int featureId, Menu menu) {
            super.onMenuOpened(featureId, menu);
            AppCompatDelegateImplBase.this.onMenuOpened(featureId, menu);
            return true;
        }

        public void onPanelClosed(int featureId, Menu menu) {
            super.onPanelClosed(featureId, menu);
            AppCompatDelegateImplBase.this.onPanelClosed(featureId, menu);
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract boolean dispatchKeyEvent(KeyEvent keyEvent);

    /* access modifiers changed from: 0000 */
    public abstract void initWindowDecorActionBar();

    /* access modifiers changed from: 0000 */
    public abstract boolean onKeyShortcut(int i, KeyEvent keyEvent);

    /* access modifiers changed from: 0000 */
    public abstract boolean onMenuOpened(int i, Menu menu);

    /* access modifiers changed from: 0000 */
    public abstract void onPanelClosed(int i, Menu menu);

    /* access modifiers changed from: 0000 */
    public abstract void onTitleChanged(CharSequence charSequence);

    /* access modifiers changed from: 0000 */
    public abstract ActionMode startSupportActionModeFromWindow(ActionMode.Callback callback);

    AppCompatDelegateImplBase(Context context, Window window, AppCompatCallback callback) {
        this.mContext = context;
        this.mWindow = window;
        this.mAppCompatCallback = callback;
        if (this.mOriginalWindowCallback instanceof AppCompatWindowCallbackBase) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        this.mAppCompatWindowCallback = wrapWindowCallback(this.mOriginalWindowCallback);
        this.mWindow.setCallback(this.mAppCompatWindowCallback);
    }

    /* access modifiers changed from: 0000 */
    public Callback wrapWindowCallback(Callback callback) {
        return new AppCompatWindowCallbackBase(callback);
    }

    public ActionBar getSupportActionBar() {
        initWindowDecorActionBar();
        return this.mActionBar;
    }

    /* access modifiers changed from: 0000 */
    public final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            initWindowDecorActionBar();
            this.mMenuInflater = new SupportMenuInflater(this.mActionBar != null ? this.mActionBar.getThemedContext() : this.mContext);
        }
        return this.mMenuInflater;
    }

    public final Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl();
    }

    /* access modifiers changed from: 0000 */
    public final Context getActionBarThemedContext() {
        Context context = null;
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            context = ab.getThemedContext();
        }
        if (context == null) {
            return this.mContext;
        }
        return context;
    }

    public final void onDestroy() {
        this.mIsDestroyed = true;
    }

    public void setHandleNativeActionModesEnabled(boolean enabled) {
    }

    public boolean isHandleNativeActionModesEnabled() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    /* access modifiers changed from: 0000 */
    public final Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    public final void setTitle(CharSequence title) {
        this.mTitle = title;
        onTitleChanged(title);
    }

    /* access modifiers changed from: 0000 */
    public final CharSequence getTitle() {
        if (this.mOriginalWindowCallback instanceof Activity) {
            return ((Activity) this.mOriginalWindowCallback).getTitle();
        }
        return this.mTitle;
    }
}
