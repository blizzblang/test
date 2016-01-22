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
			Vertex v0 = new Vertex(); 
	        v0.setXYZ(-0.5f, 0.5f, 0); v0.setRGB(1, 0, 0); v0.setST(0, 0);
	        Vertex v1 = new Vertex(); 
	        v1.setXYZ(-0.5f, -0.5f, 0); v1.setRGB(0, 1, 0); v1.setST(0, 1);
	        Vertex v2 = new Vertex(); 
	        v2.setXYZ(0.5f, -0.5f, 0); v2.setRGB(0, 0, 1); v2.setST(1, 1);
	        Vertex v3 = new Vertex(); 
	        v3.setXYZ(0.5f, 0.5f, 0); v3.setRGB(1, 1, 1); v3.setST(1, 0);
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
