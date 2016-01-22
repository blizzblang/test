package com.nathan.main;

import org.lwjgl.opengl.Display;

import com.nathan.main.Util.ImgLoader;
import com.nathan.main.Util.MathHelper;
import com.nathan.main.Util.UnsignedByte;
import com.nathan.main.rendering.OpenGL;
import com.nathan.main.rendering.VBO;
import com.nathan.main.rendering.Vertex;
import com.nathan.main.time.Timer;

public class Main {
	public static void main(String[] a)
	{
			OpenGL.initOpenGL(800, 600);
			
		VBO test = new VBO();
		test.addTexture(ImgLoader.loadImage("GameFiles/Image/sinstar/sinstar.png"));
		Model test = new Model("GameFiles/Model/box/box.obj",test);
	        test.addVertex(v0);
	        test.addVertex(v1);
	        test.addVertex(v2);
	        test.addVertex(v3);
	        test.finalize();
		while(!Display.isCloseRequested())
		{
			OpenGL.LoopStart();
			
			test.render();
			OpenGL.LoopEnd();
		}
		Display.destroy();
	}
}
