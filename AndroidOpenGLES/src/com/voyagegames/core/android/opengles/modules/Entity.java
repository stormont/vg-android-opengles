package com.voyagegames.core.android.opengles.modules;

import android.opengl.Matrix;

import com.voyagegames.core.android.opengles.interfaces.IEntity;
import com.voyagegames.core.android.opengles.modules.geometry.Vector3D;

public class Entity implements IEntity {
	
	protected Vector3D mPosition;
	protected Vector3D mScale;
	protected Vector3D mRotationAxis;
	protected float    mRotationAngle;
	
	public Entity() {
		mPosition = new Vector3D(0, 0, 0);
		mRotationAxis = new Vector3D(0, 0, 0);
		mScale = new Vector3D(1f, 1f, 1f);
	}

	@Override
	public Vector3D position() {
		return mPosition;
	}

	@Override
	public void setPosition(final Vector3D position) {
		mPosition = position;
	}

	@Override
	public float rotationAngle() {
		return mRotationAngle;
	}

	@Override
	public void setRotationAngle(final float angle) {
		mRotationAngle = angle;
	}

	@Override
	public Vector3D rotationAxis() {
		return mRotationAxis;
	}

	@Override
	public void setRotationAxis(final Vector3D axis) {
		mRotationAxis = axis;
	}

	@Override
	public Vector3D scale() {
		return mScale;
	}

	@Override
	public void setScale(final Vector3D scale) {
		mScale = scale;
	}

	@Override
	public float[] modelMatrix() {
		final float[] modelMatrix = new float[16];
		
		Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, mPosition.x, mPosition.y, mPosition.z);
        
        if (mRotationAngle != 0 && (mRotationAxis.x != 0 || mRotationAxis.y != 0 || mRotationAxis.z != 0)) {
        	Matrix.rotateM(modelMatrix, 0, mRotationAngle, mRotationAxis.x, mRotationAxis.y, mRotationAxis.z);
        }
        
        Matrix.scaleM(modelMatrix, 0, mScale.x, mScale.y, mScale.z);
		return modelMatrix;
	}

	@Override
	public float[] translationMatrix() {
		final float[] result = new float[16];
		
		Matrix.setIdentityM(result, 0);
        Matrix.translateM(result, 0, mPosition.x, mPosition.y, mPosition.z);
		return result;
	}

	@Override
	public float[] rotationMatrix() {
		final float[] result = new float[16];
		
		Matrix.setIdentityM(result, 0);
        
        if (mRotationAngle != 0 && (mRotationAxis.x != 0 || mRotationAxis.y != 0 || mRotationAxis.z != 0)) {
        	Matrix.rotateM(result, 0, mRotationAngle, mRotationAxis.x, mRotationAxis.y, mRotationAxis.z);
        }
        
		return result;
	}

	@Override
	public float[] scalingMatrix() {
		final float[] result = new float[16];
		
		Matrix.setIdentityM(result, 0);
        Matrix.scaleM(result, 0, mScale.x, mScale.y, mScale.z);
		return result;
	}
	
	@Override
	public float[] offsetModelMatrix(final IEntity entity) {
		final float[] scaling = new float[16];
		final float[] rotation = new float[16];
		final float[] translation = new float[16];
		final float[] result = new float[16];
		
		Matrix.multiplyMM(scaling, 0, entity.scalingMatrix(), 0, this.scalingMatrix(), 0);
		Matrix.multiplyMM(rotation, 0, entity.rotationMatrix(), 0, this.rotationMatrix(), 0);
		Matrix.multiplyMM(translation, 0, entity.translationMatrix(), 0, this.translationMatrix(), 0);
		
		Matrix.setIdentityM(result, 0);
		Matrix.multiplyMM(result, 0, scaling, 0, result, 0);
		Matrix.multiplyMM(result, 0, rotation, 0, result, 0);
		Matrix.multiplyMM(result, 0, translation, 0, result, 0);
		return result;
	}

}
