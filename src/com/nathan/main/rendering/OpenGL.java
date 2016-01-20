package com.nathan.main.rendering;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class OpenGL 
{
	public static void initOpenGL(int x,int y)
	{
	      
        try {
            PixelFormat pixelFormat = new PixelFormat();
            ContextAttribs contextAtrributes = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);
             
            Display.setDisplayMode(new DisplayMode(x, y));
            Display.setTitle("NaN");
            Display.create(pixelFormat, contextAtrributes);
             
            GL11.glViewport(0, 0, x, y);
        } catch (LWJGLException e) {e.printStackTrace(); System.exit(-1);}
         
        // Setup an XNA like background color
        GL11.glClearColor(0.4f, 0.6f, 0.9f, 0f);
         
        // Map the internal OpenGL coordinate system to the entire screen
        GL11.glViewport(0, 0, x, y);

	}

	public static void LoopStart() 
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	}

	public static void LoopEnd() 
	{
		Display.update();
		
	}
}
