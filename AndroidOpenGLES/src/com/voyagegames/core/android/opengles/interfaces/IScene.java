package com.voyagegames.core.android.opengles.interfaces;

import com.voyagegames.core.android.opengles.modules.geometry.Vector2D;

public interface IScene {
	
	ICamera camera();
	Vector2D viewportSize();
	void clear();
	void setCamera(ICamera camera);
	void updateViewport(int width, int height);

}
