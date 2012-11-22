package com.vg.core.android.opengles;

import android.app.Activity;
import android.os.Bundle;

import com.vg.core.android.opengles.interfaces.IRenderingContext;

public abstract class AbstractGLActivity extends Activity implements IRenderingContext {
	
	protected VersionedGLSurfaceView mView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		mView = new VersionedGLSurfaceView(this, this.openGLESVersion(), this.renderer());
        setContentView(mView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }

}
