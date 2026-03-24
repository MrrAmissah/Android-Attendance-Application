package android.support.v4.content;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public final class SharedPreferencesCompat {

    /* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
    public static final class EditorCompat {
        private static EditorCompat sInstance;
        private final Helper mHelper = new Helper();

        /* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
        private static class Helper {
            Helper() {
            }

            public void apply(@NonNull SharedPreferences.Editor editor) {
                try {
                    editor.apply();
                } catch (AbstractMethodError e) {
                    editor.commit();
                }
            }
        }

        private EditorCompat() {
        }

        public static EditorCompat getInstance() {
            if (sInstance == null) {
                sInstance = new EditorCompat();
            }
            return sInstance;
        }

        public void apply(@NonNull SharedPreferences.Editor editor) {
            this.mHelper.apply(editor);
        }
    }

    private SharedPreferencesCompat() {
    }
}
