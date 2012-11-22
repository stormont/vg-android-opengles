package com.vg.core.android.opengles.interfaces;

public interface IScene {
	
	ICamera camera();
	void clear();
	void setCamera(ICamera camera);
	void updateViewport(int width, int height);

}
