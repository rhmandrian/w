package android.support.p000v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.PopupWindow;

/* renamed from: android.support.v4.widget.PopupWindowCompat */
public class PopupWindowCompat {
    static final PopupWindowImpl IMPL;

    /* renamed from: android.support.v4.widget.PopupWindowCompat$Api21PopupWindowImpl */
    static class Api21PopupWindowImpl extends KitKatPopupWindowImpl {
        Api21PopupWindowImpl() {
        }

        public void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
            PopupWindowCompatApi21.setOverlapAnchor(popupWindow, overlapAnchor);
        }

        public boolean getOverlapAnchor(PopupWindow popupWindow) {
            return PopupWindowCompatApi21.getOverlapAnchor(popupWindow);
        }
    }

    /* renamed from: android.support.v4.widget.PopupWindowCompat$Api23PopupWindowImpl */
    static class Api23PopupWindowImpl extends Api21PopupWindowImpl {
        Api23PopupWindowImpl() {
        }

        public void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
            PopupWindowCompatApi23.setOverlapAnchor(popupWindow, overlapAnchor);
        }

        public boolean getOverlapAnchor(PopupWindow popupWindow) {
            return PopupWindowCompatApi23.getOverlapAnchor(popupWindow);
        }

        public void setWindowLayoutType(PopupWindow popupWindow, int layoutType) {
            PopupWindowCompatApi23.setWindowLayoutType(popupWindow, layoutType);
        }

        public int getWindowLayoutType(PopupWindow popupWindow) {
            return PopupWindowCompatApi23.getWindowLayoutType(popupWindow);
        }
    }

    /* renamed from: android.support.v4.widget.PopupWindowCompat$BasePopupWindowImpl */
    static class BasePopupWindowImpl implements PopupWindowImpl {
        BasePopupWindowImpl() {
        }

        public void showAsDropDown(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
            popup.showAsDropDown(anchor, xoff, yoff);
        }

        public void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
        }

        public boolean getOverlapAnchor(PopupWindow popupWindow) {
            return false;
        }

        public void setWindowLayoutType(PopupWindow popupWindow, int layoutType) {
        }

        public int getWindowLayoutType(PopupWindow popupWindow) {
            return 0;
        }
    }

    /* renamed from: android.support.v4.widget.PopupWindowCompat$GingerbreadPopupWindowImpl */
    static class GingerbreadPopupWindowImpl extends BasePopupWindowImpl {
        GingerbreadPopupWindowImpl() {
        }

        public void setWindowLayoutType(PopupWindow popupWindow, int layoutType) {
            PopupWindowCompatGingerbread.setWindowLayoutType(popupWindow, layoutType);
        }

        public int getWindowLayoutType(PopupWindow popupWindow) {
            return PopupWindowCompatGingerbread.getWindowLayoutType(popupWindow);
        }
    }

    /* renamed from: android.support.v4.widget.PopupWindowCompat$KitKatPopupWindowImpl */
    static class KitKatPopupWindowImpl extends GingerbreadPopupWindowImpl {
        KitKatPopupWindowImpl() {
        }

        public void showAsDropDown(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
            PopupWindowCompatKitKat.showAsDropDown(popup, anchor, xoff, yoff, gravity);
        }
    }

    /* renamed from: android.support.v4.widget.PopupWindowCompat$PopupWindowImpl */
    interface PopupWindowImpl {
        boolean getOverlapAnchor(PopupWindow popupWindow);

        int getWindowLayoutType(PopupWindow popupWindow);

        void setOverlapAnchor(PopupWindow popupWindow, boolean z);

        void setWindowLayoutType(PopupWindow popupWindow, int i);

        void showAsDropDown(PopupWindow popupWindow, View view, int i, int i2, int i3);
    }

    static {
        int version = VERSION.SDK_INT;
        if (version >= 23) {
            IMPL = new Api23PopupWindowImpl();
        } else if (version >= 21) {
            IMPL = new Api21PopupWindowImpl();
        } else if (version >= 19) {
            IMPL = new KitKatPopupWindowImpl();
        } else if (version >= 9) {
            IMPL = new GingerbreadPopupWindowImpl();
        } else {
            IMPL = new BasePopupWindowImpl();
        }
    }

    private PopupWindowCompat() {
    }

    public static void showAsDropDown(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
        IMPL.showAsDropDown(popup, anchor, xoff, yoff, gravity);
    }

    public static void setOverlapAnchor(PopupWindow popupWindow, boolean overlapAnchor) {
        IMPL.setOverlapAnchor(popupWindow, overlapAnchor);
    }

    public static boolean getOverlapAnchor(PopupWindow popupWindow) {
        return IMPL.getOverlapAnchor(popupWindow);
    }

    public static void setWindowLayoutType(PopupWindow popupWindow, int layoutType) {
        IMPL.setWindowLayoutType(popupWindow, layoutType);
    }

    public static int getWindowLayoutType(PopupWindow popupWindow) {
        return IMPL.getWindowLayoutType(popupWindow);
    }
}
