package com.nathan.main;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import com.nathan.main.EMS.Prop;
import com.nathan.main.Util.ImgLoader;
import com.nathan.main.Util.KeyInput;
import com.nathan.main.Util.MathHelper;
import com.nathan.main.Util.UnsignedByte;
import com.nathan.main.rendering.Camera;
import com.nathan.main.rendering.ModelLoader;
import com.nathan.main.rendering.OpenGL;
import com.nathan.main.rendering.VBO;
import com.nathan.main.rendering.Vertex;
import com.nathan.main.time.Timer;

public class Main {
	public static Camera cam;
	public static KeyInput Keyboard = new KeyInput();
	public static void main(String[] a)
	{
			OpenGL.initOpenGL(800, 600);
			cam = new Camera(new float[]{0,0,-4});
			Prop prop = new Prop(0,-1,0);
			

		while(!Display.isCloseRequested())
		{
			OpenGL.LoopStart();
			Keyboard.Update();
			cam.update();
			prop.Render();
			
			OpenGL.LoopEnd();
		}
		Display.destroy();
	}
}
