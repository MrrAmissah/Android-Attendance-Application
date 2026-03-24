package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
@RequiresApi(MotionEventCompat.AXIS_WHEEL)
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
class ParceledListSliceAdapterApi21 {
    private static Constructor sConstructor;

    ParceledListSliceAdapterApi21() {
    }

    static {
        try {
            Class theClass = Class.forName("android.content.pm.ParceledListSlice");
            sConstructor = theClass.getConstructor(List.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    static Object newInstance(List<MediaBrowser.MediaItem> itemList) {
        try {
            Object result = sConstructor.newInstance(itemList);
            return result;
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }
}
