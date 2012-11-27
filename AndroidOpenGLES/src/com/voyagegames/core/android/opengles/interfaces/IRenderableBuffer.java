package com.voyagegames.core.android.opengles.interfaces;

import java.util.List;

import com.voyagegames.core.android.opengles.modules.ShaderHandles;

public interface IRenderableBuffer {
	
	int requiredAttributeSize();
	void setHandles(ShaderHandles handles);
	void render(List<String> attributes);
	void convertToVertexBufferObject(int bufferIndex);

}
