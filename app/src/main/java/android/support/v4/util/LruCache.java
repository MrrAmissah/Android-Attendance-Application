package android.support.v4.util;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: C:\Users\princ\Andriod Attendance App\temp\classes.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    public void resize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.maxSize = maxSize;
        }
        trimToSize(maxSize);
    }

    public final V get(K key) {
        V mapValue;
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            try {
                try {
                    V mapValue2 = this.map.get(key);
                    if (mapValue2 != null) {
                        this.hitCount++;
                        return mapValue2;
                    }
                    this.missCount++;
                    V createdValue = create(key);
                    if (createdValue == null) {
                        return null;
                    }
                    synchronized (this) {
                        this.createCount++;
                        mapValue = this.map.put(key, createdValue);
                        if (mapValue != null) {
                            this.map.put(key, mapValue);
                        } else {
                            this.size += safeSizeOf(key, createdValue);
                        }
                    }
                    if (mapValue != null) {
                        entryRemoved(false, key, createdValue, mapValue);
                        return mapValue;
                    }
                    trimToSize(this.maxSize);
                    return createdValue;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            try {
                try {
                    this.putCount++;
                    try {
                        this.size += safeSizeOf(key, value);
                        try {
                            V previous = this.map.put(key, value);
                            if (previous != null) {
                                this.size -= safeSizeOf(key, previous);
                            }
                            if (previous != null) {
                                entryRemoved(false, key, previous, value);
                            }
                            trimToSize(this.maxSize);
                            return previous;
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x007e, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:? -> B:42:0x0085). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void trimToSize(int r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = r0
            r2 = r1
        L3:
            monitor-enter(r6)
            int r3 = r6.size     // Catch: java.lang.Throwable -> L81
            if (r3 < 0) goto L60
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch: java.lang.Throwable -> L81
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L81
            if (r3 == 0) goto L14
            int r3 = r6.size     // Catch: java.lang.Throwable -> L81
            if (r3 != 0) goto L60
        L14:
            int r3 = r6.size     // Catch: java.lang.Throwable -> L5e
            if (r3 <= r7) goto L5a
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch: java.lang.Throwable -> L5e
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> L5e
            if (r3 == 0) goto L21
            goto L5a
        L21:
            java.util.LinkedHashMap<K, V> r3 = r6.map     // Catch: java.lang.Throwable -> L58
            java.util.Set r3 = r3.entrySet()     // Catch: java.lang.Throwable -> L58
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L58
            java.lang.Object r3 = r3.next()     // Catch: java.lang.Throwable -> L58
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch: java.lang.Throwable -> L58
            java.lang.Object r4 = r3.getKey()     // Catch: java.lang.Throwable -> L56
            r1 = r4
            java.lang.Object r4 = r3.getValue()     // Catch: java.lang.Throwable -> L54
            r2 = r4
            java.util.LinkedHashMap<K, V> r4 = r6.map     // Catch: java.lang.Throwable -> L85
            r4.remove(r1)     // Catch: java.lang.Throwable -> L85
            int r4 = r6.size     // Catch: java.lang.Throwable -> L85
            int r5 = r6.safeSizeOf(r1, r2)     // Catch: java.lang.Throwable -> L85
            int r4 = r4 - r5
            r6.size = r4     // Catch: java.lang.Throwable -> L85
            int r4 = r6.evictionCount     // Catch: java.lang.Throwable -> L85
            r5 = 1
            int r4 = r4 + r5
            r6.evictionCount = r4     // Catch: java.lang.Throwable -> L85
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L85
            r6.entryRemoved(r5, r1, r2, r0)
            goto L3
        L54:
            r0 = move-exception
            goto L83
        L56:
            r0 = move-exception
            goto L83
        L58:
            r0 = move-exception
            goto L83
        L5a:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L5c
            return
        L5c:
            r0 = move-exception
            goto L83
        L5e:
            r0 = move-exception
            goto L83
        L60:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L7f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7f
            r3.<init>()     // Catch: java.lang.Throwable -> L7f
            java.lang.Class r4 = r6.getClass()     // Catch: java.lang.Throwable -> L7f
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L7f
            r3.append(r4)     // Catch: java.lang.Throwable -> L7f
            java.lang.String r4 = ".sizeOf() is reporting inconsistent results!"
            r3.append(r4)     // Catch: java.lang.Throwable -> L7f
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L7f
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L7f
            throw r0     // Catch: java.lang.Throwable -> L7f
        L7f:
            r0 = move-exception
            goto L83
        L81:
            r0 = move-exception
        L83:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L85
            throw r0
        L85:
            r0 = move-exception
            goto L83
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.util.LruCache.trimToSize(int):void");
    }

    public final V remove(K key) {
        Throwable th;
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            try {
                try {
                    V previous = this.map.remove(key);
                    if (previous != null) {
                        this.size -= safeSizeOf(key, previous);
                    }
                    if (previous != null) {
                        entryRemoved(false, key, previous, null);
                    }
                    return previous;
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
    }

    protected V create(K key) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result < 0) {
            throw new IllegalStateException("Negative size: " + key + "=" + value);
        }
        return result;
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int hitPercent;
        int accesses = this.hitCount + this.missCount;
        hitPercent = accesses != 0 ? (this.hitCount * 100) / accesses : 0;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(hitPercent));
    }
}
