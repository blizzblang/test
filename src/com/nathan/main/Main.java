package com.nathan.main;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.nathan.main.EMS.Prop;
import com.nathan.main.Util.KeyInput;
import com.nathan.main.rendering.Camera;
import com.nathan.main.rendering.FBO;
import com.nathan.main.rendering.OpenGL;
import com.nathan.main.rendering.ShadowMap;

import javafx.scene.effect.Shadow;


public class Main {
	public static Camera cam;
	public static KeyInput Keyboard = new KeyInput();
	public static void main(String[] a)
	{
			OpenGL.initOpenGL(800*2, 600*2);
			cam = new Camera(new float[]{2,0,-8});
			ArrayList<Prop> props = new ArrayList<Prop>();
			props.add(new Prop(0,5,0));
			props.add(new Prop(0,0,0));
			props.add(new Prop(0,-5,0));
			props.add(new Prop(5,0,0));
			props.add(new Prop(-5,0,0));
			props.add(new Prop(0,0,5));
			props.add(new Prop(0,0,-5));
			ShadowMap shadows = new ShadowMap(10);
			Prop hidden = new Prop(-5,2,0);
		while(!Display.isCloseRequested())
		{
			Keyboard.Update();
			cam.update(); 
			
			shadows.renderToOcclusionMap();
			for(Prop i : props)
				i.Render();
			shadows.finishOcclusionMap();
			
			
			OpenGL.ResetView();
			
			

			
			hidden.Render();
			for(Prop i : props)
				i.Render();
			shadows.render();
			for(Prop i : props)
				i.Render();
			
			OpenGL.LoopEnd();
					}
		Display.destroy();
	} 
}
