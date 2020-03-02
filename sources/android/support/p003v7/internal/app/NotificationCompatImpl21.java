package android.support.p003v7.internal.app;

import android.app.Notification.MediaStyle;
import android.media.session.MediaSession.Token;
import android.support.p000v4.app.NotificationBuilderWithBuilderAccessor;

/* renamed from: android.support.v7.internal.app.NotificationCompatImpl21 */
public class NotificationCompatImpl21 {
    public static void addMediaStyle(NotificationBuilderWithBuilderAccessor b, int[] actionsToShowInCompact, Object token) {
        MediaStyle style = new MediaStyle(b.getBuilder());
        if (actionsToShowInCompact != null) {
            style.setShowActionsInCompactView(actionsToShowInCompact);
        }
        if (token != null) {
            style.setMediaSession((Token) token);
        }
    }
}
