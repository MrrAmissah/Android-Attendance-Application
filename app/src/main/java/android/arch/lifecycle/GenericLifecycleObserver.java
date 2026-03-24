package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public interface GenericLifecycleObserver extends LifecycleObserver {
    void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event);
}
