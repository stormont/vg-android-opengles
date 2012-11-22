package com.voyagegames.core.android.opengles;

import java.io.IOException;
import java.io.InputStream;

import com.voyagegames.core.android.opengles.modules.Texture;

import android.content.Context;
import android.util.Log;


public class TextureFactory {
	
	private static final String TAG = TextureFactory.class.getName();
	
	public static Texture create(final Context context, final int resourceID) {
        // Create our texture. This has to be done each time the surface is created.
        final InputStream is = context.getResources().openRawResource(resourceID);

        try {
        	return new Texture(is);
        } finally {
            try {
                is.close();
            } catch(final IOException e) {
                Log.e(TAG, "Failed to create texture");
                e.printStackTrace();
            }
        }
	}

}
