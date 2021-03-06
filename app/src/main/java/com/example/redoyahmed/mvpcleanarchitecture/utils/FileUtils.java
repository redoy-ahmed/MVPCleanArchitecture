
package com.example.redoyahmed.mvpcleanarchitecture.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public final class FileUtils {

    private static final String TAG = "FileUtils";

    private FileUtils() {
        // This utility class is not publicly instantiable
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }
}
