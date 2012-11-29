package com.voyagegames.core.android.opengles.buffers;

import java.util.List;

import com.voyagegames.core.android.opengles.interfaces.IRenderableBuffer;
import com.voyagegames.core.android.opengles.modules.ShaderHandles;

public class CubeBuffer implements IRenderableBuffer {
	
	private static final int NUM_SIDES = 6;
	
	private final IRenderableBuffer[] mBuffers = new IRenderableBuffer[NUM_SIDES];
	
	public CubeBuffer(
			final IRenderableBuffer frontBuffer,
			final IRenderableBuffer rightBuffer,
			final IRenderableBuffer backBuffer,
			final IRenderableBuffer leftBuffer,
			final IRenderableBuffer topBuffer,
			final IRenderableBuffer bottomBuffer) {
		mBuffers[0] = frontBuffer;
		mBuffers[1] = rightBuffer;
		mBuffers[2] = backBuffer;
		mBuffers[3] = leftBuffer;
		mBuffers[4] = topBuffer;
		mBuffers[5] = bottomBuffer;
	}

	@Override
	public int requiredAttributeSize() {
		return mBuffers[0].requiredAttributeSize();
	}

	@Override
	public void setHandles(final ShaderHandles handles) {
		for (int i = 0; i < NUM_SIDES; i++) {
			mBuffers[i].setHandles(handles);
		}
	}

	@Override
	public void render(final List<String> attributes) {
		for (int i = 0; i < NUM_SIDES; i++) {
			mBuffers[i].render(attributes);
		}
	}

	@Override
	public void convertToVertexBufferObject(final int bufferIndex) {
		throw new IllegalArgumentException("Use custom convertToVertexBufferObject() method");
	}

	public void convertToVertexBufferObject(final int[] bufferIndices) {
		if (bufferIndices.length != NUM_SIDES) {
			throw new IllegalArgumentException("bufferIndices has an invalid length of " + bufferIndices.length);
		}
		
		for (int i = 0; i < NUM_SIDES; i++) {
			mBuffers[i].convertToVertexBufferObject(bufferIndices[i]);
		}
	}

	@Override
	public void convertToIndexBufferObject(int bufferIndex) {
		throw new IllegalArgumentException("Not yet supported");
	}

}
