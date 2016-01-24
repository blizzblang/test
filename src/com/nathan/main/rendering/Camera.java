package com.nathan.main.rendering;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera 
{
private float[] Pos=new float[3];
private Matrix4f projectionMatrix = new Matrix4f();
private Matrix4f viewMatrix = new Matrix4f();
private float FOV=60;
private float near_plane = 0.1f;
private float far_plane = 100f;
private float frustum_length = far_plane - near_plane;
	public Camera(float[] pos)
	{
		Pos = new float[]{pos[0],pos[1],pos[2]};
		
		projectionMatrix.m00 = (float) (1/ Math.tan(Math.toRadians(FOV / 2f)) / OpenGL.getAspectRatio());
		projectionMatrix.m11 = (float) (1/ Math.tan(Math.toRadians(FOV / 2f)));
		projectionMatrix.m22 = -((far_plane + near_plane) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * near_plane * far_plane) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	public Matrix4f getProjectionMatrix()
	{
		return projectionMatrix;
	}
	public void update()
	{
		
	}
	public Matrix4f getViewMatrix()
	{
		return Matrix4f.translate(new Vector3f(Pos[0],Pos[1],Pos[2]),  viewMatrix ,  viewMatrix );
	}
}
