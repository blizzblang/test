package com.nathan.main.rendering
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
 
public class Face {
     private final int[] vertexIndices = {-1, -1, -1};
     private final int[] normalIndices = {-1, -1, -1};
    public Vector3f vertex=new Vector3f();
    public Vector3f RefVex = new Vector3f();
             
    public Vector3f normal=new Vector3f();
    public Vector3f texture=new Vector3f();
    public Vector3f[] Verts = new Vector3f[3];
    public Color FaceColor = new Color(0.5f,0.5f,0.5f);
    float Radius=0;
    public Face(Vector3f v,Vector3f n,Vector3f tex)
    {
        vertex=v;
        normal=n;
        texture=tex;
    }
    public Face(Vector3f v,Vector3f n)
    {
        vertex=v;
        normal=n;
    }
    public void setVert(int i,Vector3f in)
    {
        if(i < 0 || i > 2){System.err.println("Invalid Vertex parameter for face! = "+i);System.exit(1);}
        Verts[i]=new Vector3f(in.x,in.y,in.z);
        if(Radius < Verts[i].length())Radius = Verts[i].length()+0;
    }
 
 
 
     
}
