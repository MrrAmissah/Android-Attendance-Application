package android.support.v4.view;

import android.view.VelocityTracker;
@Deprecated
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public final class VelocityTrackerCompat {
    @Deprecated
    public static float getXVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getXVelocity(pointerId);
    }

    @Deprecated
    public static float getYVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getYVelocity(pointerId);
    }

    private VelocityTrackerCompat() {
    }
}
