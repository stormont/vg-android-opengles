package com.voyagegames.core.android.opengles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voyagegames.core.android.opengles.interfaces.IRenderingContext;

public abstract class AbstractGLFragment extends Fragment implements IRenderingContext {
	
	protected VersionedGLSurfaceView mView;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		mView = new VersionedGLSurfaceView(getActivity(), this.openGLESVersion(), this.renderer());
		return mView;
	}

	@Override
	public void onPause() {
        super.onPause();
        mView.onPause();
	}

	@Override
	public void onResume() {
        super.onResume();
        mView.onResume();
	}

}
