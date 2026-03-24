package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;
import android.support.annotation.RequiresApi;
import android.support.v4.media.MediaDescriptionCompatApi21;
import android.support.v4.view.MotionEventCompat;
@RequiresApi(MotionEventCompat.AXIS_BRAKE)
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
class MediaDescriptionCompatApi23 extends MediaDescriptionCompatApi21 {
    MediaDescriptionCompatApi23() {
    }

    public static Uri getMediaUri(Object descriptionObj) {
        return ((MediaDescription) descriptionObj).getMediaUri();
    }

    /* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
    static class Builder extends MediaDescriptionCompatApi21.Builder {
        Builder() {
        }

        public static void setMediaUri(Object builderObj, Uri mediaUri) {
            ((MediaDescription.Builder) builderObj).setMediaUri(mediaUri);
        }
    }
}
