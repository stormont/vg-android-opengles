package com.vg.core.android.opengles.interfaces;

import java.util.List;

import com.vg.core.android.opengles.modules.ShaderHandles;

public interface IRenderableBuffer {
	
	int requiredAttributeSize();
	void setHandles(ShaderHandles handles);
	void render(List<String> attributes);

}
