package com.nathan.main.rendering;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.nathan.main.Main;

public class Camera 
{
private float[] Pos=new float[3];
private Matrix4f projectionMatrix = new Matrix4f();
private Matrix4f viewMatrix = new Matrix4f();
private float FOV=60;
private float near_plane = 0.1f;
private float far_plane = 100f;
private float frustum_length = far_plane - near_plane;
private Vector3f Angle = new Vector3f(0,0,0);
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
		float lmod=1f;
		float mod=0.01f;
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_A))Angle.y -= 0.01*lmod;
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_D))Angle.y += 0.01*lmod;
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_W))Angle.x -= 0.01*lmod;
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_S))Angle.x += 0.01*lmod;
		
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_T))Pos[2] += 0.01*mod;
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_G))Pos[2] -= 0.01*mod;
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_F))Pos[0] += 0.01*mod;
		if(Main.Keyboard.getKeyDown(Keyboard.KEY_H))Pos[0] -= 0.01*mod;
	}
	public Matrix4f getViewMatrix()
	{
		viewMatrix = new Matrix4f();
		viewMatrix.rotate((float) Math.toRadians(Angle.x), new Vector3f(1,0,0), viewMatrix);
		viewMatrix.rotate((float) Math.toRadians(Angle.y), new Vector3f(0,1,0), viewMatrix);
		viewMatrix.rotate((float) Math.toRadians(Angle.z), new Vector3f(0,0,1), viewMatrix);
		Matrix4f.translate(new Vector3f(Pos[0],Pos[1],Pos[2]),  viewMatrix ,  viewMatrix );
		return viewMatrix;
	}
}
