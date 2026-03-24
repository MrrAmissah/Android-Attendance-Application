package android.support.v4.os;

import android.os.Parcel;
@Deprecated
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public interface ParcelableCompatCreatorCallbacks<T> {
    T createFromParcel(Parcel parcel, ClassLoader classLoader);

    T[] newArray(int i);
}
