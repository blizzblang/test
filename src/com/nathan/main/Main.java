package com.nathan.main;

import org.lwjgl.opengl.Display;

import com.nathan.main.Util.ImgLoader;
import com.nathan.main.Util.MathHelper;
import com.nathan.main.Util.UnsignedByte;
import com.nathan.main.rendering.OpenGL;
import com.nathan.main.time.Timer;

public class Main {
	public static void main(String[] a)
	{
		OpenGL.initOpenGL(800, 600);
		while(!Display.isCloseRequested())
		{
			OpenGL.LoopStart();
			
			
			OpenGL.LoopEnd();
		}
		Display.destroy();
	}
}
