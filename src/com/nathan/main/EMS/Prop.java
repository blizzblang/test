package com.nathan.main.EMS;

import java.util.ArrayList;

import com.nathan.main.Util.ImgLoader;
import com.nathan.main.rendering.ModelLoader;
import com.nathan.main.rendering.VBO;
import com.nathan.main.rendering.Vertex;

public class Prop extends Entity 
{

	public Prop(float [] pos)
	{
		super(EntityType.Prop);
		setXYZ(pos);
		rendered = new VBO(getMatrix());
		ModelLoader testt = new ModelLoader("GameFiles/Model/box/box.obj",rendered,ImgLoader.getTexture(ImgLoader.loadImage("GameFiles/Image/sinstar/sinstar.png")));
//        Vertex v0 = new Vertex(); 
//        v0.setXYZ(-0.5f, 0.5f, 0); v0.setRGB(1, 0, 0); v0.setST(0, 0);
//        Vertex v1 = new Vertex(); 
//        v1.setXYZ(-0.5f, -0.5f, 0); v1.setRGB(0, 1, 0); v1.setST(0, 1);
//        Vertex v2 = new Vertex(); 
//        v2.setXYZ(0.5f, -0.5f, 0); v2.setRGB(0, 0, 1); v2.setST(1, 1);
//        Vertex v3 = new Vertex(); 
//        v3.setXYZ(0.5f, 0.5f, 0); v3.setRGB(1, 1, 1); v3.setST(1, 0);
//        rendered.addTexture(ImgLoader.loadImage("GameFiles/Image/sinstar/sinstar.png"));
//        rendered.addVertex(v1);
//        rendered.addVertex(v2);
//        rendered.addVertex(v3);
        
        
		rendered.finalize();
	}
	public Prop(float x,float y,float z) 
	{
		this(new float[]{x,y,z});
	}
	@Override
	public void Tick(ArrayList<Entity> i) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void Render(boolean shader) {
		rendered.render(this,shader);
		
	}
	public void setTexture(int i)
	{
		rendered.addTexture(i);
	}
	public int getTexture() {
		return rendered.getTexture();
		
	}

}
