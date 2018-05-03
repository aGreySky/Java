package util;

import java.util.UUID;

/** Ëæ»úÉú³É×Ö·û´® **/
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
